import java.util.Random;

// LifeMatrix uses a 2D boolean array to implement the logic for Conway's Game of Life game.
public class LifeMatrix {

    private boolean[][] lifeMatrix;
    private static final Random rnd = new Random();
    private static final double CHANCE_FOR_LIFE = 0.4; // Defines the chance for life in every cell in a new life matrix.

    // Constructor. Initializes a random life matrix.
    public LifeMatrix(int matrixSize) {
        this.lifeMatrix = new boolean[matrixSize][matrixSize];
        for (int i = 0; i < lifeMatrix.length; i++) {
            for (int j = 0; j < lifeMatrix[0].length; j++) {
                if (rnd.nextDouble() < CHANCE_FOR_LIFE) {
                    lifeMatrix[i][j] = true;
                }
            }
        }

    }

    // Get the matrix
    public boolean[][] getMatrix() {
        return lifeMatrix;
    }

    // Moves the life matrix to the next generation as defined by Conway's Game of Life's game rules.
    public void nextGen() {
        boolean[][] newLifeMatrix = new boolean[lifeMatrix.length][lifeMatrix[0].length];
        for (int i = 0; i < lifeMatrix.length; i++) {
            for (int j = 0; j < lifeMatrix[0].length; j++) {
                int numOfAlive = getNumOfAliveNeighbours(i, j);
                boolean newStatus = lifeMatrix[i][j];
                if (lifeMatrix[i][j] && (numOfAlive < 2 || numOfAlive >= 4)) {
                    newStatus = false;
                }
                if (!lifeMatrix[i][j] && numOfAlive == 3) {
                    newStatus = true;
                }
                newLifeMatrix[i][j] = newStatus;
            }
        }
        lifeMatrix = newLifeMatrix;
    }

    // Gets coordinates in the matrix and return the number of the living neighbours.
    private int getNumOfAliveNeighbours(int x, int y) {
        int numOfAlive = 0;
        int rowStart = Math.max(x - 1, 0);
        int rowFinish = Math.min(x + 1, lifeMatrix.length - 1);
        int colStart = Math.max(y - 1, 0);
        int colFinish = Math.min(y + 1, lifeMatrix[0].length - 1);
        for (int i = rowStart; i <= rowFinish; i++) {
            for (int j = colStart; j <= colFinish; j++) {
                if (lifeMatrix[i][j] && (x != i || y != j)) {
                    numOfAlive++;
                }
            }
        }
        return numOfAlive;
    }

}
