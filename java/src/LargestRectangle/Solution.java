package LargestRectangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(List<Integer> h) {
        int currentHeight = h.stream().max(Integer::compareTo).orElse(0);
        long largestRectangle = 0;

        Map<Integer, List<Integer>> buildingsAtHeight = IntStream.range(0, h.size())
                                                                 .boxed()
                                                                 .collect(groupingBy(h::get));


        while (currentHeight > 0) {
            List<Integer> buildingsAtCurrentHeight = Optional.ofNullable(buildingsAtHeight.get(currentHeight))
                                                             .orElse(new ArrayList<>());

            long largestAtHeight = buildingsAtCurrentHeight.stream()
                .map(i -> largestRectangle(h, i))
                .max(Long::compareTo)
                .orElse(0L);

            largestRectangle = Math.max(largestAtHeight, largestRectangle);

            currentHeight--;
        }

        return largestRectangle;
    }

    private static long largestRectangle(List<Integer> h, int i) {
        Integer currentHeight = h.get(i);

        // left
        int leftOffset = 0;
        while (0 <= i-leftOffset && h.get(i-leftOffset) >= currentHeight) {
            leftOffset++;
        }
        leftOffset--;

        // right
        int rightOffset = 0;
        while (h.size() > i+rightOffset && h.get(i+rightOffset) >= currentHeight) {
            rightOffset++;
        }
        rightOffset--;

        return currentHeight * (1 + leftOffset + rightOffset);
    }

    private static List<Integer> getBuildingsAtCurrentHeight(List<Integer> h, int currentHeight) {
        List<Integer> buildingsAtCurrentSize = new ArrayList<>();
        IntStream.range(0, h.size())
                 .filter(i ->  h.get(i).equals(currentHeight))
                 .forEach(buildingsAtCurrentSize::add);

        return buildingsAtCurrentSize;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        List<Integer> h = Arrays.stream(scanner.nextLine().split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

}
