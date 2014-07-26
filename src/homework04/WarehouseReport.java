package homework04;



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
 * @author Logan Ropelato    and James Yeates
 *
 */

public class WarehouseReport {

	protected Map<String,FoodItem> upcMap;// map of food items mapped to a upc code
	protected Map<String, Inventory<FoodItem>> warehouseInventoryMap;//map of an Inventory mapped to a warehouse(city)
	protected Map<String, Integer> warehouseTransactionMap;// map of # of transactions mapped to a warehouse
	protected Map<String, BusiestDay> warehouseBusiestDayMap;//  map of # of transactions(most) mapped to a warehouse
	protected Set<String> warehouseList;
	//protected Set<GregorianCalendar> datesSet;
	protected Set<String> upcSet;
	protected Set<FoodItem> foodItemSet;
	protected List<Scanner> scannerRequestList;
	//protected HelperMap helperMap;
		
	


	
	protected static GregorianCalendar currentDate;

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
		String upcCode = s.next();
		int quantity = Integer.parseInt(s.next());
		String warehouse = s.next();
		while(s.hasNext())
		{
			 warehouse += " "+ s.next();
			 
		}
		 warehouse.trim();

		// get the food item associated with its own upcCode
		FoodItem foodItem = upcMap.get(upcCode);
		
		//add the food item to the inventory of the warehouse
		warehouseInventoryMap.get(warehouse).addItem(foodItem, foodItem.getExpirationDate(currentDate), quantity);
		
		//add quantity to busiest day map.
		warehouseBusiestDayMap.get(warehouse).incrementActivity(quantity);


	}
	
	public void itemRequested(Scanner s)
	{
		scannerRequestList.add(s);
	}
	public void proccesRequests (List<Scanner> list)
	{
		for( Scanner s : list){
			String upcCode = s.next();
			int requestedQuantity = Integer.parseInt(s.next());
			String warehouse = s.next();
			while(s.hasNext())
			{
				warehouse += " "+ s.next();
				
			}
			warehouse.trim();
			//add quantity to busiest day map.
			warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
			//Inventory<FoodItem> currentInventory = warehouseInventoryMap.get(warehouse);
		
			FoodItem foodItem = upcMap.get(upcCode);
			warehouseInventoryMap.get(warehouse).removeMultipleItems(foodItem, requestedQuantity);
		
	}
}


	public void nextDay(Set<String> set, List<Scanner> list)
	{
		for(String s: set){

			
			proccesRequests(list);
			list.clear();
			//System.out.println(currentDate.getTime());
			warehouseInventoryMap.get(s).removeExpiredItems2(currentDate);
			
			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			warehouseBusiestDayMap.get(s).resetCurrentActivity();
		
			// date is incremented in the app1 class
			
		} 
		

	}
	public void endDay(Set<String> set, List<Scanner> list)
	{
		for(String s: set){

			
			proccesRequests(list);
			list.clear();
			//System.out.println(currentDate.getTime());
			warehouseInventoryMap.get(s).removeExpiredItems2(currentDate);
	
		
			// date is incremented in the app1 class
			
		} 
		currentDate = App1.getnewDate(currentDate);
		for(String s: set){

			
			
			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			warehouseBusiestDayMap.get(s).resetCurrentActivity();
		
			// date is incremented in the app1 class
			
		} 
		

	}
	public void checkforBusiestDay(Set<String> set)
	{
		for(String s: set){

			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			
		
			// date is incremented in the app1 class
			
		} 
		

	}
	
	public GregorianCalendar getCurrentDate(){
		return currentDate;
	}


	//Determine which products do not exist in any warehouse..  	

	//Determine which products still exist in every warehouse. ,
	//To be clear, if a product has a positive quantity in only 9 out of 10 warehouses, it would not be on this list. 

	//Determine the single busiest day for each warehouse. 



}

