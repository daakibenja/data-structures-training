/** This template was Developed by Benjamin Daaki (richarddaaki4@gmail.com) and Modified by Robert Kasumba (rkasumba@wustl.edu) */
import java.util.Scanner;

import java.io.File;
import java.util.ArrayList;

public class AlgorithmsComparison {
    public static void main(String[] args) {
        // Load the song plays csv file into an array of songs
        Song[] songsArray = getAllSongs("./song_plays.csv"); // ensure the song_plays.csv is in the same folder as this
                                                             // java file
        /** PUT THE CALL to sort or search here */
        /** The task is to sort or search through the array */
        double startTime, stopTime, timeTaken;

        startTime = System.currentTimeMillis();
        Song[] sorted1 = quickSort(songsArray);
        stopTime = System.currentTimeMillis();
        timeTaken = stopTime - startTime;
        System.out.printf("Quick sort took %f ms\n\n", timeTaken);


        startTime = System.currentTimeMillis();
        Song[] sorted2 = selectionSort(songsArray);
        stopTime = System.currentTimeMillis();
        timeTaken = stopTime - startTime;
        System.out.printf("Selection sort took %f ms\n\n", timeTaken);

        // Print the sorted values
        // I'll Will print the two arrays ie sorted1 and sorted2 side by side
        // It looks nice. 
        System.out.printf("%10s\t%10s\n", "Quick Sort", "Selection Sort");
        System.out.printf("%10s\t%10s\n", "[Id,views]", "[Id,views]");
        for (int i = 0; i < songsArray.length; i++) {
        System.out.printf("[%4d,%4d]\t[%4d,%4d]\n", sorted1[i].getId(),sorted1[i].getViews(), sorted2[i].getId(),
                sorted2[i].getViews());

        }
     }

    private static Song[] selectionSort(Song[] allSongs) {

        int total = allSongs.length;

        for (int i = 0; i < total; i++) {

            int minimum = i; // Assume current index has the minimun element
            for (int j = i + 1; j < total; j++) {

                // Compare views of all songs which are at positions minimum+1 upto
                // the end of the array. Incase you find a song with lesser views
                // update minimum to have it's index.
                if (allSongs[j].getViews() < allSongs[minimum].getViews()) {
                    minimum = j;
                }
            }
            // Swap only if minimum hasn't changed
            if (minimum != i) {
                Song temp = allSongs[i];
                allSongs[i] = allSongs[minimum];
                allSongs[minimum] = temp;
            }

        }
        // Return the sorted array
        return allSongs;
    }

    private static Song[] quickSort(Song[] songsArray) {

        // This is the base case to end the recursive calls
        if (songsArray.length <= 1) {
            return songsArray;
        }

        int length = songsArray.length;

        // Arraylists to provide expandable storage of cause we
        // don't know which how many elements they will store.
        // Left will store all songs whose views are less than or equal to the views of
        // of the pivot while right will store the rest of the songs
        ArrayList<Song> left = new ArrayList<Song>();
        ArrayList<Song> right = new ArrayList<Song>();

        // Select a pivot. It can be any element but for simlicity,
        // I'm using the last element.
        Song pivot = songsArray[length - 1];

        // Avoid including the pivot by iterating upto length-2 or < length-1
        // Failure to do this you won't reach the base case.
        for (int i = 0; i < (length - 1); i++) {

            // if a song has less or equal views to pivot song
            // Push it to the left array list otherwise push to the right array list
            if (songsArray[i].getViews() <= pivot.getViews()) {
                left.add(songsArray[i]);// Now you can see the importance of using arraylist, no worries about indices.
            } else {
                right.add(songsArray[i]);
            }
        }

        // We need to get the size of the the two array lists. We have to convert them
        // to fixed size arrays.
        int sizeLeft = left.size();
        int sizeRight = right.size();

        // Create arrays with eaqual size as their coresponding arraylists
        Song[] leftArr = new Song[sizeLeft];
        Song[] rightArr = new Song[sizeRight];

        // Populate the arrays with the songs from the arraylists
        leftArr = left.toArray(leftArr);
        rightArr = right.toArray(rightArr);

        // Now we can make recursive calls to quick sort with the songs arrays
        // All the above conversions were not in vain, note that quick sort takes array
        // as
        // parameter not array list.
        // The concatenation method combines all the passed parameters into a single
        // array
        return concatenate(quickSort(leftArr), pivot, quickSort(rightArr));

    }

    public static Song[] concatenate(Song[] leftArr, Song pivot, Song[] rightArr) {
        // We need the length of the left and right arrays to determine the length of
        // the
        // final array
        int sizeLeft = leftArr.length;
        int sizeRight = rightArr.length;

        // Size of the final array, Note that 1 is added to include the pivot
        int totalSize = sizeLeft + sizeRight + 1;

        // Now we can create the final songs array that is sufficient to hold all the
        // songs.
        Song[] finalSongsArray = new Song[totalSize];

        // this i will be used to track the indices of th final songs array so it should
        // have this
        // outside scope
        int i = 0;
        for (; i < sizeLeft; i++) {
            finalSongsArray[i] = leftArr[i];
        }

        // After copying the left array to the final array
        // i will be at the next free index, so
        // add the pivot
        // and increment i to point to the next index
        finalSongsArray[i] = pivot;
        i++;
        // j will be interating over the rightArr while i will be still
        // tracking the final songs array, both must be incremented to avoid
        // overwriting some elements in the final songs array
        for (int j = 0; j < sizeRight; j++, i++) {
            finalSongsArray[i] = rightArr[j];
        }

        return finalSongsArray;
    }

    private static Song[] getAllSongs(String fileName) {
        // An arraylist to conatain all the songs as instaces of the song object
        ArrayList<Song> allSongs = new ArrayList<Song>();
        // This will enable us skip the heading in the csv file.
        int skipHeading = 0;
        // File name make sure you use the right path.

        try (Scanner scanner = new Scanner(new File(fileName));) {
            while (scanner.hasNextLine()) {

                // Skip heading that's when the skipHeading variable is zero
                if (skipHeading == 0) {
                    scanner.nextLine();
                    skipHeading = 1;
                    continue;
                }

                // Temporary array to hold each song's data
                // song id will be index 0 and views index 1
                ArrayList<Integer> songDetails = getRecordFromLine(scanner.nextLine());

                // Create a song object using the song details
                Song song = new Song(songDetails.get(0), songDetails.get(1));

                // Push the song to the all songs arraylist
                // Then you are good to go!!!!!
                allSongs.add(song);
            }

        } catch (Exception e) {
            // Exception handling perhaps wrong file name
            System.out.println(e);
        }
        // Final songs array with size as all songs array list
        Song[] finalSongsArray = new Song[allSongs.size()];

        // Populate the final songs array and return it
        finalSongsArray = allSongs.toArray(finalSongsArray);
        return finalSongsArray;

    }

    // This function gets the song record
    private static ArrayList<Integer> getRecordFromLine(String line) {

        ArrayList<Integer> values = new ArrayList<Integer>();
        try (Scanner rowScanner = new Scanner(line)) {
            // Ofcourse these values are separated by commas
            rowScanner.useDelimiter(",");
            // Then get all the values
            while (rowScanner.hasNext()) {
                values.add(Integer.parseInt(rowScanner.next()));
            }
        }
        // return the values in an array.
        return values;
    }

}

class Song {
    private int id; // unique identifier of the song on mubali.net
    private int views; // number of times it has been played or viewed

    public Song(int songId, int numViews) {
        this.id = songId; // id must be non-negative but this requirement has not been enforced
        this.views = numViews; // views must be non-negative but this requirement has not been enforced
    }

    public int getId() {
        return this.id;
    }

    public int getViews() {
        return this.views;
    }
}