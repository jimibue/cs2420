
package extracredit01;

import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The class represents an inventory of items that each have a quantity and a critical date. THis class was change to have a 
 * Map that maps Strings (upccodes) to a queue.  This way item are ordered into to groups by which foodItem they are
 * and put in the queue from the way they are received.  This was the older item will be at the front of the list and 
 * new items will be add to the end of the list  In the inventory, items are identified by both the item object and their date and .equals is
 * used to compare items and dates. (Items that are the same but have different critical dates are kept separately in
 * the inventory.)
 * 
 * This class makes use of a DatedItem generic class (that immediately follows this class in this file).
 * 
 * @author Peter Jensen and James Yeates
 * @version January 25, 2014
 */
public class Inventory<I extends Item>
{
    
    // Instance variables
    protected Map<Integer,LinkedBlockingDeque<DatedItem<I>> > UPCQueueMap;
    protected Set<String> UPCSet;
    static long start = 0;
    static long end = 0;
    static long total= 0;
 
    

    /* Constructor */

    /**
     * Builds an empty inventory.
     */
    public Inventory ()
    {
        
        UPCQueueMap = new HashMap<Integer,LinkedBlockingDeque<DatedItem<I>>>(); 
        UPCSet = new HashSet<String>() ;
        
        
    }

    /**
     * Adds an item to the map with the specified critical date and quantity. The item and date must be non-null
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
       if( !(UPCQueueMap.containsKey(item.hashCode())))
       {
    	   UPCQueueMap.put(item.hashCode(), new LinkedBlockingDeque<DatedItem<I>>() );
    	   UPCQueueMap.get(item.hashCode()).addLast(d);
       }
       
       //if the Map contains the same food item increment the quantity 
       //if the queue does contain the item it will be in the end so
       //just check the end element in the queue
       
       else if(UPCQueueMap.get(item.hashCode()).peekLast()==null){///**********NEED TO CHECK THIS*******
    	   UPCQueueMap.get(item.hashCode()).addLast(d);
    	   
       }
       
       else if( UPCQueueMap.get(item.hashCode()).peekLast().isSameItemAndDate(d))
    	   UPCQueueMap.get(item.hashCode()).peekLast().quantity += quantity;
        
        // Didn't find it - add it to the queue.
       else 
    	   UPCQueueMap.get(item.hashCode()).addLast(d);
        
       

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
        
        if((UPCQueueMap.containsKey(item.hashCode())))
        {
        	int remaningQuantity = quantity;
           
        	// loop through the item in the queue, the item to be removed will be the oldest
     	   for(DatedItem<I> i : UPCQueueMap.get(item.hashCode()))
     	   {
     		// create a temp variable to be used to determine the remaining quantity
     		 int temp = i.quantity;  
     		 
     		 //remove the requested quantity from the first item in the queue( also the oldest)
     		 i.quantity -= remaningQuantity;
     		 
     		 //update remaining quantity
     		 remaningQuantity -= temp;
     		 //check if the item has been used up
     		 if (i.quantity < 1)
     		 {	 
                  UPCQueueMap.get(item.hashCode()).remove(i);
     		 		//***checking to see if queue size is zero because if it is
     		 		//need to remove it from the set
//     		 		if(UPCQueueMap.get(item.hashCode()).size()==0)
//     		 			{
//     		 			UPCQueueMap.remove(item.hashCode());
//     		 			//System.out.println(item.hashCode());
//     		 			}
     		 }	
     		 
     		 //check to see if request has been met, if so return
             if(remaningQuantity < 0 )
            	 
            	 return;
             }
        }
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
        for(Integer str : UPCQueueMap.keySet())
        	for (DatedItem<I> i : UPCQueueMap.get(str))
            System.out.printf ("Item: %-20s  Item Type: %-20s  Critical date: %13s     Quantity: %3s\n", i.item.getName(), i.item.getClass().getSimpleName(), formatter.format(i.criticalDate.getTime()), i.quantity);        
        System.out.println();
    }
    /**
     * This method returns a set of the foodItems in this particular set.
     * 
     * @return
     */
    public Set<FoodItem> getFoodItemSet(){
    	Set<FoodItem> foodItemSet = new HashSet<FoodItem>();//why does Tree set not work here?
    	for(Integer str : UPCQueueMap.keySet())
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
    
    public void removeExpiredItems2(GregorianCalendar cal)
//    {
//
//    	//loop through all the food items in this inventory
//    	Integer x =0;
//    	for(Integer str : UPCQueueMap.keySet())
//        	for (DatedItem<I> i : UPCQueueMap.get(str))
//        		if(i.criticalDate.equals(cal)){
//        			UPCQueueMap.get(str).remove(i);
//        		
//    	
//    	
//	}
// 
//}
  {

    	//loop through all the food items in this inventory
    	Integer x =0;
    	for(Integer str : UPCQueueMap.keySet())
    		
        		if(UPCQueueMap.get(str).peek()==null)
        			x=str;
        		else if(UPCQueueMap.get(str).peek().criticalDate.equals(cal)){
        			UPCQueueMap.get(str).removeFirst();
        			if(UPCQueueMap.get(str).size()==0)
 		 			{
 		 			x=str;
 		 			//System.out.println(item.hashCode());
 		 			}
        			
        		}
    	if(x!=0)
    		UPCQueueMap.remove(x);
    	
    	
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

