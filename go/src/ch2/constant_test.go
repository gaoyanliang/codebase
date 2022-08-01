package try_test1

import "testing"

func TestConstan(t *testing.T) {
	t.Log("My first try!")
}

// ------------- 学习声明变量
func TestFib(t *testing.T) {
	// var a int = 1
	// var b int = 1

	// var {
	//   a int = 1
	//   b int = 1
	// }

	a := 1
	b := 1

	t.Log(a)
	for i := 0; i < 10; i++ {
		t.Log(b)
		tmp := a
		a = b
		b = tmp + a
	}
	t.Log(" end")
}

// ------------- 学习如何定义变量

const (
	Mon = 1 + iota
	Tue
	Wed
	Tus
	Fir
)

func TestCon1(t *testing.T) {
	t.Log(Mon, Tue, Wed, Tus, Fir)
}

const (
	Readable = 1 << iota
	Writable
	Executable
)

func TestCon2(t *testing.T) {
	a := 7 // 0111
	t.Log(a&Readable == Readable, a&Writable == Writable, a&Executable == Executable)
}
