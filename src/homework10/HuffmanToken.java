package homework10;

import java.util.*;

/**
 * Objects of this class represent a single Huffman token. A Huffman token is
 * just a data value (a byte), a frequency, and the Huffman code for this token.
 * 
 * @author James Yeates & Peter Jensen - CS 2420
 * @version Spring 2014
 */
public class HuffmanToken implements Comparable<HuffmanToken> {
	// The byte value of this token.
	private byte value;

	// The number of times this token occurs in some data.
	private int frequency;

	// char value
	private final char charValue;

	// The Huffman code that corresponds to this token.
	// The first bit in the code is the first element in the array.
	protected ArrayList<Boolean> code;

	/**
	 * This constructor initializes the token to its default values. A byte
	 * value is stored in the token, the frequency is set to zero, and an empty
	 * code is created.
	 * <p>
	 * &nbsp;
	 * <p>
	 * 
	 * The frequency and code will be set up later as the Huffman compression
	 * (or decompression) proceeds.
	 * 
	 * @param value
	 *            The byte value to store in this token
	 */
	public HuffmanToken(byte value) {
		this.value = value;
		this.charValue = (char) value;

		
		code = new ArrayList<Boolean>();
	}

	/**
	 * Increases the frequency count by one.
	 */
	public void incrementFrequency() {
		frequency++;
	}

	/**
	 * Returns the frequency count.
	 * 
	 * @return The number of times this token exists in some data
	 */
	public int getFrequency() {

		return frequency;
	}

	/**
	 * Changes the frequency count to some particular value.
	 * 
	 * @param frequency
	 *            Any valid frequency count
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Returns the byte value stored in this token.
	 * 
	 * @return The byte value stored in this token
	 */
	public byte getValue() {

		return value;
	}

	/**
	 * Adds the specified bit to the beginning of the Huffman code for this
	 * token.
	 * 
	 * @param bit
	 *            A bit to prepend to the Huffman code for this token
	 */
	public void prependBitToCode(Boolean bit) {
		code.add(0, bit);
	}

	/**
	 * Returns a copy of the Huffman code that represents this token. The first
	 * bit of the Huffman code is in the 0th position in the ArrayList.
	 * 
	 * @return The Huffman code for this token
	 */
	public ArrayList<Boolean> getCode() {

		return code;
	}

	/**
	 * returns a string representation binary code for this tokens array of
	 * booleans
	 * 
	 * @return a binary string representation
	 */
	public String checkValue() {
		String s = "";
		int x = this.code.size();
		for (int i = 0; i < x; i++) {
			if (code.get(i))
				s += 1;
			else
				s += 0;
		}
		return s;

	}
	/**
	 * returns char value
	 * @return
	 */
	
	public char getChar()
	{
		return charValue;
	}



	@Override
	public int compareTo(HuffmanToken o) {
		
		return (this.charValue)-(o.charValue);
	}
}
