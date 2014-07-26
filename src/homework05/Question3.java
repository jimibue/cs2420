package homework05;

/**
 * this class contains methods and code to perform experiments for Question 3
 * 
 * 
 * @author James Yeates
 *
 */

public class Question3 {
	
	long comparisonCounter;
	
	public Question3(){
		comparisonCounter = 0;
	}
	
	/**
	 * does the first part of question #1 with an array of doubles from size [0-20)
	 */
	
	public void doExpirment3()
	{
		
		
		//this first loop controls the size of the array
		for(int i =0; i<=20; i++)
		{
			comparisonCounter=0;
			int tries = 100000;
			long startTime = System.currentTimeMillis();
			
			for(int j = 0; j< tries; j++)
			{
				double [] arrayOfDoubles=createRandomArray(i);
				
				quicksort(arrayOfDoubles);
				
				assertSorted(arrayOfDoubles);
			}
			long endTime = System.currentTimeMillis();
			System.out.print (i);
            System.out.print ("\t");
            System.out.print (comparisonCounter * 1.0 / tries +" "/*+(endTime-startTime)/1000+" secs"*/);
            System.out.println ();
           // System.out.println((endTime-startTime)+" secs");
		}
		
	}
	
	
	public void doExpriment3a()
	{
		
		
		//this first loop controls the size of the array
		for (int i = 1; i <= Math.pow(2,16); i=i*2)
		{
			comparisonCounter=0;
			int tries = 10000;
			long startTime = System.currentTimeMillis();
			for(int j = 0; j< tries; j++)
			{
				double [] arrayOfDoubles=createRandomArray(i);
				quicksort(arrayOfDoubles);
				assertSorted(arrayOfDoubles);
			}
			long endTime = System.currentTimeMillis();
			System.out.print (i);
            System.out.print ("\t");
            System.out.print (comparisonCounter * 1.0 / tries +" "/*+(endTime-startTime)/1000.0+" secs"*/);
            System.out.println ();
		}
		
	}
	
	/**
	 * Wrapper method for quicksort
	 * @param d an array of doubles
	 */
	
	public void quicksort(double []d)
	{
		quicksortSubArray( d, 0, d.length-1);
	}
	
	/**
	 * this method takes an array checks for size greater than 1 than breaks the array into two parts
	 * @param d an array of doubles
	 * @param start the start of the array
	 * @param end the end of the array
	 */
	
	public  void quicksortSubArray(double []d, int start, int end)
	{
		//check for base case
		if(end-start < 1)
			return;
		
		//break array in to two and use quicksort recursively to sort arrays
		int mid = partition(d,start,end);
		quicksortSubArray(d, start, mid-1);//first part
		quicksortSubArray(d,mid+1,end);
				
	}
	
	/**
	 * this method does most of the work for the quicksort.  It picks the end element as the
	 * pivot and arranges the other values less than the pivot to the left side and those greater
	 * or equal to the right. It then place the pivot in the middle and returns the new start/end positions.
	 * 
	 * @param d an array of doubles
	 * @param start an integer
	 * @param end an integer
	 * @return the new mid position
	 */
	public  int partition(double []d, int start, int end)
	{
		double pivot = d[end];
		int left = start;
		int right = end-1;
		
		while(true)
		{//?
			while(smartCounter(d[left]<pivot))
				left++;
			while(right>left && smartCounter(d[right]>pivot))//This is the only change made here
				right --;
			//check to see if left marker is past right
			if(left>=right)
				break;
			//swap
			double temp = d[left];
			d[left]=d[right];
			d[right]=temp;
			left ++;
			right--;
			
			
			
		}
		//?????
		double temp = d[left];
		d[left]=d[end];
		d[end]=temp;
		return left;
		
	}
	
	
	/**
	 * method is used to count the amount of comparisons returns its own boolean value
	 * @param b a boolean 
	 * @return a boolean 
	 */
	
	public boolean smartCounter(boolean b)
	{
		 comparisonCounter++;
		 return b;
	}
	
	/**
	 * Takes an array of doubles and checks to see if it sorted(in ascending order)
	 * throws a runtime exception if the array is not sorted
	 * 
	 * @param data an array of doubles
	 * @throws RuntimeException
	 */
	
	public static void assertSorted(double [] data) throws RuntimeException
	{
		for( int i = 0; i < data.length-1; i++)
			if(data[i]>data[i+1])
				throw new RuntimeException("error: NOT SORTED");
	}

	
	/**
	 * Creates an random array of doubles of size n with the values [0,1)
	 * 
	 * @param n an integer
	 * @return an array of doubles 
	 */
	
	public static double[] createRandomArray(int n)
	{
		double [] temp = new double[n];
		
		//fill the array with random double [0-1)
		for(int i = 0; i < n; i ++)
			temp[i]=(int) (Math.random()*10);
		return temp;
			
	}
	

}