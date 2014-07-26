package homework01;
/**
 * 
 * @author James Yeates
 *
 */

public class SelectionSortExperiment {
	
	public static void main(String[] args) {
		System.out.println("James Yeates");
		System.out.println("Assigment #1\n");
		
		System.out.println("n     |   count(n)");
		System.out.println("------|-----------");

		// make the arrays find the average number of switches.
		double [] arrZero = makeRandomArray(1);
		findAverage(arrZero);
		double [] arrOne = makeRandomArray(2);
		findAverage(arrOne);
		double [] arrTwo = makeRandomArray(4);
		findAverage(arrTwo);
		double [] arrThree = makeRandomArray(8);
		findAverage(arrThree);
		double [] arrFour = makeRandomArray(16);
		findAverage(arrFour);
		double [] arrFive = makeRandomArray(32);
		findAverage(arrFive);
		double [] arrSix = makeRandomArray(64);
		findAverage(arrSix);
		double [] arrSeven = makeRandomArray(128);
		findAverage(arrSeven);
		double [] arrEight = makeRandomArray(256);
		findAverage(arrEight);
		double [] arrNine = makeRandomArray(512);
		findAverage(arrNine);
		double [] arrTen = makeRandomArray(1024);
		findAverage(arrTen);
	

		
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
		for(int firstPostion =0; firstPostion<= size-2; firstPostion++){
			//Set a 'best position' value = first position
			int bestPostion = firstPostion;
			//Loop a 'current position' value from first position+1 to size-1 inclusive
			for(int currentPostion = firstPostion+1; currentPostion< size; currentPostion++){
				if(data[currentPostion]<data[bestPostion]){
					bestPostion = currentPostion;
					swapCount++;
				}
			}
			//swap 
			double temp = data[bestPostion];
			data[bestPostion] = data[firstPostion];
			data[firstPostion] = temp;
		}
		return swapCount;
		
	}
	
	/**
	 * This helper method finds the average amount of swaps
	 * by finding the average amount of swaps from 5000
	 * randomly created arrays. 
	 * @param data array of doubles
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