package main

import "fmt"

type A struct {
	a int
	b string
}

func main() {
	a := [4]int{1, 2, 3, 4}
	b := [4]int{1, 2, 3, 4}
	c := [4]int{1, 3, 4, 5}
	fmt.Println(a == b) // true
	fmt.Println(a == c) // false

	type A struct {
		a int
		b string
	}
	aa := A{a: 1, b: "test1"}
	bb := A{a: 1, b: "test1"}
	cc := A{a: 1, b: "test2"}
	fmt.Println(aa == bb)
	fmt.Println(aa == cc)
}
