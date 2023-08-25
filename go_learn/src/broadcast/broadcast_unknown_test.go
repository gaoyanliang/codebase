package broadcast

import (
	"fmt"
	"os"
	"os/signal"
	"strconv"
	"sync"
	"syscall"
	"testing"
	"time"
)

// 用 go 实现广播功能
// 监听者数量未知

// type worker struct 复用 broadcast_known 中的 worker

// 定义线程安全的 slice 用于新增并启动 worker
type threadSafeSlice struct {
	sync.Mutex
	workers []*worker
}

func (slice *threadSafeSlice) Push(w *worker) {
	slice.Lock()
	defer slice.Unlock()

	slice.workers = append(slice.workers, w)
}

func (slice *threadSafeSlice) Iter(routine func(*worker)) {
	slice.Lock()
	defer slice.Unlock()

	for _, worker := range slice.workers {
		routine(worker)
	}
}

func TestBroadcastUnknown(t *testing.T) {
	globalQuit := make(chan struct{})

	tss := &threadSafeSlice{}

	// 1秒钟添加一个新的worker至slice中
	var workerName string
	go func() {
		for i := 0; i < 10; i++ {
			workerName = "worker-" + strconv.Itoa(i)
			time.Sleep(1 * time.Second)
			w := &worker{
				Name: workerName,
				quit: globalQuit,
			}
			w.Start()
			tss.Push(w)
		}
	}()

	// 派发消息
	go func() {
		msg := "test msg"
		count := 0
		var sendMsg string
		for {
			select {
			case <-globalQuit:
				fmt.Println("Stop send message")
				return
			case <-time.Tick(500 * time.Millisecond):
				count++
				sendMsg = fmt.Sprintf("%s-%d", msg, count)
				fmt.Println("Send message is ", sendMsg)
				tss.Iter(func(w *worker) { w.Source <- sendMsg })
			}
		}
	}()

	// 截获退出信号
	c := make(chan os.Signal, 1)
	signal.Notify(c, syscall.SIGINT, syscall.SIGTERM)

	for sig := range c {
		switch sig {
		case syscall.SIGINT, syscall.SIGTERM: // 获取退出信号时，关闭globalQuit, 让所有监听者退出
			close(globalQuit)

			time.Sleep(1 * time.Second)
			return
		}
	}
}
