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
public class workingspecset<E extends Comparable<E>>
{
    // Instance variables.  Students are allowed
    //   only these, do not add or change instance variables.
    
    public Node root;
    private int size;
    public Node getN(){
    	return root;
    }

    // Instance methods below.
    
    /**
     * Constructs an empty set.
     */
    public workingspecset()
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
    	remove(root, target, 0);
    }
	
    
    /**
     * This method searches for the element to be removed returns if it is not found
     * calls a helper method to remove the item if it is found
     */
		
	 private void remove(Node current, E target, int direction) {
		if(current == null)
			return;
		int value =  current.data.compareTo(target);
		
		if(value>0)//search the left
			remove(current.left, target,-1);
		else if(value < 0)//search the right
			remove(current.right, target,1);
		else//target has been found remove it
			removeFoundNode(current,direction);
			
		
	}
	 /**
	  * This method deletes the found Node from the tree
	  * @param nodeToDelete the node to be removed
	  * @param direction 0 if node is root - 1 if node is the left child of the parent
	  * 					1 if the node is a right child of the parent.
	  */
	 public void removeFoundNode(Node nodeToDelete, int direction) 
	 {
		 Node focusNode; 
		 //if the node that needs to be replaced has two children find the successor
		 //and swap the data values,  reference will be taken care of later in the method
		 if(nodeToDelete.left != null && nodeToDelete.right != null)
		 {
			 focusNode = nodeToDelete.right;
			 while(focusNode.left!=null)
				 focusNode=focusNode.left;
			 nodeToDelete.data = focusNode.data;
		 }
		 else
		 {
//		   if(nodeToDelete.parent==null) 
//		   {
//			   System.out.println("YO");
//			   size--;
//		    this.root=null;
//		    nodeToDelete=null;
//		    return;
//		   } 
			 if(size ==1)
			 {
				 
				 size--;
				 root = null;
				 return;
			 }
			 if(size ==2 && nodeToDelete ==root)
			 {
				 size--;
				 if(root.left==null)
				 {
				 	root=root.right;
				 	root.parent=null;
				 }
				 else{
				 root= root.left;	
				 root.parent=null;
				 }
				 return;
			 }
			 if(size ==2 && nodeToDelete !=root)
			 {
				 size--;
				 root.left=null;
				 root.right=null;
				 return;
			 }
			focusNode = nodeToDelete; 
		 }
		 Node placementNode;
		 
		 //case for node with one left child
		 if(focusNode.left != null)
			 placementNode = focusNode.left;
		 //case for node with one right child, no children and two children 
		 else
			 placementNode = focusNode.right;
		
		 
		
		 
		 if(placementNode!=null)
			 placementNode.parent=focusNode.parent;
		 if(focusNode.parent == null)
			 root=placementNode;
		//change the reference to the value
		 else if(focusNode==focusNode.parent.right)
			 focusNode.parent.right= placementNode;
		 else if(focusNode==focusNode.parent.left)
			 focusNode.parent.left=placementNode;
		 
		 recursiveBalance(focusNode.parent);
		size--;
		//balance
		 
			 
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
     class Node
    {
    	public  E data;      // The data element - may be changed after it is assigned
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
        public E getData(){
        	return data;
        }
    }

}




