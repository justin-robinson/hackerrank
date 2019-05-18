package SimpleTextEditor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Scanner;
import java.lang.String;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int Q = Integer.valueOf(scanner.nextLine());

        SimpleTextEditor editor = new SimpleTextEditor();

        for (int i = 0; i < Q; i++) {
            String[] typeValue = scanner.nextLine().split(" ");
            try {
                editor.perform(Integer.valueOf(typeValue[0]), typeValue.length > 1 ? typeValue[1] : "");
            } catch (SimpleTextEditor.UnknownOperationError e) {
                System.out.println("Unknown Operation " + typeValue[0]);
            } catch (Exception e) {
                System.out.println("Unknown Exception");
            }
        }

        scanner.close();
    }


}

class SimpleTextEditor {

    private StringBuilder S = new StringBuilder();

    private Deque<StringBuilder> stateStack = new ArrayDeque<>();

    public void perform(Integer type, String value) throws UnknownOperationError {
        AbstractOperation operation;
        switch (type) {
            case 1:
                operation = new Append(value);
                break;
            case 2:
                operation = new Delete(value);
                break;
            case 3:
                operation = new Print(value);
                break;
            case 4:
                if (!stateStack.isEmpty()) {
                    S = stateStack.pollFirst();
                }
                return;
            default:
                throw new UnknownOperationError();

        }
        perform(operation);
    }

    private void perform(AbstractOperation operation) {
        Optional.ofNullable(operation)
                .map(o -> o.perform(S))
                .map(stateStack::offerFirst);
    }

    class UnknownOperationError extends Exception {}
}

abstract class AbstractOperation<E> {

    protected E value;

    abstract public StringBuilder perform(StringBuilder S);
}

class Append extends AbstractOperation<String> {

    Append(String value) {
        this.value = value;
    }

    public StringBuilder perform(StringBuilder S) {
        StringBuilder reversal = new StringBuilder(S);
        S.append(this.value);
        return reversal;
    }
}

class Delete extends AbstractOperation<Integer> {

    Delete(String value) {
        this.value = Integer.valueOf(value);
    }

    public StringBuilder perform(StringBuilder S) {
        if (S.length() == 0) {
            return null;
        }

        StringBuilder reversal = new StringBuilder(S);
        S.delete(S.length()-value, S.length());
        return reversal;
    }
}

class Print extends AbstractOperation<Integer> {

    Print(String value) {
        this.value = Integer.valueOf(value);
    }

    public StringBuilder perform(StringBuilder input) {
        if (this.value <= input.length()) {
            System.out.println(input.charAt(this.value-1));
        }
        return null;
    }
}
