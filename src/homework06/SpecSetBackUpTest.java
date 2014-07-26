
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
 * @author Peter Jensen
 * @version 2/22/2014
 */
public class SpecSetBackUpTest
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
       // SpecialtySet<Double> s = new SpecialtySet<Double>();
        SpecSetBackUp<Double> s = new SpecSetBackUp<Double>();
        assertEquals("An newly constructed set should have a 0 size: ", 0, s.size());
    }

    /**
     * A simple add/contains test.
     */    
    @Test
    public void test02 ()
    {
        //SpecialtySet<String> s = new SpecialtySet<String>();
        SpecSetBackUp<String> s = new SpecSetBackUp<String>();
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
        
       // SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        SpecSetBackUp<TrackedInteger> s = new SpecSetBackUp<TrackedInteger>();
        
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
        
        
        //assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 44536);
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 106225);
        
        // Uncomment if needed.
        
         System.out.println (totalActions);
         System.out.println (TrackedInteger.comparisonCount);        
         assertEquals(s.validate(), true);
    }
    /**
     * Test the size of a newly constructed set.
     */
    @Test
    public void test04 ()
    {
       // SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
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
    public void test05 ()
    {
        //SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
        
        for(int i= 0; i<= 5000; i++){
        	//int x = (int)(Math.random()*500);
        	s.add(20);
        	
        }
        
        assertEquals("should be 1 ", 1, s.size());
    }
    @Test
    public void test06 ()
    {
      //  SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
        
       
        	s.add(0);
        	s.add(1);
        	s.add(3);
        	s.add(5);
        	s.add(4);
        	s.add(6);
        	s.add(-1);
        	s.add(7);
        
        	
        
    
        	s.display();
        assertEquals( true, s.contains(3)&&s.contains(0) );
    }
    @Test
    public void test07 ()
    {
        //SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
        Set set = new TreeSet();
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
       
        System.out.println(set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
    }
    @Test
    public void test08 ()
    {
        //SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
        Set set = new TreeSet();
        for(int i= 0; i<= 500; i++){
        	int x = (int)(Math.random()*2500);
        	int y = (int)(Math.random()*2500);
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
       s.display();
        System.out.println(set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
    }
    @Test
    public void test09 ()
    {
        //SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        SpecSetBackUp<Integer> s = new SpecSetBackUp<Integer>();
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
       s.display();
       System.out.println("here");
        System.out.println("here " + set.size()+ " "+s.size());
        assertEquals("these should have the same size ", set.size(), s.size());
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
