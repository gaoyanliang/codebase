package com.yanliang.algo.nc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada
 * 链接：https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada 来源：牛客网
 *
 * <p>有三种葡萄，每种分别有\mathit a,b,ca,b,c颗。有三个人，第一个人只吃第\text 1,21,2种葡萄，第二个人只吃第\text 2,32,3种葡萄，第三个人只吃第\text
 * 1,31,3种葡萄。 适当安排三个人使得吃完所有的葡萄,并且且三个人中吃的最多的那个人吃得尽量少。
 *
 * <p>输入描述: 第一行数字\mathit TT，表示数据组数。 接下来\mathit TT行，每行三个数\mathit a,b,ca,b,c 1 \leq a,b,c \leq 10^{18}
 * , 1 \leq T \leq 101≤a,b,c≤10 18 ,1≤T≤10
 *
 * <p>输出描述: 对于每组数据，输出一行一个数字表示三个人中吃的最多的那个人吃的数量。 示例1 输入 2 1 2 3 1 2 6 输出 2 3 示例2 输入 1 12 13 11 输出 12
 *
 * @author yanliang
 */
public class EatGrapes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            Long[] nums = new Long[3];
            for (int i = 0; i < 3; i++) {
                nums[i] = sc.nextLong();
            }
            Arrays.sort(nums);
            System.out.println(soultion(nums[0], nums[1], nums[2]));
        }
    }

    public static long soultion(long a, long b, long c) {
        long sum = a + b + c;

        if (a + b > c) {
            return (sum + 2) / 3;
        }
        if (2 * (a + b) < c) {
            return (c + 1) / 2;
        }
        return (sum + 2) / 3;
    }
}
