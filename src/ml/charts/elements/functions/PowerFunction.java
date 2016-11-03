package ml.charts.elements.functions;


class PowerFunction extends Function
{
    private Function f1;
    private Function f2;

    PowerFunction(Function f1, Function f2)
    {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public double getValue(double x)
    {
        return Math.pow(f1.getValue(x), f2.getValue(x));
    }
}
