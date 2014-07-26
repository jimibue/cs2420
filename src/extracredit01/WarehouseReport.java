package extracredit01;



import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * This class stores the data structures and methods to help generate the warehouse report using an object of this class
 * @author James Yeates 
 *
 */

public class WarehouseReport {

	protected Map<String,FoodItem> upcMap;// map of food items mapped to a upc code
	protected Map<String, Inventory<FoodItem>> warehouseInventoryMap;//map of an Inventory mapped to a warehouse(city)
	protected Map<String, Integer> warehouseTransactionMap;// map of # of transactions mapped to a warehouse
	protected Map<String, BusiestDay> warehouseBusiestDayMap;//  map of # of transactions(most) mapped to a warehouse
	protected Set<String> warehouseList;
	protected Set<String> upcSet;
	protected Set<FoodItem> foodItemSet;
	protected List<Scanner> scannerRequestList;
	
	static long start, end, total;
	
	
	protected static GregorianCalendar currentDate;

	/**
	 * constructor 
	 */
	public WarehouseReport()
	{
		upcMap = new TreeMap<String,FoodItem>();
		warehouseInventoryMap = new TreeMap<String, Inventory<FoodItem>>();
		warehouseTransactionMap= new TreeMap<String, Integer>();
		warehouseBusiestDayMap = new TreeMap<String, BusiestDay>();
		warehouseList = new HashSet<String>();
		upcSet = new HashSet<String>();
		foodItemSet = new HashSet<FoodItem>();
		scannerRequestList = new ArrayList<Scanner>();
		
	

	}
	/**
	 * Takes care of the required function for when the an item is received
	 * @param s a scanner object.
	 */


	public  void itemReceived (Scanner s)
	{
		//split the scanner object in to appropriate parts
		String upcCode = s.next();
		int quantity = Integer.parseInt(s.next());
		String warehouse = s.next();
		
		//edit warehouse name
		while(s.hasNext())
			warehouse += " "+ s.next();
		 warehouse.trim();

		// get the food item associated with its own upcCode
		FoodItem foodItem = upcMap.get(upcCode);
		
		//add the food item to the inventory of the warehouse
		warehouseInventoryMap.get(warehouse).addItem(foodItem, foodItem.getExpirationDate(currentDate), quantity);
		
		//add quantity to busiest day map.
		warehouseBusiestDayMap.get(warehouse).incrementActivity(quantity);


	}
	/**
	 * This method simply adds Scanner objects to a list to be latter processed
	 * @param s Scanner
	 */
	
	public void itemRequested(Scanner s)
	{
		scannerRequestList.add(s);
	}
	
	/**
	 * This method takes the list of Scanner object and processes them all at the once(one at a time).
	 * this way item request for the day can be handled before item requests.
	 * @param list
	 */
	public void proccesRequests (List<Scanner> list)
	{
		//Scan through the Scanners
		for( Scanner s : list)
		{
			//process information in scanner
			String upcCode = s.next();
			int requestedQuantity = Integer.parseInt(s.next());
			String warehouse = s.next();
			while(s.hasNext())
			
				warehouse += " "+ s.next();
			
			warehouse.trim();
			
			//add quantity to busiest day map.
			warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
			
			//get the fooditem and remove it from the specific warehouse
			FoodItem foodItem = upcMap.get(upcCode);
			
			warehouseInventoryMap.get(warehouse).removeMultipleItems(foodItem, requestedQuantity);
			
		}
}
	/**
	 * This Method deals with the set of instruction required to be performed
	 * when the key word Next is hit. Here the list of request is processed and
	 * busiest day information is dealt with and expired items are removed from the list
	 * @param set Set<String>
	 * @param list List<Scanner>
	 */


	public void nextDay(Set<String> set, List<Scanner> list)
	{
		//increment the date
		currentDate = App1.getnewDate(currentDate);
		
		for(String s: set){
			
			//process the request
			proccesRequests(list);
			list.clear();
			warehouseInventoryMap.get(s).removeExpiredItems2(currentDate);
			
			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			
			
			warehouseBusiestDayMap.get(s).resetCurrentActivity();

		} 
		

	}
	/**
	 * This class is called when the end of data file is reached. It does the same thing as
	 * the nextDay but deals with a special case for finding the busiest day that came up when
	 * programming this assignment
	 * 
	 * @param set Set<String>
	 * 
	 * @param list List<Scanner>
	 */
	public void endDay(Set<String> set, List<Scanner> list)
	{
		for(String s: set){

			proccesRequests(list);
			list.clear();
			warehouseInventoryMap.get(s).removeExpiredItems2(currentDate);
	
		} 
		//increment the date
		currentDate = App1.getnewDate(currentDate);
		
		//special case to process busiest day information
		for(String s: set){
			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			warehouseBusiestDayMap.get(s).resetCurrentActivity();
	
		} 
		

	}
	/**
	 * method used to check for the Busiest day is  called at the end of each day
	 * @param set Set<String>
	 * 
	 */
	public void checkforBusiestDay(Set<String> set)
	{
		for(String s: set){

			warehouseBusiestDayMap.get(s).checkForBusiestDay();

		} 
		

	}
	/**
	 * getter method that returns the date
	 * @return GregorianCalendar
	 */
	
	public GregorianCalendar getCurrentDate(){
		return currentDate;
	}


}//End class

