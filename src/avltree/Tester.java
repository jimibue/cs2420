package avltree;

public class Tester {
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.add(1);
		
		if(!(tree.contains(1)))
			System.out.println("Failed");
		
	}

}
