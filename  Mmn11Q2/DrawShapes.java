import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
// DrawShapes initializes an application that draws 10 shapes with a random shape, size and color.
// The user can click a button to regenerate the random shapes.
public class DrawShapes extends Application {  
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DrawShapes.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("DrawShapes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
 public static void main(String[] args) {
        launch(args);
    }
}