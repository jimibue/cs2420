/**
 * This file is the starting point for homework assignment02.
 */

package homework02;

/**
 * Objects of this class represent a matrix (from mathematics).  Matrix
 * elements are kept as long integer values.
 * 
 * @author Your name goes here.
 * @version The current date goes here.
 */
public class Matrix
{
    // Fields - use these instance variables in your solution
    
    private int numRows;
    private int numCols;
    private long elements[][];

    
    /**
     *  Creates an empty matrix of the specified size.  All entries are
     *  set to 0.
     *  
     *  @param   rows   the number of rows to be in this matrix
     *  @param   cols   the number of columns to be in this matrix
     *  @throws  IllegalMatrixSizeException if rows or cols are non-positive
     */ 
    public Matrix (int rows, int cols)
    {
        /*
         * TODO: students must finish this constructor, the matrix should be
         * properly built when you are done. (Remember to use an array of long
         * integers.)
         */
    	if(rows<0 || cols<0)
    		throw new IllegalMatrixSizeException();
    	elements = new long[rows][cols];
    	numRows = rows;
    	numCols = cols;
    	
    	
    }
    
    
    /**
     *  Creates this matrix from an existing 2D array of integer data.
     *  The contents of the array are copied into this matrix (so that
     *  the caller can change the input array without changing this matrix).
     *  
     *  The size of this matrix will match the size of the input data, so the
     *  input data array dimensions must be non-zero.
     *  
     *  @param   d   a 2D array of integer data to place in this matrix
     *  @throws  IllegalMatrixSizeException if the input array is empty or non-rectangular
     */  
    public Matrix (int data[][])
    {
        // Extract the size from the array, make sure it is rectangular.
        
        numRows = data.length; // d.length is the number of 1D arrays in the 2D array, the length of the first dimension
        if (numRows == 0)
            throw new IllegalMatrixSizeException();
        
        for (int row = 0; row < numRows; row++)  // Check to see all rows are the same width
            if (data[row].length != data[0].length)
                throw new IllegalMatrixSizeException();

        numCols = data[0].length; // d[0] is the first 1D array, the first row
                
        // Create the matrix, copy the values from the input array.
        
        /*
         * TODO: students must finish this constructor, the matrix should be
         * properly built when you are done. (Remember to use an array of long
         * integers.)
         */
        this.elements = new long[numRows][numCols];
        for(int row = 0; row < numRows; row++){
        	for( int col = 0; col < numCols; col++){
        		elements[row][col] = data[row][col];
        	}
        }

    }
    
    
    /**
     * Returns the number of columns in this matrix.
     * 
     * @return  the number of columns in this matrix
     */
    public int getWidth ()
    {
        /*
         * TODO:  Students should update this function (one line of code).
         */
        
        return numCols;  // stub - replace with real code.
    }
    
    
    /**
     * Returns the number of rows in this matrix.
     * 
     * @return  the number of rows in this matrix
     */
    public int getHeight ()
    {
        /*
         * TODO:  Students should update this function (one line of code).
         */
        
        return numRows;  // stub - replace with real code.
    }
    
    
    /**
     * Changes one location in this matrix to the specified value.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @param value  the new value for this element in the matrix
     */
    public void setElement (int row, int col, long value)
    {
        /*
         * TODO:  Students should write this function (one line of code).
         */
    	elements[row][col] = value;
    }
    
    
    /**
     * Returns the value from one location in this matrix.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @return the value of the specified element
     */
    public long getElement (int row, int col)
    {
        /*
         * TODO:  Students should write this function (one line of code).
         */
        
        return elements[row][col];  // stub - replace with real code.
    }
    
    
    /**
     * Returns a multi-line string representing the contents of this matrix.
     * If printed, the returned string will resemble the mathematical diagram
     * of a matrix.  There will be single spaces between values and newline 
     * characters at the end of every row.  The returned string will only 
     * contain numbers, single spaces, and newline characters ('\n').
     * 
     * If the matrix contains {{1, 2, 3},{4, 5, 6},{7, 8, 9}}, this method would
     * return:
     *   "1 2 3\n4 5 6\n7 8 9\n"
     *   
     * @return a printable string representing this matrix
     */
    @Override
    public String toString ()
    {
        /*
         * TODO: replace the below return statement with the correct code, you must return a String that represents this
         * matrix, as specified above
         */
    	String matrixString ="";
    	for(int row = 0; row < this.numRows; row++){
    		for(int col = 0; col< this.numCols-1; col++){
    			matrixString += this.elements[row][col]+ " ";
    		}
    		matrixString+=this.elements[row][this.numCols-1]+"\n";
    	}
        return matrixString;  // stub - replace with real code
    }
    

    /**
     * Returns the matrix product between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the product.
     * 
     * For the multiplication, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param  m  a matrix for the righthand side of matrix multiplication 
     * @return the product (this * m)
     */
    public Matrix multiply (Matrix m)
    {
        /*
         * TODO: replace the below return statement with the correct code, This function must check if the two matrices
         * are compatible for multiplication, if not, return null. If they are compatible, determine the dimensions of
         * the resulting matrix, create it, and fill it in with the correct values for matrix multiplication.
         */
    	if(this.numCols != m.numRows)
    		return null;
    	
    	
    	Matrix newMatrix = new Matrix(this.numRows,m.numCols);
    	for (int row = 0; row < newMatrix.numRows; row++){
    		
    		for(int col = 0; col < newMatrix.numCols; col++){
    		
    			long sum =0;
    	    	for( int i=0; i< this.getWidth(); i++){
    	    		sum += this.elements[row][i] * m.elements[i][col];
    	    		}
    			newMatrix.elements[row][col]=sum;
    		
    			
    		}
    	}
    		
    	
        return newMatrix; // stub - replace with real code
    }

    /**
     * Returns the matrix sum between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the sum.
     * 
     * For the addition, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param m  a matrix for the righthand side of matrix addition 
     * @return  the sum (this + m)
     */
    public Matrix add (Matrix m)
    {
        /*
         * TODO: replace the below return statement with the correct code, This function must check if the two matrices
         * are compatible for addition, if not, return null. If they are compatible, create a new matrix and fill it in
         * with the correct values for matrix addition.
         */
    	
    	if(this.numCols != m.numCols || this.numRows != m.numRows)
    		return null; // stub - replace with real code
    	Matrix newMatrix = new Matrix(this.numRows,this.numCols);
    	for(int row = 0; row < this.numRows; row++){
    		for(int col = 0; col < this.numCols; col++){
    			newMatrix.elements[row][col] = this.elements[row][col] + m.elements[row][col];
    		}
    	}
    
    	return newMatrix;
    }

    /**
     * Returns true if Object o is a matrix and o is equal to this
     * matrix, and false otherwise.  Two matrices are equal if they
     * have the same dimensions and the the same values.
     * 
     * @return true if this matrix is equivalent to matrix o
     */    
    @Override
    public boolean equals (Object o)
    {
        if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
            return false;
        Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        /*
         * TODO: replace the return statement below with the correct code, you must return the correct value after
         * determining if this matrix is equal to the input matrix
         */
        if( this.elements.length != m.elements.length || this.elements[0].length !=m.elements[0].length)
        	return false;
        for(int row = 0; row < this.elements.length; row++)
        	for(int col =0; col <this.elements[0].length; col++)
        		if(this.elements[row][col] != m.elements[row][col])
        			return false;
        
        return true; // stub - replace with real code.
    }
    public long muliply( Matrix lhs, Matrix rhs, int currentRow, int currentCol){
    	long sum =0;
    	for( int i=0; i< lhs.getWidth(); i++){
    		sum += lhs.elements[currentRow][i] + rhs.elements[i][currentCol];
    		}
    			
    	return sum;
    }
}



/**
 * This exception type is used when a matrix is created with illegal dimensions.
 * 
 * @author Peter Jensen
 * @Version January 11, 2014
 */
class IllegalMatrixSizeException extends RuntimeException
{
    // No overriding needed.
}
