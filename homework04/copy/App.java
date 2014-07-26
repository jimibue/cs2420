package homework04.copy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Scanner;

public class App {
	
	static WarehouseReport report = new WarehouseReport();

	public static void main(String[] args) {

		Scanner inventoryScanner = getFileScanner1("data3.txt");

		while(inventoryScanner.hasNextLine())
		{
			//get each line in the file and create a new scaner
			Scanner currentLine = new Scanner(inventoryScanner.nextLine());
			String firstWord = currentLine.next();
			

			if(firstWord.equals("FoodItem"))
			{
				FoodItem item = makeFoodItem(currentLine);
				report.upcMap.put(item.getUpcCode(), item);
			}
			else if(firstWord.equals("Warehouse"))
				makeWarehouse(currentLine);
			else if(firstWord.equals("Start"))
			{
				getFirstDate(currentLine);
			}
		}
		
		
	}
	/**
	 * takes a scanner containing a string information and creates a 
	 * @param s
	 * @return
	 */

	public static FoodItem makeFoodItem (Scanner s)
	{
		String upcCode =null;
		String shelfLife = null;
		int x =0;
		String name=null;
		while(s.hasNext()){
			
			String tempString = s.next();
			if(tempString.equals("Code:"))
			{
				 upcCode = s.next();

			}
			else if( tempString.equals("life:"))
			{
				shelfLife = s.next();
			    x= Integer.parseInt(shelfLife);
			}

			else if( tempString.equals("Name:"))
			{
				 name= s.nextLine();
			}
		}
		return new FoodItem(name,upcCode,x);
	}

	/**
	 * This method prompts user to enter file  and creates a new Scanner object
	 * using the given file path.  If user enter a invalid directory program closes. 
	 * 
	 * @return Scanner - a file scanner
	 */
	public static Scanner getFileScanner(){

		Scanner input = new Scanner(System.in);
		System.out.println("please enter a file directory");
		String textFile = input.next();

		//Try catch block surrounding the scanner reading in from our data files
		try
		{
			Scanner inventoryScanner = new Scanner (new File (textFile));
			return inventoryScanner;
		}
		//If, for any reason, we cannot read in from the file, this will print a message explaining why
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read file " + e.getMessage());
			System.exit(0);
		}
		return null;
	}

	public static Scanner getFileScanner1(String s){


		String textFile = s;

		//Try catch block surrounding the scanner reading in from our data files
		try
		{
			Scanner inventoryScanner = new Scanner (new File (textFile));
			return inventoryScanner;
		}
		//If, for any reason, we cannot read in from the file, this will print a message explaining why
		catch(FileNotFoundException e)
		{
			System.out.println("Could not read file " + e.getMessage());
			System.exit(0);
		}
		return null;
	}
	/**
	 * populates map
	 * @param s
	 */

	public static void makeWarehouse(Scanner s)
	{
		String tempString = null;
		Inventory<FoodItem> tempInven = null;
		String whareHouseName=null;
		
		while(s.hasNext())
		{
			tempString = s.next();
			if(tempString.equals("-"))
			{
				tempInven = new Inventory<FoodItem>();
				whareHouseName = s.next();
				report.warehouseInventoryMap.put(whareHouseName, tempInven);
			
			}
		}
		
	}

	public static void getFirstDate (Scanner s){
		//
		s.next();
		String date = s.next();
		System.out.println(date);
		String month = date.charAt(0)+""+ date.charAt(1);
		int month1 = Integer.parseInt(month);
		//month1-=1;
		
		String day = date.charAt(3)+""+ date.charAt(4);
		int day1 = Integer.parseInt(day);
		
		String year = date.charAt(6)+""+ date.charAt(7)+date.charAt(8)+""+ date.charAt(9);
		
		int year1 = Integer.parseInt(year);
		
		
		GregorianCalendar cal = new GregorianCalendar(year1, 12, day1);
		System.out.println(cal.getTime());
		int day2 = cal.get(GregorianCalendar.DAY_OF_MONTH);
		int month2 = cal.get(GregorianCalendar.MONTH);
		int year2 = cal.get(GregorianCalendar.YEAR);
		System.out.println(month2 + "/" + day2 + "/" + year2);
		
	}
	
	public static void transactionCounter(Scanner s)
	{
		
	}



}
