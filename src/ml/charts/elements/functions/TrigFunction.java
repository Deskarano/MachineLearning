package ml.charts.elements.functions;

public class TrigFunction extends Function
{
    public static final int SIN = 0;
    public static final int COS = 1;
    public static final int TAN = 2;
    public static final int SEC = 3;
    public static final int CSC = 4;
    public static final int COT = 5;

    private int type;

    public TrigFunction(int type)
    {
        this.type = type;

    }

    int getType()
    {
        return type;
    }

    @Override
    public double getValue(double x)
    {
        switch(type)
        {
            case SIN:
                return Math.sin(x);

            case COS:
                return Math.cos(x);

            case TAN:
                return Math.tan(x);

            case SEC:
                return 1 / Math.cos(x);

            case CSC:
                return 1 / Math.sin(x);

            case COT:
                return 1 / Math.tan(x);

            default:
                throw new IllegalArgumentException("what");
        }
    }
}
