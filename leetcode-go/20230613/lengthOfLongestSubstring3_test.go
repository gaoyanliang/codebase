package _0230613

import "testing"

func lengthOfLongestSubstring(s string) int {

	if 0 == len(s) {
		return 0
	}

	left, right, res := 0, 0, 0
	var flag [256]bool

	for right < len(s) {
		if flag[s[right]] {
			flag[s[left]] = false
			left++
		} else {
			flag[s[right]] = true
			right++
		}

		if res < right-left {
			res = right - left
		}

		if left+res > len(s) || right >= len(s) {
			break
		}

	}

	return res

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
