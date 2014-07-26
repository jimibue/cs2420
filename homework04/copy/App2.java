package homework04.copy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App2 {
	
	static WarehouseReport report = new WarehouseReport();

	public static void main(String[] args) {

		Scanner inventoryScanner = getFileScanner1("data1.txt");
		String firstWord = "YO";

		while(inventoryScanner.hasNextLine() && !(firstWord.equals("End")))
		{
			//get each line in the file and create a new scanner
			Scanner currentLine = new Scanner(inventoryScanner.nextLine());
			firstWord = currentLine.next();
			

			if(firstWord.equals("FoodItem"))
			{
				//creates a new FoodItem
				FoodItem item = makeFoodItem(currentLine);
				//puts the FoodItem in a map that maps a upcCode to a FoodItem
				report.upcMap.put(item.getUpcCode(), item);
				//Create a set of unique upcCodes
				report.upcSet.add(item.getUpcCode());
				//create a set of FoodItems
				report.foodItemSet.add(item);
			}
			else if(firstWord.equals("Warehouse")){
				
				//creates a map that maps inventories to warehouses.
				//create a set of warehouse names
				makeWarehouse(currentLine);
			}
			else if(firstWord.equals("Start"))
			{
				//get the date
				report.currentDate = getDate(currentLine);
				report.datesSet.add(report.currentDate);
				
				//create a map to track busiest day
				for( String s : report.warehouseList)
					report.warehouseBusiestDayMap.put(s, new BusiestDay(s));
				//System.out.println("c");
				
			}
			else if(firstWord.equals("Receive:"))
			{
				report.itemReceived(currentLine);
				//System.out.println("d");
				
			}
			
			else if(firstWord.equals("Request:"))
			{
				report.itemRequested(currentLine);
				//System.out.println("e");
			
				
			}
			else if(firstWord.equals("Next"))
			{
				//report.currentDate= getnewDate(report.currentDate);
				report.nextDay(report.warehouseList);
				report.currentDate= getnewDate(report.currentDate);
				report.datesSet.add(report.currentDate);
	
				//System.out.println("f");
				
				
			}
			else if(firstWord.equals("End"))
			{
				System.out.println("oog");
				report.nextDay(report.warehouseList);
			    
				//System.out.println("g");
			}}}
			
		//}
		
		//System.out.println(report.currentDate.getTime()+" time");
		//System.out.println("yo!!!!");
		
		//for (String s: report.warehouseList)
			//report.warehouseInventoryMap.get(s).outputToConsole(s);
		//System.out.println(report.warehouseBusiestDayMap.get("Columbus").busiestActivity);
		//System.out.println();
//		for(String s :report.upcSet)
//			for(GregorianCalendar date : report.datesSet)
//				if(report.helperMap.quickSearch.get(s)==null|| report.helperMap.quickSearch.get(s).get(date)==null){
//					//System.out.println("null");
//				}
//				else	
//					System.out.println(report.helperMap.quickSearch.get(s).get(date).intValue()+"  ::");
//	
//	}
	
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
				report.warehouseList.add(whareHouseName);
			
			}
		}
		
	}

	public static GregorianCalendar getDate (Scanner s){
		//
		s.next();
		String date = s.next();
		//System.out.println(date);
		String month = date.charAt(0)+""+ date.charAt(1);
		int month1 = Integer.parseInt(month);
		month1-=1;
		
		String day = date.charAt(3)+""+ date.charAt(4);
		int day1 = Integer.parseInt(day);
		
		String year = date.charAt(6)+""+ date.charAt(7)+date.charAt(8)+""+ date.charAt(9);
		
		int year1 = Integer.parseInt(year);
		
		
		GregorianCalendar cal = new GregorianCalendar(year1, month1, day1);
		return cal;
		
		
	}
	  public static GregorianCalendar getnewDate(GregorianCalendar manufactureDate)
	    {
	        GregorianCalendar result = (GregorianCalendar) manufactureDate.clone();
	        result.add(Calendar.DAY_OF_MONTH, 1);
	        return result;
	    }



}