package homework03;

import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * This class is  tester class for Inventory
 * @author James Yeates
 *
 */
		

public class Tester {
	
	public static void main(String[] args) {
		
		////////testing addItem and getQuantity method from inventory class////////////
		
		Inventory<Item> inven1 = new Inventory<Item>();
		GregorianCalendar date1 = new GregorianCalendar(2014, 1, 15);
		GregorianCalendar date2 = new GregorianCalendar(2014, 1, 16);
		GregorianCalendar date3 = new GregorianCalendar(2014, 1, 17);
		GregorianCalendar date4 = new GregorianCalendar(2014, 1, 15);
		GregorianCalendar beforeDate = new GregorianCalendar(2014, 1, 14);
		GregorianCalendar afterDate = new GregorianCalendar(2014, 1, 18);
		GregorianCalendar nullDate = null;
		Item item1 = new Item("eggs");
		Item item2 = new Item("eggs");
		Item item3 = new Item("bacon");
		Item nullItem = null;
		MilkItem milkItem = new MilkItem("milk");
		CandyItem candyItem = new CandyItem("candy");
		
		////////////////////////////////////////////////////////////////////////////////
		////////testing addItem and getQuantity method from inventory class////////////
		//////////////////////////////////////////////////////////////////////////////
		
		//first test null arguments and quantity <=0
		inven1.addItem(nullItem, date1, 1);
		if(inven1.sizeOfDIList() != 0)//list should not add anything (maintain size 0)
			System.out.println(getLine());
		inven1.addItem(item1, nullDate, 1);
		if(inven1.sizeOfDIList() != 0)
			System.out.println(getLine());
		inven1.addItem(item1, date1, 0);
		if(inven1.sizeOfDIList() != 0)
			System.out.println(getLine());
		inven1.addItem(item1, date1, -1);
		if(inven1.sizeOfDIList() != 0)
			System.out.println(getLine());
		
		inven1.addItem(item1, date1, 1);
		
		if(inven1.sizeOfDIList() != 1)
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date1)!=1)
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date2)!=0)//item1 at date2 doesn't exist should it return 0?
			System.out.println(getLine());
		
		//add "equal" item
		inven1.addItem(item2, date1, 3);
		
		if(inven1.sizeOfDIList() != 1)//list size should remain the same
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date1)!=4)//quantity should change
			System.out.println(getLine());
		if(inven1.getQuantity(item2, date1)!=4)
			System.out.println(getLine());
		
		//add different item
		inven1.addItem(item3, date1, 5);
		if(inven1.sizeOfDIList() != 2)
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date1)!=4)
			System.out.println(getLine());
		if(inven1.getQuantity(item2, date1)!=4)
			System.out.println(getLine());
		if(inven1.getQuantity(item3, date1)!=5)
			System.out.println(getLine());
		
		// add subclasses....-these should work because inven1 is of type Item
		inven1.addItem(milkItem, date2, 6);
		if(inven1.sizeOfDIList() != 3)
			System.out.println(getLine());
		if(inven1.getQuantity(milkItem, date1)!=0)
			System.out.println(getLine());
		if(inven1.getQuantity(milkItem, date2)!=6)
			System.out.println(getLine());
		
		inven1.addItem(candyItem, date2, 7);
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		if(inven1.getQuantity(candyItem, date2)!=7)
			System.out.println(getLine());
		
		//create Inventory of subclasses,  check addItem and getQuantity  
		Inventory<MilkItem> milkInv = new Inventory<MilkItem>();
		//these should cause compiler errors un-comment to check.
		//milkInv.addItem(candyItem, date2, 2);
		//milkInv.addItem(item1, date2, 2);
		milkInv.addItem(milkItem, date2, 2);
		if(milkInv.sizeOfDIList() != 1)
			System.out.println(getLine());
		//if(milkInv.getQuantity(item1, date1)!=4)//should cause compiler error un-comment to check
		if(milkInv.getQuantity(milkItem, date2)!=2)
			System.out.println(getLine());
		
		///////////////////////////////////
		////Test removeItem and getQuantity method ////////
		///////////////////////////////////
		
		//Test for removing null items and dates and quantity<=0
		inven1.removeItem(candyItem, nullDate, 7);
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		inven1.removeItem(nullItem, date1, 7);
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		inven1.removeItem(item1, date1, -1);
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date1)!=4)
			System.out.println(getLine());
		
		inven1.removeItem(candyItem, date3, 3);//what happens when you remove an item that isn't there..Nothing?
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		if(inven1.getQuantity(candyItem, date2)!=7)
			System.out.println(getLine());
		
		//remove some quantity but not all
		inven1.removeItem(candyItem, date2, 3);
		if(inven1.sizeOfDIList() != 4)
			System.out.println(getLine());
		if(inven1.getQuantity(candyItem, date2)!=4)
			System.out.println(getLine());
		
		//remove  all, should remove item from list
		inven1.removeItem(candyItem, date2, 4);
		if(inven1.sizeOfDIList() != 3)
			System.out.println(getLine());
		if(inven1.getQuantity(candyItem, date2)!=0)
			System.out.println(getLine());
		
		//remove more quantity then you actually have should remove item from list
		inven1.removeItem(milkItem, date2, 56);
		if(inven1.sizeOfDIList() != 2)
			System.out.println(getLine());
		if(inven1.getQuantity(milkItem, date2)!=0)
			System.out.println(getLine());
		
		//milkInv.removeItem(item1, date3, 3); //should cause compiler error item1 is of type Item.
		//milkInv.removeItem(candyItem, date3, 3); // should cause compiler error item1 is of type candyItem.
		
		milkInv.removeItem(milkItem, date2, 0);//
		if(milkInv.sizeOfDIList() != 1)
			System.out.println(getLine());
		if(milkInv.getQuantity(milkItem, date2)!=2)
			System.out.println(getLine());
		
		milkInv.removeItem(milkItem, date2, 3);//
		if(milkInv.sizeOfDIList() != 0)
			System.out.println(getLine());
		if(milkInv.getQuantity(milkItem, date2)!=0)
			System.out.println(getLine());
		
		/////////////////////////////
		///Testing getDate/////////
		///////////////////////////
		
		GregorianCalendar notInList = new GregorianCalendar(2015, 5, 30);
		
		inven1.addItem(item1, date4, 1);
		if(inven1.getQuantity(item1, date1)!=5)
			System.out.println(getLine());
		if(inven1.getQuantity(item1, date4)!=5)
			System.out.println(getLine());
		
		if(inven1.getDate(new Item(""))!= null)
			System.out.println(getLine());
		if(inven1.getDate(item1).equals(notInList))
			System.out.println(getLine());
		if(!(inven1.getDate(item1).equals(date1)))//check to see if it returns older item
			System.out.println(getLine());
		if(inven1.getDate(item1).equals(date2))
			System.out.println(getLine());
		
		
		////////////////////////////////////////
		/////Testing getItemsPastDate//////////
		//////////////////////////////////////
		
		ArrayList<Item> arrayOfItems = new ArrayList<Item>();
		arrayOfItems.add(item1);
		arrayOfItems.add(item2);
		arrayOfItems.add(item3);
		
		Inventory<Item> inven2 = new Inventory<Item>();
		inven2.addItem(item1, date1, 1);
		inven2.addItem(item1, date2, 1);
		inven2.addItem(item1, date3, 1);
		ArrayList<Item> arrayOfItems1 = inven2.getItemsPastDate(afterDate);
		if(arrayOfItems1.size()!=3)
			System.out.println(getLine()+" "+ arrayOfItems1.size());
		ArrayList<Item> arrayOfItems2 = inven2.getItemsPastDate(beforeDate);
		if(arrayOfItems2.size()!=0)
			System.out.println(getLine()+" "+ arrayOfItems1.size());
		//ArrayList<MilkItem> h= inven2.getItemsPastDate(afterDate);//should be a complier error
		
		
			
		
		
		
		
		System.out.println("testing done");
		
		System.exit(0);
		
		Inventory<Item> list = new Inventory<Item>();
		Item milk = new Item("milk");
		Item milka = new Item("milk");
		list.addItem(milk, new GregorianCalendar(2014, 6, 27), 4);
		list.addItem(milka, new GregorianCalendar(2014, 6, 27), 4);
		list.addItem(milk, new GregorianCalendar(2014, 2, 27), 4);
		

		System.out.println(list.getQuantity(milk, new GregorianCalendar(2014, 6, 27)));
		System.out.println(list.getQuantity(milk, new GregorianCalendar(2014, 1, 27)));
		list.removeItem(milk, new GregorianCalendar(2014, 2, 27), 4);
		
		
		
	
	}
	
    /** 
	 * This helper method prints the line it was called on
	 * @return  - Current line number.
	 */ 
	public static String getLine() {
	    return "Falied at line: " +(Thread.currentThread().getStackTrace()[2].getLineNumber()-1);
	}


}
