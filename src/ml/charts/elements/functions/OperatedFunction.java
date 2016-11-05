package ml.charts.elements.functions;

class OperatedFunction extends Function
{
    static final int ADD = 0;
    static final int SUBTRACT = 1;
    static final int MULTIPLY = 2;
    static final int DIVIDE = 3;
    static final int POW = 4;
    static final int CONCATENATE = 5;

    private Function f1;
    private Function f2;

    private int type;

    OperatedFunction(Function f1, Function f2, int type)
    {
        this.f1 = f1;
        this.f2 = f2;

        this.type = type;
    }

    int getType()
    {
        return type;
    }

    Function getF1()
    {
        return f1;
    }

    Function getF2()
    {
        return f2;
    }

    @Override
    public double getValue(double x)
    {
        switch(type)
        {
            case ADD:
                return f1.getValue(x) + f2.getValue(x);

            case SUBTRACT:
                return f1.getValue(x) - f2.getValue(x);

            case MULTIPLY:
                return f1.getValue(x) * f2.getValue(x);

            case DIVIDE:
                return f1.getValue(x) / f2.getValue(x);

            case POW:
                return Math.pow(f1.getValue(x), f2.getValue(x));

            case CONCATENATE:
                return f1.getValue(f2.getValue(x));

            default:
                throw new IllegalArgumentException("what");
        }
    }
}
