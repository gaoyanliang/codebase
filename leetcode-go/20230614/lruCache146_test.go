package _0230614

import "testing"

type Node struct {
	Key, Value int
	Prev, Next *Node
}

type DoubleList struct {
	Head, Tail *Node
	Size       int
}

func NewDoubleList() *DoubleList {
	head := &Node{0, 0, nil, nil}
	tail := &Node{0, 0, nil, nil}
	head.Next = tail
	tail.Prev = head

	return &DoubleList{
		Head: head,
		Tail: tail,
		Size: 0,
	}
}

func (this *DoubleList) Add(node *Node) {
	node.Prev = this.Tail.Prev
	node.Next = this.Tail
	this.Tail.Prev.Next = node

	this.Tail.Prev = node

	this.Size++
}

func (this *DoubleList) Remove(node *Node) {
	node.Prev.Next = node.Next
	node.Next.Prev = node.Prev

	this.Size -= 1
}

func (this *DoubleList) RemoveFirst() *Node {
	if this.Head.Next == this.Tail {
		return nil
	}
	node := this.Head.Next
	this.Remove(node)
	return node
}

func (this *DoubleList) GetSize() int {
	return this.Size
}

type LRUCache struct {
	Cap   int
	Table map[int]*Node
	Cache *DoubleList
}

func Constructor(capacity int) LRUCache {

	return LRUCache{
		Cap:   capacity,
		Table: make(map[int]*Node),
		Cache: NewDoubleList(),
	}
}

func (this *LRUCache) Get(key int) int {
	if _, ok := this.Table[key]; !ok {
		return -1
	}

	this.Update(key)

	return this.Table[key].Value
}

func (this *LRUCache) Put(key int, value int) {
	if _, ok := this.Table[key]; ok {
		this.Cache.Remove(this.Table[key])
		delete(this.Table, key)
	}

	if this.Cache.GetSize() >= this.Cap {
		this.ClearOldData()
	}

	node := &Node{key, value, nil, nil}
	this.Cache.Add(node)
	this.Table[key] = node
}

func (this *LRUCache) Update(key int) {
	node := this.Table[key]

	this.Cache.Remove(node)
	this.Cache.Add(node)
}

func (this *LRUCache) ClearOldData() {
	node := this.Cache.RemoveFirst()
	delete(this.Table, node.Key)
}

func TestCache(t *testing.T) {
	lruCache := Constructor(2)

	lruCache.Put(1, 1)     // 缓存是 {1=1}
	lruCache.Put(2, 2)     // 缓存是 {1=1, 2=2}
	t.Log(lruCache.Get(1)) // 返回 1

	lruCache.Put(3, 3)     // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
	t.Log(lruCache.Get(2)) // 返回 -1 (未找到)

	lruCache.Put(4, 4) // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}

	t.Log(lruCache.Get(1)) // 返回 -1 (未找到)
	t.Log(lruCache.Get(3)) // 返回 3
	t.Log(lruCache.Get(4)) // 返回 4

}
