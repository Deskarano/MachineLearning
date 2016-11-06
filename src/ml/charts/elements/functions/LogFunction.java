package ml.charts.elements.functions;

public class LogFunction extends Function
{
    private double base;

    public LogFunction(double base)
    {
        this.base = base;
    }

    public LogFunction()
    {
        this(Math.E);
    }

    double getBase()
    {
        return base;
    }

    @Override
    public double getValue(double x)
    {
        return Math.log(x) / Math.log(base);
    }
}
