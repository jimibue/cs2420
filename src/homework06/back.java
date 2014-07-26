
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
 * @author your_name_here
 * @version current_date_here
 */
public class back<E extends Comparable<E>>
{
 
    private Node head, last, current;
    private int size;

    public back()
    {
    
    	
    }

    
    /**
     * Returns the number of elements in this SpecialtySet.
     * 
     * @return a count of the elements in this set
     */
    public int size ()
    {
    	size =0;
       	if (head==null)
    		return 0;
    	else
    	{
    		Node temp = head;
    		
    		while(temp.next!=null){
    			size++;
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
    	   {
    		   //if head is null do nothing
    	     	if (head==null){
    	    		current = null;//should be null
    	    		last=null;
    	     	}
    	     	//there is at least one node in the list check to see if it belongs in first postion
    	     	else if( data.compareTo(head.data)<0/*|| data.compareTo(head.data)==0*/){
    	     		current = head;
    	     		last=null;
    	     	}
    	     	else if(size() ==1 && data.compareTo(head.data)>0){
    	     		current = null;
    	     		last=head;
    	     	}
    	     	
    	    
    	     	//by this point our size is greater than one and data does not need to be inserted at front.
    	     	
    	    	//reset the value if data is less than data...or equal??
    	    	else if(last ==null||current ==null){
    				current =head;
    				while(current.next != null && (data.compareTo(current.data)>0||data.compareTo(current.data) < 0))//****
    				{
    					last = current;
    					current = current.next;
    					
    				}	
    				
    			}
    	    	else if( data.compareTo(last.data)<0 ){
    	    		current =head;
    	    		last =null;
//    		
    				while (current.next != null)//////********
    				{
    					if (data.compareTo(current.data) < 0||data.compareTo(current.data) == 0){
    					
    						break;
    					}
    					last = current;
    					current = current.next;
    				}
    				
    		}
    	   
    	    
    	    
    	     	else if(data.compareTo(last.data) > 0||data.compareTo(last.data)== 0)///changed to current should  be last??
    	    	{
    	     		//**********
    				while(current.next != null &&(data.compareTo(current.data) > 0||data.compareTo(last.data)==0))
    				{
    					last= current;//move  last to current
    					current = current.next;// move current to what current is pointing to.
    				}
    	    	}
    	     	
    	
    	       	else if(last ==null){
    				current =head;
    				while(current.next != null &&( data.compareTo(current.data)>0 ||data.compareTo(current.data)==0))//******
    				{
    					last = current;
    					current = current.next;
    					
    				}	
    				
    			}
    	    	
    	    	
    	    	else{
    	    		//System.out.println("Error.....Inside locate Postion.. Should never get this");
    	    	}
    	  
    	     
    	    	
    	       }
     
     
    	
    }
    
    private void locatePosition1 (E data)//change back to private!!!!!!
    {
    	   if(current == null){
    		   System.out.println(">>>>");
    		   current = head;
    	   }
    		else if (last==null){
    	   		current = head;
    	   		
    	    	}
    		else if(last ==null||current ==null){
				current =head;
				while(current.next != null && (data.compareTo(current.data)>0||data.compareTo(current.data) < 0))//****
				{
					last = current;
					current = current.next;
					
				}	
				
			}
    		else if(data.compareTo(current.data)>0)
   	     	{
    			while(current.next != null && (data.compareTo(current.data)>0||data.compareTo(current.data) < 0))//****
				{
					last = current;
					current = current.next;
					
				}	
				
    					
   	     	}
    		
    		else if( data.compareTo(last.data)<0 ){
	    		current =head;
	    		last =null;
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
    	locatePosition(data);
    	if(head ==null){
    		//System.out.println("here");
    		return false;
    	}
    	
    	
    	else if(current!=null && data.compareTo(current.data)==0){
    		
    		
    		return true;
    		
    	}
    	else if( last != null && data.compareTo(last.data)==0){
    		//System.out.println("here");
    		return true;
    	}
    	

		
    	
	return false;  // Stub
    }
    

    
    public void add (E data)
    {
	

	
	Node tempNode = new Node(data);
	//check first if size is 0 if so simple start the linked list and return
	if(head== null){
		head = new Node(data);
		current = head;
		size++;
		return;
	}
	locatePosition(data);
	if(contains(data)){
		//System.out.println("found!!!");
		return;
	}
	else if(last ==null && data.compareTo(head.data)<0) {
		tempNode.next = head;
		head = tempNode;
		
		current = head;
		last = null;
		
	}
	else
	{
		
		while (current != null)//***
		{
			if (data.compareTo(current.data) < 0){
			
				break;
			}
			last = current;
			current = current.next;
		}
		// insert between before & after
		tempNode.next =last.next;
		last.next = tempNode;
		current = tempNode;
		//System.out.println("add "+data +"after"+before.data);
	}

	
	
	
		
	

}
    

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
    	if(head==null)
    		return;
    	locatePosition(data);
    	if(!contains(data)){
    		
    	}
    	
    	//if current is null remove last
    	else if(current == null)
    	{
    		last= null;
    	}
    	else if(last == null)
    	{
    		
    		head=head.next;
    	}
    	else{
    		//System.out.println(":::");
    		//System.out.println(last.data);
    		//System.out.println(current.data);
    		last.next = current.next;
    		current = current.next;
    		
    	}
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