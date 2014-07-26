package homework06;

public class LinkedList1 {
	
	
	Node head;
	int size;
	
	public static void main(String[] args) {
		LinkedList1 l = new LinkedList1();
		
		l.add(1);
		System.out.println(l.toString());
		
		
	}
	
	public void add(int x){
		if (size ==0){
			head = new Node(x);
			Node temp = new Node(x);
			head.next= temp;
			
		}
		
		
	}
	public String toString(){
		return this.head.next.x+"";
	}
	
	
	private class Node{
		Node next;
		int x;
		
		Node(int x){
			this.x = x;
			
		}
	}

}
