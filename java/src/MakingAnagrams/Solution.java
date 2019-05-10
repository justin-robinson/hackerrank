package MakingAnagrams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    private int makeAnagram(String a, String b) {
        Map<Character, Long> aFrequencyMap = this.getCharacterFrequency(a);
        Map<Character, Long> bFrequencyMap = this.getCharacterFrequency(b);

        long numberNeeded = aFrequencyMap.entrySet()
                     .stream()
                     .map(e -> {
                         long bFrequency = bFrequencyMap.getOrDefault(e.getKey(), 0L);
                         bFrequencyMap.remove(e.getKey());
                         return Math.abs(bFrequency - e.getValue());
                     })
                     .mapToLong(Long::longValue)
                     .sum();

        numberNeeded += bFrequencyMap.entrySet()
                     .stream()
                     .map(e -> {
                         long aFrequency = aFrequencyMap.getOrDefault(e.getKey(), 0L);
                         return Math.abs(aFrequency - e.getValue());
                     })
                     .mapToLong(Long::longValue)
                     .sum();

        return (int)numberNeeded;
    }

    private Map<Character, Long> getCharacterFrequency(String input) {
        return input.chars()
                      .mapToObj(c -> (char)c)
                      .collect(
                          Collectors.groupingBy(
                              c -> c,
                              Collectors.counting()
                          )
                      );
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = (new Solution()).makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

