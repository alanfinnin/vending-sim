import java.util.*;
import java.io.*;
public class FileReading extends FileIO
{
	private double price;
	private String description;
	private int quantity;
	
	private String coinDescription;
	private double value;
	private int coinQuantity;
	private ArrayList<CoinLine> coinSetArrayList;
		
	private String type;
	private String code;
	
	
	private String line;
	private String[] lines;
	
	/**
	*	Takes products from the files
	*	then puts them in an arraylist for 
	*	return
	*/
	public ArrayList readFromStockFile() throws IOException
	{
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
		{}
		return products;
	}
	/**
	*	Pulls money from the file and should put 
	*	it in a coinSet to be returned, currently 
	*	broken, will fix
	*/
	public CoinSet readFromMoneyFile() throws IOException
	{
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
				System.out.println("FROM FILE\t" + value + " " + coinDescription + " " + coinQuantity);
				coinSetArrayList.add(new CoinLine(value, coinDescription, coinQuantity));
			}
		}
		catch(Exception e)
		{}
		//set.addSetOfCoins(coinSetArrayList);
		for(int i = 0; i < coinSetArrayList.size(); i++)
		{
			coinSetArrayList.get(i).setQuantity(coinQuantity);
			set.addCoin(coinSetArrayList.get(i).getCoin());
			System.out.println("\nfor loop for adding to arraylist\n\tSET:\t\n" + set.toString());
		}
		return set;//coinSet
	}
	/**
	*	Pulls the info from the operators file,
	*	puts them in an arraylist then returns it
	*/
	public ArrayList readFromOperatorsFile() throws IOException
	{
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
				operators.add(new Operator(type , code));
			}
		}
		catch(Exception e)
		{}
		return operators;
	}
}
