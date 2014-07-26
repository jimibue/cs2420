package extracredit02;

import java.util.ArrayList;

/**
 * CS 2420 extra credit option #2
 * 
 * This class implements a Double-Ended Queue using two sepately mantianed
 *  min and max heaps and performs the following operations
 *  size - returns the number of elements in the queue 0(1)
 *  insert - Adds an element to the queue with average time cost of O(1).
 *  removeMin - Removes and returns the element with the highest priority in O(lg n) time.
 *  removeMax - Removes and returns the element with the lowest priority in O(lg n) time.
 * 
 * @author James Yeates
 *
 * @param <T> and type that is comparable
 */

public class CopyOfMyDequeue<T extends Comparable<T>> 
{
	public int getComparisonCount() {
		return comparisonCount;
	}

	public  void setComparisonCount(int comparisonCount) {
		this.comparisonCount = comparisonCount;
	}

	public ArrayList<Item> getMinHeap() {
		return minHeap;
	}

	public ArrayList<Item> getMaxHeap() {
		return maxHeap;
	}

	private  int comparisonCount;
	private int minHeapSize, maxHeapSize;
	private ArrayList<Item> minHeap, maxHeap;
	
	
	public CopyOfMyDequeue()
	{
		minHeap = new ArrayList<Item>();
		maxHeap = new ArrayList<Item>();
		
	}
	
	/**
	 * returns the number of item in the dequeue.  Also checks
	 * to see if size returned is actally correct
	 * @return int the number of items in the dequeue
	 */
	
	public int size()
	{
		//used for debugging and to make sure size is correct
		if(maxHeap.size() != maxHeapSize || minHeap.size()!=minHeapSize)
			throw new RuntimeException("Size is incorrect size ="+ minHeapSize +"MinHeap size = " +minHeap.size());
		return minHeapSize;
	}
	/**
	 * inserts the item item into to the dequeue 
	 * @param data
	 */
	
	public void insert(T data)
	{
		if(data == null)
			return;
		Item item = new Item (data);
		
		//insert item into the min heap
		minHeap.add(minHeap.size(), item);
		item.minIndex = minHeap.size()-1;
		minHeapSize++;
		perculateUpMinHeap(minHeap.size()-1);
		
		//insert item into the max heap
		maxHeap.add(maxHeap.size(), item);
		item.maxIndex = maxHeap.size()-1;
		maxHeapSize++;
		perculateUpMaxHeap(maxHeap.size()-1);
		
	}
	public T removeMax()
	{
		if(maxHeap.size()==0)
		{
			throw new RuntimeException("error");
		}
		//get the higest proiorty item
		Item removedItem = maxHeap.get(0);
		
		//store andput the end element at the front off list.
		Item tempItem = maxHeap.remove(maxHeap.size()-1);
		tempItem.maxIndex=0;
		maxHeapSize--;
		maxHeap.set(0, tempItem);
		
		//Restore property
		percaulteDownMax(0);
		
		
		
		
		
		return removedItem.getData();
	}
	
	public T removeMin()
	{
		if(minHeap.size()==0)
		{
			throw new RuntimeException("error");
		}
		//get the higest proiorty item
		Item removedItem = minHeap.get(0);
		
		//store andput the end element at the front off list.
		Item tempItem = minHeap.remove(minHeap.size()-1);
		tempItem.minIndex=0;
		minHeapSize--;
		minHeap.set(0, tempItem);
		
		//Restore property
		percaulteDownMin(0);
		
		
		
		
		
		return removedItem.getData();
	}
	

	
	private void percaulteDownMax(int parent) {
		//special case nothing needs to be done here
		if(maxHeap.size()<2)
			return;
		
		int left = 2*parent +1; //find index of the left child
		int right = left+1; //get index of right child
		int max = left; // for optimiztion loop
		
		//loop until left child does not exist
		while(left < maxHeap.size())
		{
			//check to see if there is right child, if so compare children
			// to find the greater of the two
			if(right < maxHeap.size())
			{
				//if left is < right
				if(specialCompareTo(maxHeap.get(left).getData(), maxHeap.get(right).getData()) < 0)
					max = right;
				else
					max = left;
			}
			else
				max=left;
			//if max(chlid with greatest value > parent swap values and update variables else break
			if(specialCompareTo(maxHeap.get(max).getData(), maxHeap.get(parent).getData()) > 0)
			{
				swapMaxHeapItems(max, parent);
				parent = max;
				left =2*parent -1;
				right = parent+1;
			}
			else
				break;
			
		}
		
	}
	
	private void percaulteDownMin(int parent) {
		//special case nothing needs to be done here
		if(minHeap.size()<2)
			return;
		
		int left = 2*parent +1; //find index of the left child
		int right = left+1; //get index of right child
		int min = left; // for optimiztion loop
		
		//loop until left child does not exist
		while(left < minHeap.size())
		{
			//check to see if there is right child, if so compare children
			// to find the greater of the two
			if(right < minHeap.size())
			{
				//if left is > right
				if(specialCompareTo(minHeap.get(left).getData(), minHeap.get(right).getData()) > 0)
					min = right;
				else
					min = left;
			}
			else
				min=left;
			//if min(child with greatest value < parent swap values and update variables else break
			if(specialCompareTo(minHeap.get(min).getData(), minHeap.get(parent).getData()) < 0)
			{
				swapMinHeapItems(min, parent);
				parent = min;
				left =2*parent -1;
				right = parent+1;
			}
			else
				break;
			
		}
		
	}

	public int perculateUpMinHeap(int index)
	{
		//get value
		T data = minHeap.get(minHeapSize-1).data;
		T parentData;
		int parentIndex;
		//while not at root 
		while(index > 0)
		{
			parentIndex = (index-1)/2;// find parent, integer division
			parentData = minHeap.get(parentIndex).data;
			
			//if this is true we need to swap
			if(specialCompareTo(data, parentData) < 0)
			{
				swapMinHeapItems(parentIndex, index);
				index = parentIndex;
				
			}
			else
			{
				return index;
			}
		}
		return index;
		
	}
	
	
	public int perculateUpMaxHeap(int index)
	{
		//get value
		T data = maxHeap.get(maxHeapSize-1).data;
		T parentData;
		int parentIndex;
		//while not at root 
		while(index > 0)
		{
			parentIndex = (index-1)/2;//integer division
			parentData = maxHeap.get(parentIndex).data;
			
			//if this is true we need to swap
			if(specialCompareTo(data, parentData) > 0)
			{
				swapMaxHeapItems(parentIndex, index);
				index = parentIndex;
				
			}
			else
			{
				return index;
			}
		}
		return index;
		
	}
	
	public int specialCompareTo(T thisData, T otherData)
	{
		comparisonCount++;
		return thisData.compareTo(otherData);
	}
	
	public void swapMinHeapItems(int parentIndex, int childIndex)
	{
		Item temp = minHeap.get(parentIndex);
		int tempIndex = minHeap.get(parentIndex).minIndex;
		
		minHeap.get(parentIndex).minIndex = minHeap.get(childIndex).minIndex;
		minHeap.get(childIndex).minIndex = tempIndex;
		
		minHeap.set(parentIndex, minHeap.get(childIndex)); 
		minHeap.set(childIndex, temp); 
		
		
	}
	public void swapMaxHeapItems(int parentIndex, int childIndex)
	{
		Item temp = maxHeap.get(parentIndex);
		
		int tempIndex = maxHeap.get(parentIndex).maxIndex;
		
		maxHeap.get(parentIndex).maxIndex = maxHeap.get(childIndex).maxIndex;
		maxHeap.get(childIndex).maxIndex = tempIndex;
		
		maxHeap.set(parentIndex, maxHeap.get(childIndex)); 
		maxHeap.set(childIndex, temp); 
		
		
	}
	


	
	
	
	
	
	
	class Item 
	{
		int minIndex, maxIndex;
		private T data;
	
		Item(T data)
		{
			this.data = data;
		}
		public T getData()
		{
			return data;
		}
		
		
		
	}
}
