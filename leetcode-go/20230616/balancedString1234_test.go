package _0230616

import (
	"math"
	"testing"
)

func TestBalancedString(t *testing.T) {
	t.Log(balancedString("QWER"))
	t.Log(balancedString("QQWE"))
	t.Log(balancedString("QQQW"))
	t.Log(balancedString("QQQQ"))
}

func balancedString(s string) int {
	qcount, wcount, ecount, rcount, left, p := 0, 0, 0, 0, 0, len(s)/4

	for _, ch := range s {
		if ch == 'Q' {
			qcount++
		}
		if ch == 'W' {
			wcount++
		}
		if ch == 'E' {
			ecount++
		}
		if ch == 'R' {
			rcount++
		}
	}

	if qcount == wcount && wcount == ecount && ecount == rcount {
		return 0
	}

	ret := math.MaxInt
	for right, ch := range s {
		if ch == 'Q' {
			qcount--
		}
		if ch == 'W' {
			wcount--
		}
		if ch == 'E' {
			ecount--
		}
		if ch == 'R' {
			rcount--
		}
		for qcount <= p && wcount <= p && ecount <= p && rcount <= p {
			ret = min(ret, right-left+1)
			if s[left] == 'Q' {
				qcount++
			}
			if s[left] == 'W' {
				wcount++
			}
			if s[left] == 'E' {
				ecount++
			}
			if s[left] == 'R' {
				rcount++
			}
			left++
		}
	}
	return ret
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
