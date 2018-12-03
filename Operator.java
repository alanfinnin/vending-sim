import java.util.ArrayList;
public class Operator {
	private String type;
    private String code;
	private String permissions;
	
	Operator()
	{
		type = "User";
		code = "0000";
		permissions = "000";
	}
	
	Operator(String type, String code, String permissions){
		this.type = type;
		this.code = code;
		this.permissions = permissions;
    }
    
    public void setType(String t)
    {
        t = type;
    }

    public String getType()
    {
        return type;
    }
    
    public void setCode(String c)
    {
        c = code;
    }
	
	public String getCode()
	{
		return code;
	}
	
    public ArrayList<Operator> getAccounts(ArrayList<Operator> accounts)
    {
        return accounts;
    }
	
	public boolean canCreateAccount()
	{
		if(permissions.substring(0,1).contains("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean canAddProduct()
	{
		if(permissions.substring(1,2).contains("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean canRemove()
	{
		if(permissions.substring(2,3).contains("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{
		return type + "," + code;
	}
}
