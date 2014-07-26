
package homework04;

import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The class represents an inventory of items that each have a quantity and a critical date. The list explicitly keeps
 * track of both the quantity and critical date for every item. No item in the inventory ever has a null date or
 * non-positive quantity. In the inventory, items are identified by both the item object and their date and .equals is
 * used to compare items and dates. (Items that are the same but have different critical dates are kept separately in
 * the inventory.)
 * 
 * This class makes use of a DatedItem generic class (that immediately follows this class in this file).
 * 
 * @author Peter Jensen
 * @version January 25, 2014
 */
public class Inventory<I extends Item>
{
    
    // Instance variables
    
    protected List<DatedItem<I>> inventory;
   
    protected Map<String,LinkedBlockingDeque<DatedItem<I>> > UPCQueueMap;
    protected Set<String> UPCSet;
 
    

    /* Constructor */

    /**
     * Builds an empty inventory.
     */
    public Inventory ()
    {
        //inventory = new ArrayList<DatedItem<I>>();
        UPCQueueMap = new HashMap<String,LinkedBlockingDeque<DatedItem<I>>>(); 
        UPCSet = new HashSet<String>() ;
        
        
    }

    /**
     * Adds an item to the inventory with the specified critical date and quantity. The item and date must be non-null
     * and the quantity must be positive or no action is taken. If the item already exists with the specified critical
     * date in the inventory, the quantity for that item (and date) will be increased by the specified quantity.
     * 
     * @param item
     *            an item
     * @param criticalDate
     *            a date
     * @param quantity
     *            a positive item count
     * 
     */
    public void addItem(I item, GregorianCalendar criticalDate, int quantity)
    {
        // Check for invalid parameters.
        
        if (item == null || criticalDate == null || quantity < 1)
            return;
        
        // Make a dated item for these parameters.
        
        DatedItem<I> d = new DatedItem<I> (item, criticalDate, quantity);
        
       
       //Check to see if the UPCCode is in the warehouse, if it is not add it
       // and create a new Queue to go with it
       if( !(UPCQueueMap.containsKey(item.name)))
       {
    	   UPCQueueMap.put(item.name, new LinkedBlockingDeque<DatedItem<I>>() );
    	   UPCQueueMap.get(item.name).addLast(d);
       }
       
       //if the Map contains the same food item increment the quantity 
       //if the queue does contain the item it will be in the end so
       //just check the end element in the queue
       
       else if(UPCQueueMap.get(item.name).peekLast()==null){///**********NEED TO CHECK THIS*******
    	   UPCQueueMap.get(item.name).addLast(d);
    	   
       }
       
       else if( UPCQueueMap.get(item.name).peekLast().isSameItemAndDate(d))
    	   UPCQueueMap.get(item.name).peekLast().quantity += quantity;
        
        // Didn't find it - add it to the queue.
       else 
    	   UPCQueueMap.get(item.name).addLast(d);
        
       

    }
    
    /**
     * Reduces the quantity of a queue (specified by item) in the inventory by the specified quantity.
     * If the item's quantity is reduced to or below 0, the item is removed from the inventory. If the quantity is not
     * positive, or if the item or date is null, no action is taken.
     * 
     * @param item
     *            an item
     * @param quantity
     *            a positive item count
     */
    public void removeMultipleItems(I item, int quantity) 
    {
        // Check for invalid parameters.
        
        if (item == null || quantity < 1)
            return;

        
        // See if the item is in the inventory.
        
        if((UPCQueueMap.containsKey(item.name)))
        {
        	int remaningQuantity = quantity;
           
        	// loop through the item in the queue, the item to be removed will be the oldest
     	   for(DatedItem<I> i : UPCQueueMap.get(item.name))
     	   {
     		// create a temp variable to be used to determine the remaining quantity
     		 int temp = i.quantity;  
     		 
     		 //remove the requested quantity from the first item in the queue( also the oldest)
     		 i.quantity -= remaningQuantity;
     		 
     		 //update remaining quantity
     		 remaningQuantity -= temp;
     		 //check if the item has been used up
     		 if (i.quantity < 1)
                  UPCQueueMap.get(item.name).remove(i);
     		 		//***checking to see if queue size is zero because if it is
     		 		//need to remove it from the set
     		 		if(UPCQueueMap.get(item.name).size()==0)
     		 			UPCQueueMap.remove(item.name);
     		 		
     		 
     		 //check to see if request has been meet if so return
             if(remaningQuantity < 0 ){
            	 
            	  return;
             }
            
     	   }
        }
     }
    
    /**
     * This method returns the quantity of this item (with the specified critical date) in the inventory.
     * 
     * @param item
     *            an item
     * @param criticalDate
     *            a date
     * @return the quantity of that item with that date in the inventory
     */
    public int getQuantity(I item, GregorianCalendar criticalDate)
    {
        // Check for invalid parameters.
        
        if (item == null || criticalDate == null)
            return 0;
        
        // Make a dated item for these parameters.
        
        DatedItem<I> d = new DatedItem<I> (item, criticalDate, 0);
        
        // Try to find it in the inventory.
        
        for (DatedItem<I> i : inventory)
            if (i.isSameItemAndDate(d))
                return i.quantity;// Found it - return the quantity
        
        // Not found.
        
        return 0;
    }
    
    /**
     * This method returns the critical date for the specified item in the inventory.  If multiple matching items are in
     * the inventory with different critical dates, the oldest critical date for that kind of item is returned.  If no
     * such item is found in the inventory, null is returned.
     * 
     * @param item an item
     * @return the oldest critical date for this item
     */
    public GregorianCalendar getDate(I item)
    {
        GregorianCalendar result = null;
        
        // Optimization loop on item dates for the specified item
        
        for (DatedItem<I> i : inventory)
            if (i.item.equals(item) && (result == null || i.criticalDate.before(result)))
                result = i.criticalDate;
                
        return result;
        
    }
    
    /**
     * This method returns an ArrayList of items whose critical date is before the target date. 
     * 
     * @param targetDate a date
     * @return a list of items whose critical date is before the target date
     */
    public ArrayList<I> getItemsPastDate(GregorianCalendar targetDate)
    {
        ArrayList<I> result = new ArrayList<I>();
        
        // Find all the past due items.
        
        for (DatedItem<I> i : inventory)
            if (i.criticalDate.before(targetDate))
                result.add(i.item);
        
        return result;
    }
    
    /**
     * Programmer debugging method - not for general use.  Outputs the contents of this inventory
     * to the screen with a title label.
     */
    void outputToConsole (String inventoryName)
    {
        DateFormat formatter = DateFormat.getDateInstance();
        
        System.out.println();
        String s = "Contents of Inventory: " + inventoryName;
        System.out.println (s);
        for (int i = 0; i < s.length(); i++)
            System.out.print ('-');
        System.out.println();
        for(String str : UPCQueueMap.keySet())
        	for (DatedItem<I> i : UPCQueueMap.get(str))
            System.out.printf ("Item: %-20s  Item Type: %-20s  Critical date: %13s     Quantity: %3s\n", i.item.getName(), i.item.getClass().getSimpleName(), formatter.format(i.criticalDate.getTime()), i.quantity);        
        System.out.println();
    }
    /**
     * 
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @return
     */
    public Set<FoodItem> getFoodItemSet(){
    	Set<FoodItem> foodItemSet = new HashSet<FoodItem>();//why does Tree set not work here?
    	for(String str : UPCQueueMap.keySet())
    	for( DatedItem<I> i: UPCQueueMap.get(str)){
    		FoodItem temp = (FoodItem) i.item;
    		foodItemSet.add(temp);
    	
    	}
 
    	return foodItemSet;
    }
    /**
     * This method removes all expired items in this inventory, since a queue is used
     * and since this will be checked every day only the first item in each queue will 
     * have to be checked if the date is the same then the item will be removed if not
     * nothing will happen
     * 
     * @param cal GregorianCalendar
     */
    

    public void removeExpiredItems1(GregorianCalendar cal)
    {
    	
    	//loop through all the food items in this inventory
    	System.out.println(UPCQueueMap.keySet().size());
    	for(String s : UPCQueueMap.keySet()){
    		System.out.println(s);
    		
    		if(UPCQueueMap.get(s).peek()==null){//**********NEED TO CHECK THIS*******
    			System.out.println("here");
    			return;
    		}

//    		//look at the the first item in the queues date for equality
    		if(UPCQueueMap.get(s).peekFirst().criticalDate.equals(cal))
    			{
    				//if equal remove it from the list
    				UPCQueueMap.get(s).remove();
    				System.out.println("yo!!");
    			}
    	}	
   
}
    public void removeExpiredItems2(GregorianCalendar cal)
    {
    	
    	//loop through all the food items in this inventory
    	for(String str : UPCQueueMap.keySet())
        	for (DatedItem<I> i : UPCQueueMap.get(str))
        		if(i.criticalDate.equals(cal))
        			UPCQueueMap.get(str).remove(i);
    	
	
   
}
 	public List<DatedItem<I>>returnInventory()
	{
		return inventory;
	}
}


/**
 * Class to represent items with dates and quantities.
 * 
 * Equality is defined by == only.
 */    
class DatedItem<T extends Item>
{
    // Instance variables - kept at package level protection
    
    T item;
    GregorianCalendar criticalDate;
    int quantity;
    
    /**
     * Builds a dated item with the specified item, date, and quantity.
     * 
     * @param item
     *            an item of type I
     * @param criticalDate
     *            the critical date for this item
     * @param quantity
     *            the quantity of this item
     */
    public DatedItem (T item, GregorianCalendar criticalDate, int quantity)
    {
        this.item = item;
        this.criticalDate = criticalDate;
        this.quantity = quantity;
    }
    
    /**
     * Returns true if this dated item and the other dated item share the same items and dates.
     * 
     * @param other
     *            some other dated item
     * @return true if they are equivalent items and dates
     */
    public boolean isSameItemAndDate (DatedItem<T> other)
    {
        return item.getName().equals(other.item.getName()) && criticalDate.equals(other.criticalDate);
    }   
    

  

}





















//
////////////////////////////////////////
/////////////////////
////////////////
//
//
//
//package homework04;
//
//import java.text.DateFormat;
//import java.util.*;
//
///**
// * The class represents an inventory of items that each have a quantity and a critical date. The list explicitly keeps
// * track of both the quantity and critical date for every item. No item in the inventory ever has a null date or
// * non-positive quantity. In the inventory, items are identified by both the item object and their date and .equals is
// * used to compare items and dates. (Items that are the same but have different critical dates are kept separately in
// * the inventory.)
// * 
// * This class makes use of a DatedItem generic class (that immediately follows this class in this file).
// * 
// * @author Peter Jensen
// * @version January 25, 2014
// */
//public class Inventory<I extends Item>
//{
//    
//    // Instance variables
//    
//    protected List<DatedItem<I>> inventory;
//    
//   // protected Set<GregorianCalendar> datesSet;
//
//    /* Constructor */
//
//    /**
//     * Builds an empty inventory.
//     */
//    public Inventory ()
//    {
//        inventory = new ArrayList<DatedItem<I>>();
//       
//    }
//    
//    /**
//     * Adds an item to the inventory with the specified critical date and quantity. The item and date must be non-null
//     * and the quantity must be positive or no action is taken. If the item already exists with the specified critical
//     * date in the inventory, the quantity for that item (and date) will be increased by the specified quantity.
//     * 
//     * @param item
//     *            an item
//     * @param criticalDate
//     *            a date
//     * @param quantity
//     *            a positive item count
//     * 
//     */
//    public void addItem(I item, GregorianCalendar criticalDate, int quantity)
//    {
//        // Check for invalid parameters.
//        
//        if (item == null || criticalDate == null || quantity < 1)
//            return;
//        
//        // Make a dated item for these parameters.
//        
//        DatedItem<I> d = new DatedItem<I> (item, criticalDate, quantity);
//        
//        // Try to find it in the inventory.
//        
//        for (DatedItem<I> i : inventory)
//            if (i.isSameItemAndDate(d))
//            {
//                // Found it - increase quantity and quit.
//                
//                i.quantity += quantity;
//                return;
//            }
//        
//        // Didn't find it - add it to inventory.
//        
//        inventory.add(d);
//        
//       
//
//    }
//    
//    /**
//     * Reduces the quantity of an item (specified by item object and date) in the inventory by the specified quantity.
//     * If the item's quantity is reduced to or below 0, the item is removed from the inventory. If the quantity is not
//     * positive, or if the item or date is null, no action is taken.
//     * 
//     * @param item
//     *            an item
//     * @param criticalDate
//     *            a date
//     * @param quantity
//     *            a positive item count
//     */
//    public void removeItem(I item, GregorianCalendar criticalDate, int quantity) 
//    {
//        // Check for invalid parameters.
//        
//        if (item == null || criticalDate == null || quantity < 1)
//            return;
//        
//        // Make a dated item for these parameters.
//        
//        DatedItem<I> d = new DatedItem<I> (item, criticalDate, quantity);
//        
//        // Try to find it in the inventory.
//        
//        for (DatedItem<I> i : inventory)
//            if (i.isSameItemAndDate(d))
//            {
//                // Found it - decrease quantity and remove it if quantity falls below 0.
//                
//                i.quantity -= quantity;
//                if (i.quantity < 1)
//                    inventory.remove(i);
//                
//                return;
//            }  
//
//    }
//    
//    /**
//     * This method returns the quantity of this item (with the specified critical date) in the inventory.
//     * 
//     * @param item
//     *            an item
//     * @param criticalDate
//     *            a date
//     * @return the quantity of that item with that date in the inventory
//     */
//    public int getQuantity(I item, GregorianCalendar criticalDate)
//    {
//        // Check for invalid parameters.
//        
//        if (item == null || criticalDate == null)
//            return 0;
//        
//        // Make a dated item for these parameters.
//        
//        DatedItem<I> d = new DatedItem<I> (item, criticalDate, 0);
//        
//        // Try to find it in the inventory.
//        
//        for (DatedItem<I> i : inventory)
//            if (i.isSameItemAndDate(d))
//                return i.quantity;// Found it - return the quantity
//        
//        // Not found.
//        
//        return 0;
//    }
//    
//    /**
//     * This method returns the critical date for the specified item in the inventory.  If multiple matching items are in
//     * the inventory with different critical dates, the oldest critical date for that kind of item is returned.  If no
//     * such item is found in the inventory, null is returned.
//     * 
//     * @param item an item
//     * @return the oldest critical date for this item
//     */
//    public GregorianCalendar getDate(I item)
//    {
//        GregorianCalendar result = null;
//        
//        // Optimization loop on item dates for the specified item
//        
//        for (DatedItem<I> i : inventory)
//            if (i.item.equals(item) && (result == null || i.criticalDate.before(result)))
//                result = i.criticalDate;
//                
//        return result;
//        
//    }
//    
//    /**
//     * This method returns an ArrayList of items whose critical date is before the target date. 
//     * 
//     * @param targetDate a date
//     * @return a list of items whose critical date is before the target date
//     */
//    public ArrayList<I> getItemsPastDate(GregorianCalendar targetDate)
//    {
//        ArrayList<I> result = new ArrayList<I>();
//        
//        // Find all the past due items.
//        
//        for (DatedItem<I> i : inventory)
//            if (i.criticalDate.before(targetDate))
//                result.add(i.item);
//        
//        return result;
//    }
//    
//    /**
//     * Programmer debugging method - not for general use.  Outputs the contents of this inventory
//     * to the screen with a title label.
//     */
//    void outputToConsole (String inventoryName)
//    {
//        DateFormat formatter = DateFormat.getDateInstance();
//        
//        System.out.println();
//        String s = "Contents of Inventory: " + inventoryName;
//        System.out.println (s);
//        for (int i = 0; i < s.length(); i++)
//            System.out.print ('-');
//        System.out.println();
//        for (DatedItem<I> i : inventory)
//            System.out.printf ("Item: %-20s  Item Type: %-20s  Critical date: %13s     Quantity: %3s\n", i.item.getName(), i.item.getClass().getSimpleName(), formatter.format(i.criticalDate.getTime()), i.quantity);        
//        System.out.println();
//    }
//    /**
//     * 
//     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//     * @return
//     */
//    public Set<FoodItem> getFoodItemSet(){
//    	Set<FoodItem> foodItemSet = new HashSet<FoodItem>();//why does Tree set not work here?
//    	for( DatedItem<I> i: inventory){
//    		FoodItem temp = (FoodItem) i.item;
//    		foodItemSet.add(temp);
//    	
//    	}
// 
//    	return foodItemSet;
//    }
//    
//    
//
//    public void removeExpiredItems1(GregorianCalendar cal)
//    {
//    	//System.out.println("YO!!!!");
//    	
//    	List<DatedItem<I>> itemsToRemove = new ArrayList<DatedItem<I>>();
//    		
//    	for(DatedItem<I> item: inventory)
//    			
//    		if(item.criticalDate.equals(cal)){
//    			itemsToRemove.add(item);
//    			
//    		}
//    	inventory.removeAll(itemsToRemove);
//    		
//}
// 	public List<DatedItem<I>>returnInventory()
//	{
//		return inventory;
//	}
//}
//
//
///**
// * Class to represent items with dates and quantities.
// * 
// * Equality is defined by == only.
// */    
//class DatedItem<T extends Item>
//{
//    // Instance variables - kept at package level protection
//    
//    T item;
//    GregorianCalendar criticalDate;
//    int quantity;
//    
//    /**
//     * Builds a dated item with the specified item, date, and quantity.
//     * 
//     * @param item
//     *            an item of type I
//     * @param criticalDate
//     *            the critical date for this item
//     * @param quantity
//     *            the quantity of this item
//     */
//    public DatedItem (T item, GregorianCalendar criticalDate, int quantity)
//    {
//        this.item = item;
//        this.criticalDate = criticalDate;
//        this.quantity = quantity;
//    }
//    
//    /**
//     * Returns true if this dated item and the other dated item share the same items and dates.
//     * 
//     * @param other
//     *            some other dated item
//     * @return true if they are equivalent items and dates
//     */
//    public boolean isSameItemAndDate (DatedItem<T> other)
//    {
//        return item.getName().equals(other.item.getName()) && criticalDate.equals(other.criticalDate);
//    }   
//    
//
//  
//
//}
