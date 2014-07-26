package homework10;

import java.io.*;
import java.util.*;

/**
 * A HuffmanCompressor object contains no data - it is just an implementation of
 * the Compressor interface. It contains the compress and decompress methods,
 * along with a series of helper methods for counting tokens, building the
 * Huffman tree, and storing data in byte arrays.
 * <p>
 * 
 * The only methods that should be public are the constructor and the Compressor
 * interface methods, the rest should be private. I have left them public,
 * though, for testing.
 * 
 * @author James Yeates & Peter Jensen - CS 2420
 * @version Summer 2010
 */
public class HuffmanCompressor implements Compressor {
	// There are NO fields in the compressor class. If you need
	// to get data to or from the methods, use a parameter! (Of course,
	// you shouldn't need to add any, the definitions below are complete.)

	/**
	 * This constructor does nothing. There are no fields to initialize. It is
	 * provided simply for testing. (You must construct a HuffmanCompressor in
	 * order to test the compress and decompress methods.)
	 */
	public HuffmanCompressor() {
	}

	/**
	 * This method counts the number of times each data value occurs in the byte
	 * array. For each different value, a HuffmanToken is created and stored.
	 * When the same value is seen again, its token's frequency is increased.
	 * After all the different data values have been counted this method will
	 * return an ArrayList of HuffmanToken objects. Each token will contain a
	 * count of how many times that token occurred in the byte array. (If you
	 * summed up the counts, the total would be same as the length of the data
	 * array.)
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * Note that the returned tokens in the ArrayList may be in any order.
	 * 
	 * @param data
	 *            A list of data bytes
	 * @return A list of HuffmanTokens that contain token counts
	 */
	private ArrayList<HuffmanToken> countTokens(byte[] data) {

		TreeMap<Byte, Integer> map = new TreeMap<Byte, Integer>();
		// create a map that has counts the number of times each character
		// appears in the data []
		for (int i = 0; i < data.length; i++) {
			Byte temp = (Byte) data[i];
			if (map.containsKey(temp)) {
				int x = map.get(temp);
				x++;
				map.put(temp, x);

			} else {
				map.put(temp, 1);
			}
		}

		ArrayList<HuffmanToken> htList = new ArrayList<HuffmanToken>();

		// create the token and put it in the list

		for (Byte b : map.keySet()) {
			HuffmanToken tempToken = new HuffmanToken(b);
			tempToken.setFrequency(map.get(b));
			htList.add(tempToken);
		}

		return htList;
	}

	/**
	 * This method builds and returns a Huffman tree that contains the given
	 * tokens in its leaf nodes.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * The Huffman tree-building algorithm is used here. You may find it in the
	 * book or in your notes from lecture. Remember to first create leaf nodes
	 * for all the tokens, and add these leaf nodes to a priority queue. You may
	 * then build and return the tree.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * It is assumed that the tokens do not have Huffman codes when this method
	 * is called. Due to the side-effect of one of the HuffmanToken
	 * constructors, the HuffmanToken objects will have correct Huffman codes
	 * when this method finishes building the tree. (They are built as the tree
	 * is built.)
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * @param tokens
	 *            A list of Tokens, each one with a frequency count
	 * @return The root node of a Huffman tree
	 */
	private HuffmanNode buildHuffmanCodeTree(ArrayList<HuffmanToken> tokens) {
		//special case if there is only one token
		if (tokens.size() == 1) {
			HuffmanToken token = tokens.get(0);
			token.prependBitToCode(false);
			HuffmanNode tempNode = new HuffmanNode(token);
			return tempNode;

		}
		PriorityQueue<HuffmanNode> nodeQue = new PriorityQueue<HuffmanNode>();

		// create seperate nodes from the list add them to the Que
		for (HuffmanToken t : tokens) {
			HuffmanNode tempNode = new HuffmanNode(t);
			nodeQue.add(tempNode);
		}
		// while the node size is greater than one continue to build the tree
		while (nodeQue.size() > 1) {
			HuffmanNode temp = new HuffmanNode(nodeQue.poll(), nodeQue.poll());
			nodeQue.add(temp);

		}
		HuffmanNode root = nodeQue.poll();

		return root;
	}

	/**
	 * This method creates a dictionary of Huffman codes from a list of Huffman
	 * tokens. It is assumed that the Huffman tokens have correct Huffman codes.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * This is a simple translation. Values and Huffman codes are extracted from
	 * the tokens and added to a TreeMap object.
	 * 
	 * @param tokens
	 *            A list of Tokens, each one with a Huffman code
	 * @return A map that maps byte values to their Huffman codes
	 */
	private Map<Byte, ArrayList<Boolean>> createEncodingMap(
			ArrayList<HuffmanToken> tokens) {
		Map<Byte, ArrayList<Boolean>> encodingMap = new TreeMap<Byte, ArrayList<Boolean>>();
		HuffmanNode root = buildHuffmanCodeTree(tokens);
		// populate the map
		preOrder1(root, encodingMap);
		return encodingMap;
	}

	/**
	 * This method encodes an array of data bytes as an ArrayList of bits
	 * (Boolean values). Huffman codes are used to translate the bytes into
	 * bits.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * For every value in the data array, the corresponding Huffman code is
	 * retrieved from the map and added to a new ArrayList that will be
	 * returned.
	 * 
	 * @param data
	 *            An array of data bytes that will be encoded (compressed)
	 * @param encodingMap
	 *            A TreeMap that maps byte values to Huffman codes (bits)
	 * @return An ArrayList of bits (Booleans) that represent the compressed
	 *         (Huffman encoded) data
	 */
	private ArrayList<Boolean> encodeBytes(byte[] data,
			Map<Byte, ArrayList<Boolean>> encodingMap) {
		ArrayList<Boolean> encodedList = new ArrayList<Boolean>();
		// go through all the Bytes
		for (int i = 0; i < data.length; i++) {
			// get the Byte
			ArrayList<Boolean> temp = encodingMap.get(data[i]);
			// add the booleans to the list one at a time
			for (Boolean b : temp)
				encodedList.add(b);
		}
		return encodedList;
	}

	/**
	 * This method decodes a list of bits (which are Huffman codes) into an
	 * array of bytes. In order to do the decoding, a Huffman tree containing
	 * the tokens is required.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * To do the decoding, follow the decoding algorithm given in the book or
	 * review your notes from lecture.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * (You will need to build a Huffman tree prior to calling this method, and
	 * the Huffman tree you build should be exactly the same as the one that was
	 * used to encode the data.)
	 * 
	 * @param bitCodes
	 *            An ArrayList of bits (Booleans) that represent the compressed
	 *            (Huffman encoded) data
	 * @param codeTree
	 *            A Huffman tree that can be used to decode the bits
	 * @param dataLength
	 *            The number of bytes that will be in the decoded byte array
	 * @return An array of bytes that represent the uncompressed data
	 */
	private byte[] decodeBits(ArrayList<Boolean> bitCodes, HuffmanNode codeTree,
			int dataLength) {
		byte[] data = new byte[dataLength];
		int index = 0;

		HuffmanNode current = codeTree;
		for (int i = 0; i < bitCodes.size(); i++) {
			// if we have filled all postions remaining values might be 000
			// break
			if (index == data.length)
				break;
			boolean temp = bitCodes.get(i);
			if (current.isLeafNode()) {
				data[index] = current.getToken().getValue();
				current = codeTree;
				index++;
				i--;
			} else if (temp)
				current = current.right;
			else
				current = current.left;
		}
		return data;
	}

	/**
	 * Given any array of bytes that contain some data, this method returns a
	 * compressed form of the original data. The returned, compressed bytes must
	 * contain sufficient information so that the decompress method below can
	 * reconstruct the original data.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * Huffman coding is used to compress the data.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * Some of the code for this method has been provided for you. You should
	 * figure out what it does, it will significantly help you.
	 * 
	 * @param compressedData
	 *            An array of bytes that contains some data that should be
	 *            compressed
	 * @return An array of bytes that contains the compressed form of the
	 *         original data
	 */
	public byte[] compress(byte[] data) {
		// Variable initialization and compression steps stubbed out here.

		// create a list of the tokens that have assicated with them there
		// frequency
		ArrayList<HuffmanToken> tokens = this.countTokens(data);
		byte[] compressedBytes = null;

		// create the encodingMap
		Map<Byte, ArrayList<Boolean>> encodingMap = this
				.createEncodingMap(tokens);

		ArrayList<Boolean> encodedBits = this.encodeBytes(data, encodingMap);

		// HuffmanTools.dumpHuffmanCodes(tokens); // Useful for debugging

		// This code will place various data elements of the compressed data
		// into
		// a byte array for you.

		try {
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(byteOutput);

			// write the length of the normal array at the first of the
			// outputStream
			output.writeInt(data.length);
			// write the information to recreate the tree
			writeTokenList(output, tokens);
			// write the bits
			writeBitCodes(output, encodedBits);

			// create the byte array with info above
			compressedBytes = byteOutput.toByteArray();
		} catch (IOException e) {
			System.out.println("Fatal compression error: " + e.getMessage());
			e.printStackTrace();
		}

		// Return the byte array
		return compressedBytes;
	}

	/**
	 * Given an array of bytes that contain compressed data that was compressed
	 * using this compressor, this method will reconstruct and return the
	 * original, uncompressed data. The compressed bytes must contain sufficient
	 * information so that this method can reconstruct the original data bytes.
	 * 
	 * Huffman coding is used to decompress the data.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * Some of the code for this method has been provided for you. You should
	 * figure out what it does, it will significantly help you.
	 * 
	 * @param compressedData
	 *            An array of bytes that contains some data in compressed form
	 * @return An array of bytes that contains the original, uncompressed data
	 */
	public byte[] decompress(byte[] compressedData) {

		int dataLength;

		HuffmanNode root = null;

		ArrayList<HuffmanToken> tokens = new ArrayList<HuffmanToken>();
		ArrayList<Boolean> encodedBits = new ArrayList<Boolean>();

		// This code will extract various data elements from the compressedData
		// bytes for you.

		try {
			// get the input stream
			ByteArrayInputStream byteInput = new ByteArrayInputStream(
					compressedData);
			DataInputStream input = new DataInputStream(byteInput);

			// get the orginal length for the byte []
			dataLength = input.readInt();
			// get the array list of huffman tokens
			tokens = readTokenList(input);
			// get/ create the boolean array list
			encodedBits = readBitCodes(input);
		} catch (IOException e) {
			System.out.println("Fatal compression error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		// Decompression steps stubbed out here.

		root = buildHuffmanCodeTree(tokens);

		byte[] decompresed = decodeBits(encodedBits, root, dataLength);

		// HuffmanTools.dumpHuffmanCodes(tokens); // Useful for debugging

		// Return statement stubbed out.
		return decompresed;
	}

	// The following methods read and write data values from a ByteArray
	// Streams. Because I'm giving you
	// this code, you should try to comment these methods yourself.

	/**
	 * This method writes the the information to recreate the the encodingtable
	 * in the compressed file
	 * 
	 * @param output
	 *            the DataOutputStream from which to write the tokens
	 * @param tokens
	 *            an array of list of tokens that have the info to recreate a
	 *            tree
	 * @throws IOException
	 */
	private void writeTokenList(DataOutputStream output,
			ArrayList<HuffmanToken> tokens) throws IOException {
		// attach the length of the token size
		output.writeInt(tokens.size());

		// adds the codes, first the byte, then the frequency
		for (HuffmanToken token : tokens) {
			output.writeByte(token.getValue());
			output.writeInt(token.getFrequency());
		}
	}

	/**
	 * This method reads the tokenlist in a compressed file and creates tokens
	 * with the corresponding frequency to recreate an encoding table
	 * 
	 * @param input
	 *            the compressed file in DataInputStream
	 * @return an array list of Huffman Tokens to be use to create a tree
	 * @throws IOException
	 */
	private ArrayList<HuffmanToken> readTokenList(DataInputStream input)
			throws IOException {
		ArrayList<HuffmanToken> tokens = new ArrayList<HuffmanToken>();

		// how many tokens need to be added
		int count = input.readInt();

		// create the tokens with and assign them the appropriate frequency
		for (int i = 0; i < count; i++) {
			HuffmanToken token = new HuffmanToken(input.readByte());
			token.setFrequency(input.readInt());
			tokens.add(token);
		}

		return tokens;
	}

	/**
	 * This method takes an array list of boolean and writes thoose values into
	 * a straigth line of code(seperate as bits)
	 * 
	 * @param output
	 * @param bits
	 * @return
	 * @throws IOException
	 */
	private int writeBitCodes(DataOutputStream output, ArrayList<Boolean> bits)
			throws IOException {
		int bytesWritten = 0;

		for (int pos = 0; pos < bits.size(); pos += 8) {
			int b = 0;
			for (int i = 0; i < 8; i++) {
				b = b * 2;
				if (pos + i < bits.size() && bits.get(pos + i))
					b = b + 1;
			}
			output.writeByte((byte) b);
			bytesWritten++;
		}

		return bytesWritten;
	}

	/**
	 * This method reads the compressed file and creates an array list of
	 * booleans which can then be decode back into an orginal decompressed
	 * state.
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	private ArrayList<Boolean> readBitCodes(DataInputStream input)
			throws IOException {
		ArrayList<Boolean> bits = new ArrayList<Boolean>();

		// while there are bits
		while (input.available() > 0) {
			// read the token as a byte
			int b = input.readByte();

			for (int i = 7; i >= 0; i--)
				bits.add(((b >> i) & 1) == 1);
		}

		return bits;
	}
	/**
	 *  This helper method  uses a pre order tarversal to create a map that
	 *  contains the value as the key and code as its value.
	 * @param node the root of the huffman tree
	 * @param map the map containing the encoding bits
	 */

	private static void preOrder1(HuffmanNode node, Map<Byte, ArrayList<Boolean>> map) {
		if (node == null)
			return;
		preOrder1(node.left, map);
		if (node.isLeafNode()) {

			// HuffmanToken token = node.getToken();
			// System.out.println(node.getToken().getCode()+"!");
			map.put(node.getToken().getValue(), node.getToken().getCode());
			// System.out.println(node.getChar()+ " "+token.checkValue());
		}

		preOrder1(node.right, map);

	}

}
