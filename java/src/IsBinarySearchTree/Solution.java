package IsBinarySearchTree;

public class Solution {
/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {

        return checkBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkBSTRecursive(Node node, int min, int max) {

        if (node == null){
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }

        return checkBSTRecursive(node.left, min, node.data) &&
            checkBSTRecursive(node.right, node.data, max);
    }

    class Node {
        int data;
        Node left;
        Node right;
    }
}
