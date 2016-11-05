package ml.charts.elements.functions;

public class TrigFunction extends Function
{
    public static final int SIN = 0;
    public static final int COS = 1;
    public static final int TAN = 2;
    public static final int SEC = 3;
    public static final int CSC = 4;
    public static final int COT = 5;

    private static Function cos;
    private static Function sin;

    private int type;
    private Function base;

    public TrigFunction(int type)
    {
        this.type = type;
        if(cos == null || sin == null)
        {
            //generate the taylor polynomial of cos at x = pi (so we can compress all arguments to the range [0, 2pi)
            double[] taylorCoefficients = new double[100];
            double[] shift = new double[]{-Math.PI, 1};

            //this is not computationally expensive, so we can calculate a lot of terms
            for(int n = 0; n < taylorCoefficients.length; n++)
            {
                if(n % 2 == 0)
                {
                    taylorCoefficients[n] = Math.pow(-1, n / 2 + 1) / factorial(n);
                }
            }

            cos = Function.concatenate(new PolynomialFunction(taylorCoefficients), new PolynomialFunction(shift));
            sin = Function.concatenate(cos, new PolynomialFunction(new double[]{-Math.PI / 2, 1}));
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
                base = Function.divide(new PolynomialFunction(1), cos);
                break;

            case CSC:
                base = Function.divide(new PolynomialFunction(1), sin);
                break;

            case COT:
                base = Function.divide(cos, sin);
                break;

            default:
                throw new IllegalArgumentException("what");
        }
    }

    int getType()
    {
        return type;
    }

    @Override
    public double getValue(double x)
    {
        while(x < 0)
        {
            x += (2 * Math.PI);
        }

        while(x > 2 * Math.PI)
        {
            x -= (2 * Math.PI);
        }

        return base.getValue(x);
    }

    private static double factorial(double n)
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
}
