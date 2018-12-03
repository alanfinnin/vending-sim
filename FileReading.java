import java.util.*;
import java.io.*;
public class FileReading extends FileIO
{	
	private String line;
	private String[] lines;
	private static ArrayList<CoinLine> defaultSet = new ArrayList<CoinLine>();
	/**
	*	Takes products from the files
	*	then puts them in an arraylist for 
	*	@return readFromStockFile
	*/
	public ArrayList<LineItem> readFromStockFile()
	{
		double price;
		String description;
		int quantity;
		ArrayList<LineItem> products = new ArrayList<LineItem>();
		File stockFile = new File("Stock.txt");
		try
		{
			Scanner in = new Scanner(stockFile);
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
		catch(IOException e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		return products;
	}
	/**
	*	Pulls money from the file and puts 
	*	it in a coinSet to be returned
	*	@return readFromMoneyFile
	*/
	public CoinSet readFromMoneyFile()
	{
		defaultSet.add(new CoinLine(0.05, "5 Cent", 0));
		defaultSet.add(new CoinLine(0.10, "10 Cent", 0));
		defaultSet.add(new CoinLine(0.20, "20 Cent", 0));
		defaultSet.add(new CoinLine(0.50, "50 Cent", 0));
		defaultSet.add(new CoinLine(1.00, "1 Euro", 0));
		defaultSet.add(new CoinLine(2.00, "2 Euro", 0));
		String coinDescription;
		double value;
		int coinQuantity;
		CoinSet coinSetList;
		coinSetList = new CoinSet();
		ArrayList<CoinLine> coinSetArrayList = coinSetList.getSetOfCoins();
		CoinSet set = new CoinSet();
		File moneyFile = new File("Money.txt");
		try
		{
            Scanner in = new Scanner(moneyFile);
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
		catch(IOException e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		set.addSetOfCoins(coinSetArrayList);
		for(int i = 0; i < coinSetArrayList.size(); i++)
			if(!(set.getSetOfCoins().contains(coinSetArrayList.get(i))))
				set.addCoin(coinSetArrayList.get(i));
		for(int i = 0; i < defaultSet.size(); i++)
				if(!(set.getSetOfCoins().contains(defaultSet.get(i))))
				set.addCoin(defaultSet.get(i));
		return set;//coinSet
	}
	/**
	*	Pulls the info from the operators file,
	*	puts them in an arraylist then returns it
	*	@return readFromOperatorsFile
	*/
	public ArrayList<Operator> readFromOperatorsFile()
	{		
		String type, code, permissions;
		ArrayList<Operator> operators = new ArrayList<Operator>();
		File operatorsFile = new File("Operators.txt");
		try
		{
            Scanner in = new Scanner(operatorsFile);
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
		catch(IOException e)
		{}//Leaving the caught exception blank means the problematic line is simply skipped
		if(operators.size() == 0)
		{
			operators.add(new Operator("User", "0000", "000"));
			operators.add(new Operator("Create", "0000", "110"));
			operators.add(new Operator("Remove", "1111", "001"));
		}
		return operators;
	}
}
