package homework04;

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
	
	@Test
	public void test5() {
		
		Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		FoodItem foodItem =App1.makeFoodItem(s);
		Scanner s1 = new Scanner("- UPC Code: 0984523913  Shelf life: 1  Name: orange");
		FoodItem foodItem1 =App1.makeFoodItem(s1);
		Scanner s2 = new Scanner("date: 05/01/2010");
		GregorianCalendar date =App1.getDate(s2);
		Scanner s3 = new Scanner("date: 05/04/2010");
		GregorianCalendar date1 =App1.getDate(s3);
		
		Inventory<FoodItem> inv = new Inventory<FoodItem>();
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		
		
		//inv.outputToConsole("Some city");
		
//		Contents of Inventory: Some city
//		--------------------------------
//		Item: orange                Item Type: FoodItem       Critical date:   May 1, 2010     Quantity:   2
//		Item: orange                Item Type: FoodItem         Critical date:   May 4, 2010     Quantity:   2
//		Item: orange fat sauce      Item Type: FoodItem          Critical date:   May 1, 2010     Quantity:   2
//		Item: orange fat sauce      Item Type: FoodItem         Critical date:   May 4, 2010     Quantity:   2
//		
//		
	}
	
	@Test
	public void test6() {
		
		Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		FoodItem foodItem =App1.makeFoodItem(s);
		Scanner s1 = new Scanner("- UPC Code: 0984523913  Shelf life: 1  Name: orange");
		FoodItem foodItem1 =App1.makeFoodItem(s1);
		Scanner s2 = new Scanner("date: 05/01/2010");
		GregorianCalendar date =App1.getDate(s2);
		Scanner s3 = new Scanner("date: 05/04/2010");
		GregorianCalendar date1 =App1.getDate(s3);
		
		Inventory<FoodItem> inv = new Inventory<FoodItem>();
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		
//		inv.removeMultipleItems(foodItem, 3);
//		
//		inv.outputToConsole("Some city");
//		
//		inv.removeMultipleItems(foodItem1, 3);
//		
//		inv.outputToConsole("Some city");
//		
//		inv.removeMultipleItems(foodItem, 3);
//		
//		inv.outputToConsole("Some city");
//		
//		inv.removeMultipleItems(foodItem1, 3);
//		
//		inv.outputToConsole("Some city");
//		
		
	
	}
	
	@Test
	public void test7() {
		
		Scanner s = new Scanner("- UPC Code: 0984523912  Shelf life: 0  Name: orange fat sauce");
		FoodItem foodItem =App1.makeFoodItem(s);
		Scanner s1 = new Scanner("- UPC Code: 0984523913  Shelf life: 1  Name: orange");
		FoodItem foodItem1 =App1.makeFoodItem(s1);
		Scanner s2 = new Scanner("date: 05/01/2010");
		GregorianCalendar date =App1.getDate(s2);
		Scanner s3 = new Scanner("date: 05/05/2010");
		GregorianCalendar date1 =App1.getDate(s3);
		
		Inventory<FoodItem> inv = new Inventory<FoodItem>();
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem1, date, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		inv.addItem(foodItem1, date1, 1);
		
		inv.outputToConsole("Some city");
		inv.removeExpiredItems1(date);
		inv.outputToConsole("Some city");
	
	}
	

}
