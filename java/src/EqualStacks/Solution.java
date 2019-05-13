package EqualStacks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Function;

public class Solution {

    /*
     * Complete the equalStacks function below.
     */
    static int equalStacks(List<WeightedStack> stacks) {
        // order the stacks from heaviest to lightest
        Queue<WeightedStack> stackQueue = new PriorityQueue<>(stacks.size(), (a, b) -> -1 * a.compareTo(b));
        stackQueue.addAll(stacks);

        // remove an element from the heaviest stack until they are all equal weights
        while (stackQueue.stream().distinct().count() > 1) {
            WeightedStack biggestStack = stackQueue.remove();
            biggestStack.pop();
            stackQueue.offer(biggestStack);
        }

        return stackQueue.element().weight();
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static class WeightedStack<E> extends ArrayDeque<E> {

        private int weight = 0;
        private Function<? super E, Integer> weightMapper;

        public WeightedStack(Function<? super E, Integer> getWeightMapper) {
            this.weightMapper = getWeightMapper;
        }

        @Override
        public void push(E e) {
            super.push(e);
            this.addWeight(e);
        }

        @Override
        public E pop() {
            E e = super.pop();
            this.subtractWeight(e);
            return e;
        }

        public int weight() {
            return this.weight;
        }

        public int compareTo(Object stack) {
            return Optional.ofNullable(stack)
                           .filter(s -> s instanceof WeightedStack)
                           .map(s -> (WeightedStack) s)
                           .map(s -> Integer.compare(this.weight(), s.weight()))
                           .orElse(1);
        }

        @Override
        public boolean equals(Object stack) {
            return this.compareTo(stack) == 0;
        }

        @Override
        public int hashCode() {
            return this.weight();
        }

        private void addWeight(E e) {
            this.weight += this.getElementWeight(e);
        }

        private void subtractWeight(E e) {
            this.weight -= this.getElementWeight(e);
        }

        private int getElementWeight(E e) {
            return this.weightMapper.apply(e);
        }
    }

    public static void main(String[] args) throws IOException {

        scanner.nextLine();

        int result = equalStacks(
            Arrays.asList(
                Solution.createStack(scanner.nextLine().split(" ")),
                Solution.createStack(scanner.nextLine().split(" ")),
                Solution.createStack(scanner.nextLine().split(" "))
            )
        );

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    private static WeightedStack createStack(String[] items) {
        WeightedStack<Integer> stack = new WeightedStack<>(e -> e);
        for (int i = items.length - 1; i >= 0; i--) {
            int h1Item = Integer.parseInt(items[i].trim());
            stack.push(h1Item);
        }
        return stack;
    }
}
