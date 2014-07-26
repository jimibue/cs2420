package homework06;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * A few tests for the SpecialtySet class.  The last test demonstrates a novel
 * way to count comparisons.  Students should add more tests (especially more
 * simple tests) to validate their SpecialtySet.
 * 
 * @author Peter Jensen &James Yeates
 * @version 2/22/2014
 */
public class SpecialtySetTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown () throws Exception
    {        
    }

    /**
     * Test the size of a newly constructed set.
     */
    @Test
    public void test01 ()
    {
       SpecialtySet<Double> s = new SpecialtySet<Double>();
      
        assertEquals("An newly constructed set should have a 0 size: ", 0, s.size());
    }

    /**
     * A simple add/contains test.
     */    
    @Test
    public void test02 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
       
        s.add("Hello");
        
        // Bug in test, not your code, so fix the test first.
        s.display();
        assertEquals("The set should contain 'Hello': ", true, s.contains("Hello"));  
    }
    
    /**
     * A long running add/remove/contains test that uses the
     * helper class below.  This test is VERY incomplete.
     */    
    @Test
    public void test03 ()
    {
        // Our set.
        
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
       
        
        // A known good set to validate against.
        
        Set<Integer> v = new TreeSet<Integer>();
        
        // A random number generator seeded to give back the same sequence each time.
        
        Random r = new Random(8675309);
        
        // A debugging variable.
        
        int totalActions = 0;
        
        // Repeatedly 'visit' sequences of numbers.
        
        for (int repeat = 0; repeat < 100; repeat++)
        {
            // Pick a base, length, step
            
            int base = r.nextInt(1000);
            int length = r.nextInt(50) + 50;
            int step = r.nextInt(3) + 1;
            
            // Do an action the appropriate number of times.
            
            for (int i = 0; i < length; i++)
            {
                // Make the next integer in the sequence.
                
                TrackedInteger ti = new TrackedInteger(base + i * step);   
                
                // Pick an action.
                
                int action = r.nextInt(3);
            
                // Do the action.
              
                totalActions++;
                
                if (action == 0)
                {
                    s.add(ti);    // Change our set 
                    v.add(ti.i);  // Also change the known good set
                }
                else if (action == 1)
                {
                    s.remove(ti); // Change our set
                    v.remove(ti.i);  // Also change the known good set
                }
                else if (action == 2)
                {
                    // The 'contains' method should report identically for both sets.
                    //s.contains(ti);
                    assertEquals(s.contains(ti), v.contains(ti.i));
                }
            }
        }

        // If the specialty set is coded properly, a relatively small number of
        //   comparisons are done.
        
        
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 44536);
        
        
        // Uncomment if needed.
        
         System.out.println (totalActions);
         System.out.println (TrackedInteger.comparisonCount);        
         assertEquals(s.validate(), true);
    }
    /**
     * Basic test that test adding random runs of numbers
     */
    @Test
    public void test04 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
       
        Set set = new TreeSet();
        for(int i= 0; i<= 500; i++){
        	int x =i;
        	int y=i;
        	if(i%10==0){
        	 x = (int)(Math.random()*250+i)%10;
        	 y = (int)(Math.random()*250+i)%10;
        	}
        	if(i%9==0){
           	 x = (int)(i*-1);
           	 y = (int)(i*-2);
           	}
        	if(i%8==0){
              	 x -= x;
              	 y -= x;
              	}
        	
        	s.add(x);
        	s.add(x+y);
        	set.add(x);
        	set.add(x+y);
        	
        	assertEquals(true, s.contains(x));
        	assertEquals(true, s.contains(x+y));
        	assertEquals(set.contains(x), s.contains(x));
        	assertEquals(set.contains(x+y), s.contains(x+y));
        	
        }
       
        System.out.println(set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
    }
    @Test
    /**
     * Basic test that test adding the same number, testing for efficiency
     */
    public void test05 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(20);
        TrackedInteger.comparisonCount =0;
        for(int n= 0; n< 500; n++){
        	
        	s.add(ti);
        	
        }
        System.out.println("test5  compariaon count " +ti.comparisonCount); 
        // s.display();
        
        //only a small amount of comparisons should be done here
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 1200);
        
        //size should only be one
        assertEquals("should be 1 ", 1, s.size());
    }
    @Test
    
    /*
     * basic test used for early development testing
     */
    public void test06 ()
    {
      SpecialtySet<Integer> s = new SpecialtySet<Integer>();
     
        
       
        	s.add(0);
        	s.add(1);
        	s.add(3);
        	s.add(5);
        	s.add(4);
        	s.add(6);
        	s.add(-1);
        	s.add(7);
        
        	
    assertEquals( true, s.contains(3)&&s.contains(0)&&s.contains(-1)&&s.contains(7) );
    }
    @Test
    /*
     * this test was used early on to debug my program it adds random numbers to an array
     */
    public void test07 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
       
        Set<Integer> set = new TreeSet<Integer>();
        for(int i= 0; i<= 50000; i++){
        	int x = (int)(Math.random()*250);
        	int y = (int)(Math.random()*250);
        	
        	s.add(x);
        	s.add(x-y);
        	
        	assertEquals(true, s.contains(x));
        	assertEquals(true, s.contains(x-y));
        	set.add(x);
        	set.add(x-y);
        }
       
        //System.out.println(set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
    }
    @Test
    /*
     * this test was used early on to debug my program
     */
    public void test08 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        
        Set<Integer> set = new TreeSet<Integer>();
        for(int i= 0; i<= 500; i++){
        	int x = (int)(Math.random()*2500);
        	
        	for(int x1=0; i<5; i++)
        		x +=x1;
        	
        	s.add(x);
        	s.add(x);
        	s.add(x-1000);
        	set.add(x);
        	set.add(x-1000);
        	
        	
        	assertEquals(set.contains(x), s.contains(x));
        	assertEquals(set.contains(x-1000), s.contains(x-1000));
        
        	
        	
        }
      
        
        assertEquals("these should have the same size ", set.size(), s.size());
    }
    @Test
    /*
     * this test was used early on to debug my program
     */
    public void test09 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
       
        Set set = new TreeSet();
        for(int i= 0; i<= 10; i++){
        	int x = (int)(Math.random()*250);
        	int y = (int)(Math.random()*250);
        	for(int x1=5; x1>=0; x1--){
        		s.add(x);
        		s.add(x-1000);
            	set.add(x);
            	set.add(x-1000);
        		x -=x1;
        		
            	
            
            	assertEquals(set.contains(x), s.contains(x));
            	assertEquals(set.contains(x-1000), s.contains(x-1000));
        	}
        
       }
       //s.display();
       
        //System.out.println("here " + set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
    }
   
    @Test
    /**
     * Basic test that test adding the same number, testing for efficiency
     * this is the same test as test # 5 except it removes the number right
     * after so comparisons should be the same because we are continually adding
     * a number to an empty set which requires no comparisons and then removing the element 
     */
    public void test10 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(20);
        //reset comparison count
        TrackedInteger.comparisonCount =0;
        for(int n= 0; n< 500; n++){
        	
        	s.add(ti);
        	s.remove(ti);
        	
        	
        }
        System.out.println("test10  compariaon count " +ti.comparisonCount); 
        // s.display();
        
        //only a small amount of comparisons should be done here
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 1100);
        
        //size should only be one
        assertEquals("should be 0 ", 0, s.size());
    }
    /**
     * Basic test that test adding the same number, testing for efficiency
     * this is the same test as test # 5 except it removes the number right
     * after so comparisons should be the same because we are continually adding
     * a number to an empty set which requires no comparisons and then removing the element 
     */
    @Test
    public void test11 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(20);
        //reset comparison count
        TrackedInteger.comparisonCount =0;
        for(int n= 0; n< 500; n++){
        	
        	s.add(ti);
        	s.remove(ti);
        	
        	
        }
        System.out.println("test10  compariaon count " +ti.comparisonCount); 
        // s.display();
        
        //only a small amount of comparisons should be done here
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 1100);
        
        //size should only be one
        assertEquals("should be 0 ", 0, s.size());
    }
    /**
     *This tests adding 0-500 in ascending order, elements are being added to the
     *end of the list and should be very efficient
     */
    @Test
    public void test12 ()
    {
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        TrackedInteger ti = new TrackedInteger(0);
        //reset comparison count
        TrackedInteger.comparisonCount =0;
        //add items in order
        for(int n= 0; n< 500; n++){
        	ti = new TrackedInteger(n);
        	s.add(ti);
        	s.contains(ti);
        	
        	
        	
        }
        System.out.println("test12 part a  comparison count " +ti.comparisonCount); 
        // s.display();
        
        //only a small amount of comparisons should be done here about 1000 for add,
        //1500 for contains
       assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 2600);
        
        //size should only be one
        assertEquals("should be 0 ", 500, s.size());
        
        //Now remove the items
        
        TrackedInteger.comparisonCount =0;
        //remove items in order
        for(int n= 0; n< 500; n++){
        	ti = new TrackedInteger(n);
        	s.remove(ti);
        	
        	
        	
        	
        }
        System.out.println("test12 part b  comparison count " +ti.comparisonCount); 
        
        //only a small amount of comparisons should be done to remove items about 1000 for add,
        
       assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 1100);
        assertEquals("should be 0 ", 0, s.size());
        
        
    }
    /**
     *This tests adding 1-100 in descending  order, this is a worse case scenario
     *this test was mainly used to gather information for the write up
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
        System.out.println("test13 part a  comparison count " +ti.comparisonCount); 
        // s.display();
        
        //adding elements in descending should in this case still is efficient cause
        //they are added at beginning
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=210);
        
        //size should only be one
        assertEquals("should be 0 ", 100, s.size());
        
        //check contains going in descending order should be inefficient
        
        //check contains in descending order
        TrackedInteger.comparisonCount =0;
        for(int n= 100; n>0; n--){
        	ti = new TrackedInteger(n);
        	s.contains(ti);
        	
        	
        }
        
        System.out.println("test13 part ab  comparison count " +ti.comparisonCount); 
        // s.display();
        
        //
        //assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount >=5050);
        
        //Now remove the items should be aprox n(n+1)/2
        
        TrackedInteger.comparisonCount =0;
        //remove items in order
        for(int n= 100; n>0; n--){
        	ti = new TrackedInteger(n);
        	s.remove(ti);
        	
        	
        	
        	
        }
        System.out.println("test13 part b  comparison count " +ti.comparisonCount); 
        
        //only a small amount of comparisons should be done to remove items about 1000 for add,
        
       // assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount >=5050);
        assertEquals("should be 0 ", 0, s.size());
        
        
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
    System.out.println("test14 part a  comparison count " +ti.comparisonCount); 
    // s.display();
    
    //adding elements in descending should in this case still is efficient cause
    //they are added at beginning
    //assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <=210);
    
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
    
    System.out.println("test14 part ab  comparison count " +ti.comparisonCount); 
     //s.display();
    
    //
    //assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount >=5050);
    
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
    System.out.println("test14 part b  comparison count " +ti.comparisonCount); 
    
    //only a small amount of comparisons should be done to remove items about 1000 for add,
    
   // assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount >=5050);
    assertEquals("should be 0 ", 0, s.size());
    
  
}
@Test
public void test15 ()
{
    SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    
    TrackedInteger ti = new TrackedInteger(-1);
    //reset comparison count
    TrackedInteger.comparisonCount =0;
    //add items in order
    for(int i= 0; i<10000; i++){
    	int x =((int) Math.random()*2000000);
    	for(int j = 0; j<1; j++){
    		ti =new TrackedInteger(x+(j*100));
    		s.add(ti);
    	}
    }
    System.out.println("test15 part b  comparison count " +ti.comparisonCount); 
}

    

    
    /**
     * A helper class with a static variable for tracking all comparisons
     * made with any of this type of object.
     *
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private static class TrackedInteger implements Comparable<TrackedInteger>
    {
        static long comparisonCount = 0;
        
        Integer i;
        
        TrackedInteger(int i)
        {
            this.i = i;    
        }
        
        @Override
        public int compareTo (TrackedInteger o)
        {
            comparisonCount++;
            return i.compareTo(o.i);
        }
        
        @Override
        public boolean equals (Object o)
        {
            return (o instanceof TrackedInteger) ? ((TrackedInteger) o).compareTo(this) == 0 : false;
        }
        
        @Override
        public String toString ()
        {
            return "" + i;
        }

    }
    
}
