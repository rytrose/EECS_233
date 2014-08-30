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
	 * @param i - the value n is to be set as.
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
	 * @param i - the value n is to be set as.
	 */
	public void setn(int i){
		this.n = i;
	}
	
	/**
	 * Annotates the given value of n in accordance to the rules of "FizzBuzz".
	 */
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
	
	/**
	 * Creates a list of annotated values from a starting and ending input.
	 * @param start - the starting value of n for the list.
	 * @param end - the ending value of n for the list.
	 */
	public static String annotateList(int start, int end){
		// Annotation object that allows for "FizzBuzz" annotating.
		Annotation n = new Annotation();
		// Accounts for the case where start = end
		if(start == end){
			n.setn(start);
			return n.toString();
		}
		// Allows for string building without string concatenation.
		StringBuilder s = new StringBuilder();
		// Creates the list, separated by spaces, sans the last entry
		for(int i = start; i < end; i++){
			n.setn(i);
			s.append(n.toString());
			s.append(' ');			
		}
		// Adds the last entry, without a space after it.
		n.setn(end);
		s.append(n.toString());
		return s.toString();		
	}
}
