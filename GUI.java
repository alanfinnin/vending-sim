import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * This class file contains all the code for the JavaFX menus.
 * @author Stephen Cliffe
 */
@SuppressWarnings("unchecked")
public class GUI extends Application {

    private static Operator currentUser;
    private static VendingMachine machine;
    private static Window window;

    private boolean isAdding = false;
    private boolean isBuying = false;

    /**
     * The run() method declares the VendingMachine object and launches the JavaFX menu. It has a finally claus that
     * saves all the current arraylists to the files after the program is finished running.
     * @param vendingMachine This is the current VendingMachine object
     */
    static void run(VendingMachine vendingMachine){
        machine = vendingMachine;
        try {
            launch();
        } finally {
            machine.saveAllToFiles();
        }
    }

    /**
     * This declares the current user to be the default user with noe permissions. Also makes a new Window object.
     * @param primaryStage an empty stage.
     */
    @Override
    public void start(Stage primaryStage){
        currentUser = new Operator("User", "0000", "000");
        window = new Window(primaryStage);
        showHome();
    }

    /**
     * adminLogin() creates a menu with a text field, a password field and three buttons. One to clear the fields, one
     * to return to previous screen and another to submit the inputted data for validation. If the test passes the
     * method sets the currentUser variable to be the one logged in, else it will output a JOptionPane saying incorrect
     * password/username.
     */
    private void adminLogin() {
        GridPane pane = window.getDefaultGridPane();
        BorderPane show = new BorderPane();

        Label email = new Label("Username");
        Label pass  = new Label("Password");

        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();

        Button submit = new Button("Submit");
        submit.setOnAction(event -> {
            Operator op = Validation.loginCheck(machine, emailField.getText(), passField.getText());
            if (!(op == null)){
                currentUser = op;
                showHome();
            } else {
                window.popup("Incorrect Username or Password!");
            }
        });

        Button clear  = new Button("Clear");
        clear.setOnAction(event -> adminLogin());

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> showHome());

        pane.add(email, 0, 0);
        pane.add(emailField, 1, 0, 2, 1);
        pane.add(pass, 0, 1);
        pane.add(passField, 1, 1, 2, 1);
        pane.add(submit, 2, 3);
        pane.add(clear, 1, 3);
        pane.add(back, 0, 3);

        GridPane.setHalignment(submit, HPos.RIGHT);
        GridPane.setHalignment(back, HPos.RIGHT);

        show.setCenter(pane);
        window.setPane(show);
    }

    /**
     * createAccount() makes a menu with a text field and two password fields. It also includes a VBox with three
     * checkboxes to set the desired permissions for the created account. It has three buttons, one to go back to the
     * previous menu, one to clear ll inputted data and another to submit the data for validation. I the test succeeds
     * the new Operator will be added to the VendingMachine and a JOptionPane will display the inputted username,
     * letting you know the creation was successful.
     */
    private void createAccount() {
        GridPane pane = window.getDefaultGridPane();
        BorderPane show = new BorderPane();
        VBox perm = new VBox(5);
        perm.setPadding(new Insets(3, 3, 3, 3));

        Label email = new Label("Username");
        Label pass  = new Label("Password");
        Label check = new Label("Confirm Password");

        TextField emailField = new TextField();
        PasswordField passField = new PasswordField();
        PasswordField verify = new PasswordField();

        StringBuilder permission = new StringBuilder("000");

        Button clear  = new Button("Clear");
        clear.setOnAction(event -> createAccount());

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> showHome());

        Label heading = new Label("Permissions: ");
        CheckBox create = new CheckBox("Create Account");
        CheckBox add = new CheckBox("Add Product");
        CheckBox remove = new CheckBox("Remove Money");

        perm.getChildren().addAll(heading, create, add, remove);

        Button submit = new Button("Submit");
        submit.setOnAction(event -> {
            if (create.isSelected()){
                permission.setCharAt(0, '1');
            }
            else if (add.isSelected()){
                permission.setCharAt(1, '1');
            }
            else if(remove.isSelected()){
                permission.setCharAt(2, '1');
            }
            Operator op = Validation.accountCheck(machine, emailField.getText(), passField.getText(), verify.getText(), permission.toString());

            if (!(op == null)){
                machine.addOperator(op);
                String message = "Account Created!\nUsername: " + op.getType();
                window.popup(message, "Account Created", 1);
                showHome();
            }
        });

        pane.add(email, 0, 0);
        pane.add(emailField, 1, 0, 2, 1);
        pane.add(pass, 0, 1);
        pane.add(passField, 1, 1, 2, 1);
        pane.add(check, 0, 2);
        pane.add(verify, 1, 2, 2, 1);
        pane.add(submit, 2, 3);
        pane.add(clear, 1, 3);
        pane.add(back, 0, 3);

        GridPane.setHalignment(submit, HPos.RIGHT);
        GridPane.setHalignment(back, HPos.RIGHT);

        show.setRight(perm);
        show.setLeft(pane);
        window.setPane(show);
    }

    /**
     * This method displays a menu that has access to all other menus, a home screen. It contains four buttons available
     * to all users, login, to log into an account, show products, to display all products in the machine, insert coins,
     * to put coins into the machine and finally buy product, to buy a product. The menu contains a toolbar at the top
     * of the BorderPane that contains all the admin tools. This will not display until logged in as an operator and each
     * tool will only be available to an operator who has the correct permissions.
     */
    private void showHome(){
        GridPane pane = window.getDefaultGridPane();

        ToolBar bar = new ToolBar();
        Image gear = new Image(getClass().getResourceAsStream("images/gear.png"));
        Button admin = new Button("Login", new ImageView(gear));
        admin.setOnAction(event -> adminLogin());
        bar.getItems().add(admin);

        if (!currentUser.getPermissions().equals("000")) bar = getAdminToolbar();
        else {
            String message = "\u20ac" + String.format("%.2f", machine.getCredit());
            Label change = new Label(message);
            change.setStyle("-fx-font-size: 1.5em; ");

            Button refund = new Button("Refund");
            refund.setOnAction(event -> {
                window.popup(machine.refundCoins(), "Refund", 1);
                showHome();
            });

            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            bar.getItems().addAll(refund, region, change);
        }

        Label message = new Label("What would you like to do?\n");
        message.setStyle("-fx-font-size: 2em; ");

        Image bottle = new Image((getClass().getResourceAsStream("images/showIcon.png")));
        Button display = new Button("", new ImageView(bottle));
        display.setOnAction(event -> showProducts());
        Label showProducts = new Label("Show Products");

        Image coin = new Image((getClass().getResourceAsStream("images/coinIcon.png")));
        Button insert = new Button("", new ImageView(coin));
        insert.setOnAction(event -> chooseCoin());
        Label addCoin = new Label("Insert Coin");

        Image cart = new Image((getClass().getResourceAsStream("images/buyIcon.png")));
        Button buy = new Button("", new ImageView(cart));
        buy.setOnAction(event -> {isBuying = true; showProducts();});
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

        window.setPane(show);
    }

    /**
     * The chooseCoin() method displays a menu containing six buttons, each displaying a coin image that represents the
     * desired input. It also has a count in the bottom right that displays the current value inside the machine. There
     * is a back button in the bottom right that returns you to the home screen.
     */
    private void chooseCoin(){
        GridPane pane = window.getDefaultGridPane();

        Image cent5 = new Image(getClass().getResourceAsStream("images/5cent.png"));
        Image cent10 = new Image(getClass().getResourceAsStream("images/cent10.png"));
        Image cent20 = new Image(getClass().getResourceAsStream("images/cent20.png"));
        Image cent50 = new Image(getClass().getResourceAsStream("images/cent50.png"));
        Image euro1 = new Image(getClass().getResourceAsStream("images/euro.png"));
        Image euro2 = new Image(getClass().getResourceAsStream("images/euro2.png"));

        Button five = new Button("", new ImageView(cent5));
        five.setOnAction(event -> {machine.addCoin(new Coin(0.05, "5 Cent")); chooseCoin();});
        Button ten = new Button("", new ImageView(cent10));
        ten.setOnAction(event -> {machine.addCoin(new Coin(0.1, "10 Cent")); chooseCoin();});
        Button twenty = new Button("", new ImageView(cent20));
        twenty.setOnAction(event -> {machine.addCoin(new Coin(0.2, "20 Cent")); chooseCoin();});
        Button fifty = new Button("", new ImageView(cent50));
        fifty.setOnAction(event -> {machine.addCoin(new Coin(0.5, "50 Cent")); chooseCoin();});
        Button euro = new Button("", new ImageView(euro1));
        euro.setOnAction(event -> {machine.addCoin(new Coin(1, "1 Euro")); chooseCoin();});
        Button twoEuro = new Button("", new ImageView(euro2));
        twoEuro.setOnAction(event -> {machine.addCoin(new Coin(2, "2 Euro")); chooseCoin();});

        HBox row = new HBox();
        row.setPadding(new Insets(5, 5, 5, 5));

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> showHome());

        String money = "\u20ac" + String.format("%.2f", machine.getCredit());
        Label credit = new Label(money);
        credit.setStyle("-fx-font-size: 2em; ");

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        row.getChildren().addAll(back, region, credit);

        pane.add(five, 0,0);
        pane.add(ten, 1, 0);
        pane.add(twenty, 2, 0);
        pane.add(fifty, 0,1);
        pane.add(euro, 1, 1);
        pane.add(twoEuro, 2, 1);

        BorderPane show = new BorderPane();
        show.setTop(pane);
        show.setBottom(row);
        window.setPane(show);
    }

    private void showProducts(){
        GridPane pane;
        BorderPane show = new BorderPane();
        HBox row = new HBox(40);
        row.setPadding(new Insets(10,10,10,10));
        row.setAlignment(Pos.CENTER);

        ArrayList<LineItem> arr = machine.getProductsInStock();

        TableView<LineItem> table = new TableView<>();

        TableColumn<LineItem, String> nameCol = new TableColumn<>("Name");
        TableColumn<LineItem, Double> priceCol= new TableColumn<>("Price");
        TableColumn<LineItem, Integer>quantCol= new TableColumn<>("Quantity");
        nameCol.setMinWidth(175);
        priceCol.setMinWidth(75);
        quantCol.setMinWidth(75);

        nameCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getProduct().getDescription()));
        priceCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getProduct().getPrice()));
        quantCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        ObservableList<LineItem> list = FXCollections.observableList(arr);
        table.setItems(list);

        table.getColumns().add(nameCol);
        table.getColumns().add(priceCol);
        table.getColumns().add(quantCol);

        if (isAdding) {
            pane = getAddGrid();
            show.setCenter(pane);
        } else {
            Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
            Button back = new Button("", new ImageView(backArrow));
            back.setOnAction(event -> {isAdding = false; isBuying = false; showHome();});

            String money = "\u20ac" + String.format("%.2f", machine.getCredit());
            Label credit = new Label(money);
            credit.setStyle("-fx-font-size: 2em; ");

            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);

            row.getChildren().addAll(back, region1, credit);
            show.setBottom(row);
        }

        if (isBuying){
            Button action = new Button("Buy");
            action.setStyle("-fx-font-size: 1.3em; ");
            action.setOnAction(event -> {
                try {
                    LineItem line = table.getSelectionModel().getSelectedItem();
                    machine.buyProduct(line.getProduct());
                    showProducts();
                } catch (NullPointerException e){
                    window.popup("No product selected!");
                } catch (VendingException e){
                    window.popup(e.toString());
                }
            });
            action.setAlignment(Pos.CENTER_RIGHT);

            Region region2 = new Region();
            HBox.setHgrow(region2, Priority.ALWAYS);
            row.getChildren().addAll(region2, action);
        }

        show.setTop(table);
        window.setPane(show);
    }

    private GridPane getAddGrid(){
        GridPane pane = window.getDefaultGridPane();
        Label text1 = new Label("Description");
        Label text2 = new Label("Price");
        Label text3 = new Label("Quantity");

        TextField field1 = new TextField();
        field1.setPromptText("Fanta");
        TextField field2 = new TextField();
        field2.setPromptText("1.95");
        TextField field3 = new TextField();
        field3.setPromptText("21");

        Button submit = new Button("Submit");
        submit.setOnAction(event -> {
            try{
                String desc = field1.getText();
                double price = Double.parseDouble(field2.getText());
                int quantity = Integer.parseInt(field3.getText());

                if (price < 0) window.popup("Price less than \u20ac0!");
                else machine.addProduct(new Product(desc, price), quantity);

                showProducts();
            } catch (Exception e){
                window.popup("Invalid input!");
            }
        });

        pane.add(text1, 0,0);
        pane.add(text2, 0,1);
        pane.add(text3, 0, 2);
        pane.add(field1, 1, 0);
        pane.add(field2, 1, 1);
        pane.add(field3, 1,2);
        pane.add(submit, 1, 3);

        GridPane.setHalignment(submit, HPos.RIGHT);

        Image backArrow = new Image(getClass().getResourceAsStream("images/backArrow.png"));
        Button back = new Button("", new ImageView(backArrow));
        back.setOnAction(event -> {isAdding = false; isBuying = false; showHome();});

        pane.add(back, 0, 3);
        GridPane.setHalignment(back, HPos.LEFT);

        return pane;
    }

    private ToolBar getAdminToolbar(){
        ToolBar bar = new ToolBar();

        Image gear = new Image(getClass().getResourceAsStream("images/gear.png"));
        Button admin = new Button("Logout", new ImageView(gear));
        admin.setOnAction(event -> {
            currentUser = machine.getOperators().get(0);
            showHome();
        });

        Button create = new Button("Create Account");
        if (currentUser.canCreateAccount()) {
            create.setOnAction(event -> createAccount());
        } else {
            create.setStyle("-fx-background-color: #797979");
        }

        Button add = new Button("Add Product");
        if (currentUser.canAddProduct()){
            add.setOnAction(event -> {isAdding = true; showProducts();});
        } else {
            add.setStyle("-fx-background-color: #797979");
        }

        Button remove = new Button("Remove Money");
        if (currentUser.canRemove()) {
            remove.setOnAction(event -> {
                String s = "You have removed: " + machine.removeMoney();
                window.popup(s, "Money removed!", 1);
            });
        } else {
            remove.setStyle("-fx-background-color: #797979");
        }

        bar.getItems().add(admin);
        bar.getItems().add(create);
        bar.getItems().add(add);
        bar.getItems().add(remove);

        return bar;
    }
}
