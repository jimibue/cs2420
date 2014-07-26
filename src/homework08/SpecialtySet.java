package homework08;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Objects of this class represent a set of comparable values. The set has the
 * following performance characteristics:
 * 
 * - the set is kept in a AVL Tree
 * 
 * - getting the size of the set - theta(1)
 * 
 * - adding, removing, or searching for a random element - theta(lg(n))
 * 
 * This site (below) as well as parts of text book were used in helping me
 * design and conceptlize the alogorithms for the rotating methods and the
 * remove method
 * http://www.blackbam.at/blackbams-blog/2012/05/04/avl-tree-implementation
 * -in-java/
 * 
 * @author James Yeates
 * @version 4/03/14
 */
public class SpecialtySet<E extends Comparable<E>> {

	private Node root;
	private int size;

	/**
	 * Constructs an empty set.
	 */
	public SpecialtySet() {
		size = 0;
		root = null;
	}

	/**
	 * Returns the number of elements in this SpecialtySet. O(1) time
	 * 
	 * @return a count of the elements in this set
	 */
	public int size() {
		return size;
	}

	/**
	 * Driver Method Determine if element is in this tree O(lg n)
	 * 
	 * @param target
	 *            the element be looked for
	 * @return boolean - is element in tree
	 */
	public boolean contains(E data) {

		return contains(root, data);
	}

	/**
	 * This method recursively checks for the target in this subtree/node O(lg
	 * n)
	 * 
	 * @return boolean is target in tree
	 */
	private boolean contains(Node currentNode, E target) {
		// determine if this nodes data is < > = target's
		if (currentNode == null)
			return false;
		int value = currentNode.data.compareTo(target);

		if (value == 0)// found
			return true;
		if (value < 0)// search the right side
			return contains(currentNode.right, target);
		return contains(currentNode.left, target);
	}

	/**
	 * Adds an element to the tree non-recursively. If the element is in the
	 * tree the method will return
	 * 
	 * @param data
	 */
	public void add(E data) {
		// check if tree is empty
		if (root == null) {
			size++;
			root = new Node(data);
			return;
		}

		Node tempNode = root;
		// loop trough the tree to find position when found add and return
		while (true) {
			int value = tempNode.data.compareTo(data);

			if (value == 0)// set no duplicates
				return;
			else if (value < 0)// search right
			{
				if (tempNode.right == null)// found position
				{
					size++;
					tempNode.right = new Node(data);// add to right
					tempNode.right.parent = tempNode;// assign parent
					balance(tempNode);
					return;
				}
				tempNode = tempNode.right;
			} else {
				// search left
				if (tempNode.left == null)// found position
				{
					size++;
					tempNode.left = new Node(data);// add to left
					tempNode.left.parent = tempNode;// assign parent
					balance(tempNode);
					return;
				} else
					tempNode = tempNode.left;
			}
		}

	}

	/**
	 * Driver method guarantees that the specified data is removed if it is in
	 * the the set
	 * 
	 * @param data
	 *            a data value to be removed from the set
	 */

	public void remove(E target) {
		remove(root, target, 0);
	}

	/**
	 * This method searches for the element to be removed returns if it is not
	 * found calls a helper method to remove the item if it is found
	 */

	private void remove(Node current, E target, int direction) {
		// we have reached the bottom of the tree the value
		// to be romoved is not in the tree
		if (current == null)
			return;

		int value = current.data.compareTo(target);

		if (value > 0)// search the left
			remove(current.left, target, -1);
		else if (value < 0)// search the right
			remove(current.right, target, 1);
		else
			// target has been found remove it
			removeNode(current, direction);

	}

	/**
	 * This method deletes the found Node from the tree and retablishes
	 * connections
	 * 
	 * @param nodeToDelete
	 *            the node to be removed
	 * @param direction
	 *            0 if node is root - 1 if node is the left child of the parent
	 *            1 if the node is a right child of the parent.
	 */
	private void removeNode(Node nodeToDelete, int direction) {

		Node childNodePointer;
		Node nodeToDeletePointer;
		// if the node that needs to be replaced has two children find the
		// successor and swap the data values, reference will be taken care of
		// later in the method
		if (nodeToDelete.left != null && nodeToDelete.right != null) {
			nodeToDeletePointer = nodeToDelete.right;
			while (nodeToDeletePointer.left != null)
				nodeToDeletePointer = nodeToDeletePointer.left;
			// swap the data,refernces will be taken care below
			nodeToDelete.data = nodeToDeletePointer.data;
		} else
			nodeToDeletePointer = nodeToDelete;

		if (size != 1) {
			// At this point the pointer is pointing to a node that has either
			// 0 or one children want to point to a node before pointing to null
			if (nodeToDeletePointer.right != null)
				childNodePointer = nodeToDeletePointer.right;

			else
				childNodePointer = nodeToDeletePointer.left;

			// check to see if the node to delete is leaf, if not assign
			// it's child a new parent
			if (childNodePointer != null)
				childNodePointer.parent = nodeToDeletePointer.parent;

			// the node to deletepointer parent is null it only has one child
			// which should be the root
			if (nodeToDeletePointer.parent == null) {
				size--;
				root = childNodePointer;
				balance(root);
				return;
			}
			// change the reference to the value
			if (nodeToDeletePointer == nodeToDeletePointer.parent.right)
				nodeToDeletePointer.parent.right = childNodePointer;
			else if (nodeToDeletePointer == nodeToDeletePointer.parent.left)
				nodeToDeletePointer.parent.left = childNodePointer;
			// start balancing at the node we removed parent.
			balance(nodeToDeletePointer.parent);
			size--;
		}
		// size is one.. delete the root;
		else {
			size--;
			root = null;
			return;

		}

	}

	// //////////_____________Balancing Methods_________________\\\\\\\\\\\\

	/**
	 * This method looks at node and determines if balanced and if so if it
	 * needs which way it should be balanced, This method will call itself until
	 * the root has been reached,
	 * 
	 * @param current
	 *            the node that is currently being checked balance
	 * 
	 */

	private void balance(Node current) {

		if (current == null)// base case
			return;
		// if the parent is null it means it is the root, balance it
		if (current.parent == null)
			root = current;
		// get the difference
		int balanceFactor = (getHeight(current.left) - getHeight(current.right));

		// check to see if out of balance if load factor is -1,0,1
		// no rotations will happen and balance will be called on the parent

		// left side is "heavier"
		if (balanceFactor > 1) {
			// check if a extra rotation is needed
			if (getHeight(current.left.left) < getHeight(current.left.right))
				current.left = rotateWithRightChild(current.left);

			// will be in correct postion for this
			current = rotateWithLeftChild(current);
		}
		// right side is "heavier"
		else if (balanceFactor < -1) {
			// check if a extra rotation is needed
			if (getHeight(current.right.right) < getHeight(current.right.left))
				current.right = rotateWithLeftChild(current.right);

			current = rotateWithRightChild(current);

		}
		// keep going until the root is reached
		balance(current.parent);

	}

	/**
	 * This method returns the greater value of the current nodes left and right
	 * children height. This method is recursively called
	 * 
	 * @param current
	 *            the node of which to determines it height.
	 * 
	 * @return 0 if empty tree, 1 if node has no children, or the greater value
	 *         of the the left or right height.
	 * 
	 */

	private int getHeight(Node current) {
		// if the node being passed in is null this means that this is an empty
		// tree
		if (current == null)
			return 0;// base case
		// if currents node has no children it has a height of zero
		if (current.left == null && current.right == null)
			return 1; // base case

		// if the node has exactly one child find which child it is
		// and the find the height of that child, it has a child add 1
		else if (current.right == null || current.left == null) {
			if (current.left != null)
				return 1 + getHeight(current.left);
			if (current.right != null)
				return 1 + getHeight(current.right);
		}

		// node has two children find the max +1 for the child itself
		return 1 + Math.max(getHeight(current.left), getHeight(current.right));

	}

	/**
	 * 
	 * This method takes a unbalanced node and replace it with its left child
	 * (which will be returned by this method), it then places the left child's
	 * right node to to its left and assign parents accordingly. x and y are
	 * used in the comments to help go along with picture
	 * 
	 * @param unBalanced
	 *            the node that is unbalanced(left>right)
	 * @return the root of the balanced subtree(unbalanced.left)
	 */

	private Node rotateWithLeftChild(Node unBalanced) {

		//
		Node parent = unBalanced.parent;
		Node newRoot = unBalanced.left;
		Node newChild = newRoot.right;

		// assign y parent to x's parent (p)
		newRoot.parent = parent;

		// x.left equals y.right
		unBalanced.left = newChild;

		if (newChild != null)
			newChild.parent = unBalanced;

		newRoot.right = unBalanced;
		unBalanced.parent = newRoot;
		// is this the root?
		if (newRoot.parent == null)
			root = newRoot;
		// check to determine if newRoot is a right or left child
		else {
			// parent is still pointing to X see if it is right or left
			if (newRoot.parent.right == unBalanced) // is a right child
				newRoot.parent.right = newRoot;
			else if (newRoot.parent.left == unBalanced) // is a left child
				newRoot.parent.left = newRoot;
		}

		return newRoot;
	}

	/**
	 * 
	 * This method takes a unbalanced node and replace it with its left child
	 * (which will be returned by this method), it then places the right child's
	 * right node to to its right and assign parents accordingly. x and y are
	 * used in the comments to help visualize with pictures
	 * 
	 * @param unBalanced
	 *            the node that is unbalanced(left<right)
	 * @return the root of the balanced subtree(unbalanced.right)
	 */

	private Node rotateWithRightChild(Node unBalanced) {

		Node parent = unBalanced.parent; // x parent
		Node newRoot = unBalanced.right; // y
		Node newChild = newRoot.left;

		// assign y parent to x's parent (p)
		newRoot.parent = parent;

		// x.right equals y.left
		unBalanced.right = newChild;

		if (newChild != null)
			newChild.parent = unBalanced;

		newRoot.left = unBalanced;
		unBalanced.parent = newRoot;
		// is this the root?
		if (newRoot.parent == null)
			root = newRoot;
		// check to determine if newRoot is a right or left child
		else {
			// parent is still pointing to X see if it is right or left
			if (newRoot.parent.right == unBalanced) // is a right child
				newRoot.parent.right = newRoot;
			else if (newRoot.parent.left == unBalanced) // is a left child
				newRoot.parent.left = newRoot;
		}

		return newRoot;
	}

	// //////////_____________Node Class_________________\\\\\\\\\\\\\\\\\\

	/**
	 * A private helper class for the SpecialtySet class. Node objects are used
	 * to construct linked lists in a SpecialtySet.
	 * 
	 * Students are not allowed to change this class.
	 * 
	 * @author Peter Jensen
	 * @version 2/22/2014
	 */
	class Node {
		private E data; // The data element - may be changed after it is
						// assigned
		private Node parent; // Parent - initialized to null
		private Node left, right; // Children - initialized to null
		private int balance; // Height of this subtree - initialized to 0

		/**
		 * Builds this node to contain the specified data. By default, this node
		 * does not point to any other nodes
		 * 
		 * Also note, the data variable is final, the data reference cannot be
		 * changed. (This fact is largely irrelevant.)
		 * 
		 * @param data
		 *            the data to store in the node
		 */
		Node(E data) {
			this.data = data;
		}

		public E getData() {
			return data;
		}

		public int direction() {
			if (this.parent == null)
				return 0;
			else if (this == this.parent.left)
				return -1;
			else
				return 1;
		}
	}

	// /////////________Extra Credit__________\\\\\\\\\\\\\\\\

	/**
	 * This helper method uses a pre-order traversal to travese the tree and
	 * adds each node data vale in a singlestring
	 * 
	 * @param n
	 *            the node at which traversal begins
	 * @param s
	 *            a empty String
	 * @return a string contains the data values from each Node
	 */
	private String preorder(Node n, String s) {
		if (n == null) {
			return "";
		}
		s = (n.data + " ");
		s += preorder(n.left, s);
		s += preorder(n.right, s);
		s += ("/ ");

		return s;
	}

	/**
	 * This helper Method takes the String of data values generated from
	 * preorder and creats a string with the keys, indentation and new lines
	 * that the . tree format has.
	 * 
	 * @param s
	 *            a string contains the data values from each Node
	 * @return a string ready to be added to a .tree file
	 * 
	 */

	private String makeScanner(String s) {
		s.trim();

		int key = 0;
		String finalstring = "";
		String space = "";
		Scanner scan = new Scanner(s);
		Stack<Integer> num = new Stack<Integer>();
		Stack<String> strS = new Stack<String>();
		while (scan.hasNext()) {
			String str = scan.next();
			if (str.equals("/")) {

				finalstring.substring(2, finalstring.length());
				finalstring += strS.pop() + "</" + (num.pop()) + ">\n";

			} else {

				finalstring += space + "<" + key + " " + str + ">\n";
				strS.push(space);
				space += " ";
				num.push(key);
				key++;

			}

		}
		return (finalstring);
	}

	/**
	 * Writes out this set (as a tree) to the specified file in the .tree format
	 * from assignment #7.
	 * 
	 * @ f A file in the .tree format please!
	 */

	public void writeFile(File f) throws IOException {
		String s = "";
		s = preorder(this.root, s);
		s = makeScanner(s);
		try {

			String content = s;

			// if file doesnt exists, then create it

			if (!f.exists()) {
				f.createNewFile();

			}

			File f1 = f.getParentFile();
			f.renameTo(new File(f1, ".tree"));

			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
