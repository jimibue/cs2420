package homework07;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This is the class from which the program runs.  This class get the file and creates/draws
 * the tree.  This class is the Frame for the GUI
 * 
 * @author James
 *
 */
public class App extends JFrame implements ActionListener
{
	
	private CoordinatePanel treePanel;

	public static void main(String[] args) {
		//call constructor
		new App();
	
	}
	//constructor. 
	public App(){
		
		// part 1. get the file and create the array list
		FileProcessor f = new FileProcessor();
		Scanner s = f.getFileScanner();
		System.out.println(f.getFileName());
		List<String> list = f.makeArray(s);
		
		//create the tree
		Trees  tree = new Trees();
		tree.createTree(list);
		
		//find the size of the panel and positions of the nodes
		tree.findHeight(tree.levelList);
		tree.findWidth(tree.grid);
		tree.setNodesPos3(tree.root);
		
		//add Jmenu bar
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Return to root");
        JMenuItem returnToRoot = new JMenuItem("return to root node");
        returnToRoot.addActionListener(this);
        fileMenu.add(returnToRoot);
        menuBar.add(fileMenu);

		//make the panel containing the tree
        treePanel = new CoordinatePanel(tree);
		
		//make the JFrame with the name of the file
		JFrame frame = new JFrame(f.fileName);
		
		//set the layout and add treePanel and menuBar to a new panel.
		frame.setLayout(new BorderLayout());
		frame.add(menuBar,BorderLayout.NORTH);
		JPanel thePanel = new JPanel();
		thePanel.add(treePanel.getEnclosingPane());
		frame.add(thePanel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}

	/**
	 * listener for the return to root if clicked panel will reser to the root
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		   //Set the intail view to the root
      treePanel.clicked();
		
	}
	

}	
	

	

	






