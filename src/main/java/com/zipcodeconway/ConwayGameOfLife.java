package com.zipcodeconway;

public class ConwayGameOfLife {

    private SimpleWindow displayWindow;
    private Integer dimension;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.displayWindow = new SimpleWindow(dimension);

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {

    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        return new int[1][1];
    }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
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
        int length = world.length;
        int width = world[0].length;
        int aliveCount = 0;
        for (int i = 0; i < length; )
        return 0;
    }
}
