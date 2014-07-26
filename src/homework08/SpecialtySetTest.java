package homework08;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

/**
 * A few tests for the SpecialtySet class. The last test demonstrates a novel
 * way to count comparisons. Students should add more tests (especially more
 * simple tests) to validate their SpecialtySet.
 * 
 * @author Peter Jensen
 * @version 2/22/2014
 */
public class SpecialtySetTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the size of a newly constructed set.
	 */
	@Test
	public void test01() {
		SpecialtySet<Double> s = new SpecialtySet<Double>();
		assertEquals("An newly constructed set should have a 0 size: ", 0,
				s.size());
	}

	/**
	 * A simple add/contains test.
	 */
	@Test
	public void test02() {
		SpecialtySet<String> s = new SpecialtySet<String>();
		s.add("Hello");

		// Bug in test, not your code, so fix the test first.
		assertEquals("The set should contain 'Hello': ", true,
				s.contains("Hello"));
	}

	/**
	 * A long running add/remove/contains test that uses the helper class below.
	 * This test is VERY incomplete.
	 */
	@Test
	public void test03() {
		// Our set.

		SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();

		// A known good set to validate against.

		Set<Integer> v = new TreeSet<Integer>();

		// A random number generator seeded to give back the same sequence each
		// time.

		Random r = new Random(8675309);

		// A debugging variable.

		int totalActions = 0;

		// Repeatedly 'visit' sequences of numbers.

		for (int repeat = 0; repeat < 100; repeat++) {
			// Pick a base, length, step

			int base = r.nextInt(1000);
			int length = r.nextInt(50) + 50;
			int step = r.nextInt(3) + 1;

			// Do an action the appropriate number of times.

			for (int i = 0; i < length; i++) {
				// Make the next integer in the sequence.

				TrackedInteger ti = new TrackedInteger(base + i * step);

				// Pick an action.

				int action = r.nextInt(3);

				// Do the action.

				totalActions++;

				if (action == 0) {
					s.add(ti); // Change our set
					v.add(ti.i); // Also change the known good set
				} else if (action == 1) {
					s.remove(ti); // Change our set
					v.remove(ti.i); // Also change the known good set
				} else if (action == 2) {
					// The 'contains' method should report identically for both
					// sets.

					assertEquals(s.contains(ti), v.contains(ti.i));
				}
			}
		}

		// If the specialty set is coded properly, a relatively small number of
		// comparisons are done.
		// System.out.println(TrackedInteger.comparisonCount);
		assertTrue("Maximum comparison count test: ",
				TrackedInteger.comparisonCount <= 63164);
		TrackedInteger.comparisonCount = 0;

	}

	/**
	 * Basic test that test adding random runs of numbers
	 */
	@Test
	public void test04() {
		SpecialtySet<Integer> s = new SpecialtySet<Integer>();

		Set<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i <= 500; i++) {
			int x = i;
			int y = i;
			if (i % 10 == 0) {
				x = (int) (Math.random() * 250 + i) % 10;
				y = (int) (Math.random() * 250 + i) % 10;
			}
			if (i % 9 == 0) {
				x = (int) (i * -1);
				y = (int) (i * -2);
			}
			if (i % 8 == 0) {
				x -= x;
				y -= x;
			}

			s.add(x);
			s.add(x + y);
			set.add(x);
			set.add(x + y);

			assertEquals(true, s.contains(x));
			assertEquals(true, s.contains(x + y));
			assertEquals(set.contains(x), s.contains(x));
			assertEquals(set.contains(x + y), s.contains(x + y));

		}

		// System.out.println(set.size()+ " "+s.size());
		assertEquals("these should have the same size ", set.size(), s.size());
	}

	@Test
	/**
	 * Basic test that test adding the same number, testing for efficiency
	 * and size, not adding duplicates
	 */
	public void test05() {
		SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();

		TrackedInteger ti = new TrackedInteger(20);
		TrackedInteger.comparisonCount = 0;
		for (int n = 0; n < 10; n++) {

			s.add(ti);

		}
		// System.out.println("test5  compariaon count " +ti.comparisonCount);

		// only a small amount of comparisons should be done here
		assertTrue("Maximum comparison count test: ",
				TrackedInteger.comparisonCount <= 9);

		// size should only be one
		assertEquals("should be 1 ", 1, s.size());
	}
    @Test
    
    /*
     * basic test used for early development testing
     */
    public void test06 ()
    {
    TrackedInteger.comparisonCount =0;
      SpecialtySet<Integer> s = new SpecialtySet<Integer>();
      		s.add(0);
      		assertEquals( true, s.contains(0));
      		
      		s.add(1);s.add(3);s.add(5);s.add(4);s.add(6);
        	s.add(-1);s.add(7);
       assertEquals( true, s.contains(3)&&s.contains(0)&&s.contains(-1)&&s.contains(7) );
       assertEquals( false, s.contains(42)||s.contains(34));
    }

	@Test
	/*
	 * this test was used early on to debug my program it adds random numbers to
	 * an array
	 */
	public void test07() {
		SpecialtySet<Integer> s = new SpecialtySet<Integer>();

		Set<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i <= 50000; i++) {
			int x = (int) (Math.random() * 250);
			int y = (int) (Math.random() * 250);

			s.add(x);
			s.add(x - y);

			assertEquals(true, s.contains(x));
			assertEquals(true, s.contains(x - y));
			set.add(x);
			set.add(x - y);
		}

		// System.out.println(set.size()+ " "+s.size());
		assertEquals("these should have the same size ", set.size(), s.size());
	}

	public void test09() {
		SpecialtySet<Integer> s = new SpecialtySet<Integer>();

		Set<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i <= 10; i++) {
			int x = (int) (Math.random() * 250);

			for (int x1 = 5; x1 >= 0; x1--) {
				s.add(x);
				s.add(x - 1000);
				set.add(x);
				set.add(x - 1000);
				x -= x1;

				assertEquals(set.contains(x), s.contains(x));
				assertEquals(set.contains(x - 1000), s.contains(x - 1000));
			}

		}
		// s.display();

		// System.out.println("here " + set.size()+ " "+s.size());
		assertEquals("these should have the same size ", set.size(), s.size());
	}

	@Test
	/**
	 * Basic test that test adding the same number, testing for efficiency
	 * this is the same test as test # 5 except it removes the number right
	 * after so comparisons should be the same because we are continually adding
	 * a number to an empty set which requires no comparisons and then removing the element 
	 */
	public void test10() {
		SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();

		TrackedInteger ti = new TrackedInteger(1);
		// reset comparison count
		TrackedInteger.comparisonCount = 0;
		for (int n = 0; n < 1; n++) {

			s.add(ti);// 0
			// adding to the empty tree should not require any comparisions
			assertTrue("Maximum comparison count test: ",
					TrackedInteger.comparisonCount == 0);
			assertEquals("should be 1 ", 1, s.size());
			s.remove(ti);

		}
		//System.out.println("test10  compariaon count " + ti.comparisonCount);
		

		// only a small amount of comparisons should be done here
		assertTrue("Maximum comparison count test: ",
				TrackedInteger.comparisonCount <= 1);

		// size should only be one
		assertEquals("should be 0 ", 0, s.size());
	}

	/**
	 * This tests adding 0-500 in ascending order, elements are being added to
	 * the end of the list and should be very efficient
	 */
	@Test
	public void test12() {
		SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();

		TrackedInteger ti = new TrackedInteger(0);
		// reset comparison count
		TrackedInteger.comparisonCount = 0;
		// add items in order
		for (int n = 0; n < 500; n++) {
			ti = new TrackedInteger(n);
			s.add(ti);
			s.contains(ti);

		}
		//System.out.println("test12 part a  comparison count "
		//		+ ti.comparisonCount);
	

		
		assertTrue("Maximum comparison count test: ",
				TrackedInteger.comparisonCount <= 7987);

		// size should only be one
		assertEquals("should be 0 ", 500, s.size());

		// Now remove the items

		TrackedInteger.comparisonCount = 0;
		// remove items in order
		for (int n = 0; n < 500; n++) {
			ti = new TrackedInteger(n);

			s.remove(ti);

		}

		//System.out.println("test12 part b  comparison count "
		//		+ ti.comparisonCount);

		// only a small amount of comparisons should be done to remove items
		// about 1000 for add,

		assertTrue("Maximum comparison count test: ",
				TrackedInteger.comparisonCount <= 3029);
		assertEquals("should be 0 ", 0, s.size());
		
		TrackedInteger.comparisonCount = 0;

	}
	
    /**
     *This tests adding 1-100 in descending  order
     */
    @Test
    public void test13 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(-1);
        //reset comparison count
        TrackedInteger.comparisonCount =0;
        //add items in order
        
        for(int n= 100; n>0; n--){
        	ti = new TrackedInteger(n);
        	s.add(ti);
        	
        	
        }
       // System.out.println("test13 part a  comparison count " +ti.comparisonCount); 
        // s.display();
        
        //adding elements in descending should in this case still is efficient cause
        //they are added at beginning
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=580);
        
        //size should only be one
        assertEquals("should be 0 ", 100, s.size());
        
        //check contains going in descending order should be inefficient
        
        //check contains in descending order
        TrackedInteger.comparisonCount =0;
        for(int n= 100; n>0; n--){
        	ti = new TrackedInteger(n);
        	s.contains(ti);
        	
        	
        }
        
        //System.out.println("test13 part ab  comparison count " +ti.comparisonCount); 

        TrackedInteger.comparisonCount =0;
        //remove items in order
        for(int n= 100; n>0; n--){
        	ti = new TrackedInteger(n);
        	s.remove(ti);
        }
       // System.out.println("test13 part b  comparison count " +ti.comparisonCount); 
        
        //only a small amount of comparisons should be done to remove items about 1000 for add,
        
       assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=449);
        assertEquals("should be 0 ", 0, s.size());
        TrackedInteger.comparisonCount =0;
        
        
    }
    
    /**
     *This tests adding numbers coming in  order 0,1,-2,3,-4.... this is a worse case scenario
     *this test was mainly used to gather information for the write up
     */
    @Test
    public void test14 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(-1);
        //reset comparison count
        TrackedInteger.comparisonCount =0;
        //add items in order
        
        for(int n= 0; n<100; n++){
        	int num = n;
        	if(num%2==0)
        		num*=-1;
        	ti = new TrackedInteger(num);
        	s.add(ti);
        	
        	
        }
        //System.out.println("test14 part a  comparison count " +ti.comparisonCount); 
        // s.display();
        
        //adding elements in descending should in this case still is efficient cause
        //they are added at beginning
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=567);
        
        //size should only be one
        assertEquals("should be 0 ", 100, s.size());
        
        //check contains going in descending order should be inefficient
        
        //check contains in descending order
        TrackedInteger.comparisonCount =0;
        for(int n= 0; n<100; n++){
        	int num = n;
        	if(num%2==0)
        		num*=-1;
        	ti = new TrackedInteger(num);
        	s.contains(ti);
        
        	
        	
        	
        }
        
        //System.out.println("test14 part ab  comparison count " +ti.comparisonCount); 
         //s.display();
        
        //
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=580);
        
        //Now remove the items should be aprox n(n+1)/2
        
        TrackedInteger.comparisonCount =0;
        //remove items in order
        for(int n= 0; n<100; n++){
        	int num = n;
        	if(num% 2==0)
        		num*=-1;
        	ti = new TrackedInteger(num);
        	s.remove(ti);
        	
        	
        }
       // System.out.println("test14 part b  comparison count " +ti.comparisonCount); 
        
        //only a small amount of comparisons should be done to remove items about 1000 for add,
        
       assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=284);
        assertEquals("should be 0 ", 0, s.size());
        System.out.println("finished");
      
    }
    @Test
    public void test15()
    {
    	
    	   SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	   for(int j = 0; j<100000; j+=10000)
    	   {
    	   for(int i = 0; i<j; i++)
    	   {
    		   int x= (int)(Math.random()*100000);
    		   TrackedInteger temp = new TrackedInteger(x);
    		   s.add(temp);
    	   }
    	   System.out.println(TrackedInteger.comparisonCount);
    	   TrackedInteger.comparisonCount=0;
    	   }
    }
    

	/**
	 * A helper class with a static variable for tracking all comparisons made
	 * with any of this type of object.
	 * 
	 * @author Peter Jensen
	 * @version 2/22/2014
	 */
	private static class TrackedInteger implements Comparable<TrackedInteger> {
		static long comparisonCount = 0;

		Integer i;

		TrackedInteger(int i) {
			this.i = i;
		}

		@Override
		public int compareTo(TrackedInteger o) {
			comparisonCount++;
			return i.compareTo(o.i);
		}

		@Override
		public boolean equals(Object o) {
			return (o instanceof TrackedInteger) ? ((TrackedInteger) o)
					.compareTo(this) == 0 : false;
		}

		@Override
		public String toString() {
			return "" + i;
		}

	}

}
