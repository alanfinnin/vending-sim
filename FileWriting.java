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
	*/
	public void fromArrayListToFiles(ArrayList<LineItem> stock, CoinSet money, ArrayList<Operator> operators) throws IOException
	{
		stockToFile(stock);
		moneyToFile(money);
		operatorsToFile(operators);
	}
	/**
	*	Saves products from an lineItem
	*	arraylist to file using a FileWriter
	*/
	private void stockToFile(ArrayList<LineItem> stock) throws IOException
	{
		File stockFile = new File("Stock.txt");
		FileWriter writer = new FileWriter(stockFile);
		for(int i = 0; i < stock.size(); i++)
			writer.write(stock.get(i).toString() + "\r\n");
		writer.flush();
		writer.close();
	}
	/**
	*	Saves money from a coinSet
	*	to file using a FileWriter
	*/
	private void moneyToFile(CoinSet money) throws IOException
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
	/**
	*	Saves the operators from an arraylist
	*	of String type to file using a 
	*	FileWriter
	*/
	private void operatorsToFile(ArrayList<Operator> operators) throws IOException
	{
		File operatorsFile = new File("Operators.txt");
		FileWriter writer = new FileWriter(operatorsFile);
		for(int i = 0; i < operators.size(); i++)
			writer.write(operators.get(i).toString() + "\r\n");
		writer.flush();
		writer.close();
	}
}
