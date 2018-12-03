import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program simulates a vending machine.
 */
public class VendingMachineSimulation {
    public static void main(String[] args) throws IOException {
        run();
    }

    /**
     * This method creates the vending machine and vending machine menu object
     * and runs interfaceChoice() to either run the menus in CLI or GUI form.
     * If the images folder is missing any images the GUI will not run.
     * @throws IOException
     */
    public static void run() throws IOException{
        VendingMachine machine = new VendingMachine();
        VendingMachineMenu menu = new VendingMachineMenu();

        int choice = interfaceChoice();
        if (choice == 1){
            menu.run(machine);
        } else if (choice == 2){
            if (FileIO.checkImages()) GUI.run(machine);
            else{
                System.out.println("Missing images!");
                run();
            }
        } else {
            System.out.println("Invalid input!");
            run();
        }
    }

    /**
     * This method offers the user a choice between running the program in the
     * command line or a GUI and returns the chosen option as an int.
     * @return choice 1 = CLI 2 = GUI else = invalid
     * @throws IOException
     */
    public static int interfaceChoice()throws IOException{
        System.out.println("Which display would you like?\n1) CLI \t2) GUI");
        Scanner in = new Scanner(System.in);
        int choice = 0;
        try{
            choice = in.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Invalid Input!");
            run();
        }

        return choice;
    }
}
