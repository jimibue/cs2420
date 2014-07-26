package homework03;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This class represent an inventory of a class or sub class of Items.
 * 
 * @author James Yeates
 *
 * @version 1/24/2014
 */


public class Inventory<I extends Item> {
	
	protected List<DatedItem<I>> list;//should this be List<DatedItem<Item>> or List<DatedItem<I>>????????????
	
	public Inventory(){
		list = new ArrayList<DatedItem<I>>();
		
	}
	
	/**
	 *  adds the item to the inventory with the specified critical date and quantity. Item
	 *  and date must be non null, if item already exists with the specified critical date
	 *   in the inventory, the quantity for that item (and date) will be increased by the appropriate amount.	
	 *
	 * @param item item to added, must be non- null
	 * @param criticalDate Date must be non-null, the date at which item is due/expired/etc
	 * @param quantity the amount must be positive integer
	 */
	
	public void addItem(I item, GregorianCalendar criticalDate, int quantity){
		
		//if null input is given or quantity <= 0 return (don't do anything)
		if(item == null|| criticalDate==null|| quantity <=0)
			return;
		
		boolean isInList = false;
		
		for(DatedItem<I> i: list){
			//check to see if items and dates are equal
			if(i.getItem().equals(item) && criticalDate.equals(i.getDate())){
				i.setQuantity(i.getQuantity()+ quantity);
				isInList= true;
				
			}
		}
		if(!(isInList)){
			DatedItem<I> i = new DatedItem<I>(item,criticalDate, quantity);
			list.add(i);
		}
		
	}
	/**
		
	 *This method will reduce the quantity of an item (specified by item object and date) in the inventory
	 * by the specified quantity.  If the item's quantity is reduced to or below 0, the item is removed from 
	 * the inventory.  If the quantity is not positive, or if the item or date is null, no action is taken.
	 * 
	 * @param item item to removed, must be non- null
	 * @param criticalDate Date must be non-null, the date at which item is due/expired/etc
	 * @param quantity the amount
	 */
	
	public void removeItem(I item, GregorianCalendar criticalDate, int quantity){
		
		//if null input is given or quantity <= 0 return (don't do anything)
		if(item == null|| criticalDate==null|| quantity <=0)
			return;
		
		for(DatedItem<I> i: list){
			//check to see if items and dates are equal
			if(i.getItem().equals(item) && criticalDate.equals(i.getDate())){
				i.setQuantity(i.getQuantity()-quantity);
				if(i.getQuantity()<=0)
					list.remove(i);
				break;
			}
				
		}
		
	}
	/**
	 * This method returns the quantity of this item (with the specified critical date) in the inventory. 
	 * 
	 * @param item the item to be searched
	 * @param criticalDate the date item critical date occurs
	 * @return
	 */
	public int getQuantity(I item, GregorianCalendar criticalDate){
		
		//go through the list to see if item/date is in Inventory
		for(DatedItem<I> i: list){
			if(i.getItem().equals(item) && criticalDate.equals(i.getDate())){
				return i.getQuantity();
				}
			}
		//if it is not in the list or doesn't match the date there is none
		return 0;
	}
	/**
	 * This method returns the critical date for the specified item in the inventory.  If multiple
	 *  matching items are in the inventory with different critical dates, the oldest critical date 
	 *  for that kind of item is returned.  If no such item is found in the inventory, null is returned.
	 * @param I Item
	 * @return the critical date for the specified item in the inventory. 
	 */
	
	public GregorianCalendar getDate(I item){
		GregorianCalendar date= null;
		
		for(DatedItem<I> i: list){
			if(i.getItem().equals(item))
				if(date==null|| i.getDate().before(date))
					date = i.getDate();
				
			}
		return date;
	}
	/**
	 * This method returns an ArrayList of items whose critical date is before the target date.
	 * @param targetDate
	 * @return ArrayList of items whose critical date is before the target date
	 */
	
	public ArrayList<I> getItemsPastDate(GregorianCalendar targetDate){
		
		ArrayList<I> itemsPastDate = new ArrayList<I>();
		
		for(DatedItem<I> i: list){
			if((i.getDate()).before(targetDate)){
				I temp = i.getItem();
				itemsPastDate.add(temp);
			}
		}
		
		return itemsPastDate;
		
	}
	/**
	 * this helper method returns the size of the ArrayList for this Inventory
	 * ..used for testing purposes.
	 * @return size of arrayList
	 */
	
	public int sizeOfDIList(){
		return list.size();
	}
}
