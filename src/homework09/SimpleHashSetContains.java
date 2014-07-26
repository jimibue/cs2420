package homework09;












/**
 * This class is a hash table implementation of a set.  It is
 * designed to support simple hash table experiments.  Each table
 * has a fixed maximum size.  Elements can be added, deleted, or
 * queried, but the number of elements in the set is
 * not available to the user.  Also there is no mechanism for
 * iterating through the elements in a table.  (These restrictions
 * are imposed to prevent inaccurate testing strategies.)
 * 
 * Element equality is determined using the .equals method.
 * 
 * The hash table is implemented using open addressing and linear
 * probing.
 * 
 * In addition to the methods for accessing the set, this implementation
 * keeps a probe count that can be read and reset by the user.  Every read
 * of a location in the hash table is considered a probe.
 * 
 *  Finally, the user can request a cluster table.  This is just a
 *  String object that indicates which locations in the hash table
 *  are filled.
 * 
 * @author Peter Jensen James Yeates
 * @version Spring 2014
 */
public class SimpleHashSetContains
{
    // Instance variables.
    
    private Object[] table;
    protected int      capacity;    
    protected int      totalCount;
    protected int 	   totalVisit;
    protected int      size;
    protected SpecificProbeCounter[] arrayOfCounters;
    
    /**
     * Builds a hash set with the specified maximum size.
     * 
     * @param capacity the size of the hash table
     */
    public SimpleHashSetContains (int capacity)
    {
    	this.size = 0;
    	totalVisit =0;
        this.capacity   = capacity;
        this.table      = new Object[capacity];
        this.totalCount = 0;
        arrayOfCounters = new SpecificProbeCounter[capacity+1];
        
        for(int i = 0; i< capacity+1; i++){
        	SpecificProbeCounter temp = new SpecificProbeCounter(i);
        	arrayOfCounters[i]= temp;
        }
        
        
    }
    
    /**
     * Adds the specified element to the set.  If the hash
     * table already contains an object equal to the specified
     * object, the specified object will not be added.
     * 
     * @param element any object
     * @throws TableFullException if the hash table is full
     */
    public void add (Object element)
    {
        ///// You must complete this method /////
        
        // 1. Compute the hash code for the element, confine it to the table size.
        //   (A simple mod-based hash function.)  This will be the array index.
        //   Watch out for negative hash codes!
    	int hash = Math.abs(element.hashCode()) % capacity;
    	
    	
        
        // 2. Try to insert the element at the hash code location.  Use a loop instead of an if statement.
        // If the loop repeats, advance by one to the next open spot (linear probing).
    	totalVisit++;
    	//arrayOfCounters[size].timesVisted++;
    	for(int retry = 0; retry< capacity; retry++){

            // 3. Determine what is in the hash table by probing it.
    		int position = (hash+retry)%capacity;
    		
    		Object temp = table[position];
    		totalCount++;
    		//arrayOfCounters[size].doStuff();
    	
            
            // 4. If that location in the table is empty, store the element in it and return.
            if(temp == null)
            {
            	size++;
            	table[position]=element;
            	return;
            }
            // 5. If that location already contains the element, return.
            if(temp.equals(element))
            	return;
           
    	}
        // 6. If we get this far, the table was full.  Throw an exception.
    	throw new TableFullException();
    }
    public void add1 (Object element)
    {
        ///// You must complete this method /////
        
        // 1. Compute the hash code for the element, confine it to the table size.
        //   (A simple mod-based hash function.)  This will be the array index.
        //   Watch out for negative hash codes!
    	int hash = Math.abs(element.hashCode()) % capacity;
    	
    	
        
        // 2. Try to insert the element at the hash code location.  Use a loop instead of an if statement.
        // If the loop repeats, advance by one to the next open spot (linear probing).
    	
    	for(int retry = 0; retry< capacity; retry++){

            // 3. Determine what is in the hash table by probing it.
    		Object temp = table[(hash+retry)%capacity];
    		
    	
            
            // 4. If that location in the table is empty, store the element in it and return.
            if(temp == null)
            {
            	table[(hash+retry)%capacity]=element;
            	return;
            }
            // 5. If that location already contains the element, return.
            if(temp.equals(element))
            	return;

    	}
        // 6. If we get this far, the table was full.  Throw an exception.
    	throw new TableFullException();
    }
    
    /**
     * Returns true if this element is in the set.  The
     * element is considered to be in the set if it is
     * equal to any object in the set.
     * 
     * @param element any object
     * @return true if the element is in the set
     */
    public boolean contains (Object element)
    {
    	int hash = Math.abs(element.hashCode())%capacity;
    	arrayOfCounters[size].timesVisted++;
    	
    	
        
        // 2. Try to insert the element at the hash code location.  Use a loop instead of an if statement.
        // If the loop repeats, advance by one to the next open spot (linear probing).
    	
    	for(int retry = 0; retry< capacity; retry++){
    		arrayOfCounters[size].doStuff();

            // 3. Determine what is in the hash table by probing it.
    		Object temp = table[(hash+retry)%capacity];
    		
    	
            
            // 4. If that location in the table is empty, store the element in it and return.
            if(temp == null)
            	return false;
            
            // 5. If that location already contains the element, return.
            else if(temp.equals(element))
            	return true;
            
    	}
        // 6. If we get this far, the table was full.  Throw an exception.
    	
        return false;  // Stub - remove it.
    }
    
    /**
     * Removes the specified element from the set.  If the
     * set does not contain an object equal to the element,
     * nothing happens.
     */
    public void delete (Object element)
    {
        // Compute the hash code for the element, confine it to the table size.
        //   (A simple division-based hash function.)
        
        int hash = Math.abs(element.hashCode()) % capacity;
        
        // Try to locate the element at the hashed position.  If it is not
        //   there, simply return.
        
        int retry = 0;
        for (; retry < capacity; retry++)
        {
            // Determine what is in the hash table by probing it.
            
            Object probed = table[(hash + retry) % capacity];
            
            
            // If that location in the table is empty, the element is not in the table.
            
            if (probed == null)
                return;
            
            // If that location contains the element, terminate the loop.

            if (probed.equals(element))
                break;
        }
        
        // If the table was full, but the element was not found, return.
        
        if (retry == capacity)
            return;
        
        // Remove the element.  This involves possibly moving other elements
        //   in the hash table to collapse the cluster.
        
        int freePosition = (hash + retry) % capacity;
        
        for (int count = 1; (freePosition + count) % capacity != hash; count++)
        {
            // Determine what is in the hash table by probing it.
            
            Object probed = table[(freePosition + count) % capacity];
            ;
            
            // If null, we're done collapsing the cluster.
            
            if (probed == null)
                break;
            
            // If the probed object could legally occupy the free position, put it in the free position.
            //   To do this, determine the retry count that was used to place the probed element
            //   in the cache.
            
            int probedHash  = Math.abs(probed.hashCode()) % capacity;
            int probedRetry = ((freePosition + count) - probedHash + capacity) % capacity; 
            
            // If the probed retry is greater than or equal to our offset from the free
            //   position, move the element into the free position.
            
            if (probedRetry >= count)
            {                
                table[freePosition] = probed;
                freePosition = (freePosition + count) % capacity;
                count = 0;
            }
        }
        
        // Clear the free position.
        
        table[freePosition] = null;
        size--;
    }
    
    /**
     * Returns a string that indicates which elements of the
     * table are used.
     * 
     * @return a string that shows table usage.
     */
    public String getClusterMap ()
    {
        StringBuilder result = new StringBuilder(table.length);
        for (Object o : table)
            result.append(o == null ? '.' : 'X');
        
        return result.toString();
    }
    

    
    /**
     * A debugging method for printing out the table contents.
     */
    public void debugTable ()
    {
        for (int i = 0; i < capacity; i++)
        {
            System.out.print ("Position " + i + ": " + table[i]);
            if (table[i] != null)
                System.out.println (" (hashes to location " + (Math.abs(table[i].hashCode()) % capacity) + ")");
            else
                System.out.println ();            
        }
        System.out.println();
    }
    
    /**
     * An exception class for indicating the table is full.
     */
    static class TableFullException extends RuntimeException
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		TableFullException ()
        {
            super ("The hash table is full.");
        }
    }    
}
