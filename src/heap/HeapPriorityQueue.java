package heap;

import java.util.ArrayList;
/**
 * This class implements a priority queue as a heap.  The width
 * of the heap is specified as a parameter to the constructor.
 * 
 * @author pajensen
 */
public class HeapPriorityQueue<E extends Comparable<? super E>>
{
    // Instance variables.
    
    private int width;          // The number of children each node will have.
    private ArrayList<E> heap;  // The elements (stored in an ArrayList)
    
    /**
     * This constructor builds an empty heap with the specified
     * width.  (The width is defined here as the number of children
     * that each node will have.)  Specify a width of 2 for a traditional
     * binary heap.  Specify 1 to simulate a linked-list based heap.  (Each
     * node would have only 1 child.)  Specify 3 or more for a larger number
     * of children per node.
     * <p>
     * 
     * Priority is specified using the natural order of the objects.
     * Objects that are 'less than' other objects are first (have
     * higher priority.)
     * <p>
     * 
     * @param width the number of children each node will have
     * @throws IllegalWidthException if width is not positive
     */
    public HeapPriorityQueue (int width)
    {
        // Check for illegal widths, throw an exception for illegal widths.
        
        if (width < 0)
            throw new IllegalWidthException ("Illegal heap width: " + width);
        
        // Set up the instance variables.
        
        this.width = width;
        this.heap = new ArrayList<E>();
    }

    /**
     * Returns the number of elements in the heap.
     * 
     * @return the number of elements in the heap
     */
    public int size ()
    {
        return heap.size();
    }
    
    /**
     * Adds an element to the heap.
     * 
     * @param element the element to add
     */
    public void add (E element)
    {
        // Compute the position of the next open space for this heap and
        //   add the element to the end of the heap (at this position).
        
        // Compute the index of the parent position and get the parent
        //   element from the heap.
        
        // As long as the heap condition is violated, loop and restore
        //   the heap condition for the current node and its parent node.
        
            // Store the parent node at the current position.
            
            // Set the current position to the parent position.            
            
            // Compute the index of the parent position and get the parent
            //   element from the heap.
            
        // Store the element parameter in the heap at the current position.
        
    }
    
    /**
     * Returns and removes the highest priority element
     * from the heap.
     * 
     * @return the highest priority element in the heap
     * @throws EmptyHeapException if the heap is empty
     */
    public E poll ()
    {
        return null;   // STUB - REMOVE

        // If there are no elements, throw an exception.
        
        // If there is only one element in the heap, remove it and return it.
        
        // Save the highest priority element in a temporary variable.
        
        // Create a variable to keep track of the current position and set
        //   it to be the first position.
        
        // Remove the last element from the heap array, 
        //   keep it in a temporary variable.  (The heap size should get smaller.)
        
        // Loop, restoring the heap condition until the condition is
        //   restored (or we arrive at a child with no children).
        //   This loop appears to be infinite, but the code breaks out of the
        //   loop when the heap condition is restored.
        
            // These variables will hold the index number and element contents of
            //   the highest priority child of this element.
            
            
            // Find the highest priority child.  If no children, break the outer loop.
            //   (This is a simple minimum search.)  Set the 'best' to be the first child.
            

            // Loop through the remaining children looking for a better one.
            
                // Compute the index of the next child.  If none, break the inner loop.
                
                
                // Get the child element.  If higher priority, save it as the highest
                //   priority child.
                
            
            // Compare the highest priority child against the 'last' element.
            //   If higher, we're done, heap condition will be met.  Break the loop.
            
            // Otherwise, store this child in the current position and set the
            //   current position to be the position of the child.
            
        }
        
        // Store the last element at the current position.
        
        // Return the highest priority element (retrieved and saved early in this method).
        

	

    
    /**
     * Given the index of some node in the heap, this method
     * returns the index of its parent node in the heap, or
     * 0 if the node is the heap root node.
     * 
     * @param the index (or position) of some node in the heap
     * @return the index of the parent node for this node
     */
    private int parent (int index)
    {
        return 0; // STUB - REMOVE

        // If the index is at the root, return 0.

        // Otherwise, compute and return the index of the parent.
    }
    
    /**
     * Given the index of some node in the heap, this method
     * returns the index of its 'nth' child.  The child number
     * is specified as a number between [0..width).  If no
     * such child exists, -1 is returned.
     * 
     * @param the index (or position) of some node in the heap
     * @param the child number, between 0 and width-1 inclusive
     * @return the index of that child node for this node, or -1 if none.
     */
    private int child (int index, int childNumber)
    {
        return 0; // STUB - REMOVE
        
        // Compute the index of the child.
        
        // If the child number is illegal, or if the childIndex is
        //   beyond the end of the heap, return -1 to indicate 'no child'.
        
        // Otherwise, return the child index.
    }

    /**
     * Returns the heap as a String.
     * 
     * @return the heap as a String
     */
    public String toString ()
    {
        return heap.toString();
    }
    
    /**
     * A heap-specific exception class.
     * 
     * @author pajensen
     */
    static public class IllegalWidthException extends RuntimeException
    {
        public IllegalWidthException (String message)
        {
            super (message);
        }
    }
    
    /**
     * A heap-specific exception class.
     * 
     * @author pajensen
     */
    static public class EmptyHeapException extends RuntimeException
    {
        public EmptyHeapException (String message)
        {
            super (message);
        }
    }
}
