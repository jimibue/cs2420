package avltree;


import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

// ------------------------------------------------------------------------------
/**
 * AVLTree is a non-recursive implementation of a self-balancing node-based AVL
 * binary search tree. The data elements contained in the tree must be mutually
 * comparable.
 * 
 * @author Michael D. Naper, Jr. <MichaelNaper.com>
 * @version 2010.07.08
 * 
 * @param <E>
 *            The generic type for data elements contained in the tree.
 */
public class AVLTree<E extends Comparable<? super E>> implements Iterable<E> {

    // ~ Nested Classes ........................................................

    // -------------------------------------------------------------------------
    /**
     * TreeNode is an implementation of a binary search tree node, which stores
     * a data element, references to each of its child nodes, and its height in
     * the tree.
     */
    private class TreeNode {

        // ~ Instance Variables ................................................

        /**
         * The data element stored in this node.
         */
        private E element;

        /**
         * The reference to the left child node, if present.
         */
        private TreeNode leftChild;

        /**
         * The reference to the right child node, if present.
         */
        private TreeNode rightChild;

        /**
         * The height of the node in the tree.
         */
        private int height;

        // ~ Constructors ......................................................

        // ---------------------------------------------------------------------
        /**
         * Constructs a new instance of TreeNode with the specified data element
         * and child node references.
         * 
         * @param element
         *            The element to be stored in the node.
         * @param leftChild
         *            The left child of the node.
         * @param rightChild
         *            The right child of the node.
         */
        public TreeNode(E element, TreeNode leftChild, TreeNode rightChild) {

            this.element = element;

            this.leftChild = leftChild;
            this.rightChild = rightChild;

            height = 0;
        }

        // ---------------------------------------------------------------------
        /**
         * Constructs a new instance of TreeNode with the specified data element
         * and no child node references.
         * 
         * @param element
         *            The element to be stored in the node.
         */
        public TreeNode(E element) {

            this(element, null, null);
        }
    }

    // -------------------------------------------------------------------------
    /**
     * TreeIterator is an iterator over the AVLTree.
     */
    private class TreeIterator implements Iterator<E> {

        // ~ Instance Variables ................................................

        /**
         * The current node in the iteration.
         */
        private TreeNode iterNode;

        /**
         * The stack that stores the nodes that are enqueued behind the current
         * node.
         */
        private Stack<TreeNode> nodePath = new Stack<TreeNode>();

        // ~ Constructors ......................................................

        // ---------------------------------------------------------------------
        /**
         * Constructs a new instance of TreeIterator.
         */
        public TreeIterator() {

            iterNode = root;
        }

        // ~ Methods ...........................................................

        // ---------------------------------------------------------------------
        /**
         * Returns true if the iterator has more data elements.
         * 
         * @return True if the iterator has more elements.
         */
        @Override
        public boolean hasNext() {

            return iterNode != null || !nodePath.isEmpty();
        }

        // ---------------------------------------------------------------------
        /**
         * Returns the next data element in the iteration.
         * 
         * @return The next element in the iteration.
         */
        @Override
        public E next() {

            E nextElement = null;

            while (iterNode != null) {
                nodePath.push(iterNode);

                iterNode = iterNode.leftChild;
            }

            if (!nodePath.isEmpty()) {
                iterNode = nodePath.pop();

                nextElement = iterNode.element;

                iterNode = iterNode.rightChild;
            }

            return nextElement;
        }

        // ---------------------------------------------------------------------
        /**
         * The removal method is unsupported by this implementation of Iterator.
         */
        @Override
        public void remove() {

            throw new UnsupportedOperationException();
        }
    }

    // ~ Instance Variables ....................................................

    /**
     * The reference to the root node of the tree, if present.
     */
    private TreeNode root;

    /**
     * The size of the tree.
     */
    private int size;

    // ~ Constructors ..........................................................

    // -------------------------------------------------------------------------
    /**
     * Constructs a new, empty instance of AVLTree.
     */
    public AVLTree() {

        root = null;

        size = 0;
    }

    // ~ Methods ...............................................................

    // -------------------------------------------------------------------------
    /**
     * Inserts the specified data element into this AVLTree, if the element is
     * not already present.
     * 
     * @param element
     *            The element to be inserted.
     * @return True if insertion is performed.
     */
    public boolean add(E element) {

        if (element == null) {
            return false;
        }

        Stack<TreeNode> nodePath = new Stack<TreeNode>();

        TreeNode current = root;

        while (current != null) {
            nodePath.push(current);

            int cmp = element.compareTo(current.element);

            if (cmp < 0) {
                if (current.leftChild == null) {
                    current.leftChild = new TreeNode(element);

                    size++;

                    rebalanceTree(nodePath, true);

                    return true;
                }

                current = current.leftChild;
            } else if (cmp > 0) {
                if (current.rightChild == null) {
                    current.rightChild = new TreeNode(element);

                    size++;

                    rebalanceTree(nodePath, true);

                    return true;
                }

                current = current.rightChild;
            } else {
                return false; // Element is already stored in tree
            }
        }

        // Tree is empty:
        root = new TreeNode(element);

        size++;

        return true;
    }

    // -------------------------------------------------------------------------
    /**
     * Inserts all the data elements in the specified Collection into this
     * AVLTree, if the element is not already present.
     * 
     * @param elements
     *            The collection of elements to be inserted.
     * @return True if insertion is performed at least once.
     */
    public boolean insertAll(Collection<? extends E> elements) {

        if (elements == null) {
            return false;
        }

        boolean hasInsertion = false;

        for (E element : elements) {
            hasInsertion = add(element) || hasInsertion;
        }

        return hasInsertion;
    }

    // -------------------------------------------------------------------------
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

    // -------------------------------------------------------------------------
    /**
     * Returns true if this AVLTree contains all the data elements in the
     * specified Collection.
     * 
     * @param elements
     *            The collection of elements to be checked for containment.
     * @return True if this AVLTree contains all the elements in the specified
     *         collection.
     */
    public boolean containsAll(Collection<? extends E> elements) {

        if (elements == null) {
            return false;
        }

        for (E element : elements) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    // -------------------------------------------------------------------------
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

        TreeNode current = root;

        while (current != null) {
            int cmp = element.compareTo(current.element);

            if (cmp < 0) {
                current = current.leftChild;
            } else if (cmp > 0) {
                current = current.rightChild;
            } else {
                return current.element;
            }
        }

        return null;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the data element from this AVLTree with the smallest key, if the
     * element is present.
     * 
     * @return The element with the smallest key, or null if the tree is empty.
     */
    public E findMin() {

        if (root == null) {
            return null;
        }

        TreeNode current = root;

        while (current.leftChild != null) {
            current = current.leftChild;
        }

        return current.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the data element from this AVLTree with the largest key, if the
     * element is present.
     * 
     * @return The element with the largest key, or null if the tree is empty.
     */
    public E findMax() {

        if (root == null) {
            return null;
        }

        TreeNode current = root;

        while (current.rightChild != null) {
            current = current.rightChild;
        }

        return current.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes the specified data element from this AVLTree, if the element is
     * present.
     * 
     * @param element
     *            The element to be removed.
     * @return True if removal is performed.
     */
    public boolean remove(E element) {

        if (element == null) {
            return false;
        }

        Stack<TreeNode> nodePath = new Stack<TreeNode>();

        TreeNode current = root;

        while (current != null) {
            nodePath.push(current);

            int cmp = element.compareTo(current.element);

            if (cmp < 0) {
                if (current.leftChild != null
                        && element.equals(current.leftChild.element)) {
                    current.leftChild = removeNode(current.leftChild);

                    size--;

                    rebalanceTree(nodePath, false);

                    return true;
                }

                current = current.leftChild;
            } else if (cmp > 0) {
                if (current.rightChild != null
                        && element.equals(current.rightChild.element)) {
                    current.rightChild = removeNode(current.rightChild);

                    size--;

                    rebalanceTree(nodePath, false);

                    return true;
                }

                current = current.rightChild;
            } else {
                root = removeNode(current); // Root is to be removed

                size--;

                rebalanceTree(nodePath, false);

                return true;
            }
        }

        return false;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes the specified node from the AVLTree, returning an appropriate
     * replacement node.
     * 
     * @param removalNode
     *            The node to be removed from the tree.
     * @return The node to replace the removed node.
     */
    private TreeNode removeNode(TreeNode removalNode) {

        TreeNode replacementNode;

        if (removalNode.leftChild != null && removalNode.rightChild != null) {
            replacementNode = fetchSuccessor(removalNode);

            replacementNode.leftChild = removalNode.leftChild;
            replacementNode.rightChild = removalNode.rightChild;

            if (height(replacementNode.leftChild)
                    - height(replacementNode.rightChild) == 2) {
                if (height(replacementNode.leftChild.leftChild) >= height(replacementNode.leftChild.rightChild)) {
                    replacementNode = rotateWithLeftChild(replacementNode);
                } else {
                    replacementNode = doubleRotateWithLeftChild(replacementNode);
                }
            }

            replacementNode.height = Math.max(
                    height(replacementNode.leftChild),
                    height(replacementNode.rightChild)) + 1;
        } else {
            replacementNode = (removalNode.leftChild != null) ? removalNode.leftChild
                    : removalNode.rightChild;
        }

        removalNode.leftChild = null;
        removalNode.rightChild = null;

        return replacementNode;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes and returns the node that is the logical in-order successor of
     * the specified subtree root node.
     * 
     * @param sRoot
     *            The root of the subtree of which to fetch the logical
     *            successor node.
     * @return The successor node of the specified subtree root node.
     */
    private TreeNode fetchSuccessor(TreeNode sRoot) {

        if (sRoot == null || sRoot.rightChild == null) {
            return null;
        }

        TreeNode successorNode = sRoot.rightChild;

        if (sRoot.rightChild.leftChild == null) {
            sRoot.rightChild = successorNode.rightChild;

            return successorNode;
        } else {
            Stack<TreeNode> nodePath = new Stack<TreeNode>();

            nodePath.push(sRoot);

            TreeNode current = sRoot.rightChild;

            while (current.leftChild.leftChild != null) {
                nodePath.push(current);

                current = current.leftChild;
            }

            nodePath.push(current);

            successorNode = current.leftChild;

            current.leftChild = current.leftChild.rightChild;

            rebalanceTreeAfterFetchSuccessor(nodePath);

            return successorNode;
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Restores balance to the tree after a node successor has been fetched
     * given the specified node traversal path.
     * 
     * @param nodePath
     *            The Stack which contains the nodes in the order that they were
     *            traversed.
     */
    private void rebalanceTreeAfterFetchSuccessor(Stack<TreeNode> nodePath) {

        TreeNode current;

        while (nodePath.size() > 2) {
            current = nodePath.pop();

            if (height(current.rightChild) - height(current.leftChild) == 2) {
                if (height(current.rightChild.rightChild) >= height(current.rightChild.leftChild)) {
                    nodePath.peek().leftChild = rotateWithRightChild(current);
                } else {
                    nodePath.peek().leftChild = doubleRotateWithRightChild(current);
                }
            }

            current.height = Math.max(height(current.leftChild),
                    height(current.rightChild)) + 1;
        }

        // Current node is root of right subtree of removal node:
        current = nodePath.pop();

        if (height(current.rightChild) - height(current.leftChild) == 2) {
            if (height(current.rightChild.rightChild) >= height(current.rightChild.leftChild)) {
                nodePath.peek().rightChild = rotateWithRightChild(current);
            } else {
                nodePath.peek().rightChild = doubleRotateWithRightChild(current);
            }
        }

        current.height = Math.max(height(current.leftChild),
                height(current.rightChild)) + 1;
    }

    // -------------------------------------------------------------------------
    /**
     * Restores balance to the tree given the specified node traversal path and
     * whether insertion or removal was performed.
     * 
     * @param nodePath
     *            The Stack which contains the nodes in the order that they were
     *            traversed.
     * @param isInsertion
     *            Indicates whether insertion or removal was performed.
     */
    private void rebalanceTree(Stack<TreeNode> nodePath, boolean isInsertion) {

        TreeNode current;

        while (!nodePath.empty()) {
            current = nodePath.pop();

            // Check for an imbalance at the current node:
            if (height(current.leftChild) - height(current.rightChild) == 2) {
                // Compare heights of subtrees of left child node of
                // imbalanced node (check for single or double rotation
                // case):
                if (height(current.leftChild.leftChild) >= height(current.leftChild.rightChild)) {
                    // Check if imbalance is internal or at the tree root:
                    if (!nodePath.empty()) {
                        // Compare current element with element of parent
                        // node (check which child reference to update for the
                        // parent node):
                        if (current.element.compareTo(nodePath.peek().element) < 0) {
                            nodePath.peek().leftChild = rotateWithLeftChild(current);
                        } else {
                            nodePath.peek().rightChild = rotateWithLeftChild(current);
                        }
                    } else {
                        root = rotateWithLeftChild(current);
                    }
                } else {
                    if (!nodePath.empty()) {
                        if (current.element.compareTo(nodePath.peek().element) < 0) {
                            nodePath.peek().leftChild = doubleRotateWithLeftChild(current);
                        } else {
                            nodePath.peek().rightChild = doubleRotateWithLeftChild(current);
                        }
                    } else {
                        root = doubleRotateWithLeftChild(current);
                    }
                }

                current.height = Math.max(height(current.leftChild),
                        height(current.rightChild)) + 1;

                if (isInsertion) {
                    break;
                }
            } else if (height(current.rightChild) - height(current.leftChild) == 2) {
                if (height(current.rightChild.rightChild) >= height(current.rightChild.leftChild)) {
                    if (!nodePath.empty()) {
                        if (current.element.compareTo(nodePath.peek().element) < 0) {
                            nodePath.peek().leftChild = rotateWithRightChild(current);
                        } else {
                            nodePath.peek().rightChild = rotateWithRightChild(current);
                        }
                    } else {
                        root = rotateWithRightChild(current);
                    }
                } else {
                    if (!nodePath.empty()) {
                        if (current.element.compareTo(nodePath.peek().element) < 0) {
                            nodePath.peek().leftChild = doubleRotateWithRightChild(current);
                        } else {
                            nodePath.peek().rightChild = doubleRotateWithRightChild(current);
                        }
                    } else {
                        root = doubleRotateWithRightChild(current);
                    }
                }

                current.height = Math.max(height(current.leftChild),
                        height(current.rightChild)) + 1;

                if (isInsertion) {
                    break;
                }
            } else {
                current.height = Math.max(height(current.leftChild),
                        height(current.rightChild)) + 1;
            }
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Removes all the data elements in the specified Collection from this
     * AVLTree, if the element is present.
     * 
     * @param elements
     *            The collection of elements to be removed.
     * @return True if removal is performed at least once.
     */
    public boolean removeAll(Collection<? extends E> elements) {

        if (elements == null) {
            return false;
        }

        boolean hasRemoval = false;

        for (E element : elements) {
            hasRemoval = remove(element) || hasRemoval;
        }

        return hasRemoval;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes and returns the data element from this AVLTree with the smallest
     * key, if the element is present.
     * 
     * @return The element with the smallest key, or null if the tree is empty.
     */
    public E removeMin() {

        if (root == null) {
            return null;
        }

        TreeNode minNode;

        if (root.leftChild == null) {
            minNode = root;

            root = minNode.rightChild;
        } else {
            Stack<TreeNode> nodePath = new Stack<TreeNode>();

            TreeNode current = root;

            while (current.leftChild.leftChild != null) {
                nodePath.push(current);

                current = current.leftChild;
            }

            nodePath.push(current);

            minNode = current.leftChild;

            current.leftChild = minNode.rightChild;

            rebalanceTree(nodePath, false);
        }

        size--;

        return minNode.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Removes and returns the data element from this AVLTree with the largest
     * key, if the element is present.
     * 
     * @return The element with the largest key, or null if the tree is empty.
     */
    public E removeMax() {

        if (root == null) {
            return null;
        }

        TreeNode maxNode;

        if (root.rightChild == null) {
            maxNode = root;

            root = maxNode.leftChild;
        } else {
            Stack<TreeNode> nodePath = new Stack<TreeNode>();

            TreeNode current = root;

            while (current.rightChild.rightChild != null) {
                nodePath.push(current);

                current = current.rightChild;
            }

            nodePath.push(current);

            maxNode = current.rightChild;

            current.rightChild = maxNode.leftChild;

            rebalanceTree(nodePath, false);
        }

        size--;

        return maxNode.element;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the height of the specified node in the tree or -1 if the node is
     * null.
     * 
     * @param node
     *            The node of which to get the height.
     * @return The height of the node in the tree or -1 if the node is null.
     */
    private int height(TreeNode node) {

        return (node == null) ? -1 : node.height;
    }

    // -------------------------------------------------------------------------
    /**
     * Rotates a node with its left child.
     * 
     * @param sRoot
     *            The root of the subtree with which to rotate the node's left
     *            child.
     * @return The node that is the new root of the specified root's subtree.
     */
    private TreeNode rotateWithLeftChild(TreeNode sRoot) {

        TreeNode newRoot = sRoot.leftChild;

        sRoot.leftChild = newRoot.rightChild;
        newRoot.rightChild = sRoot;

        sRoot.height = Math.max(height(sRoot.leftChild),
                height(sRoot.rightChild)) + 1;
        newRoot.height = Math.max(height(newRoot.leftChild), sRoot.height) + 1;

        return newRoot;
    }

    // -------------------------------------------------------------------------
    /**
     * Rotates a node with its right child.
     * 
     * @param sRoot
     *            The root of the subtree with which to rotate the node's right
     *            child.
     * @return The node that is the new root of the specified root's subtree.
     */
    private TreeNode rotateWithRightChild(TreeNode sRoot) {

        TreeNode newRoot = sRoot.rightChild;

        sRoot.rightChild = newRoot.leftChild;
        newRoot.leftChild = sRoot;

        sRoot.height = Math.max(height(sRoot.leftChild),
                height(sRoot.rightChild)) + 1;
        newRoot.height = Math.max(sRoot.height, height(newRoot.rightChild)) + 1;

        return newRoot;
    }

    // -------------------------------------------------------------------------
    /**
     * Double rotates a node with its left child.
     * 
     * @param sRoot
     *            The root of the subtree with which to double rotate the node's
     *            left child.
     * @return The node that is the new root of the specified root's subtree.
     */
    private TreeNode doubleRotateWithLeftChild(TreeNode sRoot) {

        sRoot.leftChild = rotateWithRightChild(sRoot.leftChild);

        return rotateWithLeftChild(sRoot);
    }

    // -------------------------------------------------------------------------
    /**
     * Double rotates a node with its right child.
     * 
     * @param sRoot
     *            The root of the subtree with which to double rotate the node's
     *            right child.
     * @return The node that is the new root of the specified root's subtree.
     */
    private TreeNode doubleRotateWithRightChild(TreeNode sRoot) {

        sRoot.rightChild = rotateWithLeftChild(sRoot.rightChild);

        return rotateWithRightChild(sRoot);
    }

    // -------------------------------------------------------------------------
    /**
     * Removes all the data elements from this AVLTree. The AVLTree will be
     * empty after this method returns.
     */
    public void clear() {

        root = null;

        size = 0;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns true if this AVLTree contains no data elements. In other words,
     * returns true if the size of this AVLTree is zero.
     * 
     * @return True if this AVLTree contains no elements.
     */
    public boolean isEmpty() {

        return root == null;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns the number of data elements in this AVLTree.
     * 
     * @return The number of elements in this AVLTree.
     */
    public int size() {

        return size;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns an iterator over the data elements in this AVLTree in order.
     * 
     * @return An Iterator over the elements in this AVLTree.
     */
    public Iterator<E> iterator() {

        return new TreeIterator();
    }

    // -------------------------------------------------------------------------
    /**
     * Returns an array containing all the data elements in this AVLTree in
     * order.
     * 
     * @return An array containing all the elements in this AVLTree.
     */
    @SuppressWarnings("rawtypes")
    public Comparable[] toArray() {

        Comparable[] result = new Comparable[size];

        int pos = 0;

        Stack<TreeNode> nodePath = new Stack<TreeNode>();

        boolean isDone = false;

        TreeNode current = root;

        while (!isDone) {
            if (current != null) {
                nodePath.push(current);

                current = current.leftChild;
            } else {
                if (!nodePath.isEmpty()) {
                    current = nodePath.pop();

                    result[pos++] = current.element;

                    current = current.rightChild;
                } else {
                    isDone = true;
                }
            }
        }

        return result;
    }

    // -------------------------------------------------------------------------
    /**
     * Returns a string representation of this AVLTree. The returned string is a
     * sideways tree diagram representing the organization of the data elements
     * in the tree.
     * 
     * @return A string representation of this AVLTree.
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        if (root == null) {
            return result.toString();
        }

        // Encapsulates a tree node and its level in the tree:
        class StackNode {

            private TreeNode node;
            private int level;

            public StackNode(TreeNode node, int level) {

                this.node = node;
                this.level = level;
            }
        }

        Stack<StackNode> nodePath = new Stack<StackNode>();

        int pathLevel = 0;

        boolean isDone = false;

        TreeNode current = root;

        while (!isDone) {
            if (current != null) {
                nodePath.push(new StackNode(current, pathLevel));

                current = current.leftChild;

                pathLevel++;
            } else {
                if (!nodePath.isEmpty()) {
                    StackNode lastNode = nodePath.pop();

                    current = lastNode.node;

                    pathLevel = lastNode.level;

                    for (int i = 0; i < pathLevel; i++) {
                        result.append("---");
                    }

                    if (pathLevel > 0) {
                        result.append(" ");
                    }

                    result.append(current.element + "\n");

                    current = current.rightChild;

                    pathLevel++;
                } else {
                    isDone = true;
                }
            }
        }

        return result.toString();
    }
}