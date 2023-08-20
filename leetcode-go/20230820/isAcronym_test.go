package _0230820

import (
	"fmt"
	"strings"
	"testing"
)

// 7004. 判别首字母缩略词 https://leetcode.cn/contest/weekly-contest-359/problems/check-if-a-string-is-an-acronym-of-words/
// ["alice","bob","charlie"]
// "abc"
// ["an","apple"]
// "a"
// ["never","gonna","give","up","on","you"]
// "ngguoy"
func isAcronym(words []string, s string) bool {
	if len(words) != len(s) {
		return false
	}

	var build strings.Builder
	for i := 0; i < len(words); i++ {
		build.WriteString(words[i][:1])
	}
	var ret = build.String()

	return ret == s
}

func TestIsAcronym(t *testing.T) {
	//var words = []string{"alice", "bob", "charlie"}
	//var s = "abc"
	//var words = []string{"an", "apple"}
	//var s = "a"
	var words = []string{"never", "gonna", "give", "up", "on", "you"}
	var s = "ngguoy"

	fmt.Println(isAcronym(words, s))
}
