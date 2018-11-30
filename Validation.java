import java.util.ArrayList;

public class Validation{
    public static Operator loginCheck(VendingMachine m, String name, String pass){
        if (name.equals("User") && pass.equals("0000")) return null;

        ArrayList<Operator> ops = m.getOperators();
        for (Operator o: ops) {
            if (name.equals(o.getType()) && pass.equals(o.getCode())) return o;
        }
        return null;
    }

    public static boolean copyCheck(VendingMachine m, Operator operator){
        ArrayList<Operator> ops = m.getOperators();
        for (Operator op: ops){
            if (op.getType().equals(operator.getType())) return false;
        }
        return true;
    }
}
