package interview

import (
	"fmt"
	"testing"
)

// N 个协程交替打印 1-100
func Test1(t *testing.T) {

	// 协程数量
	goroutine_num := 5

	// 退出通道
	exit_ch := make(chan int)

	// 协程交互通道
	var chs []chan int

	// 初始化 n 个有缓存通道
	for i := 0; i < goroutine_num; i++ {
		chs = append(chs, make(chan int, 1))
	}

	res := 0
	j := 0
	for i := 0; i < goroutine_num; i++ {
		go func(i int) {
			for {
				<-chs[i]
				if res > 100 {
					exit_ch <- 1
					break
				}

				fmt.Println(fmt.Sprintf("gorutine%v:%v", i, res))
				res++

				if j == goroutine_num-1 {
					j = 0
				} else {
					j++
				}
				chs[j] <- 1
			}
		}(i)
	}

	chs[0] <- 1
	select {
	case <-exit_ch:
		fmt.Println("end")
	}
}
