import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

// DrawShapesController draws random shapes based on events emitted by the user.
public class DrawShapesController {

    @FXML
    private Canvas canvas;
    private static final Random rnd = new Random();

    private static enum Shape {
        RECTANGLE, OVAL, LINE
    }

    private static final Shape[] shapes = Shape.values();
    private static final int NUM_OF_SHAPES = 10;
    private static final double MAX_SHAPE_SIZE = 0.25;
    private GraphicsContext gc;

    // This function is triggered by a user click on the draw shapes button.
    // It draws 10 shapes with a random shape, color and size.
    @FXML
    void drawShapesButtonPressed(ActionEvent event) {

        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < NUM_OF_SHAPES; i++) {

            Color rndColor = new Color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble(), 1);
            gc.setFill(rndColor);
            gc.setStroke(rndColor);
            Shape shapeType = shapes[rnd.nextInt(shapes.length)];

            switch (shapeType) {
            case RECTANGLE:
                gc.fillRect(
                    rnd.nextDouble() * canvas.getWidth(), // x position
                    rnd.nextDouble() * canvas.getHeight(), // y position
                    rnd.nextDouble() * canvas.getWidth() * MAX_SHAPE_SIZE, // width
                    rnd.nextDouble() * canvas.getHeight() * MAX_SHAPE_SIZE // height
                );
                break;
            case OVAL:
                gc.fillOval(
                    rnd.nextDouble() * canvas.getWidth(), // x position
                    rnd.nextDouble() * canvas.getHeight(), // y position
                    rnd.nextDouble() * canvas.getWidth() * MAX_SHAPE_SIZE, // width
                    rnd.nextDouble() * canvas.getHeight() * MAX_SHAPE_SIZE // height
                );
                break;
            case LINE:
                double x1 = rnd.nextDouble() * canvas.getWidth();
                double y1 = rnd.nextDouble() * canvas.getHeight();
                gc.strokeLine(
                    x1, // x position of beginning
                    y1, // y position of beginning
                    x1 + rnd.nextDouble() * canvas.getWidth() * MAX_SHAPE_SIZE, // x position of end
                    y1 + rnd.nextDouble() * canvas.getHeight() * MAX_SHAPE_SIZE // y position of end
                );
                break;
            default:
                break;
            }
        }
    }
}
