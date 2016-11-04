package ml.charts;

import ml.charts.elements.Graphable;

import javax.swing.*;
import java.util.ArrayList;

public class Graph extends JLabel
{
    private int width;
    private int height;

    private double[] domain;
    private double[] range;

    private double xStep;
    private double yStep;

    private ArrayList<Graphable> data;
    private ArrayList<Integer> colors;

    public Graph(int width, int height)
    {
        this.width = width;
        this.height = height;

        data = new ArrayList<>();
        colors = new ArrayList<>();
    }
    
    public void add(Graphable g, int color)
    {
        data.add(g);
        colors.add(color);
        
        if(g.getPreferredDomain() != null)
        {
            if(domain != null)
            {
                double minX = Math.min(domain[0], g.getPreferredDomain()[0]);
                double maxX = Math.max(domain[1], g.getPreferredDomain()[1]);
                
                domain = new double[] {minX, maxX};
            }
            else
            {
                domain = g.getPreferredDomain();
            }
        }

        if(g.getPreferredRange() != null)
        {
            if(range != null)
            {
                double minX = Math.min(range[0], g.getPreferredRange()[0]);
                double maxX = Math.max(range[1], g.getPreferredRange()[1]);

                range = new double[] {minX, maxX};
            }
            else
            {
                range = g.getPreferredRange();
            }
        }

        updateSteps();

        double xpad = 10 / xStep;
        double ypad = 10 / yStep;

        domain[0] -= xpad;
        domain[1] += xpad;
        range[0] -= ypad;
        range[1] += ypad;

        updateSteps();
    }

    private void updateSteps()
    {
        if(domain == null || range == null)
        {
            domain = new double[] {-5, 5};
            range = new double[] {-5, 5};
        }

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

    private void render()
    {
        DrawableImage img = new DrawableImage(width, height);

        int[] origin = getPixelFromCoordinate(new double[] {0, 0});

        //axes
        if(origin[0] >= 0)
        {
            img.line(origin[0], 0, origin[0], height, 0xFF000000);
        }

        if(origin[1] >= 0)
        {
            img.line(0, origin[1], width, origin[1], 0xFF000000);
        }

        for(int i = 0; i < data.size(); i++)
        {
            data.get(i).draw(this, img, colors.get(i));
        }

        setIcon(new ImageIcon(img));
    }

    public int[] getPixelFromCoordinate(double[] coords)
    {
        return new int[] {(int)((coords[0] - domain[0]) * xStep), (int)((coords[1] - range[0]) * yStep)};
    }

    public double[] getCoordinateFromPixel(int[] pixel)
    {
        return new double[] {domain[0] + pixel[0] / xStep, domain[1] + pixel[1] / yStep};
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
