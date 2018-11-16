import java.io.IOException;

/**
 * This program simulates a vending machine.
 */
public class VendingMachineSimulation {
    public static void main(String[] args) throws IOException {
        VendingMachine machine = new VendingMachine();
        VendingMachineMenu menu = new VendingMachineMenu();
        menu.run(machine);
    }
}