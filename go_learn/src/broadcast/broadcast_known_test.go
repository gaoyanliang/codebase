package broadcast

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"testing"
	"time"
)

// 用 golang 实现广播功能
// 已知监听者数量
// 参考： http://www.weicot.com/%E4%BD%BF%E7%94%A8golang%E7%9A%84channel%E5%81%9A%E5%B9%BF%E6%92%AD/

type worker struct {
	Name   string
	Source chan interface{}
	quit   chan struct{}
}

func (w *worker) Start() {
	w.Source = make(chan interface{})
	go func() {
		for {
			select {
			case msg := <-w.Source:
				fmt.Println(fmt.Sprintf("=====> %s 监听到数据： %s", w.Name, msg))
			case <-w.quit:
				fmt.Println(w.Name, " quit!")
				return
			}
		}
	}()
}

func TestBroadcastKnown(t *testing.T) {

	// 全局的 quit 信号 channel
	globalQuit := make(chan struct{})

	// 定义两个 worker
	workers := []*worker{
		&worker{
			Name: "worker1",
			quit: globalQuit,
		},
		&worker{
			Name: "worker2",
			quit: globalQuit,
		}}
	for _, w := range workers {
		w.Start()
	}

	// 派发消息
	go func() {
		msg := "test msg"
		count := 0
		var sendMsg string
		for {
			select {
			case <-globalQuit:
				fmt.Println("=====> Stop send message")
				return
			case <-time.Tick(500 * time.Millisecond):
				count++
				sendMsg = fmt.Sprintf("%s-%d", msg, count)
				fmt.Println("=====> Send message is ", sendMsg)
				for _, wk := range workers {
					wk.Source <- sendMsg
				}
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
