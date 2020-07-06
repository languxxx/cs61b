import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        List<Double> myBST = new ArrayList<>();
        List<Double> optBST = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<Integer> tree = new BST();

        for (int i = 1; i <= 5000; i++) {
            tree.add(RandomGenerator.getRandomInt(50000));
            myBST.add(tree.avgDepth());
            optBST.add(ExperimentHelper.optimalAverageDepth(i));
            xValues.add(i);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("N").yAxisTitle("Avg Depth").build();
        chart.addSeries("Random generated BST", xValues, myBST);
        chart.addSeries("Optimal BST", xValues, optBST);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        List<Double> myBST = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<Integer> tree = new BST();

        for (int i = 1; i <= 5000; i++) {
            tree.add(RandomGenerator.getRandomInt(5000));
        }

        myBST.add(tree.avgDepth());
        xValues.add(0);

        for (int i = 1; i < 50000; i++) {
            tree.deleteTakingSuccessor(tree.getRandomKey());
            tree.add(RandomGenerator.getRandomInt(5000));
            xValues.add(i);
            myBST.add(tree.avgDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("N").yAxisTitle("Avg Depth").build();
        chart.addSeries("Random generated BST", xValues, myBST);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        List<Double> myBST = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<Integer> tree = new BST();

        for (int i = 1; i <= 5000; i++) {
            tree.add(RandomGenerator.getRandomInt(50000));
        }

        myBST.add(tree.avgDepth());
        xValues.add(0);

        for (int i = 1; i < 50000; i++) {
            tree.deleteTakingRandom(tree.getRandomKey());
            tree.add(RandomGenerator.getRandomInt(50000));
            xValues.add(i);
            myBST.add(tree.avgDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("N").yAxisTitle("Avg Depth").build();
        chart.addSeries("Random generated BST", xValues, myBST);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
