package com.yanliang.algo.offer.array;

import java.util.Arrays;
import java.util.Scanner;

/** @author yanliang */
public class CoinChange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }
        int amount = scanner.nextInt();

        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (coins[i] > amount) continue;
            count += amount / coins[i];
            amount = amount % coins[i];
        }
        return amount > 0 ? -1 : count;
    }
}
