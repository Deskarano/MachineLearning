package ml.stats;

import java.util.Arrays;

public class OneVarStats
{
    private double min;
    private double max;
    private double range;

    private double mean;
    private double median;

    private double standardDeviation;

    public OneVarStats(double[] data)
    {
        double[] dataCopy = new double[data.length];
        System.arraycopy(data, 0, dataCopy, 0, data.length);
        Arrays.sort(dataCopy);

        min = dataCopy[0];
        max = dataCopy[dataCopy.length - 1];
        range = max - min;

        for(int i = 0; i < dataCopy.length; i++)
        {
            mean += dataCopy[i];
        }

        mean /= dataCopy.length;
        median = dataCopy[dataCopy.length / 2];

        for(int i = 0; i < dataCopy.length; i++)
        {
            standardDeviation += Math.pow(dataCopy[i] - mean, 2);
        }

        standardDeviation /= dataCopy.length;
        standardDeviation = Math.sqrt(standardDeviation);
    }

    public double getMin()
    {
        return min;
    }

    public double getMax()
    {
        return max;
    }

    public double getRange()
    {
        return range;
    }

    public double getMean()
    {
        return mean;
    }

    public double getMedian()
    {
        return median;
    }

    public double getStandardDeviation()
    {
        return standardDeviation;
    }

    public String toString()
    {
        String result = "";

        result += "Min:\t" + min + "\n";
        result += "Max:\t" + max + "\n";
        result += "Range:\t" + range + "\n";
        result += "Mean:\t" + mean + "\n";
        result += "Median:\t" + median + "\n";
        result += "stdDev:\t" + standardDeviation + "\n";

        return result;
    }
}
