package SparseArrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {

        // map the index of each query string. This allows for duplicate query strings
        Map<String, List<Integer>> indexes = IntStream.range(0, queries.length)
                                                      .boxed()
                                                      .collect(Collectors.toMap(
                                                          i -> queries[i],
                                                          i -> {
                                                              // can't use Arrays.asList
                                                              // since it returns a different ArrayList implementation
                                                              // that doesn't implement addAll()
                                                              List<Integer> l = new ArrayList<>();
                                                              l.add(i);
                                                              return l;
                                                          },
                                                          (l1, l2) -> {
                                                              l1.addAll(l2);
                                                              return l1;
                                                          }
                                                          )
                                                      );


        int[] counts = new int[queries.length];

        // increment the count of each found query by 1
        Stream.of(strings).forEach(string -> {
            Optional.ofNullable(indexes.get(string))
                    .map(List::stream)
                    .orElse(Stream.empty())
                    .forEach(i -> counts[i] = ++counts[i]);
        });

        return counts;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int stringsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] strings = new String[stringsCount];

        for (int i = 0; i < stringsCount; i++) {
            String stringsItem = scanner.nextLine();
            strings[i] = stringsItem;
        }

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] queries = new String[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            String queriesItem = scanner.nextLine();
            queries[i] = queriesItem;
        }

        int[] res = matchingStrings(strings, queries);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
