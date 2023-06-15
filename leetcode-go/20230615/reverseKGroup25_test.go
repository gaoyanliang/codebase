package _0230615

import (
	"testing"
)

var tailN *ListNode = nil

type ListNode struct {
	Val  int
	Next *ListNode
}

func reverseKGroup(head *ListNode, k int) *ListNode {

	if head == nil || head.Next == nil {
		return head
	}

	headNode, tailNode := head, head
	for i := 0; i < k; i++ {
		if tailNode == nil {
			return head
		}
		tailNode = tailNode.Next
	}
	tmp := reverse(headNode, k)
	headNode.Next = reverseKGroup(tailNode, k)
	return tmp
}

// 反转指针
func reverse(head *ListNode, n int) *ListNode {
	if n == 1 {
		tailN = head.Next
		return head
	}

	pNode := reverse(head.Next, n-1)
	head.Next.Next = head
	head.Next = tailN

	return pNode
}

func TestReverseKGroup(t *testing.T) {
	head := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val: 3,
				Next: &ListNode{
					Val: 4,
					Next: &ListNode{
						Val: 5,
					},
				},
			},
		},
	}

	node := reverseKGroup(head, 2)
	t.Log(node)
}
