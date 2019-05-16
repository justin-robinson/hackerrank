https://www.hackerrank.com/challenges/game-of-two-stacks

Alexa has two stacks of non-negative integers, stack ***A = [a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>]*** and stack ***B = [b<sub>0</sub>, b<sub>1</sub>, ..., b<sub>n-1</sub>]*** where index **0** denotes the top of the stack. Alexa challenges Nick to play the following game:

In each move, Nick can remove one integer from the top of either stack ***A*** or ***B*** stack .
Nick keeps a running sum of the integers he removes from the two stacks.
Nick is disqualified from the game if, at any point, his running sum becomes greater than some integer  given at the beginning of the game.
Nick's final score is the total number of integers he has removed from the two stacks.
Given ***A***, ***B***, and ***x*** for ***g*** games, find the maximum possible score Nick can achieve (i.e., the maximum number of integers he can remove without being disqualified) during each game and print it on a new line.

####Input Format

The first line contains an integer, ***g*** (the number of games). The ***3 • g*** subsequent lines describe each game in the following format:

1. The first line contains three space-separated integers describing the respective values of ***n*** (the number of integers in stack ***A***), ***m*** (the number of integers in stack ), ***B*** and ***x*** (the number that the sum of the integers removed from the two stacks cannot exceed).
1. The second line contains ***n*** space-separated integers describing the respective values of ***[a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>***.
1. The third line contains ***m*** space-separated integers describing the respective values of ***[b<sub>0</sub>, b<sub>1</sub>, ..., b<sub>n-1</sub>***.

####Constraints
* **1** ≤ ***g*** ≤ **50**
* **1** ≤ ***n, m*** ≤ **10<sup>5</sup>**
* **0** ≤ ***a<sub>i</sub>, b<sub>j</sub>*** ≤ **10<sup>6</sup>**
* **1** ≤ ***x*** ≤ **10<sup>9</sup>**

####Subtasks

* * **1** ≤ ***n, m*** ≤ **100** for **50%** of the maximum score.

####Output Format

For each of the ***g*** games, print an integer on a new line denoting the maximum possible score Nick can achieve without being disqualified.

####Sample Input 0
```
1
5 4 10
4 2 4 6 1
2 1 8 5
```
####Sample Output 0
```
4
```
####Explanation 0

The two stacks initially look like this:

```
 ___ 
|   |
| 4 |  ___
|¯¯¯| |   |
| 2 | | 2 |
|¯¯¯| |¯¯¯| 
| 4 | | 1 |
|¯¯¯| |¯¯¯|
| 6 | | 8 |
|¯¯¯| |¯¯¯|
| 1 | | 5 |
 ¯¯¯   ¯¯¯
  A     B  
```

The image below depicts the integers Nick should choose to remove from the stacks. We print **4** as our answer, because that is the maximum number of integers that can be removed from the two stacks without the sum exceeding **10**.

```
         ___ 
        |   |
move 1  | 4 |  ___
         ¯¯¯  |   | 
         ___  | 2 | move 2
        |   |  ¯¯¯
move 4  | 2 |  ___
         ¯¯¯  |   | 
         ___  | 1 | move 3
        |   |  ¯¯¯
        | 4 |  ___
        |¯¯¯| |   |
        | 6 | | 8 |
        |¯¯¯| |¯¯¯|
        | 1 | | 5 |      
         ¯¯¯   ¯¯¯
          A     B  
```

(There can be multiple ways to remove the integers from the stack, the image shows just one of them.)