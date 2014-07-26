package dequeue;

import static org.junit.Assert.assertEquals;

import java.util.*;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test4();
		
		 
		SpecialtyQueue<Integer> dq = new SpecialtyQueue<Integer>();
		dq.insert(1);
		dq.insert(3);
		dq.insert(4);
		dq.insert(5);
		dq.insert(11);
		dq.insert(12);
		dq.insert(7);
		System.out.println(dq.getDualHeap().maxHeapToString());
		System.out.println(dq.getDualHeap().minHeapToString());
		
		dq.removeMin();
		System.out.println(dq.getDualHeap().maxHeapToString());
		System.out.println(dq.getDualHeap().minHeapToString());
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMax();System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMax();System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMax();System.out.println(dq.getDualHeap().checkHeapCondition());
		System.out.println(dq.getDualHeap().maxHeapToString());
		System.out.println(dq.getDualHeap().minHeapToString());
		
		System.exit(0);
		
		dq.removeMax();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMin();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMin();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMin();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMax();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMax();
		System.out.println(dq.getDualHeap().checkHeapCondition());
		dq.removeMin();
		
		System.out.println(dq.getDualHeap().checkHeapCondition());
		System.exit(0);
		
		dq.insert(7);
		dq.insert(3);
		dq.insert(11);
		dq.insert(17);
		dq.insert(16);
		dq.insert(6);
		dq.insert(19);
		dq.insert(2);
		System.out.println(dq.getDualHeap().maxHeapToString());
		for(int i = 0; i<8; i++)
		{
//		
//			int x;
//			 x=dq.removeMin();
//			System.out.println(x);
//			System.out.println(dq.getDualHeap().maxHeapToString());
//			checkIndexes(dq);
//		}
//		//System.exit(0);
//		ArrayList<Integer> al = new ArrayList<Integer>();
//	
//		for(int i = 0; i<100; i++)
//		{
//			Integer temp = (int)(Math.random()*100);
//			al.add(temp);
//			dq.insert(temp);
//			checkIndexes(dq);
//		}
//		System.out.println(dq.getDualHeap().getComparisonCount());
//		System.out.println("___________");
//		Collections.sort(al);
//		for(int i = 0; i< 10; i++)
//		{
//			System.out.println(dq.removeMin() + " " + al.remove(0));
//			System.out.println(dq.removeMax() + " " + al.remove(al.size()-1));
//			checkIndexes(dq);
//		
//		}
//		for(int i = 0; i< 10; i++)
//		{
//			System.out.println(dq.removeMax() + " " + al.remove(al.size()-1));
//			checkIndexes(dq);
//		
//		}
		
		System.out.println("done");
	}
//	
//	public static boolean checkIndexes (TwoHeapDequeue<Integer> d)
//	
//	{
//		
//		DualHeap<Integer>dq = d.getDualHeap();
//		//System.out.println(d.size());
//		for(int i= 0; i< dq.getMinHeap().size(); i++)
//		{
//			if(dq.getMinHeap().get(i).getMinIndex()!=i)
//			{
//				System.out.println(dq.getMinHeap().get(i).getMinIndex() + " is the min index, should be "
//						+ i);
//			}
//		}
//		for(int j =0; j< dq.getMaxHeap().size();j++)
//		{
//			if(dq.getMaxHeap().get(j).getMaxIndex()!=j)
//			{
//				System.out.println(dq.getMaxHeap().get(j).getMaxIndex() + " is the max index, should be "
//						+ j);
//			}
//		}
//		
//		return true;
//	}
//	
//	public static void test4() {
//		
//		TwoHeapDequeue<Integer> dq= new TwoHeapDequeue<Integer>();
//		List<Integer> control = new ArrayList<Integer>();
//		
//		
//		//add random (0-100000)
//	for(int j =0; j<100; j++){	
//		control.clear();
//		for(int i=0; i<7; i++)
//		{
//			int temp = (int)(Math.random()*15);
//			dq.insert(temp);
//			control.add(temp);
//		}
//		ArrayList<Integer> templist= new ArrayList<Integer>();
//		templist.addAll(control);
//		Collections.sort(control);
//		String s ="";
//		for(int i =0; i< 7; i++)
//		{	
//			//System.out.println(dq.getDualHeap().minHeapToString());
//			//System.out.println(dq.getDualHeap().maxHeapToString());
//			if((int)(Math.random()*2)%2!=0)
//			{
//				s+="Min-";
//				//System.out.println("removing min " +dq.removeMin().toString());
//				
//				//assertTrue(dq.getDualHeap().size()==(10-i));
//				if ( control.remove(0)!= dq.removeMin())
//				{	
//					System.out.println(templist.toString()+s);
//					return;
//					}
//			}
//			else
//			{
//				s+="Max-";
//				//System.out.println("removing max " + dq.removeMax());
//				//assertTrue(dq.getDualHeap().size()==(10-i));
//				if (control.remove(control.size()-1)!= dq.removeMax())
//				{	
//					System.out.println(templist.toString()+ s);
//				return;}
//			}
//			
//		}
//		//System.out.println(dq.getDualHeap().minHeapToString());
//		//System.out.println(dq.getDualHeap().maxHeapToString());
//		
//	
//	//System.out.println("done");	
//	}
//	}

}
}
