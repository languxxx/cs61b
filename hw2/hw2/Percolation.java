package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size, numSites = 0;
    private WeightedQuickUnionUF union;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid N");
        }
        grid = new boolean[N][N];
        size = N;
        union = new WeightedQuickUnionUF(N*N+2);
        for (int i = 0;i < N;i++) {
            for (int j = 0;j < N;j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >=size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of range");
        }
        if (!isOpen(row,col)) {
            grid[row][col] = true;
            numSites++;
            if (row == 0) {
                union.union(col,size*size);
            }
            if (row == size-1) {
                union.union(size*size-size+col,size*size+1);
            }
            if (col != 0) {
                if (isOpen(row,col-1)) {
                    union.union(row*size+col-1,row*size+col);
                }
            }
            if (col != size - 1) {
                if (isOpen(row,col+1)) {
                    union.union(row*size+col+1,row*size+col);
                }
            }
            if (row != 0) {
                if (isOpen(row-1,col)) {
                    union.union((row-1)*size+col,row*size+col);
                }
            }
            if (row != size - 1) {
                if (isOpen(row+1,col)) {
                    union.union((row+1)*size+col,row*size+col);
                }
            }
        }


    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >=size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of range");
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        return union.connected(row*size+col,size*size);
    }

    public int numberOfOpenSites() {
        return numSites;
    }

    public boolean percolates() {
        return union.connected(size*size,size+size+1);
    }

}
