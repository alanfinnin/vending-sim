public void deporting() throws IOException{
		FileWriting fw = new FileWriting();
		
		fw.fileCheck();
		boolean imagesExist = checkImages();
		if(!imagesExist)
		{}
		fw.fromArrayListToFiles(products, coins, operators);
	}
	public void importing() throws IOException{
		FileReading fr = new FileReading();
		
		fr.fileCheck();
       		products = fr.readFromStockFile();
		coins = fr.readFromMoneyFile();
		currentCoins = new CoinSet();
	}
