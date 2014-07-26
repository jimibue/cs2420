package homework07;

/**
 * This class is a edited version of the CoordinatePanel used in Lab.
 * This class creates a scrollPanel who's paint method draws a tree
 * of any size but only part of it can be viewed.  
 * 
 */


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author pajensen and James Yeates
 *
 */
public class CoordinatePanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
    // Instance variables for our ScrollPaneDemo object..
    
    private JScrollPane enclosingPane;
    private int lastMouseX, lastMouseY;
    private Trees tree;
  
 
    
    public CoordinatePanel (Trees tree)
    
    {
    	this.setLayout(new BorderLayout());

    	this.tree = tree;
        
    	// Set a size
        
        this.setMinimumSize(new Dimension(tree.screenX, tree.screenY));
        this.setPreferredSize(new Dimension(tree.screenX, tree.screenY));

        // Add self mouse listeners to our panel.
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        //add Jmenu bar
        

        // Create a scroll pane to contain our oversized JPanel.
        
        enclosingPane = new JScrollPane (this);
        enclosingPane.setBackground(Color.YELLOW);
        
        // Limit the size of the scroll pane.  Otherwise, the frame will try to expand to 
        //   accommodate the entire size of the huge panel.
        
        enclosingPane.setMinimumSize(new Dimension(100, 100));
        enclosingPane.setPreferredSize(new Dimension(1000, 800));
        
        //Set the intail view to the root if the tree is not empty
        if(tree.root!=null){
        	JViewport view = enclosingPane.getViewport();
        	Point pos = view.getViewPosition();
        	view.setViewPosition(new Point(tree.root.getxPos()-400, 0));
        }
    }
    /**
     * this method when called will reset the panel back to the root
     */
    public void clicked(){
    	 JViewport view = enclosingPane.getViewport();
         Point pos = view.getViewPosition();
         view.setViewPosition(new Point(tree.root.getxPos()-400, 0));
         repaint();
    	
    }
    
    /**
     * 
     * */
    public JScrollPane getEnclosingPane ()
    {
        return enclosingPane;
    }
    
    /**
     * The paint method for drawing our panel.  Note that because our
     * panel is huge, and the visible window is small, the graphics object
     * will be set to 'clip' any drawing that occurs outside of the current
     * drawing area.  (The current drawing area can be quite small.  If the
     * window is scrolled, only the newly exposed region needs to be drawn.)
     * 
     * @param g a graphics object
     */
    public void paint ( Graphics g)
    {
        int upperLeftX = g.getClipBounds().x;
        int upperLeftY = g.getClipBounds().y;
        int visibleWidth = g.getClipBounds().width;
        int visibleHeight = g.getClipBounds().height;
        
        // Compute a position on the panel that is above and to the left of the currently
        //   exposed view.
        
        int firstX = Math.max(0, upperLeftX - upperLeftX % 100 - 100);
        int firstY = Math.max(0, upperLeftY - upperLeftY % 100 - 100);
        
        // Compute the last possible position of a coordinate in the visible space.
        
        int lastX = (upperLeftX + visibleWidth) - (upperLeftX + visibleWidth) % 100 + 200;
        int lastY = (upperLeftY + visibleHeight) - (upperLeftY + visibleHeight) % 100 + 200;
        
        // Ignore the fact that most of our panel is invisible.  Just draw everything.
        
        // Clear the background.
        
        g.setColor (Color.WHITE);
        g.fillRect(firstX, firstY, lastX - firstX, lastY - firstY);
        if(tree.root!=null){
        	depthFirst(tree.root,g);
        }
    }

    /**
     * Adjusts the scroll pane's view by an amount equal to the mouse motion.
     */
    public void mouseDragged (MouseEvent e)
    {
        // Compute the offset from the last mouse location.
        
        int deltaX = e.getX() - lastMouseX;
        int deltaY = e.getY() - lastMouseY;
        
        // Adjust the scroll pane by the delta.  Note that to move the
        //   surface with the mouse, we must move the view in the opposite
        //   direction.  This means that we subtract the delta.
        
        JViewport view = enclosingPane.getViewport();
        Point pos = view.getViewPosition();
        view.setViewPosition(new Point(pos.x - deltaX, pos.y - deltaY));

        // Keep track of the last mouse location.  Note:  Because we moved
        //   the view, the logical location of the mouse moved an equal amount
        //   in the same direction.
        
        lastMouseX = e.getX() - deltaX;
        lastMouseY = e.getY() - deltaY;
        
        // Make sure the JViewPort fully repaints.  This will make the 
        // out-of-bounds area appear all gray instead of giving a
        // torn appearance.
        
        view.repaint();
    }
    
    /**
     * This Method goes through the tree and draws the necessary parts of the tree.  This method is
     * called inside of the paint method
     * @param root Node
     * @param g Graphics
     */
    
	public void depthFirst(Node root, Graphics g)
	{
		
		//go through each ArrayList of each node
		for(Node n : root.children){
				
			    //set color to green for branches and leaves
				g.setColor(Color.GREEN);
				g.drawLine(root.getxPos()+root.boxLength/2, root.getyPos()+25, n.getxPos()+n.boxLength/2, n.getyPos());
				depthFirst(n,g);
		}
			//draw box and string
			g.setColor(Color.BLACK);
			g.drawRect(root.getxPos(), root.getyPos(), root.boxLength, 26);
			g.drawString(root.getData(), root.getxPos()+3, root.getyPos()+18);
			
	}

    /**
     * Keeps track of the last mouse position.
     */
    public void mousePressed (MouseEvent e)
    {
        // Keep track of the last mouse location.
        
        lastMouseX = e.getX();
        lastMouseY = e.getY();
    }
   
    // Unused event handlers
    
    public void mouseMoved (MouseEvent e)    {}
    public void mouseClicked (MouseEvent e)  {}
    public void mouseEntered (MouseEvent e)  {}
    public void mouseExited (MouseEvent e)   {}
    public void mouseReleased (MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}