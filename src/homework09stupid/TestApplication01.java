package homework09stupid;

import homework09stupid.SimpleHashSet.TableFullException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * This application is used for testing and analyzing the simple hash set
 * from lab #9.
 * 
 * @author Peter Jensen
 * @version Summer 2010
 */
public class TestApplication01
{
   
    public static void main (String[] args)
    {
    	System.out.println("yo");
        // Determine the number of possible elements, the set capacity, and
        //   the number of tests.
        
        int elementCount = 100000; 
        int setCapacity  = 100;  
        int totalActions = 10000;  
        
        // Create a boolean array that will indicate if each
        //   possible number is in our hash set.
        
        boolean[] shouldBeInTheSet = new boolean[elementCount];
        
        // Create a counter to indicate how many elements are in the set.
        
        int expectedSetSize = 0;
        
        // Create the set to have the specified capacity.
        
        SimpleHashSet02 set = new SimpleHashSet02 (setCapacity);
        
        Set<NumberName> testSet = new HashSet<NumberName>(setCapacity);
        
        // Randomly add or remove numbers to the set and then
        //   verify the integrity of the set.  This loop is weighted so that
        //   additions are more likely to happen at the start and
        //   removals are more likely to happen at the end.
        
        Random r = new Random (8877641);  // Used for repeatability
        
        for (int actionCount = 0; actionCount < totalActions; actionCount++)
        {
        	if(actionCount%100==0)
        		System.out.println(actionCount);
            // Debug - print out the table.  Comment out or uncomment as needed.
            //set.debugTable();
            // System.out.println ("  " + set.getClusterMap());
            
            // Select a random number element.
            
            int number = r.nextInt(elementCount);
           
            NumberName element = new NumberName (number);
            
            // Randomly determine an action to do with this element.
            
            boolean doAdd = r.nextInt(totalActions) > actionCount;
            
            if (doAdd)
            {
                try
                {
                    //System.out.println ("Adding " + element + " (hashes to location " + (Math.abs(element.hashCode()) % setCapacity) + ")");
                    
                    // Attempt to add the element.
                    
                    set.add(element);
                    
                    
                    // If the element was in the set, nothing should have happened.
                    
                    if (!shouldBeInTheSet[number])
                    {
                        // The element was added.  If the table was full, error.
                        
                        if (expectedSetSize == setCapacity)
                        {
                            System.out.flush();
                            System.err.println ("Set sizes disagree.  The table should have " + expectedSetSize + " elements in it, but an exception was not raised.");
                            System.err.flush();
                            return;
                        }
                        
                        // Otherwise, increase the count.

                        shouldBeInTheSet[number] = true;                    
                        expectedSetSize++;
                        if(set.size!=expectedSetSize)
                        	System.out.println("error line 97ish");
                    }
                }
                catch (TableFullException e)
                {
                    // Verify that an exception should have been raised.
                    
                    if (expectedSetSize != setCapacity)
                    {
                        System.out.flush();
                        System.err.println ("Set sizes disagree.  The table should only have " + expectedSetSize + " elements in it, but it appears to have " + setCapacity + " elements in it.");
                        System.err.flush();
                        return;
                    }
                }
            }
            else
            {
                // Delete an element.

               // System.out.println ("Removing " + element + " (hashes to location " + (Math.abs(element.hashCode()) % setCapacity) + ")");
                
                // Attempt to remove the element.
                
                set.delete(element);
                
                // If the element was not in the set, nothing should have happened.
                
                if (shouldBeInTheSet[number])
                {
                    // The element was removed, mark it as removed.

                    shouldBeInTheSet[number] = false;                    
                    expectedSetSize--;
                    if(set.size!=expectedSetSize)
                    	System.out.println("error line 132ish");
                }
            }
            
            // Verify that the set only contains the elements that have been added but not removed.
            
            boolean hasError = false;
            
            for (int i = 0; i < elementCount; i++)
            {
                NumberName n = new NumberName(i);
                
                boolean inTheSet = set.contains(n); 
                
                if (inTheSet && !shouldBeInTheSet[i])
                {
                    System.out.flush();
                    System.err.println (n + " (hashes to location " + (Math.abs(n.hashCode()) % setCapacity) + ") appears to be in the set, but should not be.");
                    System.err.flush();
                    hasError = true;
                }
                
                if (!inTheSet && shouldBeInTheSet[i])
                {
                    System.out.flush();
                    System.err.println (n + " (hashes to location " + (Math.abs(n.hashCode()) % setCapacity) + ") does not appear to be in the set, but should be.");
                    System.err.flush();
                    hasError = true;
                }
            }

            // Stop testing if the validation fails.
            
            if (hasError)
                break;
        }

        // Debug - print out the table.
        //set.debugTable();
	System.out.println ();
        System.out.println ("Cluster Map:  " + set.getClusterMap());

        System.out.println ();
        System.out.println ("Total number of probes: " + set.getProbeCount());
        
//        int t=0;
//        for(int i =0; i< set.arrayOfCounters.length; i++ )
//        {
//        	//t+= set.arrayOfCounters[i].thisProbeCount;
//        	//System.out.println((i*1.0)/set.capacity);
//        	System.out.println(((set.arrayOfCounters[i].thisProbeCount)*1.0)/set.getProbeCount());
//        }
        for(int i =0; i< set.arrayOfCounters.length; i++ )
        {
        	//t+= set.arrayOfCounters[i].thisProbeCount;
        	System.out.println((i*1.0)/set.capacity);
        	//System.out.println(((set.arrayOfCounters[i].thisProbeCount)*1.0)/set.getProbeCount());
        }
        System.out.println("\n");
        for(int i =0; i< set.arrayOfCounters.length; i++ )
        {
        	//t+= set.arrayOfCounters[i].thisProbeCount;
        	System.out.println((set.arrayOfCounters[i].findAverageProbes() *1.0)/set.capacity);
        	//System.out.println(((set.arrayOfCounters[i].thisProbeCount)*1.0)/set.getProbeCount());
        }
        
        System.out.println("\n");
      
        
        
    }
    
    
    
 
    
}

