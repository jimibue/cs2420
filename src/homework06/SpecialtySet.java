package homework06;



/**
 * Objects of this class represent a set of sortable values.  The set
 * has the following performace characteristics:
 *     
 *     - the set is kept in a sorted linked list
 *     
 *     - getting the size of the set - theta(1)
 *     
 *     - adding, removing, or searching for a random element - theta(n)
 *     
 *     - adding, removing, or searching for an element that
 *       immediately follows the previously accessed element - theta(1)
 *     
 * In other words, this set performs very well if additions or
 * removals occur with long sequential runs of ordered data values.    
 *     
 * Note:  This data structure is not threadsafe because instance 
 * variables are used to keep track of visit state.  An iterator
 * would be a much better idea! 
 *     
 * @author James Yeates
 * @version 3/1/14
 */
public class SpecialtySet<E extends Comparable<E>>
{
 
    private Node head, last, current;
    private int size, size1;

    public SpecialtySet()
    {
    }

    
    /**
     * Returns the number of elements in this SpecialtySet.
     * 
     * @return a count of the elements in this set
     */
    public int size ()
    {
    	return size;
    }
    public int size1 ()
    {
    	size1 =0;
       	if (head==null)
    		return 0;
    	else
    	{
    		Node temp = head;
    		
    		while(temp.next!=null){
    			size1++;
    			temp = temp.next;
    		}
    		return(size+1);
    		
    	}  
    }

    
    /**
     * A private helper function that locates the position
     * in the linked list where 'data' exists, or could be
     * inserted.  Upon completion, two instance variables are set:
     * 
     *   The 'last' variable points to the node prior to the
     *   position that 'data' should occupy in the list.  'Last'
     *   will be null if 'data' should occupy the first position
     *   in the list.
     * 
     *   The 'current' variable either points to the node containing
     *   the data (if the data exists in the list), or the node
     *   following the position that the data should occupy (if
     *   the data does not exist in the list).  'Current' will
     *   be null if the data is/should be in the last position
     *   in the list.
     *    
     *  Finally, if the node at 'last' exists and last.data < data,
     *  this function begins the search at 'current'.  Otherwise,
     *  this function starts the search at the beginning of the
     *  linked list.   
     *    
     * @param data
     */
    private void locatePosition (E data)//change back to private!!!!!!
    {
    	//Check to see if list is empty
    	if(current== null && last == null)
    		return;
    	
    	//check to see last.data is less than data
    	else if( last != null && last.data.compareTo(data)<0)
    	{
    		//while currents data is less than data advance current and last forward
    		while(current!= null && current.data.compareTo(data)<0)
    		{
    			last = current;
    			current = current.next;
    		}
    		
    	}
    	//last.data was >= to data..need to start search from the beginning 
    	else if(last !=null){
    		current = head;
    		last=null;
    		while(current!= null&& current.data.compareTo(data)<0){
    			last = current;
    			current = current.next;
    		}
    	}

    	else if(current.data.compareTo(data)<0){
    		
    		last =null;
    		current = head;
    		while(current!= null && current.data.compareTo(data)<0)
    		{
    			last = current;
    			current = current.next;
    		}
    		
    	}
    	else{
    		//System.out.println(" in else in locate pos.");
    	}
     }
     /** Returns 'true' if the specified data is in the set,
     * false otherwise.
     * 
     * @param data  A data value to search for
     * @return      true iff the data is in the set
     */
    /* Implementation note:  The postconditions for the
     * 'locatePosition' function are also guaranteed for
     * this function.
     */
    public boolean contains (E data)
    {
    	if(data == null)
    		return false;
    	//check for empty list... is this needed??
    	if(current== null && last == null )
    		return false;
    	locatePosition(data);
    	
    	if(current != null && data.compareTo(current.data)==0)
    		return true;
    	//this should not happen left in for testing
//    	else if(last != null &&data.compareTo(last.data)==0){
//    		System.out.println("error at contains: should not be here");
//    		return true;
//    	}
    	
	return false;  // Stub
    }

    public void add (E data)
    {
	
    //check to see if data is in list if so return
    if(contains(data))
    	return;

	Node tempNode = new Node(data);
	
	//if list is empty add at the beginning
	if(current== null && last == null){
		head = tempNode;
		current = head;
	
	}
	//if last is null insert at front of list
	else if(last == null){
		tempNode.next=head;
		head = tempNode;
		current = tempNode;
		last=null;
	}

	//if current is null add at end of list
	else if(last!=null){
		tempNode.next=current;
		last.next = tempNode;
		current = tempNode;
		
	}
	else{
		System.out.println(" error at add...should never get here");
	}
	size++;

}
   /**
    * this print out the set to the console used for debugging 
    */

    public void display()
    {
    	if (head==null)
    		System.out.println("empty");
    	else
    	{
    		Node temp = head;
    		System.out.print("[ ");
    		while(temp.next!=null){
    			
    			System.out.print(temp.data+", ");
    			temp = temp.next;
    		}
    		System.out.println(temp.data+" ]");
    		
    	}
    }
    
    /**
     * Guarantees that the specified data is not in the set.
     * (The data is removed if needed.)
     * 
     * @param data  a data value to be removed from the set
     */
    /* Implementation note:  If an element is actually removed, 
     *   'current' will refer to the node following the removed
     *   node after this function, and 'last' will refer
     *   to the previous node (as appropriate).
     */
 
    public void remove (E data)
    {
    	//if set is empty return
    	if(current ==null&&last ==null)
    		return;
    	
    	//if set does not contain data return
    	if(!contains(data))
    		return;
    	
    	//if there is just on item remove it
    	if(last==null && size==1)
    	{
    		head=null;
    		current=null;
    	}
    	//remove first item if last is null
    	else if(last==null )
    	{
    		head=head.next;
    		current= head;
    	}
    	else if(last!=null){
    		last.next = current.next;
    		current= current.next;
    	}
    	else{
    		System.out.println("error at remove...should never get here");
    	}
    	size--;
    }
    
    
    /** 
     * A debugging function (not required) that
     * verifies the element count and element sortedness.
     * My test also printed out the contents of the set.
     * 
     * Students may write debugging functions like this
     * one, but they may not write external tests or other
     * internal code that depends on the execution of any 
     * internal test function.
     * 
     * @return  true iff the set passes an internal test
     */
    boolean validate ()
    {
      	Node tempNode = head;
    	while(tempNode != current){
    		if(tempNode.data.compareTo(tempNode.next.data)>0 
    				|| tempNode.data.compareTo(tempNode.next.data)==0)
    			return false;
    		tempNode=tempNode.next;
    	}
	return true;  // Stub
	  // Stub
    }


    
    // An example of an inner class (a class within another object).
    
    /**
     * A private helper class for the SpecialtySet class.
     * Node objects are used to construct linked lists
     * in a SpecialtySet.
     *
     * Students are not allowed to change this class.
     * 
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private class Node
    {
        private final E data;   // The data element - cannot be changed after it is assigned
        private Node next;      // Initialized to null when this object is created
        
        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes (next is null), although it
         * is expected that 'next' may change.  
         * 
         * Also note, the data variable is final, the data reference cannot be 
         * changed.  (This fact is largely irrelevant.)
         * 
         * @param data   the data to store in the node
         */
        Node (E data)
        {
            this.data = data;
        }
    }

}



