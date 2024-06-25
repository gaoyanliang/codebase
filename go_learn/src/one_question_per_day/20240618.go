package main

import (
	"fmt"
	"strconv"
	"strings"
)

func discountPrices(sentence string, discount int) string {
	words := strings.Split(sentence, " ")
	f := float64(100-discount) / 100

	for i, word := range words {
		word = words[i]
		if len(word) > 1 && word[0] == '$' {
			price, err := strconv.Atoi(word[1:])
			if err == nil {
				words[i] = fmt.Sprintf("$%.2f", float64(price)*f)
			}

		}
	}
	return strings.Join(words, " ")
}

func main() {
	println(discountPrices("there are $1 $2 and 5$ candies in the shop", 50))
	println(discountPrices("1 2 $3 4 $5 $6 7 8$ $9 $10$", 100))
}
