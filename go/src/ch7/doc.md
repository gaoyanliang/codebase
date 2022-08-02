## Map

```go
// Map 声明
m := map[string]int{"one": 1, "two": 2, "three": 3}

m1 := map[string]int{}

m1["one"] = 1

m2 := make(map[string]int, 10 /* Initial Capacity */)
// 为什么不初始化 len？

// 对比 slice, len 所在的位置会初始化为零值，map 无法初始化
```

### Map 访问

在访问的 Key 不存在时，仍会返回零值，不能通过返回 nil 来判断元素是否存在

### Map 遍历

```go
m := map[string]int{"one": 1, "two": 2, "three": 3}

for k,v := range m {
	t.Log(k, v)
}
```

[Show me the code]()

