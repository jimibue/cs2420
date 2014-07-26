package homework01;
/**
 * 
 * @author James Yeates
 *
 */

public class SelectionSortExperiment2 {
	
	public static void main(String[] args) {
		System.out.println("James Yeates");
		System.out.println("Assigment #1\n");
		
		System.out.println("n     |   count(n)");
		System.out.println("------|-----------");
		double [] arrZero = makeRandomArray(1);
		findAverage(arrZero);
		double [] arrOne = makeRandomArray(50);
		findAverage(arrOne);
		double [] arrTwo = makeRandomArray(100);
		findAverage(arrTwo);
		double [] arrThree = makeRandomArray(150);
		findAverage(arrThree);
		double [] arrFour = makeRandomArray(200);
		findAverage(arrFour);
		double [] arrFive = makeRandomArray(250);
		findAverage(arrFive);
		double [] arrSix = makeRandomArray(300);
		findAverage(arrSix);
		double [] arrSeven = makeRandomArray(350);
		findAverage(arrSeven);
		double [] arrEight = makeRandomArray(400);
		findAverage(arrEight);
		double [] arrNine = makeRandomArray(450);
		findAverage(arrNine);
		double [] arrTen = makeRandomArray(500);
		findAverage(arrTen);
		
//		double [] arrZero = makeRandomArray(1);
//		findAverage(arrZero);
//		double [] arrOne = makeRandomArray(2);
//		findAverage(arrOne);
//		double [] arrTwo = makeRandomArray(4);
//		findAverage(arrTwo);
//		double [] arrThree = makeRandomArray(8);
//		findAverage(arrThree);
//		double [] arrFour = makeRandomArray(16);
//		findAverage(arrFour);
//		double [] arrFive = makeRandomArray(32);
//		findAverage(arrFive);
//		double [] arrSix = makeRandomArray(64);
//		findAverage(arrSix);
//		double [] arrSeven = makeRandomArray(128);
//		findAverage(arrSeven);
//		double [] arrEight = makeRandomArray(256);
//		findAverage(arrEight);
//		double [] arrNine = makeRandomArray(512);
//		findAverage(arrNine);
//		double [] arrTen = makeRandomArray(1024);
//		findAverage(arrTen);
//		double [] arrTen1 = makeRandomArray(2048);
//		findAverage(arrTen1);
//		double [] arrTen12 = makeRandomArray(4096);
//		findAverage(arrTen12);
		
		
		
		
		
		System.exit(0);
		
		double [] d = makeRandomArray(4);
		for(int i = 0; i< d.length; i++)
			System.out.println(d[i]);
		System.out.println("========");
		
		int s = selectionSortExperiment(d);
		for(int i = 0; i< d.length; i++)
			System.out.println(d[i]);
		System.out.println("========");
		System.out.println(s);
		
		double [] d1 = makeRandomArray(0);
		
		for(int i = 0; i< d1.length; i++)
			System.out.println(d1[i]);
		
		
	}
	
	/** Returns an array of doubles filled with random values in the
	  * range [0...1).
	  *
	  * The caller specifies a non-negative n to indicate the size of
	  *   the array.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  n  The desired size of an array.
	  * @return    An array of n doubles filled with random values
	  */
	static public double[] makeRandomArray (int n){
		
		double [] arrayOfDoubles = new double[n];
		for(int i = 0; i < n; i++){
			arrayOfDoubles[i] = Math.random(); 
		}
		return arrayOfDoubles;
	}
	/** Returns a count of how many times selection sort finds a
	  * 'better' value during the sorting process.  The input array
	  * is sorted as a side effect.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  data  An array of double values
	  * @return       A count of how many times 'better' values were found
	  */
	static public int selectionSortExperiment (double[] data){
		
		int swapCount =0;
		
		//Set size = size of array
		int size = data.length;
		//Loop a 'first position' value from 0 to size-2 inclusive
		for(int fp =0; fp <= size-2; fp++){
			//Set a 'best position' value = first position
			int bestIndex = fp;
			//Loop a 'current position' value from first position+1 to size-1 inclusive
			for(int cp = fp+1; cp< size; cp++){
				if(data[cp]<data[bestIndex]){
					bestIndex = cp;
					swapCount++;
					
					
					 
				}
			}
			double temp = data[fp];
			data[fp] = data[bestIndex];
			data[bestIndex] = temp; 

		}
		return swapCount;
		
	}
	
	/**
	 * 
	 * @param data
	 */
	
	public static void findAverage(double[] data){
		double count = 0.0;
		for ( int i = 0; i< 1000; i++){
			data = makeRandomArray(data.length);
			count += selectionSortExperiment(data);
			
		}
		count /= 1000;
		//System.out.println(data.length + "      "+count);
		System.out.format("%-4d  |  %.2f\n", data.length, count);
	}

}
