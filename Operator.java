public class Operator {
    private String type,t;
    private String code,c;
    private static ArrayList<Operator> list = new ArrayList<Operator>();


	Operator(){
		this.type = "Customer";
		this.code = "0000";
		list.add(this);
	}

	Operator(String type, String code){
		this.type = type;
		this.code = code;
		list.add(this);
		/* if(type=="Admin")
		{
			
		} */
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
    public ArrayList<Operator> getList()
    {
        return this.list;
    }
	
    public boolean getAccount(String t, String c)
    {
	for(int i = 0; i < list.size(); i++)
	{
		if(list.get(i).toString().contains(t+c))
		{
			return true;
		}
	}
	return false;
    }
	
    public void addAccount(String t, String c)//NEED TO FIX THIS METHOD
    {
	this.t = t;
	this.c = t;
	list.add(this);
    }
	
    public String toString()
    {
	return getType() + getCode();
    }
}
