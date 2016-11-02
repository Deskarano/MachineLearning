import ml.charts.Graph;
import ml.charts.elements.Polynomial;
import ml.stats.OneVarStats;
import ml.stats.Stats;

public class Main
{
    public static void main(String[] args)
    {
        double[] data = {1, 2, 3, 4, 5, 6};
        data = Stats.normalize(data);
        OneVarStats stats = new OneVarStats(data);

        System.out.println(stats);
    }
}

