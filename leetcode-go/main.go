package main

import "fmt"

type A struct {
	a int
	b string
}

func main() {
	list := new([]int)

	*list = append(*list, 1)

	fmt.Println(*list)
}
