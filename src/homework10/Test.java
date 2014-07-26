package homework10;




import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		HuffmanCompressor comp = new HuffmanCompressor();
		
		
		byte[] x  =HuffmanTools.readBytesFromFile("/Users/jcc/Desktop/Yankee.txt");
	
		

		x= comp.compress(x);
		HuffmanTools.writeBytesToFile("/Users/jcc/Desktop/Compressed.txt", x);

		byte []y = comp.decompress(x);
		HuffmanTools.writeBytesToFile("/Users/jcc/Desktop/Uncompressed.txt", y);
		
		File org = new File("/Users/jcc/Desktop/Yankee.txt");
		
		File cmp = new File("/Users/jcc/Desktop/Uncompressed.txt");
		
		Scanner orgScan = new Scanner(org);
		Scanner comScan = new Scanner(cmp);
		
		while(orgScan.hasNext())
		{
			if(!(comScan.hasNext()))
					System.out.println("fail 1");
			if(!(orgScan.next().equals(comScan.next())))
				System.out.println("fail");
			
		}
		orgScan.close();
		comScan.close();
		System.out.println("done");
		
		
		
	
	}
	
	


}


