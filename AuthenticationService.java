public class AuthenticationService
{
	private VendingMachine machine = new VendingMachine();
	
	public boolean checkAccount(String t, String c)
	{
		for(int i = 0; i < machine.numOperators(); i++)
		{
			if(machine.getOperator(i).toString().contains(t+","+c))
			{
				return true;
			}
		}
		return false;
	}
}
