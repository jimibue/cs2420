package homework07;


import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the nodes of the tree and information to be drawn on a GUI
 * @author James Yeates
 *
 */

public class Node {
	//instance variables
	protected List <Node> children = new ArrayList<Node>();
	protected String data;
	protected int preNodes,  totalNodes, level, boxLength;
	protected int xPos,  yPos;
	

	//constructor
	Node(String data){
		this.data = data;
	
	}
	//constructor
	Node(String data, int currentNodes){
		this.data = data;
		preNodes = currentNodes;
		
	}
	//constructor
	Node(String data, int currentNodes, int level){
		this.data = data;
		preNodes = currentNodes;
		this.level = level;
		
	}
	/**
	 * sets the total nodes (ancestors) for this Node
	 * @param postNodes int
	 */
	public void setTotalNodes(int postNodes)
	{
		totalNodes = postNodes-preNodes;
	}
	/**
	 * gets the total nodes (ancestors) for this Node
	 * 
	 */
	public int getTotalNodes()
	{
		return totalNodes;
	}
	/**
	 * sets the level for this Node
	 * @param level int
	 */
	public void setLevel(int level)
	{
		this.level=level;
	}
	/**
	 * gets the data
	 * @return String
	 */
	public String getData(){
		return data;
	}
	/**
	 * sets the size of the box for this Node
	 */
	
	public void setBoxLength(){
		boxLength = data.length()*8 +8;
	}
	/**
	 * gets the xPos for this box
	 * @return integer
	 */
	public int getxPos() {
		return xPos;
	}
	/**
	 * sets the xPos for this box
	 * @param xPos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	/**
	 * gets the yPos for this box
	 * @return integer
	 */
	public int getyPos() {
		return yPos;
	}
	/**
	 * sets the yPos for this box
	 * @param yPos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	


	
	
}
