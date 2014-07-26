/**
 * This class contains experiment code for
 * insertion sort and mergesort.  It is currently
 * configured to test mergesort.
 */
package homework05;

import java.util.Arrays;

/**
 * A class for conducting experiments.
 * 
 * @author Peter Jensen
 * @version February 6, 2014
 */
public class SortExperiment
{
    /* Static methods and variables */
    
    /**
     * @param args
     */
    public static void main (String[] args)
    {
        new SortExperiment().doExperiment();
    }

    /* Instance (object) methods and variables */
    
    long comparisonCount;
    
    /**
     * Constructor - currently empty
     */
    public SortExperiment ()
    {
        comparisonCount = 0;
    }
    
    /**
     * Conduct our insertion sort experiment
     * @param newParam TODO
     */
    void doExperiment ()
    {
//        double[] data = makeRandomArray(10);
//        data = mergeSort (data);
//        System.out.println(Arrays.toString(data));
//        System.out.println("Comparisons: " + comparisonCount);
//    
        System.out.println();
        System.out.print ("n");
        System.out.print ("\t");
        System.out.print ("count(n)");
        System.out.println();
        
        //for (int n = 1; n <= Math.pow(2,4); n=n*2)
        for (int n = 1; n <= Math.pow(2, 16); n=n*2)
        {
            comparisonCount = 0;
            int tries = 200;
            for (int i = 0; i < tries; i++)
            {
                double[] data = makeRandomArray(n);
                insertionSort(data);
            }
            System.out.print (n);
            System.out.print ("\t");
            System.out.print (comparisonCount * 1.0 / tries);
            System.out.println ();
        }
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
   public double[] makeRandomArray (int n)
   {
       double[] data = new double[n];
       
       for (int i = 0; i < n; i++)
           data[i] = Math.random();
       
       return data;
   }
   
   /**
    * 
    */
   private boolean advanceCount (boolean b)
   {
       comparisonCount++;
       return b;
   }
   
   /**
    * Sorts the input array using insertion sort,
    * then returns a reference to the input array.
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
                  && advanceCount(temp < d[insertion_pos-1]))
           {            
               d[insertion_pos] = d[insertion_pos-1];
               insertion_pos--;
           }
           d[insertion_pos] = temp;
       }
       
       
       return d;
   }
   
   /**
    * Creates a copy of the specified elements [first...last)
    * 
    * @param a
    * @param first
    * @param last
    * @return
    */
   double [] copy (double[] a, int first, int last)
   {
       double[] result = new double[last-first];
       
       for (int dest = 0; first < last; first++, dest++)
           result[dest] = a[first];
       
       return result;
   }
   
   /**
    * Returns a sorted version of the array.
    * 
    * @param d
    * @return
    */
   public double[] mergeSort (double[] d)
   {
       if (d.length < 2)
           return d;
       
       // Create two pieces
       
       int start = 0, end = d.length, mid = (start+end)/2;
       double[] left = copy(d, start, mid), right = copy(d, mid, end);
       
       // Sort them
       
       left = mergeSort(left);
       right = mergeSort(right);
       
       // Merge them together
       
       double[] merged = new double[d.length];
       int leftPos = 0;
       int rightPos = 0;
       for (int mergedPos = 0; mergedPos < d.length; mergedPos++)
       {
           // Determine if we copy an element from left or right
       
           if (rightPos ==  right.length)
           {
               merged[mergedPos] = left[leftPos];
               leftPos++;
           }
           else if (leftPos ==  left.length)
           {
               merged[mergedPos] = right[rightPos];
               rightPos++;
           }
           else if (advanceCount(left[leftPos] < right[rightPos]))
           {
               merged[mergedPos] = left[leftPos];
               leftPos++;
           }
           else
           {
               merged[mergedPos] = right[rightPos];
               rightPos++;
           }
       }
       
       return merged;
   }
}