package homework04.copy;




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
	protected List<GregorianCalendar> datesSet;
	protected Set<String> upcSet;
	protected Set<FoodItem> foodItemSet;
	protected HelperMap helperMap;
		
	


	
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
		helperMap = new HelperMap();
		//datesSet = new TreeSet<GregorianCalendar> ();
		datesSet = new ArrayList<GregorianCalendar> ();

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
			 warehouse += " "+ s.next();

		FoodItem foodItem = upcMap.get(upcCode);
		warehouseInventoryMap.get(warehouse).addItem(foodItem, foodItem.getExpirationDate(currentDate), quantity);
		
		//add to helperMap
		helperMap.addItem(upcCode, foodItem.getExpirationDate(currentDate), quantity);
		datesSet.add(foodItem.getExpirationDate(currentDate));


		//add quantity to busiest day map.
		warehouseBusiestDayMap.get(warehouse).incrementActivity(quantity);



		//if the warehouse contains the item increment to current amount
		//		if( report.warehouseTransactionMap.containsKey(warehouse))
		//			 report.warehouseTransactionMap.put(warehouse, report.warehouseTransactionMap.get(warehouse)+quantity);
		//		else
		//			report.warehouseTransactionMap.put(warehouse, quantity);
	}
	
	public void itemRequested(Scanner s)
	{
		String upcCode = s.next();
		int requestedQuantity = Integer.parseInt(s.next());
		String warehouse = s.next();
		while(s.hasNext())
			 warehouse += " "+ s.next();
		
		//for(GregorianCalendar date : datesSet)
		for(int date = datesSet.size()-1; date>0; date--)
		{
			
			
				if(requestedQuantity<1)
					break;
				if(helperMap.quickSearch.get(upcCode)==null)
					continue;
				if(helperMap.quickSearch.get(upcCode).get(date)==null)
					continue;
				int tempAmount =helperMap.quickSearch.get(upcCode).get(date).intValue();
				if(tempAmount>requestedQuantity){
					tempAmount -= requestedQuantity;
					requestedQuantity=0;

				}
				else{
					requestedQuantity -=tempAmount;
					helperMap.quickSearch.get(upcCode).remove(date);
					
				}
			
		}
		
		
	}

//	public void itemRequested (Scanner s)
//	{
//		String upcCode = s.next();
//		int requestedQuantity = Integer.parseInt(s.next());
//		String warehouse = s.next();
//
//		FoodItem oldestItem = upcMap.get(upcCode);
//
//		//add quantity to busiest day map.
//		warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
//		Inventory<FoodItem> currentInventory = warehouseInventoryMap.get(warehouse);
//
//		//remove quantity from the inventory
//		while (requestedQuantity>0 && warehouseInventoryMap.get(warehouse).getQuantity(oldestItem, oldestItem.getExpirationDate(currentDate)) > 0)
//		{
//			int quantity =0;
//			FoodItem it  = upcMap.get(upcCode);
//			
//			
//			for (DatedItem<FoodItem> c : currentInventory.returnInventory())
//			{
//				//If the current Item matches the item being checked in the loop, then begin comparing their shelf life. 
//				//Whichever item has the smallest shelf life will have its quantity decrimented by the requestedQuantity amount
//				//If the shelf life of the item being compared to our current item is smaller, it is older.  
//				if(c.item.compareTo(oldestItem) == 1)
//				{
//					it=c.item;
//					// if these two items have the same shelf life, do nothing.
//					System.out.println("here!!!!");
//					oldestItem = c.item;
//					quantity = warehouseInventoryMap.get(warehouse).getQuantity(oldestItem, oldestItem.getExpirationDate(currentDate));
//					if(requestedQuantity <= quantity)
//					{
//						quantity=requestedQuantity;
//						requestedQuantity=0;
//					}
//					else
//						requestedQuantity -= quantity;
//					
//					
//				}
//
//				else if(c.item.compareTo(oldestItem) == 0)
//				{
//					it=c.item;
//					// if these two items have the same shelf life, do nothing.
//					System.out.println("here!!!!");
//					oldestItem = c.item;
//					quantity = warehouseInventoryMap.get(warehouse).getQuantity(oldestItem, oldestItem.getExpirationDate(currentDate));
//					if(requestedQuantity <= quantity)
//					{
//						quantity=requestedQuantity;
//						requestedQuantity=0;
//					}
//					else
//						requestedQuantity -= quantity;
//				}
//
//				else if(c.item.compareTo(oldestItem) == 1)
//				{
//			
//				}
//				
//				warehouseInventoryMap.get(warehouse).removeItem(it, it.getExpireDate(), quantity);
//
//			}
//			System.out.println(quantity+"!!!!");
//			
//			warehouseBusiestDayMap.get(warehouse).incrementActivity(requestedQuantity);
//			System.out.println(warehouseInventoryMap.get(warehouse).getQuantity(oldestItem,oldestItem.getExpirationDate(currentDate)));	
//		}
//		
//	}
	public void nextDay(Set<String> set)
	{
		for(String s: set){

			warehouseBusiestDayMap.get(s).checkForBusiestDay();
			warehouseInventoryMap.get(s).removeExpiredItems1(currentDate);
			//helperMap.removeExpiredItems(currentDate);
			//System.out.println("here");
		} 
		

	}


	//Determine which products do not exist in any warehouse..  	

	//Determine which products still exist in every warehouse. ,
	//To be clear, if a product has a positive quantity in only 9 out of 10 warehouses, it would not be on this list. 

	//Determine the single busiest day for each warehouse. 



}

