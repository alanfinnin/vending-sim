import java.util.ArrayList;

/**
 *	A set of coins.
*/
class CoinSet
{  
   private ArrayList<CoinLine> set;

   /**
    *  	Constructs a CoinSet object.
	*	Initialises the ArrayList
   */
   CoinSet()
   {
      set = new ArrayList<>();
      //this is temporary, alans readfile called here for money
      set.add(new CoinLine(0.05, "5 Cent", 0));
      set.add(new CoinLine(0.10, "10 Cent", 0));
      set.add(new CoinLine(0.20, "20 Cent", 0));
      set.add(new CoinLine(0.50, "50 Cent", 0));
      set.add(new CoinLine(1.00, "1 Euro", 0));
      set.add(new CoinLine(2.00, "2 Euro", 0));
   }


   
   /**
	*	Adds a coin to the set of coins
	*	@param coin
   */
   void addCoin(Coin coin){
   		for(CoinLine cl : set){
   			if(coin.equals(cl.getCoin())){
   				cl.addQuantity(1);
   				break;
			}
		}
   }

	/**
	 * Returns the Coinset
	 * @return Set of Coin Lines
	 */
	ArrayList<CoinLine> getSetOfCoins(){
	   return set;
   }

	/**
	 * Adds one coinset to another by checking for coins in common and adding the
	 * quantity of the coins in the passed in coinset to the quantity in the current coinset
	 * @param inputCoinLineList An ArrayList of CoinLine
	 */
   void addSetOfCoins(ArrayList<CoinLine> inputCoinLineList){
   		for(CoinLine inputCoinLine : inputCoinLineList){
   			for(CoinLine setCoinLine : set) {
				if(inputCoinLine.equals(setCoinLine)) {
					setCoinLine.addQuantity(inputCoinLine.getQuantity());
					break; //only breaks from the innermost loop thank god
				}
			}
		}
   }
   
   /**
	*	Adds the value of all the coins in the set and returns the sum
	*	@return value
   */
   double getValue(){
	   double value = 0;
	   for(CoinLine cl : set){
		   value += cl.getTotalValue();
	   }
	   return value;
   }
   
   /**
	*	Clears the set of coins
   */
   void clearCoinSet(){
	   set.clear();
   }

   ArrayList<Coin> getAcceptedCoins(){
   		ArrayList<Coin> acceptableCoins = new ArrayList<>();
   		for(CoinLine cl: set){
   			acceptableCoins.add(cl.getCoin());
		}
		return acceptableCoins;
   }

   public int size(){
   		return set.size();
   }

   @Override
   public String toString(){
   		String output = "";
	   	for(CoinLine cl : set){
		   	output += cl.toString() + ",\n";
	   	}
	   	return output;
   }
}