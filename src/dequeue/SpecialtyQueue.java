package dequeue;

/**
 * This class reprensents a double-ended priority queue. In a double-ended
 * priorirty queue the user will have lg n access to both the item with the
 * highest and lowest priority. Insertion will be O(1) given that the numbers
 * inserted are inserted in a unordered random way.
 * 
 * @author James Yeates
 * 
 * @param <T>
 *            any object that is comparable
 */

public class SpecialtyQueue<T extends Comparable<T>> {
	private DualHeap<T> dualHeap;

	// constructor
	public SpecialtyQueue() {
		dualHeap = new DualHeap<T>();
	}

	/**
	 * 
	 * @return the size of the of specialty queue
	 */
	public int size() {
		return dualHeap.size();
	}

	/**
	 * inserts the element into the specialty queue. If a null element is
	 * entered the method will not add it and will return
	 * 
	 * @param data
	 *            <T> any comparable object
	 */

	public void insert(T data) {
		if (data == null)
			return;
		dualHeap.add(data);
	}

	/**
	 * removes the item with lowest priority(greatest value). For example if elements
	 * 1,2,3 are in the specialty queue then 3 would be returned. If this
	 * method is called on an empty queue than a runtimeException is thrown
	 * 
	 * @return <T> the item with greatest priority (a.k.a value)
	 */

	public T removeMax() {
		if (dualHeap.size() == 0)

			throw new RuntimeException("Error: Queue is empty");

		return dualHeap.removeMax();
	}

	/**
	 * removes the item with highest priority(lowest value). For example if elements
	 * 1,2,3 where in the specialty queue then 1 would be returned. If this
	 * method is called on an empty queue than a runtimeException is thrown
	 * 
	 * @return <T> the item with lowest priority (a.k.a value)
	 */
	public T removeMin() {
		if (dualHeap.size() == 0)

			throw new RuntimeException("Error: Queue is empty");

		return dualHeap.removeMin();
	}

	/**
	 * Getter method for the dualHeap that represents an abstract item that is
	 * both a min and max priority queue.
	 * 
	 * @return this objects dualHeap
	 */

	public DualHeap<T> getDualHeap() {
		return dualHeap;
	}

}
