package ml.matrix;

import ml.matrix.exceptions.*;

public class Matrix
{
    /**
     * The number of rows in the matrix (0-indexed)
     */
    private int rows;

    /**
     * The number of columns in the matrix (0-indexed)
     */
    private int cols;

    /**
     * The array that stores the contents of the matrix
     */
    private double[][] contents;

    /**
     * Constructs an empty matrix of size rows x cols
     *
     * @param rows The number of rows for the new matrix
     * @param cols The number of columns for the new matrix
     */
    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;

        contents = new double[rows][cols];
    }

    /**
     * Constructs a new matrix from the supplied array
     *
     * @param contents Constructs a matrix from the supplied array with same dimensions
     * @throws MatrixFormatException When the supplied array is not rectangular
     */
    public Matrix(double[][] contents) throws MatrixFormatException
    {
        this(contents.length, contents[0].length);

        for(int i = 1; i < contents.length; i++)
        {
            if(contents[i].length != contents[i - 1].length)
            {
                throw new MatrixFormatException("Matrix is not rectangular");
            }
        }

        for(int i = 0; i < contents.length; i++)
        {
            System.arraycopy(contents[i], 0, this.contents[i], 0, contents[i].length);
        }
    }

    public Matrix(double[] nums, int rows, int cols) throws MatrixFormatException
    {
        this(rows, cols);

        if(nums.length != rows * cols)
        {
            throw new MatrixFormatException("nums must fit into matrix of size rows * cols");
        }

        int numsIndex = 0;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                contents[i][j] = nums[numsIndex];
                numsIndex++;
            }
        }
    }

    /**
     * Gets the value of the matrix at a certain position
     *
     * @param i The index of the row to get (0-indexed)
     * @param j The index of the column to get (0-indexed)
     * @return The value of the matrix at (i, j)
     * @throws MatrixIndexOutOfBoundsException If i or j is out of bounds of this matrix
     */
    public final double get(int i, int j) throws MatrixIndexOutOfBoundsException
    {
        if(i >= rows)
        {
            throw new MatrixIndexOutOfBoundsException("" + i);
        }

        if(j >= cols)
        {
            throw new MatrixIndexOutOfBoundsException("" + j);
        }

        return contents[i][j];
    }

    /**
     * Sets the value of the matrix at a certain position
     *
     * @param i     The index of the row to set (0-indexed)
     * @param j     The index of the column to set (0-indexed)
     * @param value The value to set
     * @throws MatrixIndexOutOfBoundsException If i or j is out of bounds for this matrix
     */
    public void set(int i, int j, double value) throws MatrixIndexOutOfBoundsException
    {
        if(i >= rows)
        {
            throw new MatrixIndexOutOfBoundsException("" + i);
        }

        if(j >= cols)
        {
            throw new MatrixIndexOutOfBoundsException("" + j);
        }

        contents[i][j] = value;
    }

    /**
     * Returns the number of rows in the matrix
     *
     * @return The number of rows in the matrix
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * Returns the number of columns in the matrix
     *
     * @return The number of columns in the matrix
     */
    public int getCols()
    {
        return cols;
    }

    /**
     * Returns the determinant
     *
     * @return The determinant of this matrix
     * @throws MatrixFormatException If the matrix is not square
     */
    public double determinant() throws MatrixFormatException
    {
        if(rows != cols)
        {
            throw new MatrixFormatException("Matrix must be square");
        }

        if(rows == 2)
        {
            return contents[0][0] * contents[1][1] - contents[0][1] * contents[1][0];
        }
        else
        {
            double sum = 0;

            //iterate along the top row since row/col choice is arbitrary
            for(int i = 0; i < cols; i++)
            {
                sum += contents[0][i] * cofactor(0, i);
            }

            return sum;
        }
    }

    /**
     * Returns the minor at position (i, j)
     *
     * @param i The index of the row of the minor (0- indexed)
     * @param j The index of the column of the minor (0-indexed)
     * @return The minor of matrix m at position (i, j)
     */
    public double minor(int i, int j)
    {
        Matrix trimmed = new Matrix(rows - 1, cols - 1);

        int rowIndex = 0;
        int colIndex = 0;

        for(int a = 0; a < rows; a++)
        {
            if(a == i) a++;

            for(int b = 0; b < cols; b++)
            {
                if(b == j) b++;

                if(a < rows && b < cols)
                {
                    trimmed.set(rowIndex, colIndex, contents[a][b]);
                }

                colIndex++;
            }

            colIndex = 0;
            rowIndex++;
        }

        return trimmed.determinant();
    }

    /**
     * Returns the cofactor at position (i, j)
     *
     * @param i The index of the row of the cofactor (0-indexed)
     * @param j The index of the column of the cofactor (0-indexed)
     * @return The cofactor of matrix m at position (i, j)
     */
    public double cofactor(int i, int j)
    {
        return Math.pow(-1, i + j) * minor(i, j);
    }

    /**
     * Returns the size of the matrix, formatted to "i x j"
     *
     * @return A string representing the size of the matrix
     */

    public String getSize()
    {
        return rows + "x" + cols;
    }

    /**
     * Returns the matrix as an array
     *
     * @return The contents of the matrix as an array
     */
    public double[][] asArray()
    {
        double[][] newArray = new double[contents.length][contents[0].length];

        for(int i = 0; i < contents.length; i++)
        {
            System.arraycopy(contents[i], 0, newArray[i], 0, contents[i].length);
        }

        return newArray;
    }


    /**
     * Returns a string representation of the array
     *
     * @return All the values in the array, formatted into a string
     */
    public String toString()
    {
        String result = "";

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                result += get(i, j);
                result += " ";
            }

            if(i != rows - 1)
            {
                result += "\n";
            }
        }

        return result;
    }

    /**
     * Adds two matrices together and returns the result
     *
     * @param m1 The first matrix to be added
     * @param m2 The second matrix to be added
     * @return The result of m1 + m2
     * @throws MatrixArithmeticException If the matrices do not have the same dimensions
     */
    public static Matrix add(Matrix m1, Matrix m2) throws MatrixArithmeticException
    {
        if(m1.getRows() != m2.getRows() || m1.getCols() != m2.getCols())
        {
            throw new MatrixArithmeticException("Matrices do not have the same dimensions");
        }

        Matrix result = new Matrix(m1.getRows(), m1.getCols());

        for(int i = 0; i < result.getRows(); i++)
        {
            for(int j = 0; j < result.getCols(); j++)
            {
                result.set(i, j, m1.get(i, j) + m2.get(i, j));
            }
        }

        return result;
    }

    /**
     * Subtracts two matrices and returns the result
     *
     * @param m1 The first matrix to be subtracted
     * @param m2 The second matrix to be subtracted
     * @return The result of m1 - m2
     * @throws MatrixArithmeticException If the matrices do not have the same dimensions
     */
    public static Matrix subtract(Matrix m1, Matrix m2) throws MatrixArithmeticException
    {
        return Matrix.add(m1, Matrix.multiply(m2, -1));
    }

    /**
     * Multiplies a matrix by a scalar
     *
     * @param m      The matrix to be multiplied
     * @param scalar The scalar to be multiplied
     * @return The value of scalar * m
     */
    public static Matrix multiply(Matrix m, double scalar)
    {
        Matrix result = new Matrix(m.asArray());

        for(int i = 0; i < result.getRows(); i++)
        {
            for(int j = 0; j < result.getCols(); j++)
            {
                result.set(i, j, scalar * m.get(i, j));
            }
        }

        return result;
    }

    /**
     * Multiplies two matrices together
     *
     * @param m1 The first matrix to be multiplied
     * @param m2 The second matrix to be multiplied
     * @return The value of m1 * m2
     * @throws MatrixArithmeticException If the matrices cannot be multiplied because of their dimensions
     */
    public static Matrix multiply(Matrix m1, Matrix m2) throws MatrixArithmeticException
    {
        if(m1.getCols() != m2.getRows())
        {
            throw new MatrixArithmeticException("Matrices cannot be multiplied due to their dimensions");
        }

        Matrix result = new Matrix(m1.getRows(), m2.getCols());

        for(int i = 0; i < result.getRows(); i++)
        {
            for(int j = 0; j < result.getCols(); j++)
            {
                double sum = 0;

                for(int k = 0; k < m1.getCols(); k++)
                {
                    sum += m1.get(i, k) * m2.get(k, j);
                }

                result.set(i, j, sum);
            }
        }

        return result;
    }

    /**
     * Returns the transposition of m
     *
     * @param m The matrix to be transposed
     * @return The transposed version of m
     */
    public static Matrix transpose(Matrix m)
    {
        Matrix result = new Matrix(m.getCols(), m.getRows());

        for(int i = 0; i < m.getRows(); i++)
        {
            for(int j = 0; j < m.getCols(); j++)
            {
                result.set(j, i, m.get(i, j));
            }
        }

        return result;
    }

    /**
     * Calculates the inverse matrix of a matrix m using Gaussian elimination
     *
     * @param m The matrix to invert
     * @return The inverse of m
     * @throws MatrixFormatException If the matrix is not square or has 0s along the diagonals
     */
    public static Matrix inverse(Matrix m) throws MatrixFormatException
    {
        if(m.getRows() != m.getCols())
        {
            throw new MatrixFormatException("Matrix must be square");
        }

        for(int i = 0; i < m.getRows(); i++)
        {
            if(m.get(i, i) == 0)
            {
                throw new MatrixFormatException("Matrix has 0s along diagonals");
            }
        }

        double[][] mArray = m.asArray();
        double[][] resultArray = Matrix.identityMatrix(m.getRows()).asArray();

        for(int i = 0; i < mArray.length; i++)
        {
            double divisor = mArray[i][i];

            for(int j = 0; j < mArray[i].length; j++)
            {
                mArray[i][j] /= divisor;
                resultArray[i][j] /= divisor;
            }

            for(int i2 = 0; i2 < mArray.length; i2++)
            {
                if(i2 != i)
                {
                    double multiplier = mArray[i2][i];

                    for(int j = 0; j < mArray[i2].length; j++)
                    {
                        mArray[i2][j] -= mArray[i][j] * multiplier;
                        resultArray[i2][j] -= resultArray[i][j] * multiplier;
                    }
                }
            }

        }

        return new Matrix(resultArray);
    }

    /**
     * Returns an identity matrix of specified size
     *
     * @param n The size of the resulting identity matrix, which will be n x n
     * @return An identity matrix of size n X n
     */
    public static Matrix identityMatrix(int n)
    {
        Matrix result = new Matrix(n, n);

        for(int i = 0; i < n; i++)
        {
            result.set(i, i, 1);
        }

        return result;
    }
}
