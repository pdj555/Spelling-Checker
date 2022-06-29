// James Dizikes
// CS 1323/1324, Fall 2019
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Project12 {

	public static void main(String[] args)
		throws FileNotFoundException, IOException {

		final String FILENAME_COMMON = "common-dictionary.txt";
		final String FILENAME_PERSONAL = "personal-dictionary.txt";
		
		String prompt = "Enter a word or 'quit' to stop: ";
		

		// Read the common dictionary and store the words in an ArrayList.
		
		ArrayList<String> common = readFile(FILENAME_COMMON);
		
		// Construct an ArrayList to store the personal dictionary.
		
		ArrayList<String> personal = readFile(FILENAME_PERSONAL);
		
		// Read the personal dictionary, creating the file if it doesn't 
		// exist. Store the words in an ArrayList.
		
		// Construct a Scanner to read user input from the keyboard.
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Spell Checker");
		System.out.println("-------------");
		
		// Perform a priming read to get the first word.
		System.out.print(prompt);
		String word = keyboard.nextLine().toLowerCase();
		
		// Enter the user input loop.
		while (!word.equals("quit")) {
			
			// Check if the word is in either dictionary.
			if (checkSpelling(common, personal, word)) {
				System.out.println("The word is spelled correctly.");
			} 
			else {
				System.out.println("The word was not found in the "
					+ "dictionary.");			
				System.out.println("Would you like to add it to your personal "
					+ "dictionary (yes/no)?");
				String response = keyboard.nextLine().toLowerCase();
				
				// If the user responds "yes" add the word to the end of the array and sort it.
				if (response.equals("yes")) {
					personal.add(word);
					Collections.sort(personal);
				}
			}
			
			// Get the next word from the user.
			System.out.println();
			System.out.print(prompt);
			word = keyboard.nextLine().toLowerCase();
		}
		
		keyboard.close();
		writeFile(FILENAME_PERSONAL, personal);
		System.out.println("Goodbye!");
	}
	
	
	public static ArrayList<String> readFile(String filename) throws IOException 
	{
		// Reads file into ArrayList
		File file = new File(filename);
		file.createNewFile();
		Scanner fileScanner = new Scanner(new File(filename));
		ArrayList<String> list = new ArrayList<String>();
		while (fileScanner.hasNextLine()) 
		{
			list.add(fileScanner.nextLine());
		}
		fileScanner.close();
		return list;
	}
	

	// Return true if word is in either array; otherwise, return false. Note 
	// that the arrays are sorted, so binary search can be used.
	public static boolean checkSpelling(ArrayList<String> common, ArrayList<String> personal, String word) {
			
		// Checks if word is in ArrayLists
		if (Collections.binarySearch(common, word) >= 0) {
			return true;
		}
		
		if (Collections.binarySearch(personal, word) >= 0) {
			return true;
		}
		
		return false;
	}
	
	// Write the nonempty elements of an ArrayList to a given file.
	public static void writeFile(String filename, ArrayList<String> personal)
			throws FileNotFoundException {

		PrintWriter writer = new PrintWriter(filename);

		for (int idx = 0; idx < personal.size(); ++idx) {
			writer.println(personal.get(idx));
		}

		// Close the file; otherwise, the contents will be lost.
		writer.close();
	}
}
