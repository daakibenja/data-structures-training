
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {
        String[] allWords = getAllWords("text_file.txt");
        for (int i = 0; i < allWords.length; i++) {
            System.out.print(allWords[i]);
            System.out.print(",");

        }
    }

    /**
     * This method takes a line of words and extracts the 
     * individaul words into an arraylist and returns the 
     * arraylist
     * @param line
     * @return ArrayList<String>
     */
    private static ArrayList<String> getWordsFromLine(String line) {

        ArrayList<String> words = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            
            while (rowScanner.hasNext()) {
                String word = rowScanner.next();
                if (word.matches("[a-zA-Z]+")) {
                    words.add(word);

                } else {
                    continue;
                }

            }
        }
        
        return words;
    }

    /**
     * Method to extract all words(only valid words) 
     * int an array of strings(words).
     * valid words are, words consisting of only alphabets
     */
    public static String[] getAllWords(String fileName) {
    
        ArrayList<String> allWords = new ArrayList<String>();
        

        try (Scanner scanner = new Scanner(new File(fileName));) {
            while (scanner.hasNextLine()) {
               
                ArrayList<String> wordsInLine = getWordsFromLine(scanner.nextLine());

                allWords.addAll(wordsInLine);
            }

        } catch (FileNotFoundException r) {
            // Exception handling perhaps wrong file name
            r.getMessage();
            // System.out.println(e);
        }
    
        String[] finalWordsArray = new String[allWords.size()];

    
        finalWordsArray = allWords.toArray(finalWordsArray);
        return finalWordsArray;

    }
}
