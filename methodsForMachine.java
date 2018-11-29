public void deporting() throws IOException{
		FileWriting fw = new FileWriting();
		
		fw.createFiles();
		fw.fromArrayListToFiles(products, coins, operators);
	}
	public void importing() throws IOException{
		FileReading fr = new FileReading();
		
		fr.createFiles();
        products = fr.readFromStockFile();
		coins = fr.readFromMoneyFile();
		currentCoins = new CoinSet();
	}
