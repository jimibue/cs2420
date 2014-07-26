import java.io.File;
import java.io.IOException;


public class Test {

	public static void main(String[] args) {
		AVLTree1<Integer> a = new AVLTree1<Integer>();
		
		File f = new File("/Users/jcc/Desktop/CS2420Assignments.MY.TREE");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			a.writeFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
