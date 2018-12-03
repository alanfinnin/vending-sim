import java.util.*;
import java.io.*;
public class FileReading extends FileIO
{	
	private String line;
	private String[] lines;	
	/**
	*	Takes products from the files
	*	then puts them in an arraylist for 
	*	@return readFromStockFile
	*/
	public ArrayList readFromStockFile() throws IOException
	{
		double price;
		String description;
		int quantity;
		ArrayList<LineItem> products = new ArrayList<LineItem>();
		File stockFile = new File("Stock.txt");
		Scanner in = new Scanner(stockFile);
		try
		{
			while(in.hasNextLine())
			{
				lines = in.nextLine().split(",");
				for(int i = 0; i < lines.length; i++)
					lines[i] = lines[i].trim();
				description = lines[0];
				price = Double.parseDouble(lines[1]);
				quantity = Integer.parseInt(lines[2]);
				products.add(new LineItem(new Product(description, price), quantity));
			}
		}
		catch(Exception e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		return products;
	}
	/**
	*	Pulls money from the file and puts 
	*	it in a coinSet to be returned
	*	@return readFromMoneyFile
	*/
	public CoinSet readFromMoneyFile() throws IOException
	{
		String coinDescription;
		double value;
		int coinQuantity;
		ArrayList<CoinLine> coinSetArrayList;
		coinSetArrayList = new ArrayList<CoinLine>();
		CoinSet set = new CoinSet();
		File moneyFile = new File("Money.txt");
		Scanner in = new Scanner(moneyFile);
		try
		{
			while(in.hasNextLine())
			{
				lines = in.nextLine().split(",");
				for(int i = 0; i < lines.length; i++)
					lines[i] = lines[i].trim();
				coinDescription = lines[0];
				value = Double.parseDouble(lines[1]);
				coinQuantity = Integer.parseInt(lines[2]);
				coinSetArrayList.add(new CoinLine(new Coin(value, coinDescription), coinQuantity)); //coinLine
			}
		}
		catch(Exception e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		set.addSetOfCoins(coinSetArrayList);
		for(int i = 0; i < coinSetArrayList.size(); i++)
			if(!(set.getSetOfCoins().contains(coinSetArrayList.get(i))))
				set.addCoin(coinSetArrayList.get(i));
		return set;//coinSet
	}
	/**
	*	Pulls the info from the operators file,
	*	puts them in an arraylist then returns it
	*	@return readFromOperatorsFile
	*/
	public ArrayList readFromOperatorsFile() throws IOException
	{		
		String type, code, permissions;
		ArrayList<Operator> operators = new ArrayList<Operator>();
		File operatorsFile = new File("Operators.txt");
		Scanner in = new Scanner(operatorsFile);
		try
		{
			while(in.hasNextLine())
			{
				lines = in.nextLine().split(",");
				for(int i = 0; i < lines.length; i++)
					lines[i] = lines[i].trim();
				type = lines[0];
				code = lines[1];
				permissions = lines[2];
				operators.add(new Operator(type , code, permissions));
			}
		}
		catch(Exception e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		return operators;
	}
}
