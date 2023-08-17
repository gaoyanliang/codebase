package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"strings"
	"time"
)

// 猜谜游戏
// example
//Please input your guess
//10
//You guess is 10
//Your guess is smaller than the secret number. Please try again
//50
//You guess is 50
//Your guess is bigger than the secret number. Please try again
//30
//You guess is 30
//Your guess is bigger than the secret number. Please try again
//20
//You guess is 20
//Your guess is smaller than the secret number. Please try again
//25
//You guess is 25
//Your guess is bigger than the secret number. Please try again
//22
//You guess is 22
//Your guess is smaller than the secret number. Please try again
//23
//You guess is 23
//Your guess is smaller than the secret number. Please try again
//24
//You guess is 24
//Correct, you Legend!

func main() {
	maxNum := 100
	rand.Seed(time.Now().UnixNano())
	secretNumber := rand.Intn(maxNum)
	// fmt.Println("The secret number is ", secretNumber)

	fmt.Println("Please input your guess")
	reader := bufio.NewReader(os.Stdin)
	for {
		input, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("An error occured while reading input. Please try again", err)
			continue
		}
		input = strings.Trim(input, "\r\n")

		guess, err := strconv.Atoi(input)
		if err != nil {
			fmt.Println("Invalid input. Please enter an integer value")
			continue
		}
		fmt.Println("You guess is", guess)
		if guess > secretNumber {
			fmt.Println("Your guess is bigger than the secret number. Please try again")
		} else if guess < secretNumber {
			fmt.Println("Your guess is smaller than the secret number. Please try again")
		} else {
			fmt.Println("Correct, you Legend!")
			break
		}
	}
}
