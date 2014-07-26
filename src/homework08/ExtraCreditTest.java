package homework08;





public class ExtraCreditTest {
	
	public static void main(String[] args)
	{
		//runtest(50000);
		//runtest2(50000);
		//runtest3(50000);
		//runtest4(5000);
		System.out.println("random");
		finalTest2(5000,50);
		System.out.println("----------"); 
		System.out.println("ascending");
		//finalTest(5000,1);
		//q1(255,1);
		//q2(16383,1);
		
	}
	
	
    public static void test05(int length)
    {
    	SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;;
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger(i);
    		 s.add(ti);
    	}
    	for(int i= 0; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(i);
   		 s.contains(ti);
    	}
    	for(int i= 0; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(length-i);
   		 s.remove(ti);
    	}
    	long endTime = System.currentTimeMillis();
    	System.out.println("    total time was: " + ((endTime-start)/1000.0) + " seconds");
    	System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount);
    	
    	
    	System.out.println();
    	
    	
    }
    public static void test06(int length)
    {
    	SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger(i);
    		
    	}
    	for(int i= 0; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(i);
   		 
    	}
    	for(int i= 0; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(length-i);
   		 
    	}
    	long endTime = System.currentTimeMillis();
    	System.out.println("total time was " + (endTime-start) + " milli seconds");
    	
    	
    	System.out.println();
    	
    	
    }
    
    public static void test(int length, int avlDif)
    {
    	SpecSetExtraCredit<TrackedInteger> s = new SpecSetExtraCredit<TrackedInteger>(avlDif);
    	//SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger(i);
    		 s.add(ti);
    		 ti = new TrackedInteger((int)Math.random()*100000);
    		 s.add(ti);
    		 ti = new TrackedInteger((int)Math.random()*100000);
    		 s.add(ti);
    		 ti = new TrackedInteger((int)Math.random()*100000);
    		 s.add(ti);
    	}
    	for(int i= 0; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(i);
   		 s.contains(ti);
   		 ti = new TrackedInteger((int)Math.random()*100000);
   		 s.contains(ti);
   		 ti = new TrackedInteger((int)Math.random()*100000);
   		 s.contains(ti);
   		 ti = new TrackedInteger((int)Math.random()*100000);
   		 s.contains(ti); 
   		 ti = new TrackedInteger((int)Math.random()*100000);
   		 s.contains(ti);
    	}
    	for(int i= 11; i< length; i++){
   		 TrackedInteger ti = new TrackedInteger(length-i);
   		 s.remove(ti);
   		ti = new TrackedInteger((int)Math.random()*100000);
   		s.remove(ti);
   		ti = new TrackedInteger((int)Math.random()*100000);
   		s.remove(ti);
    	}
    	long endTime = System.currentTimeMillis();
    	System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
    	System.out.println("    total time was: " + ((endTime-start)/1000.0) + " seconds");
    	System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount);
    	System.out.println("    number of rotations was:  " + s.getRotations());
    	System.out.println(" height of the tree is " + s.height(s.root));
    	
    	
    	System.out.println();
    	
    	
    }
    public static void test1(int length, int avlDif, int timesToRepeat)
    {
    	int total=0;
    	//SpecSetExtraCredit<TrackedInteger> s = new SpecSetExtraCredit<TrackedInteger>(avlDif);
    	//SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	for(int j = 0; j<timesToRepeat; j++){
    	SpecSetExtraCredit<Integer> s = new SpecSetExtraCredit<Integer>(avlDif);
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 
    		 s.add(i);
    	}
    	long endTime = System.currentTimeMillis();
    	total+= endTime-start;
    			
    	}
    	//System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
    	//System.out.println("    total time was: " + (total)/1000.0 + " seconds");
    	System.out.println(total/1000.0 );
    	//System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount/timesToRepeat);
    	//System.out.println("    number of rotations was:  " + s.getRotations());
    	//System.out.println(" height of the tree is " + s.height(s.root));
    	
    	//System.out.println();
    	
    	
    }
    
    public static void test1a(int length, int avlDif, int timesToRepeat)
    {
    	int total=0;
    	
    	TrackedInteger.comparisonCount=0;
    	for(int j = 0; j<timesToRepeat; j++){
    		SpecSetExtraCredit<Integer> s = new SpecSetExtraCredit<Integer>(avlDif);
    		long start= System.currentTimeMillis();
    		for(int i= 0; i< length; i++){
    			int k = (int)(Math.random()*100000);
    			s.add(k);
    		}
    	long endTime = System.currentTimeMillis();
    	total+= endTime-start;
    			
    	}
    	//System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
    	//System.out.println("    total time was: " + (total)/1000.0 + " seconds");
    	System.out.println(total/1000.0 );
    	//System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount/timesToRepeat);
    	//System.out.println("    number of rotations was:  " + s.getRotations());
    	//System.out.println(" height of the tree is " + s.height(s.root));
    	
    	//System.out.println();
    	
    	
    }
    public static void test2(int length, int avlDif, int timesToRepeat)
    {
    	long totaltime=0;
    	double totalrotations=0.0;
    	double height=0.0;
    	SpecSetExtraCredit<TrackedInteger> s = new SpecSetExtraCredit<TrackedInteger>(avlDif);
    	//SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	for(int j = 0; j<timesToRepeat; j++){
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger((int)(Math.random()*1000000));
    		 s.add(ti);
    	}
    	long endTime = System.currentTimeMillis();
    	totaltime+= endTime-start;
    	totalrotations+=s.getRotations();	
    	height+= s.height(s.root);
    	}
    	//System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
    	//System.out.println( (avlDif + "     "+   totaltime +"   " +TrackedInteger.comparisonCount/timesToRepeat+"   " +  height/timesToRepeat  ));
    	System.out.println( totaltime );
    	//System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount/timesToRepeat);
    	//System.out.println("    number of rotations was:  " + totalrotations/timesToRepeat);
    	//System.out.println(" height of the tree is " + height/timesToRepeat);
    	
    	//System.out.println();
    	
    	
    }
    public static void q1(int length, int avlDif)
    {
    	SpecSetExtraCredit<TrackedInteger> s = new SpecSetExtraCredit<TrackedInteger>(avlDif);
    	//SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger(i);
    		 s.add(ti);
    	}
    	System.out.println(s.height(s.root));
    	long endTime = System.currentTimeMillis();
    	System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
    	System.out.println("    total time was: " + ((endTime-start)/1000.0) + " seconds");
    	System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount);
    	System.out.println("    number of rotations was:  " + s.getRotations());
    	
    	
    	System.out.println();
    	
    	
    }
    public static void q2(int length, int avlDif)
    {	int total=0;
    	//for(int j =0; j<200; j++){
    	SpecSetExtraCredit<TrackedInteger> s = new SpecSetExtraCredit<TrackedInteger>(avlDif);
    	//SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
    	TrackedInteger.comparisonCount=0;
    	long start= System.currentTimeMillis();
    	for(int i= 0; i< length; i++){
    		 TrackedInteger ti = new TrackedInteger((int)(Math.random()*1000000));
    		 s.add(ti);
    	}
    	total+=TrackedInteger.comparisonCount;
    	long endTime = System.currentTimeMillis();
//    	System.out.println("----result for tree with size: " + length+ " avlDif: " + avlDif+"----");
//    	System.out.println("    total time was: " + ((endTime-start)/1000.0) + " seconds");
//    	System.out.println("    totalComparisios was:  " + TrackedInteger.comparisonCount);
//    	System.out.println("    number of rotations was:  " + s.getRotations());
    	
    	//}
    	System.out.println(total/100.0);
    	System.out.println(s.height(s.root));
    	
    	
    }

    

    public static void finalTest(int length,int timesToRepeat)
    {
    	test1(length,1,timesToRepeat);
    	System.out.println();
    	for(int i = 1; i<=30; i++)
    	{
    		test1(length,i,timesToRepeat);
    	}
//    	for(int i = 15; i<=50; i+=5)
//    	{
//    		test1(length,i,timesToRepeat);
//    	}
//    	for(int i =60; i<=100; i+=10)
//    	{
//    		test1(length,i,timesToRepeat);
//    	}
//    	for(int i =100; i<=1000; i+=100)
//    	{
//    		test1(length,i,timesToRepeat);
//    	}
//    	for(int i =5000; i<=7000; i+=1000)
//    	{
//    		test1(length,i,timesToRepeat);
//    	}
    	
    	
    }
    public static void finalTest2(int length,int timesToRepeat)
    {
    	test1a(length,1,timesToRepeat);
    	System.out.println();
    	for(int i = 1; i<=30; i++)
    	{
    		test1a(length,i,timesToRepeat);
    	}
//    	for(int i = 15; i<=50; i+=5)
//    	{
//    		test1a(length,i,timesToRepeat);
//    	}
//    	for(int i =60; i<=100; i+=10)
//    	{
//    		test1a(length,i,timesToRepeat);
//    	}
//    	for(int i =110; i<=1000; i+=100)
//    	{
//    		test1a(length,i,timesToRepeat);
//    	}
    	for(int i =5000; i<=6000; i+=1000)
    	{
    		test1a(length,i,timesToRepeat);
    	}
    	
    	
    }
    
    
    
    
    private static class TrackedInteger implements Comparable<TrackedInteger>
    {
        static long comparisonCount = 0;
        
        Integer i;
        
        TrackedInteger(int i)
        {
            this.i = i;    
        }
        
        @Override
        public int compareTo (TrackedInteger o)
        {
            comparisonCount++;
            return i.compareTo(o.i);
        }
        
        @Override
        public boolean equals (Object o)
        {
            return (o instanceof TrackedInteger) ? ((TrackedInteger) o).compareTo(this) == 0 : false;
        }
        
        @Override
        public String toString ()
        {
            return "" + i;
        }

    }
    
}


