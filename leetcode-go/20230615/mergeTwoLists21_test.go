package _0230615

import "testing"

//type ListNode struct {
//	Val  int
//	Next *ListNode
//}

func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	dummy := &ListNode{Val: -1}
	tmp := dummy

	for list1 != nil && list2 != nil {
		if list1.Val <= list2.Val {
			tmp.Next = list1
			list1 = list1.Next
		} else {
			tmp.Next = list2
			list2 = list2.Next
		}
		tmp = tmp.Next
	}
	if list1 != nil {
		tmp.Next = list1
	}
	if list2 != nil {
		tmp.Next = list2
	}
	return dummy.Next
}

func TestMergeTwoLists(t *testing.T) {
	list1 := &ListNode{1, &ListNode{2, &ListNode{Val: 3}}}
	list2 := &ListNode{1, &ListNode{3, &ListNode{Val: 4}}}
	tmp := mergeTwoLists(list1, list2)
	t.Logf("%v", tmp)
}
