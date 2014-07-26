package homework05;

/**
 * this class contains methods and code to perform experiments for Question 6
 * uses ints [0-9)
 * 
 * @author James Yeates
 *
 */

public class Arraylists {
	
	int[] data;
	int count;
	int growthCount;
	int copy;
	
	public Arraylists(){
		data= new int[10];
		count=0;
		growthCount=0;
		int copy = 0;
		
	}
	
	
	/**
	 * Adds a string at the end of the Dynamic Array.  If the Array is not big enough
	 * a new array twice the original size is made with original values copied into it
	 * @param s the String to be added
	 */
	public void add(int n) {
		
		//if array is full make a new array twice the size and copy values into it
		if( count == data.length){
			growthCount++;
			int []tempData = new int[count*3];
			for(int i =0; i< data.length; i++){
				tempData[i]= data[i];
				copy++;
			}
			
			data = tempData;
			data[count]=n;
			
		}
		data[count] = n;
		copy++;
		count++;
	}


public void add1000(int n) {
	
	//if array is full make a new array twice the size and copy values into it
	if( count == data.length){
		
		growthCount++;
		int []tempData = new int[count+1000];
		for(int i =0; i< data.length; i++){
			tempData[i]= data[i];
			copy++;
		}
		
		data = tempData;
		data[count]=n;
		
	}
	data[count] = n;
	count++;
}
public void add10per(int n) {
	
	//if array is full make a new array twice the size and copy values into it
	if( count == data.length){
		
		//System.out.println(count);
		growthCount++;
		int []tempData = new int[count+(int)(count*.1)];
		for(int i =0; i< data.length; i++){
			tempData[i]= data[i];
			copy++;
		}
		
		data = tempData;
		data[count]=n;
		
	}
	data[count] = n;
	count++;
}

}
