import java.util.*;
import java.io.*;
public class FileWriting
{
	private String line;
	public void fromArrayListToFiles(ArrayList<LineItem> stock, ArrayList<CoinSet> money, ArrayList<String> operators) throws IOException
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
			writer.write(stock.get(i).getProduct().getDescription() + "," + stock.get(i).getProduct().getPrice() + "," + stock.get(i).getQuantity());
		writer.flush();
		writer.close();
	}
	private void moneyToFile(ArrayList<CoinSet> money) throws IOException
	{
		File moneyFile = new File("Money.txt");
		FileWriter writer = new FileWriter(moneyFile);
		for(int i = 0; i < money.size(); i++)
			//writer.write();
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