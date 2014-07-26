package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App1 {
	
	static WarehouseReport report;

	public static void main(String[] args) {
		
		App1 a = new App1("data2");
		
		a.printbusiestDayInfo();
		a.printUnstockedProducts();
		a.printFullyStockedProducts();
		
	}
	public App1(String fileName){
		report = new WarehouseReport();
		
		//get the file
		Scanner inventoryScanner = getFileScanner1(fileName);
		
		//scan the file
		while(inventoryScanner.hasNextLine())
		{
			//get each line in the file and create a new scanner
			Scanner currentLine = new Scanner(inventoryScanner.nextLine());
			String firstWord = currentLine.next();
			
			
			if(firstWord.equals("FoodItem"))
			{
				processFoodItem(currentLine);
				
			}
			else if(firstWord.equals("Warehouse")){
				
				makeWarehouse(currentLine);
			
			}
			else if(firstWord.equals("Start"))
			{
				processStart(currentLine);
				
			
			}
			else if(firstWord.equals("Receive:"))
			{
				
				report.itemReceived(currentLine);
				
				
			}
			
			else if(firstWord.equals("Request:"))
			{
				
				report.itemRequested(currentLine);
				
				
			}
			else if(firstWord.equals("Next"))
			{
				//report.checkforBusiestDay(report.warehouseInventoryMap.keySet());
				report.currentDate= getnewDate(report.currentDate);
				report.nextDay(report.warehouseInventoryMap.keySet(), report.scannerRequestList);
				
				
			   
				
			}
			else if(firstWord.equals("End"))
			{
				
				report.endDay(report.warehouseInventoryMap.keySet(),report.scannerRequestList);
				
				
				
			    
				
			}
			
		}
	}
		
//		for(String s : report.warehouseList)
//		{
//			report.warehouseInventoryMap.get(s).outputToConsole(s);
//		}
//		System.out.println(report.currentDate.getTime());
//		System.out.println("Finished");
		
		


	
	public void printbusiestDayInfo()
	{
		System.out.println("--------Busiest Day Info-------");

		for (String s : report.warehouseList)
		{
			report.warehouseBusiestDayMap.get(s).printBusiestDayInfo();

		}
		System.out.println("\n");
		
		
	}
	
	public void printUnstockedProducts()
	{
		Set<FoodItem> unstocked = new HashSet<FoodItem>(report.foodItemSet);
		
		//remove stocked products
		for (String s : report.warehouseList)
		{
			unstocked.removeAll(report.warehouseInventoryMap.get(s).getFoodItemSet());
		}
		
		System.out.println("-----Unstocked Food Items-------\n");
		for(FoodItem f : unstocked)
		{
			System.out.println(f.upcCode+ "    " + f.name);
		}
		
	}
	
	public void printFullyStockedProducts()
	{
		System.out.println("\n---------Fully Stocked Products-------\n");
		if(report.warehouseList==null)
		{
			System.out.println("None");
			return;
		}
		if(report.foodItemSet==null)
		{
			System.out.println("None");
			return;
		}
	
		Set<FoodItem> tempSet = new HashSet<FoodItem>(report.foodItemSet);
		//remove stocked products
		int count =0;
		for (String s : report.warehouseList)
		{
			Set<FoodItem> tempSet1 = report.warehouseInventoryMap.get(s).getFoodItemSet();
			if(count ==0){
				
				tempSet.retainAll(tempSet1);
				
				count++;
			}
			else{
				
				tempSet.retainAll(tempSet1);
				
				
			}
		}
		
		
		for(FoodItem f : tempSet)
		{
			System.out.println(f.upcCode+ "    " + f.name);
		}
		
	}

	
	/**
	 * takes a scanner containing a string information and creates a Fooditem
	 * @param s Scanner object containing in formation to make a Food item 
	 * @return a food item
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
	
	
	public static void processFoodItem(Scanner s)
	{
		FoodItem item = makeFoodItem(s);// create the fooditem
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
	 * @param s SCanner
	 * 
	 * @return GregorianCalendar
	 */

	public static void processStart(Scanner s)
	{
		WarehouseReport.currentDate = getDate(s);
		report.warehouseList = report.warehouseInventoryMap.keySet();
		
		for( String str : report.warehouseList){
			report.warehouseBusiestDayMap.put(str, new BusiestDay(str));
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