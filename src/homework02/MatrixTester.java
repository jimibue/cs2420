/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with more code to
 * sufficiently test your Matrix class. We will be using our own MatrixTester for grading.
 */
package homework02;

/**
 * Application for testing the Matrix class.
 * 
 * @author James Yeates
 * @version 1/15/14
 */
public class MatrixTester
{
    public static void main (String[] args)
    {

        System.out.println ("James Yeates");
        System.out.println ("Assignment #2");        
        
        // Note - when you create a new int[][], you can supply initial values, see
        //   below for the syntax.
        
     // These statements exercise the second Matrix constructor.
        
        Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
  
        Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
        
        Matrix M3 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
        
        Matrix M4 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

        // This is the known correct result of multiplying M1 by M2.
        
        Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
       
        /*
         * Note that none of the tests below will be correct until you have implemented all methods. This is just one
         * example of a test, you must write more tests and cover all cases.
         */

        // Get the matrix computed by your times method.
        // This statement exercises your multiply method.
        
        Matrix computedMultiply = M1.multiply(M2);

        // This statement exercises your toString method.
        
        System.out.println ("Test #1:");

        // This statement exercises your toString method.
        
        System.out.println("  Computed result for M1 * M2:\n" + computedMultiply);

        // This statement exercises your .equals method, and makes sure that your 
        // computed result is the same as the known correct result.
        
        if (!referenceMultiply.equals(computedMultiply))
            System.out.println("  Should be:\n" + referenceMultiply);
        else
            System.out.println ("  Correct");

        // TODO: fill in more tests that fully exercise all of your methods
        
        //Test first constructor, setElement, getElement, 
        
        Matrix M6 = new Matrix(2,4);
        M6.setElement(1, 2, 3);
        
        Matrix M7 = new Matrix(new int[][]{ {0, 0, 0, 0}, {0, 0, 3, 0} });
        String s = M7.toString();
        
        if(!(M6.toString().equals("0 0 0 0\n0 0 3 0\n")))
        	System.out.println(getLine()  );
        
        if(!(M6.toString().equals(M7.toString()))) 
        	System.out.println(getLine());
        
        if((M6.getElement(1, 2)!= 3))
        	System.out.println(getLine());
        
        if((M6.getElement(0, 0)!= 0))
        	System.out.println(getLine());
       
        if(M6.getHeight()!=2)
        	System.out.println(getLine());
        
        if(M6.getWidth()!=4)
        	System.out.println(getLine());

        //test add
        Matrix matrixAddition = M1.add(M3);
        
        Matrix refernceAdditon = new Matrix(new int[][]{ {2,4,6}, {4,10,12} });
        
        if(!(matrixAddition.equals(refernceAdditon)))
        		System.out.println(getLine());
        
        // test for incorrect dimensions
        Matrix incorrectDimensions = M1.add(M2);
        
        try{
        	System.out.println(incorrectDimensions.toString());
        	System.out.println(getLine());
        	
        }catch(NullPointerException e){
        	//do nothing, should fail
        }
        
        Matrix M10 = new Matrix (new int[][]{{1, 2, 3},{4, 5, 6},{7, 8, 9}});
        
        if(!(M10.toString().equals("1 2 3\n4 5 6\n7 8 9\n")))
        	System.out.println(getLine());
        
        //int x = M10.getElement(1, 1);  should cause compiler error elements are of type long.
        
        //test for improper use/creation of matrices
        int [][] x = new int[0][1];
        try{
        	Matrix M11 = new Matrix(x);
        	System.out.println(getLine());
        }
        catch(IllegalMatrixSizeException e){
        	// do nothing should be caught
        }
        
        //check to see if non-uniform array sizes are caught
        int [] arr1 = new int[4];
        int [] arr2 = new int[3];
        int [][] arr3 = new int[2][0];
        arr3[0] = arr1;
        arr3[1] = arr2;
        try{
        	Matrix M12 = new Matrix(arr3);
        	System.out.println(getLine());
        }
        catch(IllegalMatrixSizeException e){
        	// do nothing should be caught
        }
        
        
        try{
        	Matrix M13 = referenceMultiply.multiply(new Matrix (new int [][]{{0},{0},{0},{0}}));
        	System.out.println(M13.toString() );
        	System.out.println(getLine());
        }
        catch(NullPointerException e){
        	// do nothing should be caught
        }
        try{
        	Matrix M14 = M1.add(M2);
        	System.out.println(M14.toString() );
        	System.out.println(getLine());
        }
        catch(NullPointerException e){
        	// do nothing should be caught
        }
        try{
        	Matrix M14 = M2.add(M1);
        	System.out.println(M14.toString() );
        	System.out.println(getLine());
        }
        catch(NullPointerException e){
        	// do nothing should be caught
        }

        System.out.println("Testing done");
        	
        
    }
    

    /** 
	 * This helper method prints the line it was called on
	 * @return  - Current line number.
	 */ 
	public static String getLine() {
	    return "Falied at line: " +(Thread.currentThread().getStackTrace()[2].getLineNumber()-1);
	}
}