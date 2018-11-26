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
		
	private String username;
	private String password;
	private String userTypeCode;
	
	private String line;
	private String[] lines;
	
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
				description = lines[0];
				price = Double.parseDouble(lines[1]);
				quantity = Integer.parseInt(lines[2]);
				products.add(new LineItem(new Product(description, price), quantity));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: parsing error in Stock file");
		}
		return products;
	}
	public void readFromMoneyFile() throws IOException
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
				coinDescription = lines[0];
				value = Double.parseDouble(lines[1]);
				coinQuantity = Integer.parseInt(lines[2]);
				
				coinSetArrayList.add(new CoinLine(value, coinDescription, coinQuantity));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: parsing error in Money file");
		}
		set.addSetOfCoins(coinSetArrayList);
		return set;
	}
	public ArrayList readFromOperatorsFile() throws IOException
	{
		ArrayList<String> operators = new ArrayList<String>();
		File operatorsFile = new File("Operators.txt");
		Scanner in = new Scanner(operatorsFile);
		try
		{
			while(in.hasNextLine())
			{
				lines = in.nextLine().split(",");
				username = lines[0];
				password = lines[1];
				operators.add(username + "," + password);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: parsing error in Operators file");
		}
		return operators;
	}
}
