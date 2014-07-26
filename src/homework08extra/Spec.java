package homework08extra;

import javax.management.RuntimeErrorException;





/**
 * Objects of this class represent a set of sortable values.  The set
 * has the following performace characteristics:
 *     
 *     - the set is kept in a AVL Tree
 *     
 *     - getting the size of the set - theta(1)
 *     
 *     - adding, removing, or searching for a random element - theta(lg(n))
 *     
 *     
 * @author James Yeates
 * @version current_date_here
 */
public class Spec<E extends Comparable<E>>
{
    // Instance variables.  Students are allowed
    //   only these, do not add or change instance variables.
    
    protected Node root;
    private int size;

    // Instance methods below.
    
    /**
     * Constructs an empty set.
     */
    public Spec()
    {
    	size=0;
    	root = null;
    }
	public void printInOrder(Node node)
	{
		
		
		if(node == null)
			return;
		printInOrder(node.left);
		System.out.print(node.data+" ");
		printInOrder(node.right);
		//System.out.print("/ ");
		
					
	}
	public void printPreOrder(Node node)
	{
		
		
		if(node == null)
			return;
		System.out.print(node.data+" ");
		printPreOrder(node.left);
		
		printPreOrder(node.right);
		//System.out.print("/ ");
		
					
	}

    
    /**
     * Returns the number of elements in this SpecialtySet.
     * 
     * @return a count of the elements in this set
     */
    public int size ()
    {
        return size;  // Stub
    }



    
	/**
	 * Determine if element is in this tree
	 * 
	 * @param target the element be looked for
	 * @return boolean - is element in tree
	 */
    public boolean contains (E data)
    {
    	
		return contains(root,data);
    }
	/**
	 * This method recursively checks for the target in this subtree/node
	 * @return boolean is target in tree
	 */
	public boolean contains(Node currentNode, E target) 
	{
		//determine if this nodes data is < > = target's
		if(currentNode == null)
			return false;
		int value = currentNode.data.compareTo(target);
	
		if(value == 0)//found
			return true;
		if(value<0)//search the right side
			return contains(currentNode.right, target);
		return contains(currentNode.left, target);
	}
    

	/**
	 * Adds an element to the tree.  If the element is in the tree the method
	 * will return
	 * @param data
	 */
	public void add(E data)
	{
		//check if tree is empty
		if(root == null)
		{
			size++;
			root = new Node (data);
			return;
		}
	
		Node tempNode = root;
		//loop trough the tree to find position when found add and return
		while(true)
		{
			int value = tempNode.data.compareTo(data);
		
			if(value == 0)//set no duplicates
				return;
			else if(value < 0)//search right
			{
				if(tempNode.right ==null)//found position
				{
					size++;
					tempNode.right = new Node (data);//add to right
					tempNode.right.parent=tempNode;
					recursiveBalance(tempNode);
					return;
				}
				tempNode = tempNode.right;
			}
			else{
			//search left
				if(tempNode.left ==null)//found position
				{
					size++;
					tempNode.left = new Node (data);//add to left
					tempNode.left.parent=tempNode;
					recursiveBalance(tempNode);
					return;
				}
				else
					tempNode = tempNode.left;
			}
		}
		
	}
    
    /**
     * Driver method
     * Guarantees that the specified data is not in the set.
     * (The data is removed if needed.)
     * 
     * @param data  a data value to be removed from the set
     */


    public void remove(E target)
    {
    	remove(root, target);
    }
	
    
    /**
     * This method searches for the element to be removed returns if it is not found
     * calls a helper method to remove the item if it is found
     */
		
	 private void remove(Node current, E target) {
		if(current == null)
			return;
		int value =  current.data.compareTo(target);
		
		if(value>0)//search the left
			remove(current.left, target);
		else if(value < 0)//search the right
			remove(current.right, target);
		else//target hassbeen found remove it
			removeFoundNode(current);
			
		
	}
	 public void removeFoundNode(Node q) {
		  Node r;
		  // at least one child of q, q will be removed directly
		  if(q.left==null || q.right==null) {
		   // the root is deleted
		   if(q.parent==null) {
		    this.root=null;
		    q=null;
		    return;
		   }
		   r = q;
		  } else {
		   // q has two children --> will be replaced by successor
		   r = successor(q);
		   q.data = r.data;
		  }
		  
		  Node p;
		  if(r.left!=null) {
		   p = r.left;
		  } else {
		   p = r.right;
		  }
		  
		  if(p!=null) {
		   p.parent = r.parent;
		  }
		  
		  if(r.parent==null) {
		   this.root = p;
		  } else {
		   if(r==r.parent.left) {
		    r.parent.left=p;
		   } else {
		    r.parent.right = p;
		   }
		   // balancing must be done until the root is reached.
		   recursiveBalance(r.parent);
		  }
		  r = null;
		 }
	 public Node successor(Node q) {
		  if(q.right!=null) {
		   Node r = q.right;
		   while(r.left!=null) {
		    r = r.left;
		   }
		   return r;
		  } else {
		   Node p = q.parent;
		   while(p!=null && q==p.right) {
		    q = p;
		    p = q.parent;
		   }
		   return p;
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
	return false;  // Stub
    }
    
    private int height(Node cur) {
    	  if(cur==null) {
    	   return -1;
    	  }
    	  if(cur.left==null && cur.right==null) {
    	   return 0;
    	  } else if(cur.left==null) {
    	   return 1+height(cur.right);
    	  } else if(cur.right==null) {
    	   return 1+height(cur.left);
    	  } else {
    	   return 1+maximum(height(cur.left),height(cur.right));
    	  }
    	 }
    	 
    	 /**
    	  * Return the maximum of two integers.
    	  */
    	 private int maximum(int a, int b) {
    	  if(a>=b) {
    	   return a;
    	  } else {
    	   return b;
    	  }
    	 }
    	 
    	 /**
    	  * Left rotation using the given node.
    	  * 
    	  * 
    	  * @param n
    	  *            The node for the rotation.
    	  * 
    	  * @return The root of the rotated tree.
    	  */
    	 public Node rotateLeft(
    			 Node n) {
    	  
    	  Node v = n.right;
    	  v.parent = n.parent;
    	  
    	  n.right = v.left;
    	  
    	  if(n.right!=null) {
    	   n.right.parent=n;
    	  }
    	  
    	  v.left = n;
    	  n.parent = v;
    	  
    	  if(v.parent!=null) {
    	   if(v.parent.right==n) {
    	    v.parent.right = v;
    	   } else if(v.parent.left==n) {
    	    v.parent.left = v;
    	   }
    	  }
    	  
    	  setBalance(n);
    	  setBalance(v);
    	  
    	  return v;
    	 }
    	 
    	 public void recursiveBalance(Node cur) {
    		  
    		  // we do not use the balance in this class, but the store it anyway
    		  setBalance(cur);
    		  int balance = cur.balance;
    		  
    		  // check the balance
    		  if(balance==-2) {
    		   
    		   if(height(cur.left.left)>=height(cur.left.right)) {
    		    cur = rotateRight(cur);
    		   } else {
    		    cur = doubleRotateLeftRight(cur);
    		   }
    		  } else if(balance==2) {
    		   if(height(cur.right.right)>=height(cur.right.left)) {
    		    cur = rotateLeft(cur);
    		   } else {
    		    cur = doubleRotateRightLeft(cur);
    		   }
    		  }
    		  
    		  // we did not reach the root yet
    		  if(cur.parent!=null) {
    		   recursiveBalance(cur.parent);
    		  } else {
    		   this.root = cur;
    		  // System.out.println("------------ Balancing finished ----------------");
    		  }
    		 }
    	 private void setBalance(Node cur) {
    		  cur.balance = height(cur.right)-height(cur.left);
    		 }
    	 
    	 /**
    	  * Right rotation using the given node.
    	  * 
    	  * @param n
    	  *            The node for the rotation
    	  * 
    	  * @return The root of the new rotated tree.
    	  */
    	 public Node rotateRight(Node n) {
    	  
    	  Node v = n.left;
    	  v.parent = n.parent;
    	  
    	  n.left = v.right;
    	  
    	  if(n.left!=null) {
    	   n.left.parent=n;
    	  }
    	  
    	  v.right = n;
    	  n.parent = v;
    	  
    	  
    	  if(v.parent!=null) {
    	   if(v.parent.right==n) {
    	    v.parent.right = v;
    	   } else if(v.parent.left==n) {
    	    v.parent.left = v;
    	   }
    	  }
    	  
    	  setBalance(n);
    	  setBalance(v);
    	  
    	  return v;
    	 }
    	 /**
    	  * 
    	  * @param u The node for the rotation.
    	  * @return The root after the double rotation.
    	  */
    	 public Node doubleRotateLeftRight(Node u) {
    	  u.left = rotateLeft(u.left);
    	  return rotateRight(u);
    	 }
    	 
    	 /**
    	  * 
    	  * @param u The node for the rotation.
    	  * @return The root after the double rotation.
    	  */
    	 public Node doubleRotateRightLeft(Node u) {
    	  u.right = rotateRight(u.right);
    	  return rotateLeft(u);
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
    	private  E data;      // The data element - may be changed after it is assigned
    	private Node parent;       // Parent - initialized to null
    	private Node left, right;  // Children - initialized to null
    	private int balance;        // Height of this subtree - initialized to 0
        
        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes 
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




