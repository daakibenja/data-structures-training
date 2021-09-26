public class SumOfNumbers {
    public static void main(String[] args) {

        // sum to hold the sum of the first n numbers a is the first number 0 in this
        // case. r is the difference between the consecutive numbers 1 in this case

        // I'm using the formula Sn = (n/2)[(2a)-(n-1)r] which is the sum of first n
        // terms of an arithmetic progression So finding the sum the first n numbers for
        // each n=0...1,000,000,000 will take same time.
        // limit is the largest value which when reached the program terminates
        int limit = 1000000000;
        double sum, a, r, n, start, stop, finalTime, initialTime;
        a = 1;
        r = 1;
        // Print the table header, enough width i.e Number will
        // be 9 chars wide, Sum 10 and Time also 10.
        System.out.printf("%9s\t%10s\t%10s\n", "Number", "Sum", "Time");

        // Get the initial time to be used to get the total time taken by
        // the program to run.

        initialTime = System.currentTimeMillis();

        for (int i = 0; i <= limit; i++) {
            // this will be used to know the time taken for
            // each iteration. i.e stop-start will give us the time
            // taken by the iteration
            start = System.currentTimeMillis();
            n = i;
            sum = (n / 2) * ((2 * a) + (n - 1) * r);

            // Get the current time after the operation is finished
            stop = System.currentTimeMillis();
            // Print out the results following the same format as in
            // the header.
            System.out.printf("%9d\t%10.0f\t%10f ms\n", i, sum, stop - start);

        }
        // Get current time at the end of the program
        finalTime = System.currentTimeMillis();
        // Print out the total time taken by the program
        // by subtracting initialTime from finalTime.
        System.out.printf("\n\nTotal time taken: %f\n\n", finalTime - initialTime);

    }

}
