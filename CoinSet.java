import java.util.ArrayList;

/**
 *	A set of coins.
*/
public class CoinSet
{  
   private ArrayList<Coin> set;

   /**
    *  	Constructs a CoinSet object.
	*	Initialises the ArrayList
   */
   public CoinSet()
   {  
      set = new ArrayList<Coin>();
   }
   
   /**
	*	Adds a coin to the set of coins
	*	@param coin
   */
   public void addCoin(Coin coin){
	   set.add(coin);
   }
   
   public ArrayList<Coin> getSetOfCoins(){
	   return set;
   }
   
   public void addSetOfCoins(ArrayList<Coin> cs){
	   set.addAll(cs);
   }
   
   /**
	*	Adds the value of all the coins in the set and returns the sum
	*	@return value
   */
   public double getValue(){
	   double value = 0;
	   for(Coin coin : set){
		   value += coin.getValue();
	   }
	   return value;
   }
   
   /**
	*	Clears the set of coins
   */
   public void clearCoinSet(){
	   set.clear();
   }
}