import java.util.*;
import java.io.*;
public class FileIO
{
	public static void createFiles() throws IOException
	{
		File stockFile = new File("Stock.txt");
		if(!(stockFile.exists()))
			stockFile.createNewFile();
		
		File moneyFile = new File("Money.txt");
		if(!(moneyFile.exists()))
			moneyFile.createNewFile();
		
		File adminFile = new File("Operators.txt");
		if(!(adminFile.exists()))
			adminFile.createNewFile();
	}
}
