package homework04.copy;

import java.util.GregorianCalendar;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class App1Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * this test makeFoodItem in the app1 class
	 */

	@Test
	public void test() {
		
		Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 3  Name: orange");
		FoodItem foodItem =App1.makeFoodItem(s);
		Assert.assertEquals(foodItem.getUpcCode(), "0984523912");
		Assert.assertEquals(foodItem.name, "orange");
		Assert.assertEquals(foodItem.shelfLife, 3);
		
	}
	/**
	 * this test makeFoodItem in the app1 class
	 */

	@Test
	public void test1() {
		
		Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		FoodItem foodItem =App1.makeFoodItem(s);
		Assert.assertEquals(foodItem.getUpcCode(), "0984523912");
		Assert.assertEquals(foodItem.name, "orange fat sauce");
		Assert.assertEquals(foodItem.shelfLife, 0);
		
	}
	/**
	 * testing  getDates and getNewDates
	 */
	
	@Test
	public void test2() {
		
		Scanner s = new Scanner("date: 05/01/2010");
		GregorianCalendar date =App1.getDate(s);
		GregorianCalendar date1 = new GregorianCalendar(2010, 04, 01);
		Assert.assertEquals(date, date1);
		
		s = new Scanner("date: 01/01/2010");
		GregorianCalendar date2 =App1.getDate(s);
		GregorianCalendar date3 = new GregorianCalendar(2010, 00, 01);
		Assert.assertEquals(date2, date3);
		
		s = new Scanner("date: 12/31/2010");
		GregorianCalendar date4 =App1.getDate(s);
		GregorianCalendar date5 = new GregorianCalendar(2010, 11, 31);
		Assert.assertEquals(date4, date5);
		
		GregorianCalendar date6 =App1.getnewDate(date4);
		GregorianCalendar date7 = new GregorianCalendar(2011, 00, 01);
		Assert.assertEquals(date6, date7);
		
		
	}
	
	@Test
	/**
	 * testing when keyword is food item
	 */
	public void test3() {
		
		String fileName = "data1a.txt";
		//Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		App1 test = new App1(fileName);
		Assert.assertEquals(test.report.upcMap.get("0984523912").getUpcCode(), "0984523912");
		
		Assert.assertEquals(test.report.upcMap.get("0984523912").name, "orange sauce");
		Assert.assertEquals(test.report.upcMap.get("0984523912").shelfLife, 3);
		
		Assert.assertEquals(test.report.upcMap.get("0984523911").getUpcCode(), "0984523911");
		
		Assert.assertEquals(test.report.upcMap.get("0984523911").name, "orange");
		Assert.assertEquals(test.report.upcMap.get("0984523911").shelfLife, 1);
		
		Assert.assertTrue(test.report.upcSet.contains("0984523911"));
		Assert.assertTrue(test.report.upcSet.contains("0984523912"));
		
		Assert.assertTrue(test.report.foodItemSet.contains(test.report.upcMap.get("0984523911")));
		Assert.assertTrue(test.report.foodItemSet.contains(test.report.upcMap.get("0984523912")));
		
		
		
	}
	
	@Test
	/**
	 * testing when keyword 'warehouse'
	 */
	public void test4() {
		
		String fileName = "data1a.txt";
		//Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		App1 test = new App1(fileName);
		Assert.assertTrue(test.report.warehouseInventoryMap.containsKey("Slat"));
		Assert.assertTrue(test.report.warehouseInventoryMap.containsKey("Columbus City"));
		Assert.assertFalse(test.report.warehouseInventoryMap.containsKey("Columbus Citys"));
		
		
		
		
		
	}
	
	

}
