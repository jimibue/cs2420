package dequeue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class was created to determine if the SpecialtyQueue class
 * complexity meets the standards of double ended priorty queue. This test uses
 * the number of times the compareTo() method is called to establish complexity.
 * This test assumes/ uses a large range of randomily distrubuted numbers for
 * each test, and does not consider special cases such as priorty queues
 * generated from ascending/descending values, or a small range of values. This
 * class was also used to gather data for the analysis report and print
 * statments have been commented out.
 * 
 * To pass these test the exact cost should not exceed these values
 * 
 * -insert() - should not exceed and average of 6 comparisons per insertion if
 * pass this wil show insert is O(1)
 * 
 * -removeMin()/removeMax() - should not exceed and average of 4* lg n
 * comparisons per remove call (n is size of queue) this will show
 * removeMin/removeMax is O(lg n)
 * 
 * ** size is not tested because it uses the size() method from ArrayList which
 * is gaurnteed to be O(1)
 * 
 * @author James Yeates
 * 
 */
public class SpecialtyQueueTest2 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * This test the complexity of the insert function which should be 0(1).
	 * Must not excced six
	 */

	@Test
	public void test() {
		SpecialtyQueue<Integer> sq = new SpecialtyQueue<Integer>();

		int interval = 0;

		// add one million items to sq print out comparison count
		// every time 50,000 new elements are added
		for (int i = 0; i <= 1000000; i++) {

			// check complexity
			if (i == interval) {
				// System.out.println(interval);
				// System.out.println(sq.getDualHeap().getComparisonCount());
				// System.out.println(interval+
				// " "+dq.getDualHeap().getComparisonCount());
				assertTrue(sq.getDualHeap().getComparisonCount() <= 6 * interval);
				interval += 50000;
			}
			sq.insert((int) (Math.random() * 2000000));
		}

	}

	/**
	 * Tests the complexity of the removeMin() function by finding the average
	 * number of comparisons of removing an item 1000 times on a heap of sizes
	 * 2^n from n=0,1,2...21. assumes that the actual complexity should never be
	 * greater the 4*lg(n)
	 */
	@Test
	public void test1() {
		SpecialtyQueue<Integer> sq;

		int removeCount = 0;

		// repeat this expirment 21 times each time doubling the size
		for (int power = 0; power < 22; power++) {
			removeCount = 0;
			sq = new SpecialtyQueue<Integer>();

			// insert the items
			for (int i = 0; i <= Math.pow(2, power); i++) {
				sq.insert((int) (Math.random() * 100000));
			}
			sq.getDualHeap().setComparisonCount(0);// reset comparison count

			// remove the item, add the comparison count to removeCount
			// insert a new item, reset comparison count,
			for (int i = 0; i < 1000; i++) {
				sq.removeMin();
				removeCount += sq.getDualHeap().getComparisonCount();
				sq.insert((int) (Math.random() * 1000000));
				sq.getDualHeap().setComparisonCount(0);
			}
			// test complexity
			assertTrue(4 * power >= removeCount / 1000);

			// used for report
			// System.out.println(Math.pow(2, power));
			// System.out.println(" " + removeCount / 1000);
		}

	}

	/**
	 * Tests the complexity of the removeMax() function by finding the average
	 * number of comparisons of removing an item 1000 times on a heap of sizes
	 * 2^n from n=0,1,2...21. assumes that the actual complexity should never be
	 * greater the 4*lg(n)
	 */
	@Test
	public void test2() {
		SpecialtyQueue<Integer> sq;

		int removeCount = 0;

		// repeat this expirment 21 times each time doubling the size
		for (int power = 0; power < 22; power++) {
			removeCount = 0;
			sq = new SpecialtyQueue<Integer>();

			// insert the items
			for (int i = 0; i <= Math.pow(2, power); i++) {
				sq.insert((int) (Math.random() * 100000));
			}
			sq.getDualHeap().setComparisonCount(0);// reset comparison count

			// remove the item, add the comparison count to removeCount
			// insert a new item, reset comparison count,
			for (int i = 0; i < 1000; i++) {
				sq.removeMax();
				removeCount += sq.getDualHeap().getComparisonCount();
				sq.insert((int) (Math.random() * 100000));
				sq.getDualHeap().setComparisonCount(0);
			}
			// test complexity
			assertTrue(4 * power >= removeCount / 1000);

			// used for report
			// System.out.println(Math.pow(2, power));
			// System.out.println(" " + removeCount / 1000);
		}
	}
}
