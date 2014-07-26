package extracredit02;



/**
 * This class contains helper methods to test and determine if the project
 * was done properly
 * @author James Yeates
 *
 */

public class TestTools {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyDequeue<Integer> dq = new MyDequeue<Integer>();
		
		for(int i = 0; i< 7; i++)
			dq.insert((int)(Math.random()*200));
			//dq.insert(5-i);

		for(int i =0; i< dq.size(); i++)
		{
			System.out.println(dq.getMinHeap().get(i).getData()+"  "+dq.getMaxHeap().get(i).getData());
		}
		//System.out.println(dq.getMinHeap().size());
		//System.out.println(testMinHeapProperty(dq));
		//System.out.println(dq.getComparisonCount());
		System.out.println(checkIndexes(dq));
		
		System.out.println("________");
		
		dq.removeMax();
		//.removeMin();
		
		for(int i =0; i< dq.getMaxHeap().size(); i++)
		{
			System.out.println(dq.getMinHeap().get(i).getData()+"  "+dq.getMaxHeap().get(i).getData());
		}
		System.out.println(checkIndexes(dq));
		
		System.out.println("________");
		
		dq.removeMax();
		//dq.removeMin();
		
		for(int i =0; i< dq.getMaxHeap().size(); i++)
		{
			System.out.println(dq.getMinHeap().get(i).getData()+"  "+dq.getMaxHeap().get(i).getData());
		}
		System.out.println(checkIndexes(dq));
	
	}
	
	/**
	 * This method checks that for each node with children that the data in 
	 * the children is <= to parent
	 * @param queue
	 * @return
	 */
	public static Boolean testMinHeapProperty(MyDequeue<Integer> queue)
	{
		
		int currentIndex = 0;
		int lastParent = (queue.getMinHeap().size()-1)/2;
		
		for(int i =0; i<lastParent; i++)
		{
			

			if(currentIndex*2+2 < queue.getMinHeap().size())
			{
				
				boolean compare1 =queue.getMinHeap().get(currentIndex).getData()>
					queue.getMinHeap().get(currentIndex*2 +1).getData() ;
				boolean compare2 =queue.getMinHeap().get(currentIndex).getData() >
					queue.getMinHeap().get(currentIndex*2 +2).getData() ;	
				if(compare1 || compare2)	
					return false;
					
			}
			
			//one child
			if(queue.getMinHeap().get(currentIndex*2+1) != null)
			{
				boolean compare1 =queue.getMinHeap().get(currentIndex).getData() >
				queue.getMinHeap().get(currentIndex*2 +1).getData() ;	
				if(compare1 )	
					return false;
			}
			if(i==lastParent-1)
				return true;
			else
				currentIndex++;
		}
		System.out.println("here");
		return false;
	}
	
	/**
	 * 
	 * @param dq
	 * @return
	 */
	public static boolean checkIndexes (MyDequeue<Integer> dq)
	{
		for(int i= 0; i< dq.getMinHeap().size(); i++)
		{
			if(dq.getMinHeap().get(i).minIndex!=i)
			{
				System.out.println(dq.getMinHeap().get(i).minIndex + " is the min index, should be "
						+ i);
			}
		}
		for(int j =0; j< dq.getMaxHeap().size();j++)
		{
			if(dq.getMaxHeap().get(j).maxIndex!=j)
			{
				System.out.println(dq.getMaxHeap().get(j).maxIndex + " is the max index, should be "
						+ j);
			}
		}
		return true;
	}

}
