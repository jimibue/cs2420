package homework10sum;

import java.util.*;



/**
 * Objects of this class represent one node in a
 * Huffman tree.  A Huffman tree is a binary two-tree, so
 * each node has a left and right child (or no children).  In addition,
 * a HuffmanNode object contains a totalFrequency count
 * that is the sum of the frequencies of the left and right
 * sub-node (if any).  Lastly, a HuffmanNode object keeps a list
 * of all the tokens stored in its subtree.
 * 
 * @author Peter Jensen - CS 2420
 * @version Summer 2010
 */
public class HuffmanNode implements Comparable<HuffmanNode>
{
    // These variables are not required, however, they make
    //   comparing nodes with equal frequencies easier.  Can you see why?
    private static int count = 0;
    private int objectCount = count++;
    
    // The sum of all the frequencies in all the leaf nodes at or below
    //   this node.
    private int totalFrequency;
    
    // A list of all the tokens contained at or below this node.
    private ArrayList<HuffmanToken> tokens;
    
    // The left and right children of this node.
    protected HuffmanNode left, right;
  
    /**
     * This constructor creates a leaf node in the Huffman tree.
     * A leaf node in a Huffman tree is mapped to a single token.
     * Use this token to initialize the frequency in this node.
     * 
     * @param  token  The token to store in this leaf node
     */   
    public HuffmanNode (HuffmanToken token)
    {
    	tokens = new ArrayList<HuffmanToken>();
    	tokens.add(token);
    	this.totalFrequency= token.getFrequency();
    	
	// Stubbed out
    }
    
    /**
     * This constructor creates an interior node in the Huffman tree.
     * It takes two non-null nodes as parameters, and it initializes
     * its fields by combining the frequency and tokens of the child nodes.
     * <p>&nbsp;<p>
     * 
     * This constructor has a major side effect.  To simplify building
     * of Huffman codes, this constructor will prepend Huffman code
     * bits to all of the tokens' codes stored below this point in the
     * Huffman tree.  All of the tokens in this node's left child subtree should
     * have a zero (false) bit prepended to their code, and all of the
     * tokens in this node's right child subtree should have a one (true) bit
     * prepended to their code.  Fortunately, each node contains a list
     * of the tokens in its subtree, so this step can be done without a
     * traversal.
     * <p>&nbsp;<p>
     * 
     * The advantage of this side effect is that when a Huffman tree has
     * been completely built, the Huffman codes for each token will also have
     * been built.
     * 
     * @param  left  The subtree to become the left child of this node
     * @param  right  The subtree to become the right child of this node
     */   
    public HuffmanNode (HuffmanNode left, HuffmanNode right)
    {
    	this.count = left.count + right.count;//?????
    	
    	tokens = new ArrayList<HuffmanToken>();
    	tokens.addAll(left.tokens);
    	tokens.addAll(right.tokens);
    	//for(HuffmanToken token: left.tokens)
    		//token.prependBitToCode(false);
    	//for(HuffmanToken token: right.tokens)
    		//token.prependBitToCode(true);
    	
    	this.left = left;
    	this.right = right;
    	this.totalFrequency = left.totalFrequency+right.totalFrequency;
    	
    }
    
    /**
     * Returns the left subtree of this node, or null if this
     * is a leaf node.
     * 
     * @return  The left subtree of this node
     */   
    public HuffmanNode getLeftSubtree ()
    {
	
        return this.left;
    }
    
    /**
     * Returns the right subtree of this node, or null if this
     * is a leaf node.
     * 
     * @return  The right subtree of this node
     */   
    public HuffmanNode getRightSubtree ()
    {
	
        return this.right;
    }
    
    /**
     * Returns true if this node is a leaf node, false otherwise.
     * 
     * @return  True if this node is a leaf node
     */   
    public boolean isLeafNode ()
    {
    	//if it has a child
    	if(right != null || left != null )
    		return false;
    	//no children
        return true;
    }
    
    /**
     * If this is a leaf node, this method returns the token
     * that was used to create this leaf node.  Null is returned
     * if this is not a leaf node.
     * 
     * @return  The token stored in this leaf node
     */   
    public HuffmanToken getToken ()
    {
    	if(this.isLeafNode())
    		return this.tokens.get(0);
        return null; 
    }
    
    /**
     * This method assumes that a HuffmanNode is stored in Object o.
     * (Note that a typecast is required in the method below.)
     * True is returned if this node is identical to the node in o,
     * and false otherwise.  (Hint -- just use your compareTo function
     * below.)
     * 
     * @param  o  An object that should be a HuffmanNode
     * @return  True if the this node equals the given node
     */   
    public boolean equals (Object o)
    
    {
    	//if it is not type HuffmanNode return false
    	if (!(o instanceof HuffmanNode ))
    			return false;
    	
    	//type cast to Huffman Node
    	if(this.compareTo((HuffmanNode)o)==0)
    		return true;
    	//not equal
        return false;
    }
    
    /**
     * This method determines which of two nodes is less than the other.
     * This is used to determine priority of nodes when they are
     * inserted into a priority queue.<p>&nbsp;<p>
     * 
     * Huffman nodes are compared by their frequency first, and in the
     * case of equal frequencies, by their object count second.  If their
     * frequencies and object counts are equal, the HuffmanNode objects
     * are equal.<p>&nbsp;<p>
     * 
     * This method returns:<p>
     * -1 if 'this' HuffmanNode object is less than 'node'<br>
     * 0 if 'this' HuffmanNode object is equal to 'node'<br>
     * 1 if 'this' HuffmanNode object is greater than to 'node'<br>
     * 
     * @param  node  An object that should be a HuffmanNode
     * @return  -1, 0, or 1, depending on the relative order of the nodes
     */   
    public int compareTo (HuffmanNode node)
    {
    	if(this.totalFrequency < node.totalFrequency)
    		return -1;
    	if(this.totalFrequency > node.totalFrequency)
    		return 1;
    	if(this.objectCount < node.objectCount)
    		return -1;
    	if(this.objectCount > node.objectCount)
    		return 1;
        return 0;
    }
}