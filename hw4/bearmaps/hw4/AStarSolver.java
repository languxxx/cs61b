package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    HashMap<Vertex, Double> distTo;
    HashMap<Vertex, Vertex> edgeTo;
    ArrayHeapMinPQ<Vertex> PQ;
    Vertex s;
    Vertex t;
    AStarGraph<Vertex> graph;
    SolverOutcome outcome;
    double totalTime;
    int numStatesExplored;

    /*
    In pseudocode, this memory optimized version of A* is given below:

        Create a PQ where each vertex v will have priority p equal to the sum of v’s distance from the source
        plus the heuristic estimate from v to the goal, i.e. p = distTo[v] + h(v, goal).

        Insert the source vertex into the PQ.

        Repeat until the PQ is empty, PQ.getSmallest() is the goal, or timeout is exceeded:
            p = PQ.removeSmallest()
            relax all edges outgoing from p

    And where the relax method pseudocode is given as below:

        relax(e):
            p = e.from(), q = e.to(), w = e.weight()
            if distTo[p] + w < distTo[q]:
                distTo[q] = distTo[p] + w
            if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
            if q is not in PQ: add(q, distTo[q] + h(q, goal))
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.PQ = new ArrayHeapMinPQ<>();
        this.s = start;
        this.t = end;
        this.totalTime = timeout;
        this.graph = input;
        this.outcome = SolverOutcome.TIMEOUT;
        this.numStatesExplored = 0;

        Stopwatch sw = new Stopwatch();

        distTo.put(start, 0.0);
        PQ.add(start, 0);

        Vertex p;
        while (sw.elapsedTime() <= timeout) {
            if (PQ.size() == 0) {
                this.outcome = SolverOutcome.UNSOLVABLE;
                this.totalTime = sw.elapsedTime();
                break;
            }

            p = PQ.removeSmallest();
            numStatesExplored++;

            if (p.equals(t)) {
                this.outcome = SolverOutcome.SOLVED;
                this.totalTime = sw.elapsedTime();
                break;
            }

            for (WeightedEdge<Vertex> edge : graph.neighbors(p)) {
                relax(edge);
            }
        }
    }

    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from(), q = e.to();
        double w = e.weight(), h = graph.estimatedDistanceToGoal(q,t);

        if (!distTo.containsKey(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            PQ.add(q, distTo.get(q) + h);
        }
        else if (distTo.get(q) > distTo.get(p) + w) {
            distTo.replace(q, distTo.get(p) + w);
            edgeTo.replace(q, p);

            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo.get(q) + h);
            }
            else {
                PQ.add(q, distTo.get(q) + h);
            }
        }
    }

    public SolverOutcome outcome() {
        return this.outcome;
    }

    public List<Vertex> solution() {
        LinkedList<Vertex> soln = new LinkedList<>();
        if (outcome() != SolverOutcome.SOLVED) {
            return soln;
        }
        Vertex v = t;
        while(!v.equals(s)) {
            soln.addFirst(v);
            v = edgeTo.get(v);
        }
        soln.addFirst(s);
        return soln;
    }

    public double solutionWeight() {
        return this.distTo.get(this.t);
    }

    public int numStatesExplored() {
        return this.numStatesExplored - 1;
    }

    public double explorationTime() {
        return this.totalTime;
    }
}
