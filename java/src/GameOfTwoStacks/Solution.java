package GameOfTwoStacks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            List<Integer> nmx = GameOfTwoStacks.toIntList(scanner.nextLine().split(" "));

            int result = GameOfTwoStacks.twoStacks(
                nmx.get(2),
                GameOfTwoStacks.toIntList(scanner.nextLine().split(" ")),
                GameOfTwoStacks.toIntList(scanner.nextLine().split(" "))
            );
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    private static class GameOfTwoStacks {

        /*
         * Complete the twoStacks function below.
         */
        static int twoStacks(int x, List<Integer> a, List<Integer> b) {

            int sum = 0;
            int i = 0;
            while (i < a.size() && sum+a.get(i) <= x ){
                sum += a.get(i++);
            }

            int numberOfMoves = i;

            int j=0;
            while (j<b.size() && i>=0) {
                sum += b.get(j++);

                while (sum>x && i>0) {
                    sum -= a.get(--i);
                }

                int currentNumberOfMoves = i + j;
                if (sum<=x && currentNumberOfMoves>numberOfMoves) {
                    numberOfMoves = currentNumberOfMoves;
                }
            }

            return numberOfMoves;
        }

        static List<Integer> toIntList(String[] array) {
            return Arrays.stream(array)
                         .map(String::trim)
                         .map(Integer::parseInt)
                         .collect(Collectors.toList());
        }
    }
}
