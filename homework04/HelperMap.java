package homework04;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * This class is going to help organize a map that maps upc codes to another 
 * map the maps dates with quantites
 * @author James Yeates
 *
 */

public class HelperMap {
	
	
	protected Map<String,TreeMap<GregorianCalendar,Integer>> quickSearch;
	protected int daysSinceStartDate;
	
	public HelperMap()
	{
		
		quickSearch = new TreeMap<String,TreeMap<GregorianCalendar,Integer>>();
		daysSinceStartDate =0;
		
	}
	
	/**
	 * Adds a food item to the map, creates a new date map if needed. if date maps exists then the quantity is added.
	 * @param upcCode a String
	 * 
	 * @param criticalDate GregorianCalendar
	 * @param quantity an integer
	 */
	
	public void addItem(String upcCode, GregorianCalendar criticalDate, int quantity)
	{
		
		if (upcCode == null || criticalDate == null || quantity < 1)
            return;
	       if(quickSearch.containsKey(upcCode)){
	    	   if(quickSearch.get(upcCode).containsKey(criticalDate)){
	    		  int temp = quickSearch.get(upcCode).get(criticalDate).intValue()+ quantity;
	    		  quickSearch.get(upcCode).put(criticalDate, temp);
	    	   }
	    	   
	    	   else 
	    		   quickSearch.get(upcCode).put(criticalDate, quantity);
	       }
	       else
	       {
	    	   quickSearch.put(upcCode,new TreeMap<GregorianCalendar,Integer> ());
	    	   quickSearch.get(upcCode).put(criticalDate, quantity);
	       }
	}
	
	
	public void removeItem(String upcCode, GregorianCalendar criticalDate, int quantity)
	{
		
		 if (upcCode == null || criticalDate == null || quantity < 1)
	            return;
		//get the value for tha item
        int amount = quickSearch.get(upcCode).get(criticalDate).intValue();
        //remove all more has been asked for than that is available
        if (amount < quantity)
        	quickSearch.get(upcCode).remove(criticalDate);
        else
        {
        	int newAmount = amount-quantity;
        	quickSearch.get(upcCode).put(criticalDate, newAmount);
        	
        }
	}
	public void removeExpiredItems(GregorianCalendar date)
	{
		Set<String> set = quickSearch.keySet();
		Set<GregorianCalendar> datesToRemove = new TreeSet<GregorianCalendar>();
		
		//loop throught each upcCode
		for(String s : set){
			Set<GregorianCalendar> dateSet = quickSearch.get(s).keySet();
			//loop through dates
			for(GregorianCalendar dates : dateSet){
				if(dates.equals(date)){
					quickSearch.get(s).remove(dates);
					break;
				}
					
			}
		}
	}
	
	public void itemRequested(String upcCode, int requestedQuantity,Set<GregorianCalendar> datesSet){
		for(GregorianCalendar date : datesSet)
			
		{
			
			
				if(requestedQuantity<1)
					break;
				if(quickSearch.get(upcCode)==null)
					continue;
				if(quickSearch.get(upcCode).get(date)==null)
					continue;
				int tempAmount =quickSearch.get(upcCode).get(date).intValue();
				if(tempAmount>requestedQuantity){
					tempAmount -= requestedQuantity;
					requestedQuantity=0;
					quickSearch.get(upcCode).put(date, tempAmount);
				}
				else{
					requestedQuantity -=tempAmount;
					quickSearch.get(upcCode).remove(date);
					
				}
			
		}
	}

}
