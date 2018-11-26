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
	public void fromArrayListToFiles(ArrayList<LineItem> stock, CoinSet money, ArrayList<String> operators) throws IOException
	{
		stockToFile(stock);
		moneyToFile(money);
		operatorsToFile(operators);
	}
	private void stockToFile(ArrayList<LineItem> stock) throws IOException
	{
		File stockFile = new File("Stock.txt");
		FileWriter writer = new FileWriter(stockFile, true);
		for(int i = 0; i < stock.size(); i++)
			writer.write(stock.get(i).toString());
		writer.flush();
		writer.close();
	}
	private void moneyToFile(CoinSet money) throws IOException
	{
		moneySet = new ArrayList<CoinLine>();
		moneySet = money.getSetOfCoins();
		File moneyFile = new File("Money.txt");
		FileWriter writer = new FileWriter(moneyFile);
		for(int i = 0; i < moneySet.size(); i++)
			writer.write(moneySet.get(i).toString());
		writer.flush();
		writer.close();
	}
	private void operatorsToFile(ArrayList<String> operators) throws IOException
	{
		File operatorsFile = new File("Operators.txt");
		FileWriter writer = new FileWriter(operatorsFile);
		for(int i = 0; i < operators.size(); i++)
			writer.write(operators.get(i));
		writer.flush();
		writer.close();
	}
}
