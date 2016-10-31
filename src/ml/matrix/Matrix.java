package ml.matrix;

import ml.matrix.exceptions.*;

public class Matrix
{
    private int rows;
    private int cols;
    private double[][] contents;

    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;

        contents = new double[rows][cols];
    }

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
            for(int j = 0; j < contents[i].length; j++)
            {
                this.contents[i][j] = contents[i][j];
            }
        }
    }

    public double get(int i, int j)
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

    public void set(int i, int j, double value)
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

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }

    public String getSize()
    {
        return rows + "x" + cols;
    }

    public double[][] asArray()
    {
        return contents;
    }

    public Vector[] asVectors()
    {
        Vector[] result = new Vector[rows];
        Matrix transposed = Matrix.transpose(this);

        for(int i = 0; i < result.length; i++)
        {
            result[i] = new Vector(transposed.asArray()[i]);
        }

        return result;
    }

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

            result += "\n";
        }

        return result;
    }

    public static Matrix add(Matrix m1, Matrix m2)
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

    //TODO: fix
    public static Matrix subtract(Matrix m1, Matrix m2)
    {
        return add(m1, Matrix.multiply(m2, -1));
    }

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

    public static Matrix multiply(Matrix m1, Matrix m2)
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

    public static double determinant(Matrix m)
    {
        if(m.getRows() != m.getCols())
        {
            throw new MatrixFormatException("Matrix must be square");
        }

        if(m.getRows() == 2)
        {
            return m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);
        }
        else
        {
            double sum = 0;

            //iterate along the top row since row/col choice is arbitrary
            for(int i = 0; i < m.getCols(); i++)
            {
                sum += cofactor(m, 0, i) * minor(m, 0, i);
            }

            return sum;
        }
    }

    public static Matrix inverse(Matrix m)
    {
        if(m.getRows() != m.getCols())
        {
            throw new MatrixFormatException("Matrix must be square");
        }

        double determinant = Matrix.determinant(m);

        Matrix result = matrixOfMinors(m);
        result = Matrix.transpose(result);
        result = Matrix.multiply(result, 1 / determinant);

        return result;
    }

    public static double minor(Matrix m, int i, int j)
    {
        Matrix trimmed = new Matrix(m.getRows() - 1, m.getCols() - 1);

        int rowIndex = 0;
        int colIndex = 0;

        for(int a = 0; a < m.getRows(); a++)
        {
            if(a == i) a++;

            for(int b = 0; b < m.getCols(); b++)
            {
                if(b == j) b++;

                try
                {
                    trimmed.set(rowIndex, colIndex, m.get(a, b));
                }
                catch(MatrixIndexOutOfBoundsException e)
                {
                    //TODO: probably fix this lazy exception hacking
                }

                colIndex++;
            }

            colIndex = 0;
            rowIndex++;
        }

        return determinant(trimmed);
    }

    public static double cofactor(Matrix m, int i, int j)
    {
        return Math.pow(-1, i + j) * minor(m, i, j);
    }

    public static Matrix matrixOfMinors(Matrix m)
    {
        Matrix result = new Matrix(m.getRows(), m.getCols());

        for(int i = 0; i < result.getRows(); i++)
        {
            for(int j = 0; j < result.getCols(); j++)
            {
                result.set(i, j, Matrix.minor(m, i, j));
            }
        }

        return result;
    }

    public static Matrix matrixOfCofactors(Matrix m)
    {
        Matrix result = new Matrix(m.getRows(), m.getCols());

        for(int i = 0; i < result.getRows(); i++)
        {
            for(int j = 0; j < result.getCols(); j++)
            {
                result.set(i, j, Matrix.cofactor(m, i, j));
            }
        }

        return result;
    }

    public static Matrix identityMatrix(int size)
    {
        Matrix result = new Matrix(size, size);

        for(int i = 0; i < size; i++)
        {
            result.set(i, i, 1);
        }

        return result;
    }
}
