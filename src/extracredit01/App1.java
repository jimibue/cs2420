package extracredit01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
/**
 * This class is responsible for getting the file from the user, processing the file
 * and printing the information to the console
 * 
 * @author James Yeates
 * @version 3/22/14
 */

public class App1 {
	
	static WarehouseReport report;
	static long start, end, total;

	public static void main(String[] args) {
		
		long start1 = System.currentTimeMillis();
		App1 a = new App1();
		long stop1 = System.currentTimeMillis();
		
//		start = System.currentTimeMillis();
//		
//		end= System.currentTimeMillis();
//		total+= end-start;
		System.out.println("total time: " + (stop1 - start1) /1000.0);
		
		
		
		System.out.println("Report by James Yeates\n");
		a.printUnstockedProducts();
		a.printFullyStockedProducts();
		a.printbusiestDayInfo();
		
		//used for debugging
//		for(String s : report.warehouseList)
//		{
//			report.warehouseInventoryMap.get(s).outputToConsole(s);
//		}

		System.out.println(total/1000.0);
		
	}
	/**
	 * constructor...  gets and processes the file
	 * @param fileName
	 */
	public App1(){
		
		report = new WarehouseReport();
		
		//get the file
		//Scanner inventoryScanner = getFileScanner1(fileName);
		Scanner inventoryScanner = getFileScanner();
		
		//scan the file
		start = System.currentTimeMillis();
		while(inventoryScanner.hasNextLine())
		{
			//get each line in the file and create a new scanner
			Scanner currentLine = new Scanner(inventoryScanner.nextLine());
			String firstWord = currentLine.next();
			
			//process each line coming from the scanner based on the first word of the
			//new line
			if(firstWord.equals("FoodItem"))
			
			
				processFoodItem(currentLine);
				
			else if(firstWord.equals("Warehouse"))
		
				makeWarehouse(currentLine);
			
			else if(firstWord.equals("Start"))
			
				
				processStart(currentLine);
				
			else if(firstWord.equals("Receive:"))

			
				report.itemReceived(currentLine);
				
			else if(firstWord.equals("Request:"))

				report.itemRequested(currentLine);
			
			else if(firstWord.equals("Next"))

				report.nextDay(report.warehouseInventoryMap.keySet(), report.scannerRequestList);
		
			else if(firstWord.equals("End"))
				
				report.endDay(report.warehouseInventoryMap.keySet(),report.scannerRequestList);
	
		}
		System.out.println((total = System.currentTimeMillis()-start)/1000);
	}
		
	/**
	 * this method prints the gets and prints the busiest day information to the console
	 */
		

	public void printbusiestDayInfo()
	{
		
		System.out.println("\nBusiest Days:");
		
		//loop through all the warehouses
		for (String s : report.warehouseList)
			
			//get and print the information to the console
			report.warehouseBusiestDayMap.get(s).printBusiestDayInfo();
	}
	/**
	 * this method determines which products are missing from every warehouse
	 * and prints out the results to the console
	 */
	
	public void printUnstockedProducts()
	{
		//if no there are no foods items
		if(report.foodItemSet.size()==0)
		{
			System.out.println();
			return;
		}
			
		
		//assume all items are stocked
		Set<FoodItem> unstocked = new HashSet<FoodItem>(report.foodItemSet);
		
		//loop through all the warehouses
		for (String s : report.warehouseList)
		{
			//if the warehouse contains the food item remove it from the unstocked set.
			unstocked.removeAll(report.warehouseInventoryMap.get(s).getFoodItemSet());
		}
		//print out the information
		System.out.println("Unstocked Products:");
		for(FoodItem f : unstocked)
		{
			System.out.println(f.upcCode+ " " + f.name);
		}
		
	}
	/**
	 * This method determines which products are contained in every warehouse
	 * and prints that information to the console
	 */
	
	public void printFullyStockedProducts()
	{
		System.out.println("\nFully-Stocked Products");
		//check for an empty warehouse list
		if(report.warehouseList==null)
		{
			System.out.println();
			return;
		}
		//check for an empty food item list
		if(report.foodItemSet==null)
		{
			System.out.println("None");
			return;
		}
	
		//assume all food Item are fully stocked
		Set<FoodItem> tempSet = new HashSet<FoodItem>(report.foodItemSet);
		//remove stocked products
		
		for (String s : report.warehouseList)
		{
			//get the set from the current warehouse and do an intersection (retainAll)
			//with the tempSet
			Set<FoodItem> tempSet1 = report.warehouseInventoryMap.get(s).getFoodItemSet();
			tempSet.retainAll(tempSet1);
		}
		
		//print the fully-stocked products information 
		for(FoodItem f : tempSet)
		{
			System.out.println(f.upcCode+ " " + f.name);
		}
		
	}

	
	/**
	 * takes a scanner containing a string information and creates a Fooditem
	 * @param s Scanner object containing in formation to make a Food item 
	 * @return a food item
	 */

	public static FoodItem makeFoodItem (Scanner s)
	{
		//get info
		String upcCode =null;
		String shelfLife = null;
		int x =0;
		String name=null;
		//Process information and assign it to appropriate variables to create the fooditem
		while(s.hasNext()){
			
			String tempString = s.next();
			if(tempString.equals("Code:"))
			
				upcCode = s.next();
			
			else if( tempString.equals("life:"))
			{
				shelfLife = s.next();
			    x= Integer.parseInt(shelfLife);
			}

			else if( tempString.equals("Name:"))
			{
				 name= s.nextLine().trim();
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
	/**
	 * This method was used to directly get the file used for testing.
	 * @param s String
	 * @return Scanner
	 */

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
	 * This method calls other methods to help organize the procedures
	 * needed to be done when processing food items
	 * @param s Scanner
	 */
	
	public static void processFoodItem(Scanner s)
	{
		//make the food item 
		FoodItem item = makeFoodItem(s);
		//add the item to appropriate data structures
		report.upcMap.put(item.getUpcCode(), item);
		report.upcSet.add(item.getUpcCode());
		report.foodItemSet.add(item);
	}
	/**
	 * populates map that maps inventories  to warehouses
	 * this method is called when warehouse is keyword
	 * @param s Scanner
	 */

	public static void makeWarehouse(Scanner s)
	{
		String tempString = null;
		Inventory<FoodItem> tempInven = null;
		String whareHouseName=null;
		
		//process Scanner to retrieve the information need to create a warehouse
		while(s.hasNext())
		{
			tempString = s.next();
			if(tempString.equals("-"))
			{
				tempInven = new Inventory<FoodItem>();
				whareHouseName = s.next();
				
				//check to see if city is more than one word
				while(s.hasNext())
					whareHouseName +=  " "+s.next();
				whareHouseName.trim();
				report.warehouseInventoryMap.put(whareHouseName, tempInven);
				
			
			}
		}
		
	}
	/**
	 * This method takes information from a scanner and creates a date
	 * @param s Scanner
	 * 
	 * @return GregorianCalendar
	 */

	public static void processStart(Scanner s)
	{
		//get the start date
		WarehouseReport.currentDate = getDate(s);
		//make a set of warehouse 
		report.warehouseList = report.warehouseInventoryMap.keySet();
		//make a map of warehouse and BusiestDay objects
		for( String str : report.warehouseList){
			report.warehouseBusiestDayMap.put(str, new BusiestDay(str));
		}
	}
	/**
	 * This method take in a Scanner that contains a string with the format
	 * mm/dd/yyyy and converts it into a GregorianCalendar object
	 * @param s Scanner
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar getDate (Scanner s){
		// get the date
		s.next();
		String date = s.next();
		
		//get the month convert to int subtract 1 because java months start at 0
		String month = date.charAt(0)+""+ date.charAt(1);
		int month1 = Integer.parseInt(month);
		month1-=1;
		
		//get the day convert to int
		String day = date.charAt(3)+""+ date.charAt(4);
		int day1 = Integer.parseInt(day);
		
		//get the day convert to int
		String year = date.charAt(6)+""+ date.charAt(7)+date.charAt(8)+""+ date.charAt(9);
		int year1 = Integer.parseInt(year);
		
		GregorianCalendar cal = new GregorianCalendar(year1, month1, day1);
		return cal;
		
		
	}
	
	/**
	 * This method increments the day by one.
	 * @param manufactureDate the date to be incremented
	 * @return GregorianCalendar
	 */
	  public static GregorianCalendar getnewDate(GregorianCalendar manufactureDate)
	    {
	        GregorianCalendar result = (GregorianCalendar) manufactureDate.clone();
	        result.add(Calendar.DAY_OF_MONTH, 1);
	        return result;
	    }



}