import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

// Controller for Game of Life.
// There's no option to restart the game because there's no requirement to implement it :-)
public class GameOfLifeController {

    @FXML
    private Canvas canvas;
    @FXML
    private Button button;

    private LifeMatrix lifeMatrix;
    private GraphicsContext gc;
    private static final int MATRIX_SIZE = 10;

    // Runs right after the root element has been completely processed. Sets the square's color.
    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKOLIVEGREEN);
        gc.setLineWidth(0.2); // make the strokes less visible
    }

    // Moves the life matrix to the next generation and then draws it on the canvas.
    @FXML
    private void nextGen() {
        if (button.getText().equals("Start Game")) {
            lifeMatrix = new LifeMatrix(MATRIX_SIZE);
            button.setText("Next Generation");
        } else {
            lifeMatrix.nextGen();
        }
        drawLifeMatrix();
    }

    // Draws the given life matrix on the canvas
    private void drawLifeMatrix() {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        boolean[][] matrix = lifeMatrix.getMatrix();
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                double x = i / (double) MATRIX_SIZE * canvasWidth;
                double y = j / (double) MATRIX_SIZE * canvasHeight;
                double w = canvasWidth / MATRIX_SIZE;
                double h = canvasHeight / MATRIX_SIZE;
                if (matrix[i][j]) {
                    gc.fillRect(x, y, w, h);
                }
                gc.strokeRect(x, y, w, h);
            }
        }
    }

}
