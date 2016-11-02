package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;

public abstract class AbstractElement
{
    public abstract double[] getPreferredDomain();
    public abstract double[] getPreferredRange();

    public abstract void draw(Graph g, DrawableImage img);
}
