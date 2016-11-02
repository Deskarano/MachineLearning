package ml.charts;

import java.awt.image.BufferedImage;

public class DrawableImage extends BufferedImage
{
    /**
     * Creates a new DrawableImage with specified dimensions
     * @param width The width of the new DrawableImage
     * @param height The height of the new DrawableImage
     */
    public DrawableImage(int width, int height)
    {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Overriden setRGB method so that indexing starts from the bottom left corner
     * @param x
     * @param y
     * @param argb
     */
    public void setRGB(int x, int y, int argb)
    {
        super.setRGB(x, super.getHeight() - y - 1, argb);
    }

    /**
     * Draws a line of color argb from (x1, y1) to (x2, y2) on the DrawableImage
     * @param x1 The x-coordinate of the pixel to draw the line from
     * @param y1 The y-coordinate of the pixel to draw the line from
     * @param x2 The x-coordinate of the pixel to draw the line to
     * @param y2 The y-coordinate of the pixel to draw the line to
     * @param argb The color (in ARGB format) to draw the line with
     */
    public void line(int x1, int y1, int x2, int y2, int argb)
    {
        //draws the line from the x-axis if the slope is less than 45 degrees, from y-axis otherwise
        if(Math.abs(x2 - x1) > Math.abs(y2 - y1))
        {
            double slope = (double) (y2 - y1) / Math.abs(x2 - x1);

            for(int i = 0; i < Math.abs(x2 - x1); i++)
            {
                setRGB(x1 + i * sign(x2 - x1), (int) (y1 + (double) i * slope), argb);
            }
        }
        else
        {
            double slope = (double) (x2 - x1) / Math.abs(y2 - y1);

            for(int i = 0; i < Math.abs(y2 - y1); i++)
            {
                setRGB((int) (x1 + (double) i * slope), y1 + i * sign(y2 - y1), argb);
            }
        }
    }

    /**
     * Draws a rectangle of color argb with corners (x1, y1) and (x2, y2) on the DrawableImage
     * @param x1 The x-coordinate of the first corner of the rectangle
     * @param y1 The y-coordinate of the first corner of the rectangle
     * @param x2 The x-coordinate of the second corner of the rectangle
     * @param y2 The y-coordinate of the second corner of the rectangle
     * @param argb The color (in ARGB format) to draw the rectangle with
     * @param filled Indicates whether the rectangle should be filled or not
     */
    public void rectangle(int x1, int y1, int x2, int y2, int argb, boolean filled)
    {
        if(!filled)
        {
            line(x1, y1, x2, y1, argb);
            line(x2, y1, x2, y2, argb);
            line(x2, y2, x1, y2, argb);
            line(x1, y2, x1, y1, argb);
        }
        else
        {
            for(int y = Math.min(y1, y2); y < Math.max(y1, y2); y++)
            {
                line(x1, y, x2, y, argb);
            }
        }
    }

    /**
     * Draws a circle of color argb with center (centerX, centerY) and radius r on the DrawableImage
     * @param centerX The x-coordinate of the center of the circle
     * @param centerY The y-coordinate of the center of the circle
     * @param r The radius of the circle
     * @param argb The color (in ARGB format) to draw the circle with
     * @param filled Indicates whether the circle should be filled or not
     */
    public void circle(int centerX, int centerY, int r, int argb, boolean filled)
    {
        //TODO: this can be optimized somehow. Sometimes it calls setRGB() with repeating values
        if(!filled)
        {
            //the 10 here is an arbitrary magic number that ensures circles are drawn nicely
            for(double i = 0; i < Math.PI / 2; i += (Math.PI / (r * 10)))
            {
                int dx = (int) (r * Math.cos(i));
                int dy = (int) (r * Math.sin(i));

                setRGB(centerX + dx, centerY + dy, argb);
                setRGB(centerX + dx, centerY - dy, argb);
                setRGB(centerX - dx, centerY + dy, argb);
                setRGB(centerX - dx, centerY - dy, argb);
            }
        }
        else
        {
            if(r == 1)
            {
                setRGB(centerX, centerY, argb);
            }
            else
            {
                circle(centerX, centerY, r, argb, false);
                circle(centerX, centerY, r - 1, argb, true);
            }
        }
    }

    /**
     * Returns the primary sign of the number
     * @param num The number to return the sign for
     * @return -1 if the number is negative, 1 if it is positive, and 0 if it is 0
     */
    private int sign(int num)
    {
        if(num < 0) return -1;
        if(num > 0) return 1;
        return 0;
    }

}
