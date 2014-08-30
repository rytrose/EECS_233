/**
 * Annotates a number in accordance to the rules of the "FizzBuzz" game.
 * EECS 233
 * @author Ryan Rose
 */

public class Annotation {
	/**
	 * The integer to be annotated.
	 */
	private int n = 0;
	
	/**
	 * Constructor that sets n to a default of 0. 
	 */
	public Annotation(){
	}
	
	/**
	 * Constructor with an integer parameter sets n to the parameter value.
	 */
	public Annotation(int i){
		this.n = i;
	}
	
	/**
	 * Gets the current value of n.
	 */
	public int getn(){
		return this.n;
	}
	
	/**
	 * Sets the current value of n.
	 */
	public void setn(int i){
		this.n = i;
	}
	
	public String toString(){
		if(getn() == 0){
			return "0";
		}
		else if((getn() % 3 == 0) && (getn() % 5 == 0)){
			return "FizzBuzz";
		}
		else if(getn() % 3 == 0){
			return "Fizz";
		}
		else if(getn() % 5 == 0){
			return "Buzz";
		}
		else{
			return new Integer(getn()).toString();
		}
	}
}
