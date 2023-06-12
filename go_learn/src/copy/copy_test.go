package copy

import (
	"log"
	"testing"
)

// 复制一个对象

type Obj struct {
	x int
	y int
}

func TestCopy(t *testing.T) {
	objA := &Obj{1, 1}
	objB := *objA
	objC := &objB

	objD := *&objA

	log.Print(objA)
	log.Print(objB)
	log.Print(objC)
	log.Print(objD)
	log.Print("------")

	objB.x = 2
	objB.y = 2

	objD.x = 4
	objD.y = 4

	log.Print(objA)
	log.Print(objB)
	log.Print(objC)
	log.Print(objD)
	log.Print("------")

	objC.x = 3
	objC.y = 3

	log.Print(objA)
	log.Print(objB)
	log.Print(objC)
	log.Print(objD)
	log.Print("------")

}
