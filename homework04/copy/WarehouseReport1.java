package homework04.copy;



import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
/**
 * This class stores the data structures and methods to help generate the warehouse report using an object of this class
 * @author Logan Ropelato    and James Yeates
 *
 */

public class WarehouseReport1 {

	protected Map<String,FoodItem> upcMap;// map of food items mapped to a upc code
	protected Map<String, Inventory<FoodItem>> warehouseInventoryMap;//map of an Inventory mapped to a warehouse(city)
	protected Map<String, Integer> warehouseTransactionMap;// map of # of transactions mapped to a warehouse
	protected Map<String, BusiestDay> warehouseBusiestDayMap;//  map of # of transactions(most) mapped to a warehouse
	protected Set<String> warehouseList;
	protected Set<String> upcSet;
	protected Set<FoodItem> foodItemSet;



	protected static GregorianCalendar currentDate;

	public WarehouseReport1()
	{
		upcMap = new TreeMap<String,FoodItem>();
		warehouseInventoryMap = new TreeMap<String, Inventory<FoodItem>>();
		warehouseTransactionMap= new TreeMap<String, Integer>();
		warehouseBusiestDayMap = new TreeMap<String, BusiestDay>();
		warehouseList = new HashSet<String>();
		upcSet = new HashSet<String>();
		foodItemSet = new HashSet<FoodItem>();

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


		FoodItem foodItem = upcMap.get(upcCode);
		warehouseInventoryMap.get(warehouse).addItem(foodItem, foodItem.getExpirationDate(currentDate), quantity);


		//add quantity to busiest day map.
		warehouseBusiestDayMap.get(warehouse).incrementActivity(quantity);



		//if the warehouse contains the item increment to current amount
		//		if( report.warehouseTransactionMap.containsKey(warehouse))
		//			 report.warehouseTransactionMap.put(warehouse, report.warehouseTransactionMap.get(warehouse)+quantity);
		//		else
		//			report.warehouseTransactionMap.put(warehouse, quantity);
	}

	public void itemRequested (Scanner s)
	{
		String upcCode = s.next();
		int requestedQuantity = Integer.parseInt(s.next());
		String warehouse = s.next();

		FoodItem oldestItem = upcMap.get(upcCode);

		//add quantity to busiest day map.
		warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
		Inventory<FoodItem> currentInventory = warehouseInventoryMap.get(warehouse);

		//remove quantity from the inventory
		while (warehouseInventoryMap.get(warehouse).getQuantity(oldestItem, oldestItem.getExpirationDate(currentDate)) > 0)
		{
			// find oldest food item, decriment quantity by 'requestedQuantity' amount

			for (DatedItem<FoodItem> c : currentInventory.returnInventory())
			{
				//If the current Item matches the item being checked in the loop, then begin comparing their shelf life. 
				//Whichever item has the smallest shelf life will have its quantity decrimented by the requestedQuantity amount
				//If the shelf life of the item being compared to our current item is smaller, it is older.  
				if(c.item.compareTo(oldestItem) == -1)
				{
					oldestItem = c.item;                    
				}

				if(c.item.compareTo(oldestItem) == 0)
				{
					// if these two items have the same shelf life, do nothing.
				}

				if(c.item.compareTo(oldestItem) == 1)
				{
					//If the item in the array is newer than our current item, we dont want to decriment its shelflife. Do nothing.
				}


			}

			warehouseInventoryMap.get(warehouse).removeItem(oldestItem, oldestItem.getExpirationDate(currentDate), requestedQuantity);
			warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
			System.out.println(warehouseInventoryMap.get(warehouse).getQuantity(oldestItem,oldestItem.getExpirationDate(currentDate)));	
		}
	}
	public void nextDay(Set<String> set)
	{
		for(String s: set){

			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			//warehouseInventoryMap.get(s).removeExpiredItems(currentDate);
		} 
	}


	//Determine which products do not exist in any warehouse..  	

	//Determine which products still exist in every warehouse. ,
	//To be clear, if a product has a positive quantity in only 9 out of 10 warehouses, it would not be on this list. 

	//Determine the single busiest day for each warehouse. 



}