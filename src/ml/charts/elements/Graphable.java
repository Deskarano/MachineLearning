package ml.charts.elements;

import ml.charts.DrawableImage;
import ml.charts.Graph;

public interface Graphable
{
    double[] getPreferredDomain();

    double[] getPreferredRange();

    void draw(Graph g, DrawableImage img, int color);
}
