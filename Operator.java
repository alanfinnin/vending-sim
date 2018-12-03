import java.util.ArrayList;
class Operator {
    private String type;
    private String code;
    private String permissions;

	/**
	*	The constructure for the 
	*	operator which sets the defaults
	*/
    Operator() {
        type = "User";
        code = "0000";
        permissions = "000";
    }
	/**
	*	The constructure for the 
	*	operator, this sets the 
	*	variables from the argument
	*	@param type The name of the operator
	*	@param code The code/password for the operator
	*	@param permissions The permissions associated with the operator
	*/
    Operator(String type, String code, String permissions) {
        this.type = type;
        this.code = code;
        this.permissions = permissions;
    }
	/**
	*	A setter for the type 
	*	@param t The argument to set type to
	*/
    public void setType(String t) {
        type = t;
    }
	/**
	*	returns the type
	*	@return type
	*/
    public String getType() {
        return type;
    }
	/**
	*	Sets the code/password for the operator
	*	@param c The code to be changed to
	*/
    public void setCode(String c) {
        code = c;
    }
	/**
	*	returns the code associated with the operator
	*	@return code The code
	*/
    public String getCode() {
        return code;
    }
	/**
	*	returns the permissions of the operator
	*	@return permissions
	*/
    public String getPermissions(){
        return permissions;
    }
	/**
	*	setter for the permissions 
	*	@param s the string the permissions is
	*	to be set to
	*/
    public void setPermissions(String s){
        permissions = s;
    }
	/**
	*	returns a boolean whether 
	*	the operator is able to remove money
	*	@return permissions.substring(2, 3).contains("1") the boolean whether the substrinf contains "1"
	*/
    public boolean canRemove() {
        return permissions.substring(2, 3).contains("1");
    }
	/**
	*	returns a boolean whether an operator can add products
	*	@return permissions.substring(1, 2).contains("1") whether it contains "1"
	*/
    public boolean canAddProduct() {
        return permissions.substring(1, 2).contains("1");
    }
	/**
	*	a boolean whether the operator can create accounts
	*	@return permissions.substring(1, 2).contains("1") whether it contains "1"
	*/
    public boolean canCreateAccount() {
        return permissions.substring(0, 1).contains("1");
    }
	/**
	*	the toString to get the info on the info 
	*	@return type + "," + code + "," + permissions the string of info
	*/
    @Override
    public String toString() {
        return type + "," + code + "," + permissions;
    }
}
