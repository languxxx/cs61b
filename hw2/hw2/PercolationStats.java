package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    Percolation grid;
    int[] thres;
    int[] index;
    int row,col;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        thres = new int[T];
        for (int t = 0; t < T;t++) {
            grid = pf.make(N);
            index = StdRandom.permutation(N*N);
            for (int i = 0;i < N;i++) {
                row = i/N;
                col = i - row*N;
                grid.open(row,col);
                if (grid.percolates()) {
                    thres[t] = grid.numberOfOpenSites();
                    break;
                }

            }
        }

    }

    public double mean() {
        return StdStats.mean(thres);
    }

    public double stddev() {
        return StdStats.stddev(thres);
    }

    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.sqrt(thres.length);
    }

    public double confidenceHigh() {
        return mean() - 1.96*stddev()/Math.sqrt(thres.length);
    }
}
