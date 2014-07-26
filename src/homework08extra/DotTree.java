package homework08extra;

import homework08.SpecialtySet;

import java.io.File;
import java.io.IOException;

public class DotTree {
	
	public static void main(String[] args) {
		
		SpecialtySet<String> tester = new SpecialtySet<String>();
		
		tester.add("the");
		tester.add("quick");
		tester.add("brown");
		tester.add("fox");
		tester.add("jumped");
		tester.add("over");
		tester.add("the");
		tester.add("lazy");
		tester.add("dog");
		//String s = tester.InOrder(tester.root, "", 2);
		//System.out.println(s);
		//tester.PreOrder(tester.root,0,"");
		System.out.println();
//		String s =tester.preorder(tester.root,"");
//		String a =tester.makeScanner(s);
		File f = new File("/Users/jcc/Desktop/a.tree.txt");
		SpecialtySet<Integer> t = new SpecialtySet<Integer>();
		for(int i = 0; i<1000;i++)
			
			t.add(i);
		
		try {
			t.writeFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
	public String makeString(SpecialtySet<String> s)
	{
		
		return null;
	}

}



