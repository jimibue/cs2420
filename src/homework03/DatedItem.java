package homework03;

import java.util.GregorianCalendar;
/**
 * This class helps organize Item in the Inventory class
 * 
 * @author James Yeates
 *
 */
		

public class DatedItem<T extends Item>{
	
	private T item;
	private GregorianCalendar date;
	private int quantity;
	
	
	public DatedItem(T item, GregorianCalendar date, int quantity){
		
		this.item = item;
		this.quantity = quantity;
		this.date = date;
		
	}
	
	public T getItem() {
		return item;
	}


	public void setItem(T item) {
		this.item = item;
	}


	public GregorianCalendar getDate() {
		return date;
	}



	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}
