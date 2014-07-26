package homework05;

public class Question6 {
	
	long comparisonCounter;
	
	public Question6(){
		comparisonCounter = 0;
	}
	
	/**
	 * does the first part of question #1 with an array of doubles from size [0-20)
	 */
	
	public void doExpirment6()
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
            System.out.print (comparisonCounter * 1.0 / tries);
            System.out.println ();
           // System.out.println((endTime-startTime)+" secs");
		}
		
	}
	
	
	public void doExpriment6a()
	{
		
		
		//this first loop controls the size of the array
		for (int i = 1; i <= Math.pow(2,20); i=i*2)
			
		{
			long startTime = System.currentTimeMillis();
			comparisonCounter=0;
			int tries = 50;
			for(int j = 0; j< tries; j++)
			{
				double [] arrayOfDoubles=createRandomArray(i);
				quicksort(arrayOfDoubles);
				assertSorted(arrayOfDoubles);
			}
			long endTime = System.currentTimeMillis();
			System.out.print (i);
            System.out.print ("\t");
            System.out.print (comparisonCounter * 1.0 / tries+" "+ (endTime-startTime)/1000.0+" secs");
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
		
		else if(end - start<8)//size of array is 8 or less use insertion sort
			insertionSort1(d,start,end);
		else{
			//break array in to two and use quicksort recursively to sort arrays
			int mid = partition(d,start,end);
			quicksortSubArray(d, start, mid-1);//first part
			quicksortSubArray(d,mid+1,end);
		}	
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
			while(right>left && smartCounter(d[right]>=pivot))
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
			temp[i]= Math.random();
		return temp;
			
	}
	
	   /**
	    * Sorts the input array using insertion sort,
	    * then returns a reference to the input array.
	    * 
	    * *written by Peter Jensen
	    * 
	    * @param d  an array of doubles
	    * @return   the sorted array of doubles
	    */
	   public double[] insertionSort (double[] d)
	   {
	       for (int curr_pos = 1; curr_pos < d.length; curr_pos++)
	       {
	           double temp = d[curr_pos];
	           int insertion_pos = curr_pos;
	           while (insertion_pos > 0 
	                  && smartCounter(temp < d[insertion_pos-1]))
	           {            
	               d[insertion_pos] = d[insertion_pos-1];
	               insertion_pos--;
	           }
	           d[insertion_pos] = temp;
	       }
	       
	       
	       return d;
	   }
	   public double[] insertionSort1 (double[] d,int start,int end)
	   {
	       for (int curr_pos = start+1; curr_pos < end+1; curr_pos++)
	       {
	           double temp = d[curr_pos];
	           int insertion_pos = curr_pos;
	           while (insertion_pos > 0 
	                  && smartCounter(temp < d[insertion_pos-1]))
	           {            
	               d[insertion_pos] = d[insertion_pos-1];
	               insertion_pos--;
	           }
	           d[insertion_pos] = temp;
	       }
	       
	       
	       return d;
	   }
	

}