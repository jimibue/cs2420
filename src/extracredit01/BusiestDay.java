package extracredit01;
/**
 * This class helps create an object to keep track of the busiest day
 * 
 * 
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * This class helps create an object to keep track of the busiest day
 * 
 * @author James Yeates and Logan Ropelato
 *
 */

public class BusiestDay {
	
	protected int currentActivity;
	protected int busiestActivity;
	protected GregorianCalendar busiestDay;
	protected String cityName;
	
	/**
	 * increments the current activity for the day
	 * @param n an integer
	 */
	public  void incrementActivity(int n){
		
		currentActivity += n;
		
	}
	/**
	 * Constructor
	 * @param s a String
	 */
	public BusiestDay(String s)
	{
		busiestDay = getnewDate((WarehouseReport.currentDate));
		currentActivity = 0;
		busiestActivity = 0;
		this.cityName =s;
	}
	
	/**
	 * Checks to see if this day is has had the most transactions
	 */
	
	public void checkForBusiestDay(){
		
		if(currentActivity >= busiestActivity){
			busiestDay = getnewDate(WarehouseReport.currentDate);
			busiestActivity = currentActivity;
		}
		
		currentActivity = 0;
	
	}
	/**
	 * reset the current activity for next day
	 */
	public void resetCurrentActivity()
	{
		currentActivity = 0;
	}
	
	/**
	 * prints out the information need for the report
	 */
	public void printBusiestDayInfo()
	{
		if(busiestDay.get(busiestDay.MONTH)+1<10 && busiestDay.get(busiestDay.DAY_OF_MONTH)<10)
		{
			System.out.println(cityName+ " " +"0"+ (busiestDay.get(busiestDay.MONTH)+1)+"/0"+busiestDay.get(busiestDay.DAY_OF_MONTH)+"/"+
					busiestDay.get(busiestDay.YEAR)+" " + busiestActivity);
			
		}
		else if(busiestDay.get(busiestDay.MONTH)+1<10)
		{
			System.out.println(cityName+ " " +"0"+ (busiestDay.get(busiestDay.MONTH)+1)+"/"+busiestDay.get(busiestDay.DAY_OF_MONTH)+"/"+
					busiestDay.get(busiestDay.YEAR)+" " + busiestActivity);
			
		}
		else if(busiestDay.get(busiestDay.DAY_OF_MONTH)<10)
		{
			System.out.println(cityName+ " " + (busiestDay.get(busiestDay.MONTH)+1)+"/0"+busiestDay.get(busiestDay.DAY_OF_MONTH)+"/"+
					busiestDay.get(busiestDay.YEAR)+" " + busiestActivity);
			
		}
		else
		System.out.println(cityName+ " " + (busiestDay.get(busiestDay.MONTH)+1)+"/"+busiestDay.get(busiestDay.DAY_OF_MONTH)+"/"+
				busiestDay.get(busiestDay.YEAR)+" " + busiestActivity);
	}
	
	/**
	 * This method decrements the day by one.  This was need to get the date of the busiest day
	 * right without affecting the processing of the other data
	 * @param manufactureDate the date to be incremented
	 * @return GregorianCalendar
	 */
	  public static GregorianCalendar getnewDate(GregorianCalendar manufactureDate)
	    {
	        GregorianCalendar result = (GregorianCalendar) manufactureDate.clone();
	        result.add(Calendar.DAY_OF_MONTH, -1);
	        return result;
	    }

	

}
