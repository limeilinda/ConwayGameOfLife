package com.zipcodeconway;

import java.util.Random;

public class ConwayGameOfLife {
    private int[][] current;
    private int[][] next;
    private int dimension;
    private SimpleWindow window;


    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.current = createRandomStart(dimension);
        this.next = new int[dimension][dimension];
        this.window = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.dimension = dimension;
        this.current = startmatrix;
        this.next = new int[dimension][dimension];
        this.window = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomBoard = new int[dimension][dimension];
        Random rand = new Random();
        for ( int i = 0; i < dimension; i++ ) {
            for ( int j = 0; j < dimension; j++ ) {
                randomBoard[i][j] = rand.nextInt(2);
            }
        }
        return randomBoard;
    }

    public int[][] simulate(Integer maxGenerations) {
        for ( int i = 0; i <= maxGenerations; i++ ) {
            this.window.display(this.current, i);
            for ( int row = 0; row < this.dimension; row++ ) {
                for ( int col = 0; col < this.dimension; col++ ) {
                    this.next[row][col] = isAlive(row, col, this.current);
                }
            }
            copyAndZeroOut(this.next, this.current);
            this.window.sleep(2000);
        }
        return this.current;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {

        for ( int row = 0; row < this.dimension; row++ ) {
            for ( int col = 0; col < this.dimension; col++ ) {
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
        int status = world[row][col];


        for ( int i = row - 1; i <= row + 1; i++ ) {
            for ( int j = col - 1; j <= col + 1; j++ ) {
                if ( i != row || j != col ) {
                    int newRow = i;
                    int newCol = j;
                    if ( newRow < 0 ) newRow = dimension - 1;
                    if ( newRow == dimension ) newRow = 0;
                    if ( newCol < 0 ) newCol = dimension - 1;
                    if ( newCol == dimension ) newCol = 0;
                    liveCount += world[newRow][newCol];
                }
            }
        }

        if ( liveCount < 2 || liveCount > 3 ) status = 0;
        else if ( status == 1 && liveCount == 2 ) status = 1;
        else if ( liveCount == 3 ) status = 1;

        return status;
    }
}