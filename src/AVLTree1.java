
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;





/**
 * This class is the complete and tested implementation of an AVL-tree.
 */
public class AVLTree1 <E extends Comparable<E>> {
 
 protected AvlNode root; // the root node
 int size;
 
/***************************** Core Functions ************************************/

 /**
  * Add a new element with key "k" into the tree.
  * 
  * @param k
  *            The key of the new node.
  */
 
 /**
  * Returns true if this AVLTree contains the specified data element.
  * 
  * @param element
  *            The element whose presence is to be tested.
  * @return True if this AVLTree contains the specified element.
  */
 public boolean contains(E element) {

     if (element == null) {
         return false;
     }

     return find(element) != null;
 }
 /**
  * Returns a reference to the specified data element in this AVLTree, or
  * null if the element is not present.
  * 
  * @param element
  *            The element to be found.
  * @return The reference to the specified element in the tree, or null if
  *         the element is not present.
  */
 public E find(E element) {

     if (element == null) {
         return null;
     }

     AvlNode current = root;

     while (current != null) {
         
    	 
    	 int cmp = element.compareTo(current.key);

         if (cmp < 0) {
             current = current.left;
         } else if (cmp > 0) {
             current = current.right;
         } else {
             return current.key;
         }
     }

     return null;
 }

 public void add(E k) {
  // create new node
  AvlNode n = new AvlNode(k);
  // start recursive procedure for inserting the node
  insertAVL(this.root,n);
 }
 
 /**
  * Recursive method to insert a node into a tree.
  * 
  * @param p The node currently compared, usually you start with the root.
  * @param q The node to be inserted.
  */
 public void insertAVL(AvlNode p, AvlNode q) {
  // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
  if(p==null) {
	  size++;
   this.root=q;
  } else {
   
	int value = q.key.compareTo(p.key);
   // If compare node is smaller, continue with the left node
   if(value<0) {
    if(p.left==null) {
     p.left = q;
     q.parent = p;
     size++;
     // Node is inserted now, continue checking the balance
    recursiveBalance(p);
    } else {
     insertAVL(p.left,q);
    }
    
   } else if(value>0) {
    if(p.right==null) {
     p.right = q;
     q.parent = p;
     size++;
     // Node is inserted now, continue checking the balance
    recursiveBalance(p);
    } else {
     insertAVL(p.right,q);
    }
   } else {
    // do nothing: This node already exists
   }
  }
 }
 
 /**
  * Check the balance for each node recursivly and call required methods for balancing the tree until the root is reached.
  * 
  * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
  */
 public void recursiveBalance(AvlNode cur) {
  
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
 public void remove(E k) {
	  // First we must find the node, after this we can delete it.
	  removeAVL(this.root,k);
	 }

 
 /**
  * Finds a node and calls a method to remove the node.
  * 
  * @param p The node to start the search.
  * @param q The KEY of node to remove.
  */
 public void removeAVL(AvlNode p,E q) {
  if(p==null) {
   // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
   return;
  } 
  else {
	  int value = p.key.compareTo(q);
   if(value > 0)  {
    removeAVL(p.left,q);
   } else if(value < 0) {
    removeAVL(p.right,q);
   } else if(value==0) {
    // we found the node in the tree.. now lets go on!
    removeFoundNode(p);
   }
  }
 }
 
 /**
  * Removes a node from a AVL-Tree, while balancing will be done if necessary.
  * 
  * @param nodeToXd The node to be removed.
  */
 public void removeFoundNode(AvlNode nodeToXd) {
  AvlNode reference;
  // at least one child of q, q will be removed directly
  if(nodeToXd.left==null || nodeToXd.right==null) {
   // the root is deleted
//   if(q.parent==null) 
//   {
//	   System.out.println("YO");
//	   size--;
//    this.root=null;
//   // q=null;
//    //return;
//   } 
   reference = nodeToXd;
  } 
  else {
   // q has two children --> will be replaced by successor
   reference = successor(nodeToXd);
   nodeToXd.key = reference.key;
  }
 
  AvlNode p;
  if(reference.left!=null) {
   p = reference.left;
  }  
  else {
   p = reference.right;
  }
  
  if(p!=null) {
   p.parent = reference.parent;
  }
  
  if(reference.parent==null) {
   this.root = p;
  } else {
   if(reference==reference.parent.left) {
    reference.parent.left=p;
   } else {
    reference.parent.right = p;
   }
   //size--;
   // balancing must be done until the root is reached.
   recursiveBalance(reference.parent);
  }
  size--;
  //replace = null;
 }
 public void printIn(AvlNode root){
	 if(root==null)
		 return;
	 else
	 {
		printIn(root.left);
		System.out.print(root.key+" ");
		printIn(root.right);
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
 public AvlNode rotateLeft(AvlNode n) {
  
  AvlNode v = n.right;
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
 
 /**
  * Right rotation using the given node.
  * 
  * @param n
  *            The node for the rotation
  * 
  * @return The root of the new rotated tree.
  */
 public AvlNode rotateRight(AvlNode n) {
  
  AvlNode v = n.left;
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
 public AvlNode doubleRotateLeftRight(AvlNode u) {
  u.left = rotateLeft(u.left);
  return rotateRight(u);
 }
 
 /**
  * 
  * @param u The node for the rotation.
  * @return The root after the double rotation.
  */
 public AvlNode doubleRotateRightLeft(AvlNode u) {
  u.right = rotateRight(u.right);
  return rotateLeft(u);
 }
 
/***************************** Helper Functions ************************************/
 
 /**
  * Returns the successor of a given node in the tree (search recursivly).
  * 
  * @param q The predecessor.
  * @return The successor of node q.
  */
 public AvlNode successor(AvlNode q) {
  
   AvlNode r = q.right;
   boolean wentIn = false;
   while(r.left!=null) {
    r = r.left;
    wentIn = true;
   }
  
//   if(r.right!=null && wentIn){
//	   r.parent.left=r.right;
//	   r.right=null;
//   		
//   }
   return r;
 
//  else {
//   AvlNode p = q.parent;
//   while(p!=null && q==p.right) {
//    q = p;
//    p = q.parent;
//   }
//   return p;
//  }
 // return null;
 }
 
 /**
  * Calculating the "height" of a node.
  * 
  * @param cur
  * @return The height of a node (-1, if node is not existent eg. NULL).
  */
 private int height(AvlNode cur) {
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
  * Only for debugging purposes. Gives all information about a node.
  
  * @param n The node to write information about.
  */
// public void debug(AvlNode n) {
//  int l = 0;
//  int r = 0;
//  int p = 0;
//  if(n.left!=null) {
//   l = n.left.key;
//  }
//  if(n.right!=null) {
//   r = n.right.key;
//  }
//  if(n.parent!=null) {
//   p = n.parent.key;
//  }
//  
//  System.out.println("Left: "+l+" Key: "+n+" Right: "+r+" Parent: "+p+" Balance: "+n.balance);
//  
//  if(n.left!=null) {
//   debug(n.left);
//  }
//  if(n.right!=null) {
//   debug(n.right);
//  }
// }
 
 private void setBalance(AvlNode cur) {
  cur.balance = height(cur.right)-height(cur.left);
 }
 
 /**
  * Calculates the Inorder traversal of this tree.
  * 
  * @return A Array-List of the tree in inorder traversal.
  */
 final protected ArrayList<AvlNode> inorder() {
  ArrayList<AvlNode> ret = new ArrayList<AvlNode>();
  inorder(root, ret);
  return ret;
 }
 
 /**
  * Function to calculate inorder recursivly.
  * 
  * @param n
  *            The current node.
  * @param io
  *            The list to save the inorder traversal.
  */
 final protected void inorder(AvlNode n, ArrayList<AvlNode> io) {
  if (n == null) {
   return;
  }
  inorder(n.left, io);
  io.add(n);
  inorder(n.right, io);
 }
 /** Writes out this set (as a tree) to the specified file
  * in the .tree format from assignment #7.
  */ 
public void writeFile (File f) throws IOException
{
	
	try {
		 
		String content = "This is the content to write into file";

		

		// if file doesnt exists, then create it
		if (!f.exists()) {
			f.createNewFile();
		}

		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();

		System.out.println("Done");

	} catch (IOException e) {
		e.printStackTrace();
	}
}



/** Here is the AVL-Node class for Completenesse **/
class AvlNode/*<E extends Comparable<E>>*/ {
 public AvlNode left;
 public AvlNode right;
 public AvlNode parent;
 public E key;
 public int balance;

 public AvlNode(E k) {
  left = right = parent = null;
  balance = 0;
  key = k;
 }
 public String toString() {
  return "" + key;
 }

}
}

