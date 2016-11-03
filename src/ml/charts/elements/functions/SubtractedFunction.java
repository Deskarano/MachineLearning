package ml.charts.elements.functions;


class SubtractedFunction extends Function
{
    private Function f1;
    private Function f2;

    SubtractedFunction(Function f1, Function f2)
    {
        this.f1 = f1;
    }

    @Override
    public double getValue(double x)
    {
        return f1.getValue(x) - f2.getValue(x);
    }
}
