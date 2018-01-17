package ml.charts.elements.functions;

public class TrigFunction extends Function
{
    static final int sin = 0;
    static final int cos = 1;
    static final int tan = 2;
    static final int sec = 3;
    static final int csc = 4;
    static final int cot = 5;

    private int type;

    public static final TrigFunction SIN = new TrigFunction(sin);
    public static final TrigFunction COS = new TrigFunction(cos);
    public static final TrigFunction TAN = new TrigFunction(tan);
    public static final TrigFunction SEC = new TrigFunction(sec);
    public static final TrigFunction CSC = new TrigFunction(csc);
    public static final TrigFunction COT = new TrigFunction(cot);

    private TrigFunction(int type)
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
            case sin:
                return Math.sin(x);

            case cos:
                return Math.cos(x);

            case tan:
                return Math.tan(x);

            case sec:
                return 1 / Math.cos(x);

            case csc:
                return 1 / Math.sin(x);

            case cot:
                return 1 / Math.tan(x);

            default:
                throw new IllegalArgumentException("what");
        }
    }

    public String toString()
    {
        switch(type)
        {
            case sin:
                return "y = sin(x)";

            case cos:
                return "y = cos(x)";

            case tan:
                return "y = tan(x)";

            case sec:
                return "y = sec(x)";

            case csc:
                return "y = csc(x)";

            case cot:
                return "y = cot(x)";

            default:
                throw new IllegalArgumentException("what");
        }
    }
}
