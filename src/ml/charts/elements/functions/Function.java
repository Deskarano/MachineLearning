package ml.charts.elements.functions;

import ml.charts.DrawableImage;
import ml.charts.Graph;
import ml.charts.elements.Graphable;

import java.util.Arrays;

public abstract class Function implements Graphable
{
    public abstract double getValue(double x);

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

    @Override
    public void draw(Graph g, DrawableImage img, int color)
    {
        int width = g.getWidth();
        int height = g.getHeight();

        double[] coords = g.getCoordinateFromPixel(new int[]{0, 0});
        double result = getValue(coords[0]);

        int[] lastPixel = g.getPixelFromCoordinate(new double[]{coords[0], result});

        for(int i = 1; i < width; i++)
        {
            coords = g.getCoordinateFromPixel(new int[]{i, 0});
            result = getValue(coords[0]);

            int[] pixel = g.getPixelFromCoordinate(new double[]{coords[0], result});

            if(pixel[0] >= 0 && pixel[0] < width && pixel[1] >= 0 && pixel[1] < height)
            {
                if(lastPixel[0] >= 0 && lastPixel[0] < width && lastPixel[1] >= 0 && lastPixel[1] < height)
                {
                    //draw lines instead of points to make the function look smooth
                    img.line(lastPixel[0], lastPixel[1], pixel[0], pixel[1], color);
                }
            }

            lastPixel = pixel;
        }
    }

    public static Function add(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.ADD);
    }

    public static Function add(Function f, double c)
    {
        return Function.add(f, new PolynomialFunction(c));
    }

    public static Function subtract(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.SUBTRACT);
    }

    public static Function subtract(Function f, double c)
    {
        return Function.subtract(f, new PolynomialFunction(c));
    }

    public static Function subtract(double c, Function f)
    {
        return Function.subtract(new PolynomialFunction(c), f);
    }

    public static Function multiply(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.MULTIPLY);
    }

    public static Function multiply(Function f, double c)
    {
        return Function.multiply(f, new PolynomialFunction(c));
    }

    public static Function divide(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.DIVIDE);
    }

    public static Function divide(Function f, double c)
    {
        return Function.divide(f, new PolynomialFunction(c));
    }

    public static Function divide(double c, Function f)
    {
        return Function.divide(new PolynomialFunction(c), f);
    }

    public static Function pow(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.POW);
    }

    public static Function pow(Function f, double c)
    {
        return Function.pow(f, new PolynomialFunction(c));
    }

    public static Function pow(double c, Function f)
    {
        return Function.pow(new PolynomialFunction(c), f);
    }

    public static Function concatenate(Function f1, Function f2)
    {
        return new OperatedFunction(f1, f2, OperatedFunction.CONCATENATE);
    }

    public static Function derivative(Function f)
    {
        if(f instanceof TrigFunction)
        {
            int type = ((TrigFunction) f).getType();

            switch(type)
            {
                case TrigFunction.SIN:
                    return new TrigFunction(TrigFunction.COS);

                case TrigFunction.COS:
                    return Function.multiply(new TrigFunction(TrigFunction.SIN), -1);

                case TrigFunction.TAN:
                    return Function.pow(new TrigFunction(TrigFunction.SEC), 2);

                case TrigFunction.SEC:
                    return Function.multiply(new TrigFunction(TrigFunction.SEC), new TrigFunction(TrigFunction.TAN));

                case TrigFunction.CSC:
                    return Function.multiply(Function.multiply(new TrigFunction(TrigFunction.CSC), -1), new TrigFunction(TrigFunction.COT));

                case TrigFunction.COT:
                    return Function.multiply(Function.pow(new TrigFunction(TrigFunction.CSC), 2), -1);

                default:
                    //should never be called
                    throw new IllegalArgumentException("what");
            }
        }
        else if(f instanceof PolynomialFunction)
        {
            //TODO: check that newCoefficients size is not less than 0
            double[] coefficients = ((PolynomialFunction) f).getCoefficients();
            double[] newCoefficients = new double[coefficients.length - 1];

            for(int i = 1; i < coefficients.length; i++)
            {
                newCoefficients[i - 1] = i * coefficients[i];
            }

            return new PolynomialFunction(newCoefficients);
        }
        else if(f instanceof LogFunction)
        {
            return Function.divide(1, Function.multiply(new PolynomialFunction(0, 1), Math.log(((LogFunction) f).getBase())));
        }
        else
        {
            //its an operated function!
            int type = ((OperatedFunction) f).getType();

            Function d1 = Function.derivative(((OperatedFunction) f).getF1());
            Function d2 = Function.derivative(((OperatedFunction) f).getF2());

            if(type == OperatedFunction.ADD)
            {
                return Function.add(d1, d2);
            }
            else if(type == OperatedFunction.SUBTRACT)
            {
                return Function.subtract(d1, d2);
            }
            else if(type == OperatedFunction.MULTIPLY)
            {
                Function f1 = Function.multiply(((OperatedFunction) f).getF1(), d2);
                Function f2 = Function.multiply(((OperatedFunction) f).getF2(), d1);
                return Function.add(f1, f2);
            }
            else if(type == OperatedFunction.DIVIDE)
            {
                Function numerator = Function.subtract(Function.multiply(((OperatedFunction) f).getF1(), d2), Function.multiply(((OperatedFunction) f).getF2(), d1));
                Function denominator = Function.pow(((OperatedFunction) f).getF2(), 2);

                return Function.divide(numerator, denominator);
            }
            else if(type == OperatedFunction.POW)
            {
                //TODO: fix
                return Function.multiply(Function.concatenate(new LogFunction(), ((OperatedFunction) f).getF1()), f);
            }
            else if(type == OperatedFunction.CONCATENATE)
            {
                return Function.multiply(d2, Function.concatenate(d1, ((OperatedFunction) f).getF2()));
            }
            else
            {
                throw new IllegalArgumentException("what");
            }
        }
    }
}
