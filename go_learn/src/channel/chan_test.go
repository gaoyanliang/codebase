package channel

import (
	"context"
	"fmt"
	"math/rand"
	"testing"
	"time"
)

// 启动一个 worker goroutine 一直产生随机数，知道找到满足条件的数时，手动调用 cancel 取消 ctx，让 worker goroutine 退出
func TestChan(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	ctx, cancel := context.WithTimeout(context.Background(), time.Millisecond*100)
	defer cancel() // 一般推荐 defer 中调用cancel()

	ret := make(chan int)
	go RandWithCancel(ctx, ret)
	for r := range ret {
		// 当找到满足条件的数时就退出
		fmt.Println("recv r:", r)
		if r == 20 {
			fmt.Println("==> find r:", r)
			break
		}
	}
	//cancel()                // 这里测试就手动调用cancel() 取消context
	time.Sleep(time.Second) // sleep 等待 worker goroutine 退出
}

func RandWithCancel(ctx context.Context, ret chan int) {
	defer close(ret)
	timer := time.NewTimer(time.Millisecond)
	for {
		select {
		case <-ctx.Done():
			fmt.Println("ctx cancel")
			timer.Stop()
			return
		case <-timer.C:
			r := rand.Intn(100)
			ret <- r
			timer.Reset(time.Millisecond)
		}
	}
}

// 启动一个 worker goroutine 一直产生随机数，直到 ctx 超时后退出
func TestCase2(t *testing.T) {
	rand.Seed(time.Now().UnixNano())
	// ctx, cancel := context.WithTimeout(context.Background(), time.Millisecond*100)
	ctx, cancel := context.WithDeadline(context.Background(), time.Now().Add(time.Millisecond*100))
	// defer cancel() // 一般推荐 defer 中调用cancel()

	ret := make(chan int)
	go RandWithTimeout(ctx, ret)
	for r := range ret {
		fmt.Println("recv r:", r)
		// 当找到满足条件的数时就退出
		if r == 20 {
			fmt.Println("========> find r:", r)
			break
		}
	}
	cancel()                // 这里测试就手动调用cancel() 取消context
	time.Sleep(time.Second) // sleep 等待 worker goroutine 退出
}

func RandWithTimeout(ctx context.Context, ret chan int) {
	defer close(ret)
	timer := time.NewTimer(time.Millisecond)
	for {
		select {
		case <-ctx.Done():
			fmt.Println("ctx cancel")
			timer.Stop()
			return
		case <-timer.C:
			r := rand.Intn(100)
			ret <- r
			timer.Reset(time.Millisecond)
		}
	}
}
