//package homework02;
//
//public class MatrixMathTester {
//	
//	
//	public static void main(String[] args) {
//		 Matrix M1 = new Matrix(100,100);
//		 Matrix M2 = new Matrix(100,100);
//		 System.out.println(M1.getHeight());
//		 
//		 for(int i=0; i<M1.getHeight();i++)
//			 for(int j =0; j<M1.getWidth(); j++)
//				 M1.setElement(i, j, (int)(1150000));
//		
//		 for(int i=0; i<M2.getHeight();i++)
//			 for(int j =0; j<M2.getWidth(); j++)
//				 M2.setElement(i, j, (int)(1150000));
//		 int x =1150000;
//		 int y=0;
//		 
//		 long startTime = System.currentTimeMillis();
//		 for(int i =0; i< 1000; i++)
//			 M1.add(M2);
//		 long endTime = System.currentTimeMillis();
//		 
//		 long totalTime = endTime - startTime;
//		 
//		 long startFor = System.currentTimeMillis();
//		 
//		 System.out.println(y);
//			
//		 long endFor = System.currentTimeMillis();
//		 
//		 long forLoopTime = endFor - startFor;
//		 
//		 long FinalTime = totalTime - forLoopTime;
//		 
//		 System.out.println(totalTime+" - "+ forLoopTime);
//		 System.out.println("time taken for first add method is "+ totalTime);
//		 
//		 startTime = System.currentTimeMillis();
//		 for(int i =0; i< 100; i++)
//			 M1.add1(M2);
//		 endTime = System.currentTimeMillis();
//		 
//		 totalTime = endTime - startTime;
//		 
//		 startFor = System.currentTimeMillis();
//		 	
//		 for(int i =0; i< 1000; i++){}
//			
//		 endFor = System.currentTimeMillis();
//		 
//		 forLoopTime = endFor - startFor;
//		 
//		 FinalTime = totalTime - forLoopTime;
//		 
//		 System.out.println(totalTime+" - "+ forLoopTime);
//		 System.out.println("time taken for first add method is "+ totalTime);
//		 
//		 
//		 startTime = System.currentTimeMillis();
//		
//			 System.out.println();
//		 endTime = System.currentTimeMillis();
//		 
//		 System.out.println(endTime-startTime);
//	}
//
//}
