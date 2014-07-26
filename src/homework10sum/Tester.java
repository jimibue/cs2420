package homework10sum;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFileChooser;

public class Tester {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		byte []test = HuffmanTools.readBytesFromFile();
//		
//		HuffmanTools.dumpBytesAsBits(test, 0, test.length);
//		System.out.println();
//		HuffmanTools.dumpBytesAsCharacters(test, 0, test.length);
//		System.out.println();
//		HuffmanTools.dumpBytesAsValues(test, 0, test.length);
//		System.out.println();
//		for(int i =0; i< test.length;i++)
//			
//		{
//			System.out.print((char)test[i]);
//		}"/Users/jcc/Desktop/HuffmanTest"
		File f = new File("/Users/jcc/Desktop/Yankee.txt");
		
		
	
		try {
			DataInputStream inputStream = new DataInputStream(new FileInputStream("/Users/jcc/Desktop/Yankee.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		Tester obj = new Tester();
		
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list = obj.readBitCodes(new DataInputStream(new FileInputStream("/Users/jcc/Desktop/Yankee.txt")));
		
			
	
		byte [] x;
		try {
			 x = getBytesFromFile(f);
			 System.out.println(x.length);
			TreeMap<Character,Integer> map = makeMap(x);
//			 for(int i = 0; i< x.length;i++)
//				{
//					System.out.print((char)x[i]);
//				}
			for(Character c : map.keySet())
			System.out.println(c +"   "+ map.get(c));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Integer.MAX_VALUE);
		System.out.println(f.length());
		
	     //Scanner scan = getFileScanner();
//		TreeMap<Character,Integer> map = makeMap(scan);
//		
//		for(Character c : map.keySet())
//			System.out.println(c +"   "+ map.get(c));
//		
//		System.out.println("done");
//		System.out.println(map.size());
		
		
	}
	
	
    static public File writeBytesToFile1 (byte[] data)
    {
    	File f =null;
        System.out.println ("The save file dialog window may be behind your other windows.");
        JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
		    f = chooser.getSelectedFile();
		    
		}
        return f;
    }
    

	/**
	 * This Method opens a JFileChooser and creates a scanner object from the file
	 * selected by the user.  This method will simply close if user
	 * does not choose a file or if a file not found exception occurs
	 * 
	 * @return a scanner object containing a file
	 */

	public static Scanner getFileScanner() {

		// Open a new window for user to choose a file
		JFileChooser fileChooser = new JFileChooser();

		int returnValue = fileChooser.showOpenDialog(null);

		// check to see if user selected a file, and if it was a valid file. If
		// not program closes
		if (returnValue == fileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();

			// create the scanner object, exit if file not found 
			try {
				Scanner s = new Scanner(file);
				return s;
			} catch (FileNotFoundException e) {

				System.exit(0);
			}

		} 
		//exit if user did not select a file
		else {
			System.exit(0);
		}
		//execution won't get here, but is needed to prevent a compiler error
		return null;

	}
	
	public static TreeMap<Character, Integer> makeMap(byte [] s)
	{
		TreeMap<Character, Integer>  map = new TreeMap<Character, Integer>();
			
		for(int i =0; i< s.length;i++)
			{
				char temp = (char)s[i];
				if(map.containsKey(temp))
				{
				
					int x = map.get(temp);
					x++;
				map.put(temp, x);
					
				}
				else
				{
					map.put(temp, 1);
				}
			}
		
		return map;
	}
	
	// Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {        
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }
    
    /** 
     * The student must write the comments for this method.
     */
    public ArrayList<Boolean> readBitCodes (DataInputStream input) throws IOException
    {
        ArrayList<Boolean> bits = new ArrayList<Boolean> ();
        
        while (input.available() > 0)
        {
            int b = input.readByte ();
            
            for (int i = 7; i >= 0; i--)
                bits.add (((b >> i) & 1) == 1);
        }
        
        return bits;
    }
	

}
