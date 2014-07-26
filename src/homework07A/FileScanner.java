package homework07A;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FileScanner {
	
	private String fileName;
	
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

}
