package homework07;

import java.io.File;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
/**
 * This class open A JFileChooser for the user to select a .tree file and
 * has a method to create a specialized array list to be used to construct a tree
 * @author James Yeates
 * @version 3/10/14
 */
public class FileProcessor {
	
	protected String fileName;
	
	/**
	 * This Method opens a JFileChooser and creates a scanner object from the file
	 * selected by the user.  This method will simply close if user
	 * does not choose a file or if a file not found exception occurs
	 * 
	 * @return a scanner object containing a file
	 */

	public  Scanner getFileScanner() {

		// Open a new window for user to choose a file
		JFileChooser fileChooser = new JFileChooser();

		int returnValue = fileChooser.showOpenDialog(null);

		// check to see if user selected a file, and if it was a valid file. If
		// not program closes
		if (returnValue == fileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();
			fileName = file.getName();

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
	/**
	 * This method takes Scanner object containing the tree data and parses it into
	 * an array list.  
	 * @param scan Scanner 
	 * @return ArrayList<String>
	 */
	
	public List<String> makeArray(Scanner scan){
		
		List<String> list = new ArrayList<String>();
		
		//Scan the file
		while (scan.hasNext()){
			String s = scan.next();
			
			//starting new tag check for '/' and add if it is in else add the next string.
			if(s.charAt(0)=='<'){
				if(s.charAt(1)=='/')
					list.add("/");
			}
			// remove the > from string and add it to the list
			else{
				s= s.substring(0, s.length()-1);
				list.add(s);
			}
		}
		return list;
		
	}
	/**
	 * getter method to get the filename of the file chosen by the user.
	 * @return String
	 */
	
	public String getFileName()
	{
		return fileName;
	}
	
}//end


