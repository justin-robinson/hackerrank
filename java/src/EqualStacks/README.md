https://www.hackerrank.com/challenges/equal-stacks/problem

You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height. You can change the height of a stack by removing and discarding its topmost cylinder any number of times.

Find the maximum possible height of the stacks such that all of the stacks are exactly the same height. This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they're all the same height, then print the height. The removals must be performed in such a way as to maximize the height.

**Note:** An empty stack is still a stack.

####Input Format

The first line contains three space-separated integers, *n<sub>1</sub>*, *n<sub>2</sub>* , and *n<sub>3</sub>*, describing the respective number of cylinders in stacks **1**, **2**, and **3**. The subsequent lines describe the respective heights of each cylinder in a stack from top to bottom:

The second line contains *n<sub>1</sub>* space-separated integers describing the cylinder heights in stack **1**. The first element is the top of the stack.
The third line contains *n<sub>2</sub>* space-separated integers describing the cylinder heights in stack **2**. The first element is the top of the stack.
The fourth line contains *n<sub>3</sub>* space-separated integers describing the cylinder heights in stack **3**. The first element is the top of the stack.

####Constraints
* 0 ≤ *n<sub>1</sub>*, *n<sub>2</sub>*, *n<sub>3</sub>* ≤ 10<sup>5</sup>
* 0 < *height of any cylinder* ≤ 100

####Output Format

Print a single integer denoting the maximum height at which all stacks will be of equal height.

####Sample Input
```
5 3 4
3 2 1 1 1
4 3 2
1 1 4 1
```
####Sample Output
```
5
```

####Explanation

Initially, the stacks look like this:

```
       ___
 ___  |   |
|   | | 4 |  ___
| 3 | |   | |   |
|   | |   | |¯¯¯|
|¯¯¯| |¯¯¯| |¯¯¯|
| 2 | | 3 | | 4 | 
|¯¯¯| |   | |   |
|¯¯¯| |¯¯¯| |   |
|¯¯¯| | 2 | |¯¯¯|
 ¯¯¯   ¯¯¯   ¯¯¯ 
```

Observe that the three stacks are not all the same height. To make all stacks of equal height, we remove the first cylinder from stacks **1** and **2**, and then remove the top two cylinders from stack **3** (shown below).

```
       ___
 ___  |   |
|   | | 4 |  ___
| 3 | |   | |   |
|   | |   | |¯¯¯|
 ¯¯¯   ¯¯¯   ¯¯¯

 ___   ___   ___
|   | |   | |   |
| 2 | | 3 | | 4 | 
|¯¯¯| |   | |   |
|¯¯¯| |¯¯¯| |   |
|¯¯¯| | 2 | |¯¯¯|
 ¯¯¯   ¯¯¯   ¯¯¯ 
```

As a result, the stacks undergo the following change in height:
1. **8 - 3 = 5**
1. **9 - 4 = 4**
1. **7 - 1 - 1 = 5**

All three stacks now have ***height* = 5**. Thus, we print **5** as our answer.