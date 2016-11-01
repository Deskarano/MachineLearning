package ml.charts;

import ml.charts.elements.AbstractSeries;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JLabel
{
    private static double COMPRESSION = .5;
    private int width;
    private int height;

    private double[] domain;
    private double[] range;

    private double xStep;
    private double yStep;

    private ArrayList<AbstractSeries> data;

    public Graph(int width, int height)
    {
        this.width = width;
        this.height = height;

        data = new ArrayList<>();
    }
    
    public void add(AbstractSeries e)
    {
        data.add(e);
        
        if(e.getPreferredDomain() != null)
        {
            if(domain != null)
            {
                double minX = Math.min(domain[0], e.getPreferredDomain()[0]);
                double maxX = Math.max(domain[1], e.getPreferredDomain()[1]);
                
                domain = new double[] {minX, maxX};
            }
            else
            {
                domain = e.getPreferredDomain();
            }
        }

        if(e.getPreferredRange() != null)
        {
            if(range != null)
            {
                double minX = Math.min(range[0], e.getPreferredRange()[0]);
                double maxX = Math.max(range[1], e.getPreferredRange()[1]);

                range = new double[] {minX, maxX};
            }
            else
            {
                range = e.getPreferredRange();
            }
        }

        domain[0]--;
        domain[1]++;
        range[0]--;
        range[1]++;

        updateSteps();
    }

    private void updateSteps()
    {
        xStep = Math.abs((double) width / (domain[1] - domain[0]));
        yStep = Math.abs((double) height / (range[1] - range[0]));
    }

    public void display()
    {
        render();

        JFrame f = new JFrame();
        f.add(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }

    public void render()
    {
        DrawableImage img = new DrawableImage(width, height);

        for(AbstractSeries s : data)
        {
            s.draw(this, img);
        }

        setIcon(new ImageIcon(img));
    }

    public int[] getPixelFromCoordinate(double[] coords)
    {

        return new int[] {(int)((coords[0] - domain[0]) * xStep), (int)((coords[1] - range[0]) * yStep)};
    }

    public double[] getCoordinateFromPixel(int[] pixel)
    {
        return new double[] {pixel[0] / xStep, pixel[1] / yStep};
    }
}
