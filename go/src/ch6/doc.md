## 数组的声明

```go
// 数组的声明
var a [3]int //声明并初始化为默认零值
a[0] = 1

//声明同时初始化
b := [3]int{1, 2, 3}

//多维数组初始化
c := [2][2]int{{l, 2}, {3, 4}} 

// 与其他编程语言的差异
func TestTravelArray(t *testing.T) {
	a := [...]int{1, 2, 3, 4, 5}
	for idx /* 索引 */, elem/* 元素 */ := range a{
	    fmt.Println(idx, elem)	
    }
}
```

## 数组遍历

```go
// 与其他编程语言的差异
func TestTravelArray(t *testing.T) {
	a := [...]int{1, 2, 3, 4, 5}
	for idx /* 索引 */, elem/* 元素 */ := range a{
	    fmt.Println(idx, elem)	
    }
}
```

## 数组截取

```go
a [开始索引 (包含), 结束索引 (不包含)]
a := [...]int{1, 2, 3, 4, 5}
a[1:2] // 2
a[1:3] // 2,3
a[1:len(a)] // 2, 3, 4, 5
a[1:] // 2, 3, 4, 5
a[:3] // 1, 2, 3
```

## 切片

### 切片内部结构

![](../../img/7.png)

### 切片共享存储结构

![](../../img/8.png)

## 数组 vs 切片

1. 容量是否可伸缩
2. 是否可以进行比较

- [Show me the code](array_test.go)
- [Show me the code](slice_test.go)

