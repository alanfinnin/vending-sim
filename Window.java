import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.*;

/**
 * This class creates a Window to display panes in.
 * @author Stephen Cliffe 17237157
 */
public class Window {

    private Stage stage;

    /**
     * The constructor takes in a stage to configure a window.
     * @param s This is a passed in stage to configure.
     */
    public Window(Stage s){
        stage = s;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/stage_vend_icon.png")));
        stage.setTitle("Vending Machine Simulation");
        stage.setMinWidth(200);
        stage.setMinHeight(100);
        stage.hide();
        stage.toFront();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    /**
     * This method sets the window to display the passed int BorderPane.
     * @param pane a BorderPane containing nodes.
     */
    public void setPane(BorderPane pane){
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        this.centerWindow();
        this.display();
    }

    /**
     * This method shows the stage.
     */
    public void display(){
        stage.show();
    }

    /**
     * This method creates a JOptionPane menu that displays the inputted string.
     * @param s a String to display
     */
    public void popup(String s){
        JOptionPane.showMessageDialog(null, s, "Error", 0);
    }

    /**
     * This method creates a JOptionPane menu that displays the inputted string.
     * @param s a String to display
     * @param t a Title fo the stage
     * @param i the type of icon
     */
    public void popup(String s, String t, int i){
        JOptionPane.showMessageDialog(null, s, t, i);
    }

    /**
     * This method creates and returns a GriPane that is already ccnfigured with Padding & Alignment.
     * @return GridPane includes Alignment & Insets
     */
    public GridPane getDefaultGridPane() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

    /**
     * Puts the window in the center of the screen.
     */
    public void centerWindow(){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
