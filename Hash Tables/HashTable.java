
import java.io.File;
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

    private static ArrayList<String> getWordsFromLine(String line) {

        ArrayList<String> words = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            // Ofcourse these values are separated by commas
            // line = line.replaceAll("-?\b+", "");
            // rowScanner.useDelimiter(" ");
            // Then get all the values
            while (rowScanner.hasNext()) {
                String word = rowScanner.next();
                if (word.matches("[a-zA-Z]+")) {
                    words.add(word);

                } else {
                    continue;
                }

            }
        }
        // return the values in an array.
        return words;
    }

    public static String[] getAllWords(String fileName) {
        // An arraylist to conatain all the songs as instaces of the song object
        ArrayList<String> allWords = new ArrayList<String>();
        // This will enable us skip the heading in the csv file
        // File name make sure you use the right path.

        try (Scanner scanner = new Scanner(new File(fileName));) {
            while (scanner.hasNextLine()) {

                // Skip heading that's when the skipHeading var

                // Temporary array to hold each song's data
                // song id will be index 0 and views index 1
                ArrayList<String> wordsInLine = getWordsFromLine(scanner.nextLine());

                // Create a song object using the song details
                // Song song = new Song(songDetails.get(0), songDetails.get(1));

                // Push the song to the all songs arraylist
                // Then you are good to go!!!!!
                allWords.addAll(wordsInLine);
            }

        } catch (Exception e) {
            // Exception handling perhaps wrong file name
            System.out.println(e);
        }
        // Final songs array with size as all songs array list
        String[] finalWordsArray = new String[allWords.size()];

        // Populate the final songs array and return it
        finalWordsArray = allWords.toArray(finalWordsArray);
        return finalWordsArray;

    }
}
