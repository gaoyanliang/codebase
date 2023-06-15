package _0230615

type TreeNode struct {
	Val         int
	Left, Right *TreeNode
}

func levelOrder(root *TreeNode) [][]int {
	ret := [][]int{}
	if root == nil {
		return ret
	}

	q := []*TreeNode{root}

	for i := 0; len(q) > 0; i++ {
		ret = append(ret, []int{})
		tmpq := []*TreeNode{}
		length := len(q)
		for j := 0; j < length; j++ {
			node := q[j]
			ret[i] = append(ret[i], node.Val)
			if node.Left != nil {
				tmpq = append(tmpq, node.Left)
			}
			if node.Right != nil {
				tmpq = append(tmpq, node.Right)
			}
		}
		q = tmpq
	}
	return ret
}
