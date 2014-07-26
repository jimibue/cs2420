package dequeue;

import java.util.ArrayList;
import java.util.List;

/**
 * The DualHeap class contains a seperate min and max heap that are connected in
 * the sense that when an object is added/removed to a dualHeap obeject it is
 * added/removed from both the min heap and the max heap. This class provides
 * the implemetation for the TwoHeapDeQueue insert(), removeMin(), removeMax(),
 * and size() methods.
 * 
 * @author James Yeates
 * 
 * @param <T>
 *            any comparable object
 */

public class DualHeap<T extends Comparable<T>> {

	private List<Item> minHeap, maxHeap;
	private int comparisonCount;

	public DualHeap() {
		minHeap = new ArrayList<Item>();
		maxHeap = new ArrayList<Item>();
	}

	/**
	 * returns the size of the dualHeap. if the size of the internal min and max
	 * heap are not the same a error has occuered and size might be inaccurate
	 * so a RuntimeException is thrown
	 * 
	 * @return int the size of the dual heap
	 */
	public int size() {
		if (minHeap.size() != maxHeap.size())
			throw new RuntimeException(
					"Error: internal heaps sizes do not match");
		// else return minHeap.size();
		return minHeap.size();
	}

	/**
	 * This method adds the same item object to min and max heap in the proper
	 * postion and updates the indexes while the item is perculating up the
	 * heaps
	 * 
	 * @param item
	 *            an item object that stores the data and min heap index and max
	 *            heap index
	 */

	public void add(T data) {

		// Create the item
		Item item = new Item(data);

		// insert at end of min heap,set min index, restore heap
		minHeap.add(minHeap.size(), item);
		item.setMinIndex(minHeap.size() - 1);
		perculateUpMinHeap(minHeap.size() - 1);

		// insert at end of max heap,set max index, restore heap
		maxHeap.add(maxHeap.size(), item);
		item.setMaxIndex(maxHeap.size() - 1);
		perculateUpMaxHeap(maxHeap.size() - 1);

	}

	/**
	 * This method removes and returns the item with the highest priorty from
	 * both the min and max heap and restores the heap property for both
	 * repectively.
	 * 
	 * @return T the item with the highestPriorty
	 */
	public T removeMax() {
		// if size is one just remove and return the item
		if (maxHeap.size() == 1) {
			minHeap.remove(0);
			return maxHeap.remove(0).data;
		}
		// get the higest proiorty item
		Item removedItem = maxHeap.get(0);

		// set the end element to the root of the maxHeap.
		Item tempItem = maxHeap.remove(maxHeap.size() - 1);
		tempItem.maxIndex = 0;
		maxHeap.set(0, tempItem);

		// Restore the max heap property
		percolateDownMax(0);

		// remove item from min Heap
		Item replacement = minHeap.remove(minHeap.size() - 1);
		// if item to be removed from the min Heap is the
		// end, just return
		if (removedItem.minIndex == minHeap.size())
			return removedItem.getData();

		// set the replacement to the removedItem index, updated index
		minHeap.set(removedItem.minIndex, replacement);
		replacement.minIndex = removedItem.minIndex;

		// restore minHeap
		// determine wether new inserted item needs to percolate up or down.
		int replacementParent = (replacement.minIndex - 1) / 2;
		if (specialCompareTo(replacement.getData(),
				minHeap.get(replacementParent).getData()) < 0)
			perculateUpMinHeap(replacement.minIndex);
		else
			percolateDownMin(replacement.minIndex);

		return removedItem.getData();
	}

	/**
	 * This method removes and returns the item with the lowest priorty from
	 * both the min and max heap and restores the heap property for both
	 * repectively.
	 * 
	 * @return T the item with the lowestPriorty
	 */
	public T removeMin() {
		// if size is one remove both items
		if (minHeap.size() == 1) {
			maxHeap.remove(0);
			return minHeap.remove(0).data;
		}
		// get the lowest proiorty item
		Item removedItem = minHeap.get(0);

		// set the end element to the root of the minHeap.
		Item tempItem = minHeap.remove(minHeap.size() - 1);
		tempItem.minIndex = 0;
		minHeap.set(0, tempItem);

		// Restore property
		percolateDownMin(0);

		// remove from maxHeap
		Item replacement = maxHeap.remove(maxHeap.size() - 1);
		// check if the node to be removed from the min Heap is the
		// end so, if it is just remove it
		
		if (removedItem.maxIndex == maxHeap.size())
			return removedItem.getData();

		// set the replacement to the removedItem index, updated index
		maxHeap.set(removedItem.maxIndex, replacement);
		replacement.maxIndex = removedItem.maxIndex;

		// restore maxHeap
		// determine wether new inserted item needs to percolate up or down.
		int replacementParent = (replacement.maxIndex - 1) / 2;
		if (specialCompareTo(replacement.getData(),
				maxHeap.get(replacementParent).getData()) > 0)
			perculateUpMaxHeap(replacement.maxIndex);

		else
			percolateDownMax(replacement.minIndex);

		return removedItem.getData();

	}

	/**
	 * This helper method restores the max heap property after an new item has
	 * been inserted. This method allows for the item to be inserted anywhere in
	 * the heap and will start where it is started
	 * 
	 * @param int- the index/starting point of the method
	 */
	private void percolateDownMax(int parent) {
		// special case nothing needs to be done here
		if (maxHeap.size() < 2)
			return;

		int left = 2 * parent + 1; // find index of the left child
		int right = left + 1; // get index of right child
		int max = left; // for optimiztion loop

		// loop until left child does not exist
		while (left < maxHeap.size()) {
			// check to see if there is right child, if so compare children
			// to find the greater of the two
			if (right < maxHeap.size()) {
				// if left is < right
				if (specialCompareTo(maxHeap.get(left).getData(),
						maxHeap.get(right).getData()) < 0)
					max = right;
				else
					max = left;
			} else
				max = left;
			// if child value > parent swap values and update variables, else
			// break
			if (specialCompareTo(maxHeap.get(max).getData(), maxHeap
					.get(parent).getData()) > 0) {
				swapMaxHeapItems(max, parent);
				parent = max;
				left = 2 * parent + 1;
				right = left + 1;
			} else
				return;
		}
	}

	/**
	 * This helper method restores the min heap property after an new item has
	 * been inserted. This method allows for the item to be inserted anywhere in
	 * the heap and will start where it is started
	 * 
	 * @param parent
	 *            int- the index/starting point of the method
	 */

	private void percolateDownMin(int parent) {
		// special case nothing needs to be done here
		if (minHeap.size() < 2)
			return;

		int left = 2 * parent + 1; // find index of the left child
		int right = left + 1; // get index of right child
		int min = left; // for optimiztion loop

		// loop until left child does not exist
		while (left < minHeap.size()) {

			// check to see if there is right child, if so compare children
			// to find the smaller of the two children
			if (right < minHeap.size()) {
				// if left is > right
				if (specialCompareTo(minHeap.get(left).getData(),
						minHeap.get(right).getData()) > 0)
					min = right;
				else
					min = left;
			} else
				min = left;
			// if min(child) with greatest value < parent swap values and update
			// variables else break
			if (specialCompareTo(minHeap.get(min).getData(), minHeap
					.get(parent).getData()) < 0) {
				swapMinHeapItems(min, parent);
				parent = min;
				left = 2 * parent + 1;
				right = left + 1;
			} else
				break;
		}
	}

	/**
	 * this helper method restores the minHeap property by checking if the
	 * childs value is less than its parents value, if they are equal the child
	 * is in the right postion and nothing is done. This method also updates the
	 * index that relate the min and max Heaps with one another
	 * 
	 * @param index
	 *            int the index at which the percualte up process begins
	 */

	private void perculateUpMinHeap(int index) {
		// get the last value from the list
		T data = minHeap.get(index).getData();// ?????index or minHeap.size()-1
		T parentData;
		int parentIndex;

		// while the front of the list has not been reached
		while (index > 0) {

			// find the parent of the item, determine its value
			parentIndex = (index - 1) / 2;// find parent, integer division
			parentData = minHeap.get(parentIndex).getData();

			// if childs data < parent, need to swap
			if (specialCompareTo(data, parentData) < 0) {
				swapMinHeapItems(parentIndex, index);
				index = parentIndex;
			}
			// if childs data >= parent, it is in the right place, return.
			else
				return;

		}
	}

	/**
	 * this helper method restores the maxHeap property by checking if the
	 * childs value is greater than its parents value, if they are equal the
	 * child is in the right postion and nothing is done. This method also
	 * updates the index that relate the min and max Heaps with one another
	 * 
	 * @param index
	 *            int the index at which the percualate up process begins
	 */

	private void perculateUpMaxHeap(int index) {
		// get the last value from the list
		T data = maxHeap.get(index).getData();// ?????index or minHeap.size()-1
		T parentData;
		int parentIndex;
		// while the front of the list has not been reached
		while (index > 0) {
			// find the parent of the item, determines it value
			parentIndex = (index - 1) / 2;// integer division
			parentData = maxHeap.get(parentIndex).getData();

			// if childs data > parent, need to swap
			if (specialCompareTo(data, parentData) > 0) {
				swapMaxHeapItems(parentIndex, index);
				index = parentIndex;
			}
			// if childs data >= parent, it is in the right place, return.
			else
				return;
		}
	}

	/**
	 * This method is a special version of the compare to method that keeps
	 * track of how many times it has been called. returns negative number if
	 * thisData < otherData returns postive number if thisData > otherData
	 * returns 0 if thisData = otherData
	 * 
	 * same as --- thisData.compareTo(otherData) -----
	 * 
	 * @param thisData
	 *            T
	 * @param otherData
	 *            T
	 * @return int thisData.compareTo(otherData)
	 */

	private int specialCompareTo(T thisData, T otherData) {
		comparisonCount++;
		return thisData.compareTo(otherData);
	}

	/**
	 * This helper method swaps the a parent node with a child node in the min
	 * Heap. Two things are swaped the items themselves and also the index
	 * variables need to be swaped to keep acurate location of there postion in
	 * the minHeap
	 * 
	 * @param parentIndex
	 *            int- the index of the parent to swaped
	 * @param childIndex
	 *            int- the index of the child to be swaped
	 */

	private void swapMinHeapItems(int parentIndex, int childIndex) {
		// set the temp vairiable
		Item tempItem = minHeap.get(parentIndex);
		int tempIndex = minHeap.get(parentIndex).getMinIndex();

		// swap the indecies of the items
		minHeap.get(parentIndex).setMinIndex(
				minHeap.get(childIndex).getMinIndex());
		minHeap.get(childIndex).setMinIndex(tempIndex);

		// swap the items
		minHeap.set(parentIndex, minHeap.get(childIndex));
		minHeap.set(childIndex, tempItem);

	}

	/**
	 * This helper method swaps the a parent node with a child node in the max
	 * Heap. Two things are swaped the items themselves and also the index
	 * variables need to be swaped to keep acurate location of there postion in
	 * the maxHeap
	 * 
	 * @param parentIndex
	 *            int- the index of the parent to swaped
	 * @param childIndex
	 *            int- the index of the child to be swaped
	 */

	private void swapMaxHeapItems(int parentIndex, int childIndex) {
		// set the temp vairiable
		Item tempItem = maxHeap.get(parentIndex);
		int tempIndex = maxHeap.get(parentIndex).getMaxIndex();

		// swap the indecies of the items
		maxHeap.get(parentIndex).setMaxIndex(
				maxHeap.get(childIndex).getMaxIndex());
		maxHeap.get(childIndex).setMaxIndex(tempIndex);
		// swap the items
		maxHeap.set(parentIndex, maxHeap.get(childIndex));
		maxHeap.set(childIndex, tempItem);

	}

	/**
	 * This class represents an object that stores the data for the Dual heap as
	 * well as that data postion in the min and maxHeap
	 * 
	 * @author James Yeates
	 */

	private class Item {

		private int minIndex, maxIndex;
		private T data;

		public Item(T data) {
			this.data = data;
		}

		// getters and setters
		/**
		 * @return the minIndex
		 */
		public int getMinIndex() {
			return minIndex;
		}

		/**
		 * @param minIndex
		 *            the minIndex to set
		 */
		public void setMinIndex(int minIndex) {
			this.minIndex = minIndex;
		}

		/**
		 * @return the maxIndex
		 */
		public int getMaxIndex() {
			return maxIndex;
		}

		/**
		 * @param maxIndex
		 *            the maxIndex to set
		 */
		public void setMaxIndex(int maxIndex) {
			this.maxIndex = maxIndex;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(T data) {
			this.data = data;
		}

		public String toString() {
			return data + " min index is " + minIndex + " and a max index is "
					+ maxIndex;

		}

	}// end of inner Item class

	// /////////////////////////////////////////////////////////////////////////
	// ////////////////////Methods for debugging and testing////////////////////
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @return string- the minHeap as a string in the format "{1, 2, 3}"
	 */
	public String minHeapToString() {
		if (minHeap.size() == 0)
			return "{}";
		String s = "{";
		for (int i = 0; i < minHeap.size() - 1; i++) {
			s += minHeap.get(i).getData() + ", ";
		}
		s += minHeap.get(minHeap.size() - 1).getData() + "}";
		return s;
	}

	/**
	 * 
	 * @return string- the maxHeap as a string in the format "{1, 2, 3}"
	 */
	public String maxHeapToString() {
		if (maxHeap.size() == 0)
			return "{}";
		String s = "{";
		for (int i = 0; i < maxHeap.size() - 1; i++) {
			s += maxHeap.get(i).getData() + ", ";
		}
		s += maxHeap.get(maxHeap.size() - 1).getData() + "}";
		return s;
	}

	/**
	 * This method checks wether or not the object who called this method
	 * minHeap and maxHeap are repectively following the heap property
	 * 
	 * @return boolean - true if both minHeap and MaxHeap are mantaining the
	 *         heap property.
	 */
	public boolean checkHeapCondition() {

		int parent = 0;
		int left = 1;
		int right = 2;
		// while there is still a left child check condtions
		while (left < minHeap.size()) {
			// if two children check both
			if (right < minHeap.size()) {
				// check minHeap
				if (minHeap.get(left).getData()
						.compareTo(minHeap.get(parent).getData()) < 0
						|| minHeap.get(right).getData()
								.compareTo(minHeap.get(parent).getData()) < 0)
					return false;
				// check maxHeap
				if (maxHeap.get(left).getData()
						.compareTo(maxHeap.get(parent).getData()) > 0
						|| maxHeap.get(right).getData()
								.compareTo(maxHeap.get(parent).getData()) > 0)
					return false;
			}
			// just left child
			else {
				// check minHeap
				if (minHeap.get(left).getData()
						.compareTo(minHeap.get(parent).getData()) < 0)
					return false;
				// check maxHeap
				if (maxHeap.get(left).getData()
						.compareTo(maxHeap.get(parent).getData()) > 0)
					return false;
			}
			// Increment parent by one keep looping
			parent++;
			left = 2 * parent + 1;
			right = left + 1;

		}

		// if we got here we passed all the test and can return true
		return true;
	}

	// ///////////////////////////////////////////////////////////////////////
	// Getters and Setters for DualHeap Class(used for debugging and testing)//
	// ////////////////////////////////////////////////////////////////////////
	/**
	 * @return the minHeap
	 */
	public List<Item> getMinHeap() {
		return minHeap;
	}

	/**
	 * @return the maxHeap
	 */
	public List<Item> getMaxHeap() {
		return maxHeap;
	}

	/**
	 * @return the comparisonCount
	 */
	public int getComparisonCount() {
		return comparisonCount;
	}

	public void setComparisonCount(int comparisonCount) {
		this.comparisonCount = comparisonCount;
	}

}// end of DualHeap class
