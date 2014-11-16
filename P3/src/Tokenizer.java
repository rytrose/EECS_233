
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class reads and extracts the words of a file, and stores the normalized words
 * in an ArrayList
 * EECS 233, Prof. Lewicki
 * @author Ryan Rose
 */
public class Tokenizer {
	
	/**
	 * ArrayList that stores the extracted and normalized words.
	 */
	private ArrayList<String> list = new ArrayList<String>();
	
	/**
	 * Constructor that extracts the words from a file, whose
	 * name is given by a String argument.
	 * @param s String name of the file from which to obtain the words
	 * @throws FileNotFoundException 
	 */
	public Tokenizer(String s) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(s));
		while(sc.hasNext() == true){
			String word = sc.next();
			word = word.toLowerCase();
			word = word.replaceAll("\\s", "");
			word = word.replaceAll("\\W", "");
			if(word.equals("") != true)
				list.add(word);
		}
		sc.close();
	}
	
	/**
	 * Constructor that extracts words directly from a String array.
	 * @param s String array from which to obtain the words.
	 */
	public Tokenizer(String[] s){
		for(String string : s){
			Scanner sc = new Scanner(string);
			while(sc.hasNext() == true){
				String word = sc.next();
				word = word.toLowerCase();
				word = word.replaceAll("\\s", "");
				word = word.replaceAll("\\W", "");
				if(word.equals("") != true)
					list.add(word);
			}
			sc.close();
		}
	}
	
	/**
	 * Returns the list of words created by the constructors.
	 * @return returns the list of words created by the constructors
	 */
	public ArrayList<String> wordList(){
		return list;
	}
}
