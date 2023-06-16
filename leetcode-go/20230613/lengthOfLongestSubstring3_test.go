package _0230613

import "testing"

func lengthOfLongestSubstring(s string) (ans int) {

	if 0 == len(s) {
		return
	}

	left := 0
	flag := [256]bool{}

	for i := 0; i < len(s); i++ {
		for flag[s[i]] {
			flag[s[left]] = false
			left++
		}
		flag[s[i]] = true

		ans = max(ans, i-left+1)
	}
	return
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func TestLengthOfLongestSubstring(t *testing.T) {

	var (
		s1 = "abcabcbb"
		s2 = "bbbbb"
		s3 = "pwwkew"
	)

	t.Log(lengthOfLongestSubstring(s1))
	t.Log(lengthOfLongestSubstring(s2))
	t.Log(lengthOfLongestSubstring(s3))

}
