package homework10;


import static org.junit.Assert.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the J Unit test to test the compressor for homework 10
 * requires the .txt files uploaded with this assignment (Ulysses.txt,
 * Compressed.txt, Uncompressed.txt, and Yankee.txt) to be add the project
 * folder running this test or test 3 and test 4 will not work
 * 
 * @author James Yeates
 *
 */
public class UnitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * This tests compresions that only has one element
	 */
	@Test
	public void testA() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s = "a";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	/**
	 * This tests compresions that only has one element
	 */
	@Test
	public void testB() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s = "AAAAAAAAAA";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	/**
	 * This tests compresions that only has no elements
	 */
	@Test
	public void testC() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s = "";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	/**
	 * simple test that compresses and decompress "HI"
	 */

	@Test
	public void test() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s = "HI";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	@Test
	public void test1() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s ="ABABABBABABABABABABBABABABACDCDCDDCDCDCEFEFEFEFEFFELLLLLLLL" +
				"HJHKHJHKHKJHYUIYIUYIUYIUYIUYIUYIUYIUYIUYIUYIU";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	@Test
	public void test2() {
		HuffmanCompressor comp = new HuffmanCompressor();
		String s = "Hello how are you what is" +
				"up I don;'t 1234567790 ^&*^(^&*^(*^*&^	rwtyueiuyiouyOIYIAYSUjgjhagsjdgGJKGKJASGDJ" +
				"HAKLSHDKAHSKDJHIUAf i sdfaskjdf l jsjawiefbcvmnbzx," +
				"mcbv..>?<?><?>M>MZXCZVZFSHD,.<V`1~~1231" +
				"QWJEHRLQHWEKRH |}{[[]] THEJHSKH LASDIFUYIWE 215346!@##$%^&%^*%^(*)(*&^$%^#";
		byte [] a = s.getBytes();
		byte [] com = comp.compress(a);
		byte [] dec = comp.decompress(com);
		
		String t =HuffmanTools.createStringFromBytes(dec);
		
		assertTrue(s.equals(t));
	}
	/**
	 * This test compress a large file(Yankee.txt) and decompress it back to its orginal
	 * form it then checks if the decompressed file is the exact same, by comparing
	 * each token of the orignal file and the decompressed file.  Also checks to see 
	 * that the compressed size is apprioate
	 */
	@Test
	public void test3()
	{
	HuffmanCompressor comp = new HuffmanCompressor();
		
		
		byte[] x  =HuffmanTools.readBytesFromFile("Yankee.txt");
		assertEquals(676516, x.length);
		
		x= comp.compress(x);
		assertTrue(x.length < 386000);
		
		HuffmanTools.writeBytesToFile("Compressed.txt", x);
		
		

		byte []y = comp.decompress(x);
		HuffmanTools.writeBytesToFile("Uncompressed.txt", y);
		assertEquals(676516, y.length);
		File org = new File("Yankee.txt");
		
		File cmp = new File("Uncompressed.txt");
		
		Scanner orgScan;
		try {
			orgScan = new Scanner(org);
			Scanner comScan = new Scanner(cmp);
			while(orgScan.hasNext())
			{
				assertTrue(comScan.hasNext());
				assertEquals(true, orgScan.next().equals(comScan.next()));
				
				
			}
			
			orgScan.close();
			comScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	/**
	 * This test compress a large file(Ulyses.txt) and decompress it back to its orginal
	 * form it then checks if the decompressed file is the exact same, by comparing
	 * each token of the orignal file and the decompressed file.  Also checks to see 
	 * that the compressed size is apprioate
	 */
	@Test
	public void test4()
	{
	HuffmanCompressor comp = new HuffmanCompressor();
		
		
		byte[] x  =HuffmanTools.readBytesFromFile("Ulysses.txt");
		assertEquals(1561677, x.length);
		
		x= comp.compress(x);
		assertTrue(x.length < 960000);
		
		HuffmanTools.writeBytesToFile("Compressed.txt", x);
		
		

		byte []y = comp.decompress(x);
		HuffmanTools.writeBytesToFile("Uncompressed.txt", y);
		assertEquals(1561677, y.length);
		File org = new File("Ulysses.txt");
		
		File cmp = new File("Uncompressed.txt");
		
		Scanner orgScan;
		try {
			orgScan = new Scanner(org);
			Scanner comScan = new Scanner(cmp);
			while(orgScan.hasNext())
			{
				assertTrue(comScan.hasNext());
				assertEquals(true, orgScan.next().equals(comScan.next()));
				
				
			}
			
			orgScan.close();
			comScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
	}
	
	/**
	 * This test compress a large file(Ulyses.txt) and decompress it back to its orginal
	 * form it then checks if the decompressed file is the exact same, by comparing
	 * each token of the orignal file and the decompressed file.  Also checks to see 
	 * that the compressed size is apprioate
	 */
	@Test
	public void test5()
	{
	HuffmanCompressor comp = new HuffmanCompressor();
		
		
		byte[] x  =HuffmanTools.readBytesFromFile("80Days.txt");
		assertEquals(385865, x.length);
		
		x= comp.compress(x);
		assertTrue(x.length < 225000);
		
		HuffmanTools.writeBytesToFile("Compressed.txt", x);
		
		

		byte []y = comp.decompress(x);
		HuffmanTools.writeBytesToFile("Uncompressed.txt", y);
		assertEquals(385865, y.length);
		File org = new File("80Days.txt");
		
		File cmp = new File("Uncompressed.txt");
		
		Scanner orgScan;
		try {
			orgScan = new Scanner(org);
			Scanner comScan = new Scanner(cmp);
			while(orgScan.hasNext())
			{
				assertTrue(comScan.hasNext());
				assertEquals(true, orgScan.next().equals(comScan.next()));
				
				
			}
			
			orgScan.close();
			comScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	/**
	 * This test compress a large file(Ulyses.txt) and decompress it back to its orginal
	 * form it then checks if the decompressed file is the exact same, by comparing
	 * each token of the orignal file and the decompressed file.  Also checks to see 
	 * that the compressed size is apprioate
	 */
	@Test
	public void test6()
	{
	HuffmanCompressor comp = new HuffmanCompressor();
		int incorrecCount = 0;
		byte[] file  =HuffmanTools.readBytesFromFile("80Days.txt");
		byte[] orginal  =HuffmanTools.readBytesFromFile("80Days.txt");
		byte[] newFile = comp.decompress(comp.compress(file));
		
		assertEquals(orginal.length, newFile.length);
		
		for(int i = 0; i < orginal.length; i++)
		{
			if(orginal[i] != newFile[i])
			{
				System.out.println(i);
			}
					
		}
	
		
	}
	
	
	

}
