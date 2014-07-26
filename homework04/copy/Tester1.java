package homework04.copy;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Tester1 {
	
	
	
	public static void main(String[] args) {
		
		/*
		 * Step one--- get the FoodItem
		 */
		
		FoodItem foodItem1 = new FoodItem("chestnut puree with vanilla","0353264991"  , 2  );
		FoodItem foodItem2 = new FoodItem("the orange box","0984523912"  , 1  );
		
		/*
		 * Step 1a- create a map the maps upcCodes to a FoodItem--?
		 */
		Map<String,FoodItem> upcMap  = new TreeMap<String,FoodItem>();
		
		upcMap.put(foodItem1.getUpcCode(), foodItem1);
		upcMap.put(foodItem2.getUpcCode(), foodItem2);
		
		System.out.println(upcMap.containsKey("0353264991") +" !");
		//System.out.println(upcMap.);
		
		/*
		 * Step two--- get the warehouses
		 */
		
		String whouse1 = "Columbus";
		//do we need to make a specific inventory object for each warehouse?
		Inventory<FoodItem> colmbusInv = new Inventory<FoodItem>();
		String whouse2 = "Scottsdale";
		Inventory<FoodItem> scottsdaleInv = new Inventory<FoodItem>();
		String whouse3 = "Tacoma";
		Inventory<FoodItem> tacomaInv = new Inventory<FoodItem>();
		
		/*
		 * step three- create a map that maps warehouse names to inventories
		 */
		
		
		Map<String,Inventory<FoodItem>> warehouses = new TreeMap<String,Inventory<FoodItem>>();
		
		warehouses.put(whouse1, colmbusInv);
		warehouses.put(whouse2,scottsdaleInv );
		warehouses.put(whouse3, tacomaInv);
		
		/*
		 * step 4- get the start Date
		 */
		
		GregorianCalendar testDate1 = new GregorianCalendar(2010,4,2);
		GregorianCalendar startDate = new GregorianCalendar(2011,13,1);
		startDate.getTime();
		System.out.println(startDate.getTime());
		startDate.add(5,1);
		startDate.getTime();
		System.out.println(startDate.getTime());
		//System.exit(0);
		GregorianCalendar testDate = new GregorianCalendar(2010,5,2);
		
		GregorianCalendar testDate2 = new GregorianCalendar(2009,4,2);
		GregorianCalendar testDate3 = new GregorianCalendar(2013,4,2);
		
		
		/*
		 * step 5 - processes receive and request
		 */
		
		/*
		 * Receive
		 */
		//Receive: 0984523912 7 Tacoma
		
		System.out.println(upcMap.get("0984523912"));
		//get the food item 
		FoodItem tempItem = upcMap.get("0984523912");
		
		FoodItem tempItem1 = upcMap.get("0353264991");
		//get the critical date
		GregorianCalendar expirationDate = tempItem.getExpirationDate(testDate);
		
		warehouses.get("Tacoma").addItem(tempItem, expirationDate, 7);
		System.out.println(warehouses.get("Tacoma").getDate(tempItem).getTime()+"  !!!");
		//warehouses.get("Tacoma").addItem(tempItem1, testDate1, 6);
		
		for(String i : warehouses.keySet())
			System.out.println(i);
		System.out.println(warehouses.get("Tacoma").getItemsPastDate(expirationDate)+" 00");
		System.out.println(warehouses.get("Tacoma").getQuantity(tempItem, expirationDate));
		
		//process receive request
	}

}
