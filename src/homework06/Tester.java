package homework06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Tester {
	
	
	
	public static void main(String[] args) {

		SpecialtySet<Integer> s= new SpecialtySet<Integer>();
		
		
		
		s.add(1);
		s.add(2);
		s.add(4);
		s.add(-2);
		s.display();
		
		s.add(3);
		
		s.display();
		
		
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(3);
		s.add(-1);
		
		s.display();
		System.out.println();
		 s.remove(4);
		 s.remove(2);
		 s.remove(1);
		 s.remove(3);
		 
		 
		 
		s.display();
		
		
		for(int i =0; i<1000; i++){
			Integer x =(int)( (Math.random())*10023)-128;
//			if (x > 127)
//			{
//				s.add(x);
//			}
			s.add(x);

			
		}
		s.display();
		int count =0;
		for(int i =0; i<10000; i++){
			Integer x =(int)( (Math.random())*10023)-128;
			if(s.contains(x)){
				s.remove(x);
				count++;
			
		}
		}
		s.display();
		System.out.println(count);
		System.out.println(s.size());
		
	}

}
		


			

