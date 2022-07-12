package com.zipcodeconway;

import java.util.Random;

public class ConwayGameOfLife {

    private SimpleWindow displayWindow;
    private Integer dimension;
    private int[][] next;
    private int[][] current;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.displayWindow = new SimpleWindow(dimension);
        this.current = createRandomStart(dimension);
        this.next = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.dimension = dimension;
        this.displayWindow = new SimpleWindow(dimension);
        this.current = startmatrix;
        this.next = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        Random rand = new Random();
        int[][] randomBoard = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                randomBoard[i][j] = rand.nextInt(0, 2);
            }
        return randomBoard;
    }

    public int[][] simulate(Integer maxGenerations) {
        for (int i = 0; i <= maxGenerations; i++) {
            this.displayWindow.display(this.current, i);
            for (int row = 0; row < this.dimension; row++) {
                for (int col = 0; row < dimension; col++) {
                    this.next[row][col] = isAlive(row, col, this.current);
                }
            }
            copyAndZeroOut(this.next, this.current);
        }
        return this.current;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                current[row][col] = next[row][col];
                next[row][col] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int liveCount = 0;

        // row - 1, col - 1 = top right
        // row - 1, col     = top
        // row - 1, col + 1 = top right
        // row    , col - 1 = left
        // row    , col + 1 = right
        // row + 1, col - 1 = bottom left
        // row + 1, col     = bottom
        // row + 1, col + 1 = bottom right

        if (row - 1 >= 0) {
            if (world[row - 1][col] == 1) liveCount++;
            if (col - 1 >= 0) {
                if (world[row - 1][col - 1] == 1) liveCount++;
            }
            if (col + 1 < this.dimension) {
                if (world[row - 1][col + 1] == 1) liveCount++;
            }
        }
        if (row + 1 < this.dimension) {
            if (world[row + 1][col] == 1) liveCount++;
            if (col - 1 >= 0) {
                if (world[row + 1][col - 1] == 1) liveCount++;
            }
            if (col + 1 < this.dimension) {
                if (world[row + 1][col + 1] == 1) liveCount++;
            }
        }
        if (col - 1 >= 0) {
            if (world[row][col - 1] == 1) liveCount++;
        }
        if (col + 1 < this.dimension) {
            if (world[row][col + 1] == 1) liveCount++;
        }
        return liveCount;
    }
}
