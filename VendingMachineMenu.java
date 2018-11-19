import java.io.IOException;
import java.util.Scanner;

/**
 * A menu from the vending machine.
 */
public class VendingMachineMenu {
    private Scanner in;
    private static Coin[] coins = {new Coin(0.05, "5 cent"),
            new Coin(0.1, "10 cent"),
            new Coin(0.5, "50 cent"),
            new Coin(1, "euro")};

    /**
     * Constructs a VendingMachineMenu object
     */
    public VendingMachineMenu() {
        in = new Scanner(System.in);
    }

    /**
     * Runs the vending machine system.
     *
     * @param machine the vending machine
     */
    public void run(VendingMachine machine) throws IOException {
        boolean more = true;
        while (more) {
            System.out.println("S)how products I)nsert coin B)uy A)dd product R)emove coins Q)uit ");
            String command = in.nextLine().toUpperCase();
            if (command.equals("S")) {
                System.out.println(machine.showProducts());
            } else if (command.equals("I")) { //allows one coin be inserted at a time
                machine.addCoin((Coin) getChoice(coins));
            } else if (command.equals("R")) {
                System.out.println("Removed: " + machine.removeMoney());
            } else if (command.equals("B")) {
                try {
                    Product p = (Product) getChoice(machine.getProductTypes());
                    machine.buyProduct(p);
                    System.out.println("Purchased: " + p);
                } catch (VendingException ex) {
                    System.out.println(ex.getMessage());
                } catch (StringIndexOutOfBoundsException ex){
					System.out.println("No products available");
				}
            } else if (command.equals("A")) {
                System.out.println("Description:");
                String description = in.nextLine();
                System.out.println("Price:");
                double price = in.nextDouble();
                System.out.println("Quantity:");
                int quantity = in.nextInt();
                in.nextLine(); // read the new-line character
                machine.addProduct(new Product(description, price), quantity);
            } else if (command.equals("Q")) {
                more = false;
            }
        }
    }

    private Object getChoice(Object[] choices) throws StringIndexOutOfBoundsException {
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }


}

