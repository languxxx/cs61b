package bearmaps.hw4.integerhoppuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * The Integer Hop puzzle implemented as a graph.
 * Created by hug.
 */
public class IntegerHopGraph implements AStarGraph<Integer> {

    @Override
    public List<WeightedEdge<Integer>> neighbors(Integer v) {
        ArrayList<WeightedEdge<Integer>> neighbors = new ArrayList<>();
        neighbors.add(new WeightedEdge<>(v, v * v, 10));
        neighbors.add(new WeightedEdge<>(v, v * 2, 5));
        neighbors.add(new WeightedEdge<>(v, v / 2, 5));
        neighbors.add(new WeightedEdge<>(v, v - 1, 1));
        neighbors.add(new WeightedEdge<>(v, v + 1, 1));
        return neighbors;
    }

    @Override
    public double estimatedDistanceToGoal(Integer s, Integer goal) {
        // possibly fun challenge: Try to find an admissible heuristic that
        // speeds up your search. This is tough!
        double w1, w2, w3;
        if (s < goal) {
            w1 = goal - s;
            w2 = Math.log(((double)goal / (double) s)) / Math.log(2.0) * 5;
            w3 = Math.log((double)goal) / Math.log((double) s) * 20;
        }
        else {
            w1 = s - goal;
            w2 = Math.log(((double)s / (double) goal)) / Math.log(2.0) * 5;
            w3 = Math.log((double)s) / Math.log((double) goal) * 10;
        }

        return Math.min(Math.min(w1, w2), w3);
    }
}
