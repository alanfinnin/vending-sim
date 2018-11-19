import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage stage = new Stage();
    private boolean isAdmin = false;

    /**
     * The run() method initializes the GUI methods
     */
    public static void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        userMenu();
    }

    /**
     * This method takes a filled in GridPane and makes a window to display it. It closes the window
     * that was previously opened. It adds an icon and a title to the window, then shows it.
     * @param pane A filled in GridPane to be displayed.
     */
    private void display(BorderPane pane){
        stage.close();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/stage_vend_icon.png")));
        stage.setMinHeight(150);
        stage.setMinWidth(350);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Vending Machine Simulation");
        stage.show();
    }

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
        submit.setOnAction(event -> {isAdmin = true; userMenu();});

        Button clear  = new Button("Clear");
        clear.setOnAction(event -> adminLogin());

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> userMenu());

        pane.add(email, 0, 0);
        pane.add(emailField, 1, 0, 2, 1);
        pane.add(pass, 0, 1);
        pane.add(passField, 1, 1, 2, 1);
        pane.add(submit, 2, 2);
        pane.add(clear, 1, 2);
        pane.add(back, 0, 2);

        GridPane.setHalignment(submit, HPos.RIGHT);
        GridPane.setHalignment(back, HPos.RIGHT);

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

        ToolBar bar = new ToolBar();
        Image gear = new Image(getClass().getResourceAsStream("images/gear.png"));
        Button admin = new Button("Login", new ImageView(gear));
        admin.setOnAction(event -> adminLogin());
        bar.getItems().add(admin);

        if (isAdmin){
            Button create = new Button("Create Account");
            create.setOnAction(event -> createAccount());

            Button add = new Button("Add Product");
            add.setOnAction(event -> addProduct());

            Button remove = new Button("Remove Money");
            remove.setOnAction(event -> {});


            admin.setText("Logout");
            admin.cancelButtonProperty();
            admin.setOnAction(event -> {isAdmin = false; userMenu();});

            bar.getItems().add(create);
            bar.getItems().add(add);
            bar.getItems().add(remove);
        }

        Label message = new Label("What would you like to do?\n");
        message.setStyle("-fx-font-size: 2em; ");

        Image bottle = new Image((getClass().getResourceAsStream("images/showIcon.png")));
        Button display = new Button("", new ImageView(bottle));
        display.setOnAction(event -> { });
        Label showProducts = new Label("Show Products");

        Image coin = new Image((getClass().getResourceAsStream("images/coinIcon.png")));
        Button insert = new Button("", new ImageView(coin));
        insert.setOnAction(event -> chooseCoin());
        Label addCoin = new Label("Insert Coin");

        Image cart = new Image((getClass().getResourceAsStream("images/buyIcon.png")));
        Button buy = new Button("", new ImageView(cart));
        buy.setOnAction(event -> { });
        Label buyProduct = new Label("Buy");

        pane.add(message, 0, 0, 3, 1);
        pane.add(display, 0, 2);
        pane.add(insert, 1, 2);
        pane.add(buy, 2, 2);
        pane.add(showProducts, 0, 3);
        pane.add(addCoin, 1, 3);
        pane.add(buyProduct, 2, 3);

        GridPane.setHalignment(showProducts, HPos.CENTER);
        GridPane.setHalignment(addCoin, HPos.CENTER);
        GridPane.setHalignment(buyProduct, HPos.CENTER);

        BorderPane show = new BorderPane();
        show.setTop(bar);
        show.setCenter(pane);

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
        cancel.setOnAction(event -> userMenu());

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

        Image cent5 = new Image(getClass().getResourceAsStream("images/5cent.png"));
        Image cent10 = new Image(getClass().getResourceAsStream("images/cent10.png"));
        Image cent20 = new Image(getClass().getResourceAsStream("images/cent20.png"));
        Image cent50 = new Image(getClass().getResourceAsStream("images/cent50.png"));
        Image euro1 = new Image(getClass().getResourceAsStream("images/euro.png"));
        Image euro2 = new Image(getClass().getResourceAsStream("images/euro2.png"));

        Button five = new Button("", new ImageView(cent5));
        five.setOnAction(event -> System.out.println("5 cent"));
        Button ten = new Button("", new ImageView(cent10));
        ten.setOnAction(event -> System.out.println("10 cent"));
        Button twenty = new Button("", new ImageView(cent20));
        twenty.setOnAction(event -> System.out.println("20 cent"));
        Button fifty = new Button("", new ImageView(cent50));
        fifty.setOnAction(event -> System.out.println("50 cent"));
        Button euro = new Button("", new ImageView(euro1));
        euro.setOnAction(event -> System.out.println("1 euro"));
        Button twoEuro = new Button("", new ImageView(euro2));
        twoEuro.setOnAction(event -> System.out.println("2 euro"));

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> userMenu());

        pane.add(five, 0,0);
        pane.add(ten, 1, 0);
        pane.add(twenty, 2, 0);
        pane.add(fifty, 0,1);
        pane.add(euro, 1, 1);
        pane.add(twoEuro, 2, 1);
        pane.add(back, 0,2);

        BorderPane show = new BorderPane(pane);
        display(show);
    }

    private void createAccount(){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        Label email = new Label("Email");
        Label pass  = new Label("Password");
        Label check = new Label("Confirm Password");

        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();
        PasswordField verify = new PasswordField();

        Button submit = new Button("Submit");
        submit.setOnAction(event -> {  });

        Button clear  = new Button("Clear");
        clear.setOnAction(event -> createAccount());

        pane.add(email, 0, 0);
        pane.add(emailField, 1, 0, 2, 1);
        pane.add(pass, 0, 1);
        pane.add(passField, 1, 1, 2, 1);
        pane.add(check,0,2);
        pane.add(verify, 1, 2, 2, 1);
        pane.add(submit, 0, 3);
        pane.add(clear, 2, 3);

        GridPane.setHalignment(clear, HPos.RIGHT);

        BorderPane show = new BorderPane(pane);
        display(show);
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
