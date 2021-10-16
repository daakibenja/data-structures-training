import java.util.Scanner;

class Graph {

    public static void main(String[] args) {
        // Hard coded adjacency matrix
        int[][] adjacency_matrix = { { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 } };
        Scanner scanner = new Scanner(System.in);
        // with in this loop we shall be accepting two verices or nodes
        // from the user and then determine if there is a node or not
        for (;;) {
            System.out.print("Enter two nodes to\ndetermine if there is an edge between them\nEnter: ");
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            if (node1 >= 1 && node2 >= 1 && node1 <= 10 && node2 <= 10) {
                // We are sure we are dealing with integers
                // Don't forget array indices start at 0, so we subtract
                // to cater for this.
                if (adjacency_matrix[node1 - 1][node2 - 1] == 1) {
                    System.out.println("\nThere is a edge between " + node1 + " and " + node2);
                } else {
                    System.out.println("\nThere is no edge between " + node1 + " and " + node2);
                }
            } else {
                System.out.println("Please enter valid node values.\n");
            }
        }
    }
}