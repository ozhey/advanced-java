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

    // drawShapesButtonPressed is triggered by a user click on the draw shapes button.
    // It draws 10 shapes with a random shape, color and size.
    @FXML
    void drawShapesButtonPressed(ActionEvent event) {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        for (int i = 0; i < NUM_OF_SHAPES; i++) {
            Shape shapeType = shapes[rnd.nextInt(shapes.length)]; 
            double x = rnd.nextDouble() * canvasWidth;
            double y = rnd.nextDouble() * canvasHeight;
            double width = rnd.nextDouble() * canvasWidth * MAX_SHAPE_SIZE;
            double height = rnd.nextDouble() * canvasHeight * MAX_SHAPE_SIZE;
            Color rndColor = new Color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble(), 1); 
            gc.setFill(rndColor);
            switch (shapeType) {
                case RECTANGLE:
                gc.fillRect(x, y, width, height);
                break;
                case OVAL:
                gc.fillOval(x, y, width, height);
                break;
                case LINE:
                gc.setStroke(rndColor);
                gc.strokeLine(x, y, x + width, y + height);
                break;
            default:
                break;
            }
        }
    }
}
