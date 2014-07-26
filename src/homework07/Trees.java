package homework07;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Stack;
/**
 * This class represent a tree who Nodes can contain multiple children so long as they are not duplicates.
 * @author James Yeates
 *
 * @version 3/10/2014
 */

public class Trees {
	
	//private String tester ="";//used for testing
	protected Node root;
	private Stack<Node> theStack;
	protected int maxHeight,maxWidth, screenX,screenY;
	
	protected Map <Integer,Integer> grid;
	protected Map <Integer,Integer> charCount;
	protected Map<Integer, ArrayList<Node>> levelList;
	private int totalNodes,level;


	//constructor
	public Trees(){
		grid = new HashMap<Integer,Integer>();
		levelList = new HashMap<Integer,ArrayList<Node>>();
		theStack = new Stack<Node>();
		
	}

	/**
	 * Takes the array list created from the .tree file and creates a tree
	 * also creates maps that
	 * @param s List<String>
	 */
	
	public void createTree(List<String> s)
	{
		//empty list return
		if(s.size()==0)
			return;
	
		
		//set instance vairables
		totalNodes=0;
		level=0;
		String temp = s.get(0);
		
		//add the root
		root = new Node(temp);
		totalNodes++;
		theStack.add(root);
		root.setBoxLength();
		
		grid.put(0, 1);
		ArrayList<Node> tempList = new ArrayList<Node>();
		tempList.add(root);
		
		levelList.put(0, tempList);
		
		// -go through the arraylist and push/ pop items on the stack as needed
		//- create new nodes and add children nodes to the appropriate list
		// -populates maps
		for(int i =1; i< s.size();i++){
			
			// get the next string
			temp = s.get(i);
		
			//if string is not '/' means we have a new node to construct so
			// increment level and nodes
			if(!(temp.equals("/")))
			{
				level++;
				totalNodes++;
				
				//make the node
				Node tempNode = new Node (temp,totalNodes,level);
				
				//determine how big the box needs to be when this is drawn
				tempNode.setBoxLength();
				populateMaps(level, tempNode);
				
				// add this node to array list of the node on top of the stack, which is it parent
				//add this node to the stack
				Node topNode = theStack.peek();
				topNode.children.add(tempNode);
				theStack.add(tempNode);
				
				
			}
			// if '/' was next set the total of nodes for the node on the stack and pop it because
			// it has no more children.
			else{
				level--;
				theStack.peek().setTotalNodes(totalNodes);
				theStack.pop();
				
			}
				
		}
		
	}
	/**
	 * this method is called in side of createTree() and populates the maps for this tree
	 * @param level integer
	 * @param n Node
	 */
	public void populateMaps(int level, Node n)
	{
		
		//if level is in add the node to the list 
		if(levelList.containsKey(level))
		{
			levelList.get(level).add(n);
			Integer newSize = grid.get(level) +1;
			
			grid.put(level,newSize);
		}
		//create a new key and list and put it in the map
		else
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			temp.add(n);
			levelList.put(level, temp);
			grid.put(level, 1);
			
		}
	}
	/**
	 *  calculates the height (in total Nodes) of the height used to
	 *  determine the size of the panel that will contain the tree
	 * @param m Map
	 */
	
	public void findHeight(Map <Integer,ArrayList<Node>> m){
		 maxHeight = m.keySet().size();
	}
	/**
	 *  calculates the width (in total Nodes) of the height used to
	 *  determine the size of the panel that will contain the tree
	 * @param m Map
	 */
	public void findWidth(Map<Integer,Integer> m){
		int max = 0;
		for(int x : m.values()){
			if (x>max)
				max=x;
		}
		maxWidth = max;
	}
	/**
	 * this method uses the maps and determines the positions  of the nodes
	 * in a fashion similar to breadth first traversal
	 * @param root Node
	 */


	public void setNodesPos3(Node root)
	{
		// check for empty tree set size to a default value
		if (root == null){
			screenY = 100;
			screenX = 800;
			return;
		}

		//int totalNodes = root.getTotalNodes();// was not used
		int max =0;
		//loops through each level of the tree
		for(int i = 0; i< maxHeight;i++)
		{
			
			int width = levelList.get(i).size();
			int x=0;
			int thisScreenX = width *100+100;
			
			//loops through each node of the current level
			for(int j = 0; j<width; j++){
				
				levelList.get(i).get(j).setyPos(i*100+100);
				levelList.get(i).get(j).setxPos((j+1)*((maxWidth+1)/(width +1)*100)+x);
				x+=levelList.get(i).get(j).boxLength;
	
			}
			//determine the widest width
			thisScreenX+= x;
			if(thisScreenX> max)
				max=thisScreenX;
		}
		//set the size of the panel for the tree
		screenY = maxHeight *100+200;
		screenX = max;
	}
	public void setNodesPosAVL(Node root)
	{
		// check for empty tree set size to a default value
		if (root == null){
			screenY = 100;
			screenX = 800;
			return;
		}

		//int totalNodes = root.getTotalNodes();// was not used
		int max =0;
		//loops through each level of the tree
		for(int i = 0; i< maxHeight;i++)
		{
			
			int width = levelList.get(i).size();
			int x=0;
			int thisScreenX = width *100+100;
			
			//loops through each node of the current level
			for(int j = 0; j<width; j++){
				
				levelList.get(i).get(j).setyPos(i*100+100);
				levelList.get(i).get(j).setxPos((j+1)*((maxWidth+1)/(width +1)*100)+x);
				x+=levelList.get(i).get(j).boxLength;
	
			}
			//determine the widest width
			thisScreenX+= x;
			if(thisScreenX> max)
				max=thisScreenX;
		}
		//set the size of the panel for the tree
		screenY = maxHeight *100+200;
		screenX = max;
	}
}
