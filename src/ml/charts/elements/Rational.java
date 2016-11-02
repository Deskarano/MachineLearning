package ml.charts.elements;

public class Rational extends Function
{
    private Polynomial p1;
    private Polynomial p2;

    public Rational(double[] coefficients1, double[] coefficients2)
    {
        this(new Polynomial(coefficients1), new Polynomial(coefficients2));
    }

    public Rational(Polynomial p1, Polynomial p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public double getValue(double x)
    {
        return p1.getValue(x) / p2.getValue(x);
    }

    @Override
    public double[] getPreferredDomain()
    {
        return null;
    }

    @Override
    public double[] getPreferredRange()
    {
        return null;
    }
}
