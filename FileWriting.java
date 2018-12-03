import java.util.*;
import java.io.*;
public class FileWriting extends FileIO
{
	private String line;
	private ArrayList<CoinLine> moneySet;
	/**
	*	The main and only public method avaliable
	*	this uses the other private methods to
	*	carry out the saving
	*	@param stock arraylist of stock to be sent 
	*	to the method stockToFile
	*
	*	@param money coinset to be sent to the
	*	method moneyToFile
	*
	*	@param operators arraylist of operators 
	*	to be sent to operatorsToFile
	*	@return void
	*/
	public void fromArrayListToFiles(ArrayList<LineItem> stock, CoinSet money, ArrayList<Operator> operators) 
	{
		stockToFile(stock);
		moneyToFile(money);
		operatorsToFile(operators);
	}
	/**
	*	Saves products from an lineItem
	*	arraylist to file using a FileWriter
	*	@param stock the stock arraylist, taken in to be saved
	*	@return void
	*/
	public void stockToFile(ArrayList<LineItem> stock)
	{
		try
		{
			File stockFile = new File("Stock.txt");
			FileWriter writer = new FileWriter(stockFile);
			for(int i = 0; i < stock.size(); i++)
				writer.write(stock.get(i).toString() + "\r\n");
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Error in stockToFile\n" + e);
		}
	}
	/**
	*	Saves money from a coinSet
	*	to file using a FileWriter
	*	@param money the holder for the coins to be saved 
	*	@return void
	*/
	public void moneyToFile(CoinSet money)
	{
		try
		{
			moneySet = new ArrayList<CoinLine>();
			moneySet = money.getSetOfCoins();
			File moneyFile = new File("Money.txt");
			FileWriter writer = new FileWriter(moneyFile);
			for(int i = 0; i < moneySet.size(); i++)
				writer.write(moneySet.get(i).toString() + "\r\n");
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Error in moneyToFile\n" + e);
		}
	}
	/**
	*	Saves the operators from an arraylist
	*	of String type to file using a 
	*	FileWriter
	*	@param operators the arraylist of operators to be saved
	*	@return void 
	*/
	public void operatorsToFile(ArrayList<Operator> operators) 
	{
		try
		{
			File operatorsFile = new File("Operators.txt");
			FileWriter writer = new FileWriter(operatorsFile);
			for(int i = 0; i < operators.size(); i++)
				writer.write(operators.get(i).toString() + "\r\n");
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
				System.out.println("Error in operatorsToFile\n" + e);
		}
	}
}
