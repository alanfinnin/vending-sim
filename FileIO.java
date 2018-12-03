import java.util.*;
import java.io.*;
public class FileIO
{
	/**
	*	Checks the files essencial files for 
	*	the operation and creates them if they 
	*	are not 
	*	@return void 
	*/
	public static void fileCheck() throws IOException
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
	/**
	*	Checks the files essencial files for 
	*	the operation and creates them if they 
	*	are not 
	*	@return void 
	*/
	public static boolean checkImages() throws IOException
	{
		boolean allFilesPresent = true;
		File image1 = new File("images/5cent.png");
		if(!(image1.exists()))
			allFilesPresent = false;
		File image2 = new File("images/backArrow.png");
		if(!(image2.exists()))
			allFilesPresent = false;
		File image3 = new File("images/buyIcon.png");
		if(!(image3.exists()))
			allFilesPresent = false;
		File image4 = new File("images/cent10.png");
		if(!(image4.exists()))
			allFilesPresent = false;
		
		File image5 = new File("images/cent20.png");
		if(!(image5.exists()))
			allFilesPresent = false;
		File image6 = new File("images/cent50.png");
		if(!(image6.exists()))
			allFilesPresent = false;
		File image7 = new File("images/coinIcon.png");
		if(!(image7.exists()))
			allFilesPresent = false;
		File image8 = new File("images/euro.png");
		if(!(image8.exists()))
			allFilesPresent = false;
	
		File image9 = new File("images/euro2.png");
		if(!(image9.exists()))
			allFilesPresent = false;
		File image10 = new File("images/gear.png");
		if(!(image10.exists()))
			allFilesPresent = false;
		File image11 = new File("images/showIcon.png");
		if(!(image11.exists()))
			allFilesPresent = false;
		File image12 = new File("images/stage_vend_icon.png");
		if(!(image12.exists()))
			allFilesPresent = false;
		return allFilesPresent;
	}
}
