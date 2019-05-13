import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    /**
     * Creates a difference array from the queries
     * @param n length of array
     * @param queries {@link Query} array
     * @return
     */
    static long arrayManipulation(int n, Query[] queries) {
        long[] differenceArray = new long[n];

        Arrays.stream(queries)
              .forEach(q -> Solution.add(q, differenceArray));

        return Solution.getMax(differenceArray);
    }

    /**
     * add a query to the difference array
     * @param q {@link Query}
     * @param differenceArray difference array
     */
    private static void add(Query q, long[] differenceArray) {
        differenceArray[q.a] += q.k;
        if (q.b+1 < differenceArray.length) {
            differenceArray[q.b+1] -= q.k;
        }
    }

    /**
     * Running sum to get the max value at any point in the difference array
     * @param differenceArray populated difference array
     * @return max value
     */
    private static long getMax(long[] differenceArray) {
        long max = 0L;
        long runningSum = max;
        for (long delta : differenceArray) {
            runningSum += delta;
            max = Math.max(max, runningSum);
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        Query[] queries = new Query[m];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            // substract one since the input is based on 1-indexed arrays
            int a = Integer.parseInt(queriesRowItems[0]) - 1;
            int b = Integer.parseInt(queriesRowItems[1]) - 1;
            int k = Integer.parseInt(queriesRowItems[2]);

            queries[i] = new Query(a, b, k);
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static class Query {

        public int a;
        public int b;
        public int k;

        Query(int a, int b, int k) {
            this.a = a;
            this.b = b;
            this.k = k;
        }
    }
}
