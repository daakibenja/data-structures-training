import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {

        String[] allWords = getAllWords("text_file.txt");
        String[] bucket = new String[10000];// Need a big enough array to fit the biggest index created by the hash
                                            // function
        int totalCollisions = 0;// for information about collisions
        int totalWords = 0; // for total hashed words

        // for each word call the generateHash method to generate an index
        // for that word. there are two cases.
        // case 1. If the index generated is empty in the bucket array then insert the
        // word at that location
        // case 2. This is the case for collision, If the location is not null, then we
        // need to findout
        // whether the word there is the same as the word we have if it's the same, do
        // nothing otherwise concat a # plus
        // the word it's to whatever is stored there.
        for (int i = 0; i < allWords.length; i++) {

            int index = generateHash(allWords[i]);
            if (bucket[index] == null) {
                bucket[index] = allWords[i];
                totalWords++;

            } else if (bucket[index].indexOf(allWords[i]) == -1) {
                totalCollisions++;
                totalWords++;
                bucket[index] = bucket[index] + "#" + allWords[i];
            }

        }
        // Summary about the hashing process.
        System.out.println("Total Words(valid and unique): " + totalWords + "\nTotal collisions: " + totalCollisions);
        Scanner scanner = new Scanner(System.in);

        // Within this loop, you can search for a word to see if it exists or not.
        // You can as well terminate the loop.
        // Get a word from the user , convert it to lower case, generate a hash for the
        // word
        // then look at the index(hash) generated for the properties below.
        // It doesn't exist if the index <0 or index >10000 or location in the bucket is
        // null
        // or if the location is not null but the word is not among the # separated
        // words.
        while (true) {
            System.out.print("\nEnter word to search: ");
            String word = scanner.nextLine();

            word = word.toLowerCase(); // This is compulsory to avaoid logical errors in our hash table.
            int index = generateHash(word);

            if (index < 0 || index > 10000 || bucket[index] == null || (bucket[index].indexOf(word) == -1)) {
                System.out.println("The word doesn't exist");

            } else {

                System.out.println("Word Exits and it's " + bucket[index]);

            }
            System.out.println("Press Enter to continue Or enter anything to quit");

            if ((scanner.nextLine().length() > 0)) {
                System.out.println("Exiting...");

                break;
            }

        }
        scanner.close();
    }

    /**
     * This method takes a line of words and extracts the individaul words into an
     * arraylist and returns the arraylist
     * 
     * @param line
     * @return ArrayList<String>
     */
    private static ArrayList<String> getWordsFromLine(String line) {

        ArrayList<String> words = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {

            while (rowScanner.hasNext()) {
                String word = rowScanner.next();
                word = word.toLowerCase();
                // By pass short words
                if (word.length() <= 2) {
                    continue;
                }
                // Match the alphabet letters
                if (word.matches("[a-zA-Z]+")) {
                    words.add(word);

                }
                // For words with non alphabet characters at the end eg . , ? () ! ' ' ""/
                // etc We have considered the common symbols
                // We only considered .?,()\"';:
                // Remove the symbol and keep the word
                else if (word.matches("[a-zA-Z]+[.?,;:()\"']+")) {
                    word = word.replaceAll("[.?,;:()]+", "");
                    words.add(word);
                } else {
                    continue;
                }

            }
        }

        return words;
    }

    /**
     * Method to extract all words(only valid words) int an array of strings(words).
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

    /**
     * This method works like this returns the total sum of the squares of the
     * indexes of the it's letters in the alphabet with a having index 0, z 25. It's
     * misleading to categorize this as O(N) since it's worst case comes when we
     * pass the longest word which is independent of the number of words we have in
     * the file.
     * 
     * @param word
     * @return
     */
    public static int generateHash(String word) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String[] letters = word.split("");
        int totalSum = 0;
        for (int i = 0; i < letters.length; i++) {
            totalSum = totalSum + (int) Math.pow(alphabet.indexOf(letters[i]), 2);
        }
        return totalSum;
    }
}
