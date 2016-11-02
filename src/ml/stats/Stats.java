package ml.stats;

public class Stats
{
    public static double[] normalize(double[] data)
    {
        double[] result = new double[data.length];
        OneVarStats s = new OneVarStats(data);

        for(int i = 0; i < data.length; i++)
        {
            result[i] = (data[i] - s.getMean()) / s.getStandardDeviation();
        }

        return result;
    }
}
