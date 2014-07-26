/**
 * this class contains methods and code to perform experiments for Question 4
 * 
 * 
 * @author James Yeates
 *
 */
package homework05;

import java.util.Arrays;

/**
 * This class was used in lecture and was written by Peter Jensen it is used in this assigment to
 * generate selection sort comparisions
 * 
 * @author Peter Jensen
 * @version February 6, 2014
 */
public class Question4
{


    /* Instance (object) methods and variables */
    
    long comparisonCount;
    
    /**
     * Constructor - currently empty
     */
    public Question4 ()
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
        for (int n = 0; n <= 20; n++)
        {
        	long startTime = System.currentTimeMillis();
            comparisonCount = 0;
            int tries = 5000000;
            for (int i = 0; i < tries; i++)
            {
                double[] data = makeRandomArray(n);
                insertionSort(data);
            }
            long endTime = System.currentTimeMillis();
            System.out.print (n);
            System.out.print ("\t");
            //System.out.print (/*comparisonCount * 1.0 / tries+" "+ */(endTime-startTime)/1000.0);
            System.out.print (comparisonCount * 1.0 / tries+" " );
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
}