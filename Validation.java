import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Stephen Cliffe
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

    static boolean copyCheck(VendingMachine m, Operator operator) {
        ArrayList<Operator> ops = m.getOperators();
        for (Operator op : ops) {
            if (op.getType().equals(operator.getType())) return false;
        }
        return true;
    }

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
