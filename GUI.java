import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage stage = new Stage();

    /**
     * The run() method initializes the GUI methods
     */
    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        accountChoice();
    }

    /**
     * This method takes a filled in GridPane and makes a window to display it. It closes the window
     * that was previously opened. It adds an icon and a title to the window, then shows it.
     * @param pane A filled in GridPane to be displayed.
     */
    private void display(BorderPane pane){
        stage.close();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("stage_vend_icon.png")));
        stage.setMinHeight(150);
        stage.setMinWidth(350);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Vending Machine Simulation");
        stage.show();
    }

    /**
     * This method makes a GridPane with options for accessing the admin options or the normal user
     * options then initializes the adminLogin() method of the ... method depending on the button
     * chosen. It passes the filled GridPane to the display() method.
     */
    private void accountChoice(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label loginChoice = new Label("What account would you like to use?\n\n");
        Button admin = new Button("Admin");
        admin.setTooltip(new Tooltip("Login as admin"));
        admin.setOnAction(event -> adminLogin());

        Button user = new Button("User");
        user.setTooltip(new Tooltip("Continue on as user"));
        user.setOnAction(event -> userMenu());

        pane.add(loginChoice, 0, 0, 2 ,1);
        pane.add(admin, 0, 1);
        pane.add(user, 1, 1);

        BorderPane show = new BorderPane(pane);
        display(show);    }

    /**
     * The adminLogin() method makes and fills in a GridPane with a password field and an email
     * field. It also contains a clear button that refreshes the menu and a submit button which
     * validates the data. The pane also contains a button that leads to the createAccount() method.
     */
    private void adminLogin() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label email = new Label("Email");
        Label pass  = new Label("Password");

        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();

        Button submit = new Button("Submit");
        submit.setOnAction(event -> { adminMenu(); });

        Button clear  = new Button("Clear");
        clear.setOnAction(event -> adminLogin());

        pane.add(email, 0, 0);
        pane.add(emailField, 1, 0, 2, 1);
        pane.add(pass, 0, 1);
        pane.add(passField, 1, 1, 2, 1);
        pane.add(submit, 0, 2);
        pane.add(clear, 1, 2);

        BorderPane show = new BorderPane(pane);
        display(show);
    }

    /**
     *
     */
    private void userMenu(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label message = new Label("What would you like to do?\n");

        Button display = new Button("Show Products");
        display.setOnAction(event -> { });

        Button insert = new Button("Insert Coin");
        insert.setOnAction(event -> { });

        Button buy = new Button("Buy");
        buy.setOnAction(event -> { });

        pane.add(message, 0, 0, 3, 1);
        pane.add(display, 0, 2);
        pane.add(insert, 1, 2);
        pane.add(buy, 2, 2);

        BorderPane show = new BorderPane(pane);
        display(show);
    }

    /**
     *
     */
    private void adminMenu(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label message = new Label("What would you like to do?");

        Button create = new Button("Create Account");
        create.setOnAction(event -> { });

        Button add = new Button("Add Product");
        add.setOnAction(event -> addProduct());

        Button remove = new Button("Remove Money");
        remove.setOnAction(event -> {});

        pane.add(message, 0,0,3,1);
        pane.add(create, 0,1);
        pane.add(add, 1,1);
        pane.add(remove, 2, 1);

        BorderPane show = new BorderPane(pane);
        display(show);
    }

    /**
     *
     */
    private void addProduct(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label text1 = new Label("Description");
        Label text2 = new Label("Price");
        Label text3 = new Label("Quantity");

        TextField field1 = new TextField();
        TextField field2 = new TextField();
        TextField field3 = new TextField();

        Button submit = new Button("Submit");
        submit.setOnAction(event -> {
            try{
                String desc = field1.getText();
                double price = Double.parseDouble(field2.getText());
                int quantity = Integer.parseInt(field3.getText());
            } catch (Exception e){
                error("Invalid input detected!");
                addProduct();
            }
        });

        Button cancel = new Button("Cancel");
        cancel.setAlignment(Pos.BOTTOM_RIGHT);
        cancel.setOnAction(event -> adminMenu());

        pane.add(text1, 0,0);
        pane.add(text2, 0,1);
        pane.add(text3, 0, 2);
        pane.add(field1, 1, 0);
        pane.add(field2, 1, 1);
        pane.add(field3, 1,2);
        pane.add(submit, 0, 3);
        pane.add(cancel, 1, 3);

        BorderPane show = new BorderPane(pane);
        display(show);
    }
    
    private void chooseCoin(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);
        
        Button five = new Button();
        Button ten = new Button();
    }

    /**
     *
     * @param s
     */
    private void error(String s){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label error = new Label(s);

        pane.add(error, 0, 0);

        BorderPane show = new BorderPane(pane);
        display(show);
    }
}
