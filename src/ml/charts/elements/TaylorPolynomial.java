package ml.charts.elements;

//TODO this
public class TaylorPolynomial extends Polynomial
{
    public TaylorPolynomial()
    {
        this(new double[]{});
    }

    private TaylorPolynomial(double[] coefficients)
    {
        super(coefficients);
    }
}
