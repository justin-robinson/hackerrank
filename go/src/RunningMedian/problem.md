https://www.hackerrank.com/challenges/ctci-find-the-running-median/copy-from/75422208

The median of a dataset of integers is the midpoint value of the dataset for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your dataset of integers in non-decreasing order, then:

* If your dataset contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted dataset *{1, 2, 3}, 2*,  is the median.
* If your dataset contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted dataset *{1, 2, 3,  4}, (2+3)/2 = 2.5*,  is the median.

Given an input stream of *n* integers, you must perform the following task for each *i<sup>th</sup>* integer:

1. Add the *i<sup>th</sup>* integer to a running list of integers.
1. Find the median of the updated list (i.e., for the first element through the *i<sup>th</sup>* element).
1. Print the list's updated median on a new line. The printed value must be a double-precision number scaled to **1** decimal place (i.e., **12.3** format).

####Input Format

The first line contains a single integer, *n*, denoting the number of integers in the data stream. 
Each line *i* of the *n* subsequent lines contains an integer, *a<sub>i</sub>* , to be added to your list.

####Constraints
* 1 ≤ *n* ≤ 10<sup>5</sup>
* 0 ≤ *a<sub>i</sub>* ≤ 10<sup>5</sup>

####Output Format

After each new integer is added to the list, print the list's updated median on a new line as a single double-precision number scaled to  decimal place (i.e.,  format).

####Sample Input
```
6
12
4
5
3
8
7
```

####Sample Output

```
12.0
8.0
5.0
4.5
5.0
6.0
```

####Explanation
There are *n* = 6 integers, so we must print the new median on a new line as each integer is added to the list:
1. *list* = {12}, *median* = 12.0
1. *list* = {12, 4} ⟶ {4, 12}, *median* = (12+4)/2 = 8.0
1. *list* = {12, 4, 5} ⟶ {4, 5, 12}, *median* = 5.0
1. *list* = {12, 4, 5, 3} ⟶ {3, 4, 5, 12}, *median* = (4+5)/2 = 4.5
1. *list* = {12, 4, 5, 3, 8} ⟶ {3, 4, 5, 8, 12}, *median* = 5.0
1. *list* = {12, 4, 5, 3, 8, 7} ⟶ {3, 4, 5, 7, 8, 12}, *median* = (5+7)/2 = 6.0

