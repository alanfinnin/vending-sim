import java.util.*;
import java.io.*;
public class FileInput
{
	private static ArrayList<String> Stock = new ArrayList<String>();
	private static ArrayList<String> Money = new ArrayList<String>();
	private static ArrayList<String> Admin = new ArrayList<String>();
	public static void main(String[] args) throws IOException
	{
		createFiles();
		pullFromFiles();
	}
	public static void clearArrayLists()
	{
		Stock.clear();
		Money.clear();
		Admin.clear();
	}
	public static void createFiles() throws IOException
	{
		File stockFile = new File("Stock.txt");
		if(!(stockFile.exists()))
			stockFile.createNewFile();
		
		File moneyFile = new File("Money.txt");
		if(!(moneyFile.exists()))
			moneyFile.createNewFile();
		
		File adminFile = new File("Operator.txt");
		if(!(adminFile.exists()))
			adminFile.createNewFile();
	}
	public static void pullFromFiles() throws IOException
	{
		File file1 = new File("Stock.txt");
        File file2 = new File("Money.txt");
        File file3 = new File("Operator.txt");
		
		double price;
		String description;
		int quantity;
		
		String coinDescription;
		double value;
		int coinQuantity;
		
		String username;
		String password;
		
		String line;
		String[] lines;
		
		Scanner fileIn = new Scanner(file1);
		while(fileIn.hasNextLine()) //Stock
		{
			lines = fileIn.nextLine().split(",");
			price = Double.parseDouble(lines[0]);
			description = lines[1];
			quantity = Integer.parseInt(lines[2]);
			
			Stock.add(new Product(description, price));
		}
		fileIn = new Scanner(file2);
		while(fileIn.hasNextLine()) //Money
		{
			lines = fileIn.nextLine().split(",");
			coinDescription = lines[0];
			value = Double.parseDouble(lines[1]);
			coinQuantity = Integer.parseInt(lines[2]);
			
			Coin.add(new Coin(value, coinDescription))
			
		}
		fileIn = new Scanner(file3);
		while(fileIn.hasNextLine()) //Admin
		{
			lines = fileIn.nextLine().split(",");
			username = lines[0];
			password = lines[1];
			
			Admin.add(username, password);
		}
		fileIn.close();
	}
}