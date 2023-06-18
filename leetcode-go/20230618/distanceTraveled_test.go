package _0230618

import "testing"

func TestDistanceTraveled(t *testing.T) {

}

func distanceTraveled(mainTank int, additionalTank int) int {
	if mainTank < 5 {
		return mainTank * 10
	}

	ret := 0
	for mainTank >= 5 {
		ret += 5 * 10
		if additionalTank > 0 {
			mainTank += 1
			additionalTank -= 1
		}
		mainTank -= 5
	}

	if mainTank > 0 {
		ret += mainTank * 10
	}

	return ret

}
