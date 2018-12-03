import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.*;

/**
 * @author Stephen Cliffe
 */
public class Window {

    private Stage stage;

    public Window(Stage s){
        stage = s;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/stage_vend_icon.png")));
        stage.setTitle("Vending Machine Simulation");
        stage.setMinWidth(200);
        stage.setMinHeight(100);

        stage.show();
    }

    public void setPane(BorderPane pane){
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }

    public void display(){
        stage.show();
    }

    public void popup(String s){
        JOptionPane.showMessageDialog(null, s, "Error", 0);
    }

    public void popup(String s, String t, int i){
        JOptionPane.showMessageDialog(null, s, t, i);
    }

    public GridPane getDefaultGridPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

}
