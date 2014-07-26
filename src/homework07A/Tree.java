package homework07A;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Tree {
	
	Node root;
	
	public Tree(Scanner s)
	{
		root = new Node(s,null);
	}
	

	public static void main(String[] args) {
		
		//Scanner s = getFileScanner();
	
		String s2 ="<1 E> <2 N> <4 A> <7 L> <12 K> </12> </7> </4> <5 J> <8 H> </8> <9 I> </9> </5> </2> <3 F> <6 G> <10 0> " +
				"<13 B> </13> </10> <11 C> <14 D> </14> <15 M> </15> </11> </6> </3> </1> "; 
		Scanner s1 = new Scanner(s2);
		Scanner s3 = new Scanner("<1 A> <2 B> <3 C> </6> <3 D> </6>  </6> </6> ");
		Tree tree = new Tree(s3);
		tree.depthFirst(tree.root);
		
	
	
	}

	
	public void depthFirst(Node root)
	{
		for(Node n : root.children)
				
				depthFirst(n);
	
			//tester+=root.data;
			System.out.println(root.data +" child size; " + root.children.size() );
	}
	public static Scanner getFileScanner() {

		// Open a new window for user to choose a file
		JFileChooser fileChooser = new JFileChooser();

		int returnValue = fileChooser.showOpenDialog(null);

		// check to see if user selected a file, and if it was a valid file. If
		// not program closes
		if (returnValue == fileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();
			//fileName = file.getName();

			// create the scanner object, exit if file not found 
			try {
				Scanner s = new Scanner(file);
				return s;
			} catch (FileNotFoundException e) {

				System.exit(0);
			}

		} 
		//exit if user did not select a file
		else {
			System.exit(0);
		}
		//execution won't get here, but is needed to prevent a compiler error
		return null;

	}
	
	
	
	class Node{
		
		String data;
		ArrayList<Node> children = new ArrayList<Node>();
		Node parent;
		
		public Node(Scanner scan, Node parent)
		{
			while(scan.hasNext()){
			
				
			String temp = scan.next();
			if(temp.startsWith("</"))
			{
				
					
					return;
				
				
			}
			else
				
			{
				String s = scan.next();
				s=s.substring(0, s.length()-1);
				this.data = s;
				this.parent = parent;
				//this.parent = parent;
				this.children.add(new Node(scan, this));
				
				
				
					
				System.out.println(s);

					
				
			}
				
			}	
			

		}
		public Node(String data, Node parent)
		{
			this.data = data;
			this.parent = parent;
		}
		public Node()
		{
			
		}
		public Node(String data)
		{
			this.data = data;
		}
	
	

	}
}
