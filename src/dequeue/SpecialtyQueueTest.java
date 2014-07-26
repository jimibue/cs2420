package dequeue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class test the functionailty of the SpeqialtyQue class.  Some of
 * the numbers from the original testing have been changed to reduced the run 
 * time of this test
 * 
 * @author James Yeates
 *
 */
public class SpecialtyQueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Basic test the tests insert and size methods and checks if both heaps
	 * stored intenally are maintaining the min and max heap condition
	 */
	@Test
	public void test() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();

		assertTrue(sq.size() == 0);
		sq.insert(1);
		assertTrue(sq.size() == 1);
		sq.insert(2);
		assertTrue(sq.size() == 2);
		sq.insert(3);
		assertTrue(sq.size() == 3);
		assertTrue(sq.getDualHeap().checkHeapCondition());
		assertTrue( sq.removeMax()==3);
		assertTrue( sq.removeMin()==1);

	}

	/**
	 * This test inserts 10000 items in differnet ways to different dequeue and
	 * checks if the min/max heap condition is maintaned internally in the 
	 * object by looking at  arrangement of its min and max heaps.
	 */
	@Test
	public void test1() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		
		//the size of the specilaty queue
		int size = 1000000;

		// add in ascending order
		for (int i = 0; i < size; i++)
			sq.insert(i);
		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		
		sq = new SpecialtyQueue<Integer>();

		// add in descending order
		for (int i = 0; i < size; i++)
			sq.insert(size - i);
		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		
		sq = new SpecialtyQueue<Integer>();

		// add the same item
		for (int i = 0; i < size; i++)
			sq.insert(0);
		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		
		sq = new SpecialtyQueue<Integer>();

		// add random (0-10000)
		for (int i = 0; i < size; i++)
			sq.insert((int) (Math.random() * size));
		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		
		sq = new SpecialtyQueue<Integer>();

		// add random (0-100)
		for (int i = 0; i < size; i++)
			sq.insert((int) (Math.random() * 100));
		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		
		sq = new SpecialtyQueue<Integer>();

		// add random (0-100000)
		for (int i = 0; i < size; i++)
			sq.insert((int) (Math.random() * 100000));

		assertTrue(sq.getDualHeap().checkHeapCondition());
		//System.out.println(sq.getDualHeap().getComparisonCount());
		

		
	}

	/**
	 * This test adds 100000 random elments integer to the queue and a ArrayList
	 * the array is then sorted. It calls removeMin and compare that the equall
	 * the items being removed from the 0 index of the sorted list they should
	 * be equal. repeats the same test on removeMax(), also checks if size is
	 * the same
	 */

	@Test
	public void test2() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		List<Integer> control = new ArrayList<Integer>();

		// add the same random number (0-100000) to both structures
		for (int i = 0; i < 100000; i++) {
			int temp = (int) (Math.random() * 100000);
			sq.insert(temp);
			control.add(temp);
		}
		assertEquals(control.size(), sq.size());
		Collections.sort(control);
		// remove min item from queue and the control list -check for equality
		for (int i = 0; i < 100000; i++) {
			assertTrue(sq.getDualHeap().size() == (100000 - i));
			assertEquals(control.remove(0), sq.removeMin());
		}
		assertEquals(control.size(), sq.size());
		sq = new SpecialtyQueue<Integer>();

		// add the same random number (0-100000) to both structures
		for (int i = 0; i < 100000; i++) {
			int temp = (int) (Math.random() * 100000);
			sq.insert(temp);
			control.add(temp);
		}
		assertEquals(control.size(), sq.getDualHeap().size());
		Collections.sort(control);
		// remove max item from queue and the control list -check for equality
		for (int i = 0; i < 100000; i++) {
			assertEquals(control.remove(control.size() - 1), sq.removeMax());
		}
		assertEquals(control.size(), sq.size());
		assertTrue(sq.size() == 0);

	}

	/**
	 * This test was primarily used for debugging. it created a dequeue and a
	 * control array list and randomily choose to removeMax or removeMin. After
	 * an item was removed the min and max heap were printed to the console to
	 * help determine what was happening, the print statements have been
	 * commented out
	 */

	@Test
	public void test3() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		List<Integer> control = new ArrayList<Integer>();

		// add 20 integers to both structures
		for (int i = 0; i < 20; i++) {
			int temp = (int) (Math.random() * 200);
			sq.insert(temp);
			control.add(temp);
		}
		Collections.sort(control);

		// randomily call removeMin()/ removeMax()
		for (int i = 0; i < 20; i++) {
			// System.out.println(dq.getDualHeap().minHeapToString());
			// System.out.println(dq.getDualHeap().maxHeapToString());
			if ((int) (Math.random() * 2) % 2 != 0) {
				// System.out.println("removing min "
				// +dq.removeMin().toString());

				assertTrue(sq.size() == (20 - i));
				assertEquals(control.remove(0), sq.removeMin());
			} else {
				// System.out.println("removing max " + dq.removeMax());
				assertTrue(sq.size() == (20 - i));
				assertEquals(control.remove(control.size() - 1), sq.removeMax());
			}

		}
		// System.out.println(dq.getDualHeap().minHeapToString());
		// System.out.println(dq.getDualHeap().maxHeapToString());

	}

	/**
	 * This test adds random numbers to the Specialty Queue and to an ArrayList
	 * The ArrayList is sorted and act as a control group and removeMin() or
	 * removeMax() are randomily called. The appropriate value from the
	 * arraylist is pulled and compared to the integer pulled from the
	 * priortyQueue. The size was intaily set to 2,000,000 and passed, but the
	 * number was reduced becuase it took 5 minutes, the majoirty of that time
	 * came from the assert equal call
	 * 
	 */

	@Test
	public void test4() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		List<Integer> control = new ArrayList<Integer>();

		// the size of the queue being tested
		int size = 100000;

		// this loops control how many times this expirement is run
		for (int timesToReapet = 0; timesToReapet < 1; timesToReapet++) {
			control.clear();
			sq = new SpecialtyQueue<Integer>();

			// add numbers to structures
			for (int i = 0; i < size; i++) {
				int temp = (int) (Math.random() * 1000000);// determines range
															// of numbers
				sq.insert(temp);
				control.add(temp);
			}

			Collections.sort(control);

			// randomliy call removeMin/removeMax, remove all the items and
			// test to see if they are coming out in the correct order
			for (int i = 0; i < size; i++) {

				if ((int) (Math.random() * 2) % 2 != 0) {
					assertTrue(sq.size() == (size - i));
					assertEquals(control.remove(0), sq.removeMin());
				} else {
					assertTrue(sq.size() == (size - i));
					assertEquals(control.remove(control.size() - 1),
							sq.removeMax());
				}

			}
			assertTrue(sq.size() == 0);
		}
	}

	/**
	 * this test does the same thing as test4 it just selects a small range of
	 * random ints testing for potential errors that having many duplicates
	 * might create
	 */

	@Test
	public void test6() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		List<Integer> control = new ArrayList<Integer>();

		// this loops control how many times this expirement is run
		// it is just set to one right now but was set to 1000
		// during testing phase
		for (int timesToReapet = 0; timesToReapet < 1; timesToReapet++) {
			control.clear();
			sq = new SpecialtyQueue<Integer>();
			
			// add numbers to structures
			for (int i = 0; i < 100000; i++) {
				int temp = (int) (Math.random() * 100000);
				sq.insert(temp);
				control.add(temp);
			}
			Collections.sort(control);

			// randomliy call removeMin/removeMax, remove all the items and
			// test to see if they are coming out in the correct order
			for (int i = 0; i < 100000; i++) {
				
				if ((int) (Math.random() * 2) % 2 != 0)
				{
					assertEquals(control.remove(0), sq.removeMin());
					
				}

				else
				{
					assertEquals(control.remove(control.size() - 1),
							sq.removeMax());
					
				}

			}
			assertTrue(sq.getDualHeap().size() == 0);
		}
	}
	

	/**
	 * this test does the same thing as test 4 but it also will insert item
	 * to periodically while removing  items.  Numbers have been changed to 
	 * reduce running time from the orginal test
	 */

	@Test
	public void test5() {

		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();
		List<Integer> control = new ArrayList<Integer>();
		
		int rangeOfInts =10000;
		int intialSize =10000;

		// this loops control how many times this expirement is run
		// it is just set to one right now but was set to 1000
		// during testing phase
		for (int timesToReapet = 0; timesToReapet < 1; timesToReapet++) {
			control.clear();
			sq = new SpecialtyQueue<Integer>();
			
			// add numbers to structures
			for (int i = 0; i < intialSize; i++) {
				int temp = (int) (Math.random() * rangeOfInts);
				sq.insert(temp);
				control.add(temp);
			}
			Collections.sort(control);

			// randomliy call removeMin/removeMax, remove all the items and
			// test to see if they are coming out in the correct order
			for (int i = 0; i < intialSize; i++) {
				
				int x = (int) (Math.random() * 3);
				
				if (x% 3 == 0)
				{
					assertEquals(control.remove(0), sq.removeMin());
					
				}

				else if(x% 2 == 0)
				{
					assertEquals(control.remove(control.size() - 1),
							sq.removeMax());
					
				}
				else
				{
					int temp = (int) (Math.random() * rangeOfInts);
					sq.insert(temp);
					control.add(temp);
					Collections.sort(control);
				
				}

			}
			
		}
	}
	


	

}
