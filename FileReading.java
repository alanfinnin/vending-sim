import java.util.*;
import java.io.*;
public class FileReading
{
	private double price;
	private String description;
	private int quantity;
	
	private String coinDescription;
	private double value;
	private int coinQuantity;
		
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
				price = Double.parseDouble(lines[0]);
				description = lines[1];
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
		CoinSet set = new CoinSet();
		File stockFile = new File("Money.txt");
		Scanner in = new Scanner(stockFile);
		
		try
		{
			while(in.hasNextLine())
			{
				lines = in.nextLine().split(",");
				coinDescription = lines[0];
				value = Double.parseDouble(lines[1]);
				coinQuantity = Integer.parseInt(lines[2]);
				
				//set.addCoin(new Coin(value, coinDescription), quantity ??);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: parsing error in Money file");
		}
		//return set;
	}
	public ArrayList readFromOperatorsFile() throws IOException
	{
		ArrayList<String> operators = new ArrayList<String>();
		File stockFile = new File("Operators.txt");
		Scanner in = new Scanner(stockFile);
		
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
