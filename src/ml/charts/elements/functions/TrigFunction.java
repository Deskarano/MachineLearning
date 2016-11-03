package ml.charts.elements.functions;

public class TrigFunction extends Function
{
    public static final int SIN = 0;
    public static final int COS = 1;
    public static final int TAN = 2;
    public static final int SEC = 3;
    public static final int CSC = 4;
    public static final int COT = 5;

    public static Function cos;
    public static Function sin;

    private int type;
    private Function base;

    public TrigFunction(int type)
    {
        this.type = type;
        if(cos == null)
        {
            double[] taylorCoefficients = new double[14];
            double[] shift = new double[]{-Math.PI, 1};

            for(int n = 0; n < taylorCoefficients.length; n++)
            {
                if(n % 2 == 0)
                {
                    taylorCoefficients[n] = Math.pow(-1, n / 2) / (double) factorial(n);
                }
            }

            cos = Function.concatenate(new PolynomialFunction(taylorCoefficients), new PolynomialFunction(shift));
            sin = Function.concatenate(cos, new PolynomialFunction(new double[]{Math.PI / 2, 1}));
        }

        switch(type)
        {
            case SIN:
                base = sin;
                break;

            case COS:
                base = cos;
                break;

            case TAN:
                base = Function.divide(sin, cos);
                break;

            case SEC:
                base = Function.divide(new PolynomialFunction(new double[]{1}), cos);
                break;

            case CSC:
                base = Function.divide(new PolynomialFunction(new double[]{1}), sin);
                break;

            case COT:
                base = Function.divide(cos, sin);
                break;
        }
    }

    @Override
    public double getValue(double x)
    {
        if(type == SIN || type == TAN || type == CSC || type == COT)
        {
            return sign(x) * base.getValue(Math.abs(x) % (2 * Math.PI));
        }
        else
        {
            return -base.getValue(Math.abs(x) % (2 * Math.PI));
        }
    }

    private static int factorial(int n)
    {
        if(n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

    private int sign(double num)
    {
        if(num < 0) return -1;
        if(num > 0) return 1;
        return 0;
    }

}
