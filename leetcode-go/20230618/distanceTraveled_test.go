package _0230618

import "testing"

func TestDistanceTraveled(t *testing.T) {

	t.Log(distanceTraveled1(5, 10)) // 60
	t.Log(distanceTraveled1(1, 2))  // 10

}

func distanceTraveled1(mainTank int, additionalTank int) int {
	if mainTank < 5 {
		return mainTank * 10
	}

	ret := 0
	for mainTank >= 5 {
		t := mainTank / 5
		mainTank = mainTank % 5
		ret += t * 10 * 5

		if additionalTank > 0 {
			mainTank += min(t, additionalTank)
			additionalTank -= min(t, additionalTank)
		}
	}

	if mainTank > 0 {
		ret += mainTank * 10
	}

	return ret
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
