import javax.swing.*;
import java.util.ArrayList;

/**
This class contains all of the checks for the accounts to see if they are duplicated etc.
 * @author Stephen Cliffe 17237157
 */
class Validation {

    static Operator loginCheck(VendingMachine m, String name, String pass) {
        if (name.equals("User") && pass.equals("0000")) return null;

        ArrayList<Operator> ops = m.getOperators();
        for (Operator o : ops) {
            if (name.equals(o.getType()) && pass.equals(o.getCode())) return o;
        }
        return null;
    }

    /**
     * Checks for existing operators within the same machine
     * with the same type/name
     * 
     * @param m Vending Machine
     * @param operator Operator to compare
     * @return comparison result
     */
    private static boolean copyCheck(VendingMachine m, Operator operator) {
        ArrayList<Operator> ops = m.getOperators();
        for (Operator op : ops) {
            if (op.getType().equals(operator.getType())) return false;
        }
        return true;
    }

    /**
     * Takes in the credentials and validates them. 
     * Returns an operator object if they are correct, returns null if they are incorrect
     * 
     * @param m Vending Machine
     * @param name Operator Name
     * @param pass Operator Password/Code
     * @param verify Copy of Operator password
     * @param perm Permissions as string
     * @return Operator
     */
    static Operator accountCheck(VendingMachine m, String name, String pass, String verify, String perm) {
        Operator op = new Operator(name, pass, perm);

        if (!pass.equals(verify)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", 0);
        } else if (name.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Blank spaces invalid!", "Error", 0);
        } else if (copyCheck(m, op)) {
            return op;
        } else {
            JOptionPane.showMessageDialog(null, "Username taken!", "Error", 0);
        }
        return null;
    }
}
