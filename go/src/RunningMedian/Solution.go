package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "sort"
    "strconv"
    "strings"
)



func main() {
    reader := bufio.NewReaderSize(os.Stdin, 1024 * 1024)

    nTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
    checkError(err)
    n := int32(nTemp)

    a := make([]int, 0, n)

    for i := 0; i < int(n); i++ {
        aItem, err := strconv.ParseInt(readLine(reader), 10, 64)
        checkError(err)
        insertSortedAscending(&a, int(aItem))
        // fmt.Println(a)
        median, err := getMedian(a)
        if err == nil {
            fmt.Printf("%.1f\n", median)
        }
    }
}

func insertSortedAscending(a *[]int, f int) {
    s:=*a
    l:=len(s)
    if l==0 { *a = append(*a, f) }

    i := sort.Search(l, func(i int) bool { return s[i] > f})

    s = append(s, 0)
    copy(s[i+1:], s[i:])
    s[i] = f
    *a = s
}

func getMedian(a []int) (float64, error) {
    var arrayLength int = len(a)
    if arrayLength == 0 {
        return 0, MedianError{"can't find median of array length 0"}
    }
    if arrayLength % 2 == 0 {
        var leftOfMiddle = a[arrayLength/2 - 1]
        var rightOfMiddle = a[(arrayLength/2)]
        return float64(leftOfMiddle + rightOfMiddle) / 2, nil
    } else {
        var middle = a[arrayLength/2]
        return float64(middle), nil;
    }

}

func readLine(reader *bufio.Reader) string {
    str, _, err := reader.ReadLine()
    if err == io.EOF {
        return ""
    }

    return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
    if err != nil {
        panic(err)
    }
}

type MedianError struct {
    Message string
}

func (e MedianError) Error() string {
    return fmt.Sprintf("%v", e.Message)
}
