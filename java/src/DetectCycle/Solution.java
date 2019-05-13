package DetectCycle;

/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as:
    class Node {
        int data;
        Node next;
    }
*/

import java.util.HashSet;
import java.util.Set;

public class Solution {

    boolean hasCycle(Node head) {
        Node node = head;
        Set<Node> previousDatas = new HashSet<Node>();
        int iterationCount = 0;
        final int MAX_ITERATION_COUNT = 100;
        while(node != null){
            iterationCount++;

            if(previousDatas.contains(node)){
                return true;
            }

            previousDatas.add(node);
            node = node.next;

            if(iterationCount >= MAX_ITERATION_COUNT) {
                return false;
            }
        }
        return false;
    }

    private class Node {
        int data;
        Node next;
    }
}
