package com.yanliang.algo.big_integer;

import java.util.Scanner;

/**
 * 大数除法
 *
 * 参考： https://cloud.tencent.com/developer/article/1818199
 * @author yanliang
 */
public class Divide {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(divide(sc.next(), sc.next()));
        }
    }

    private static final String ZERO = "0";

    // num1(被除数) / num2(除数)
    public static String divide(String num1, String num2) {
        // 除数不能为 0
        if (ZERO.equals(num2)) {
            return ZERO;
        }

        // 被除数 小于 除数时
        if (judge(num1, num2)) {
            System.out.println("商 = 0, 余数 = " + num1);
            return ZERO;
        }

        String value = "0";//结果
        while (!judge(num1,num2))
        {
            StringBuilder sbTeam = new StringBuilder(num2);//用这个往后面不断加0 和 num 做减法
            StringBuilder sbCount = new StringBuilder("1");//次数 可能很大

            int subLen=num1.length() - num2.length();//统计大概要加几个零(保持除数和被除数个数长度相同)
            for(int i=0;i<subLen;i++)
            {
                sbTeam.append('0');
                sbCount.append('0');
            }

            //如果0 加多了 那么要删一个 类似"12300" / "23" "12300"比"23000"小
            //所以要 "2300" 对应比"23"扩大 "100"倍数，每减一次"2300" 则结果加"100"
            if(judge(num1, sbTeam.toString()))
            {
                sbTeam = sbTeam.deleteCharAt(sbTeam.length()-1);
                sbCount = sbCount.deleteCharAt(sbCount.length()-1);
            }

            // 一直能减的时候
            while (!judge(num1,sbTeam.toString()))
            {
                num1 = Sub.subString(num1, sbTeam.toString());
                value = Add.add(sbCount.toString(), value);
            }
        }
        return  value;
    }

    /**
     * 判断被除数是否小于除数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean judge(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return true;
        } else if (num1.length() == num2.length()) {
            for (int i = 0; i < num1.length(); i ++) {
                if (num1.charAt(i) < num2.charAt(i)) return true;
            }
        }
        return false;
    }
}
