package com.yanliang.codebase.big_integer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 大数运算
 * @author yanliang
 * @date 8/13/20205:42 PM
 */
public class BigInteger {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String num1 = scanner.nextLine();
		String num2 = scanner.nextLine();

		int[] ans = new int[num1.length() + num2.length()];

		int[] ns1 = new int[num1.length()];
		for (int i = 0, length = num1.length(); i < length; i++) {
			ns1[length - 1 - i] = num1.charAt(i) - '0';
		}

		int[] ns2 = new int[num2.length()];
		for (int i = 0, length = num2.length(); i < length; i++) {
			ns2[length - 1 - i] = num2.charAt(i) - '0';
		}

		for (int i = 0; i < ns1.length; i++) {
			for (int j = 0; j < ns2.length; j++) {
				ans[i + j] += ns1[i] * ns2[j];
			}
		}

		for (int i = 0; i < ans.length; i++) {
			if (ans[i] >= 10) {
				ans[i + 1] += ans[i] / 10;
				ans[i] %= 10;
			}
		}

		int index = ans.length - 1;
		while (index >= 0 && ans[index] == 0) {
			index--;
		}

		if (index < 0) {
			System.out.println(0);
		} else {
			for (int i = index; i >= 0; i--) {
				System.out.print(ans[i]);
			}
		}

		System.out.println();
	}
	public static void main1(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String num1 = handNum(scanner.nextLine());
		String num2 = handNum(scanner.nextLine());

		// 数字1是否负数
		boolean negative1 = false;
		if (num1.charAt(0) == '-') {
			negative1 = true;
			num1 = num1.substring(1);
		} else if (num1.charAt(0) == '+') {
			num1 = num1.substring(1);
		}

		// 数字2是否负数
		boolean negative2 = false;
		if (num2.charAt(0) == '-') {
			negative2 = true;
			num2 = num2.substring(1);
		} else if (num2.charAt(0) == '+') {
			num2 = num2.substring(1);
		}

		// 如果符号相反
		if (negative1 ^ negative2) {
			// 数字一为负数
			if (negative1) {
				subtract(num2, num1);
			} else {
				subtract(num1, num2);
			}
		} else {
			if (negative1) {
				System.out.print('-');
			}

			add(num1, num2);
		}
	}

	private static void subtract(String num1, String num2) {
		// 先判断大小
		if (num1.length() > num2.length()) {
			subtract(num1, num2, false);
		} else if (num1.length() < num2.length()) {
			subtract(num2, num1, true);
		} else {
			boolean negative = false;
			for (int i = 0, length = num1.length(); i < length; i++) {
				if (num1.charAt(i) > num2.charAt(i)) {
					break;
				} else if (num1.charAt(i) < num2.charAt(i)) {
					negative = true;
					break;
				}
			}
			if (negative) {
				subtract(num2, num1, negative);
			} else {
				subtract(num1, num2, negative);
			}
		}
	}

	/**
	 * 一定是大减小
	 *
	 * @param num1
	 * @param num2
	 * @param negative
	 */
	private static void subtract(String num1, String num2, boolean negative) {

		int[] ns1 = new int[num1.length()];
		for (int i = 0; i < num1.length(); i++) {
			ns1[i] = num1.charAt(i) - '0';
		}

		int[] ns2 = new int[num2.length()];
		for (int i = 0; i < num2.length(); i++) {
			ns2[i] = num2.charAt(i) - '0';
		}

		int i1 = ns1.length - 1;
		int i2 = ns2.length - 1;
		int[] ans = new int[ns1.length];

		int n1;
		int n2;
		while (i1 >= 0 || i2 >= 0) {
			n1 = ns1[i1];
			n2 = i2 < 0 ? 0 : ns2[i2];
			if (n1 < n2) {
				borrow(ns1, i1 - 1);
				n1 += 10;
			}

			ans[i1] = n1 - n2;

			i1--;
			i2--;
		}

		i1 = 0;
		while (ans[i1] == 0 && i1 < ans.length) {
			i1++;
		}

		if (i1 == ans.length) {
			System.out.print(0);
		} else {
			if (negative) {
				System.out.print('-');
			}

			for (int i = i1; i < ans.length; i++) {
				System.out.print(ans[i]);
			}
			System.out.println();
		}
	}

	private static void borrow(int[] ns, int index) {
		for (int i = index; i >= 0; i--) {
			if (ns[i] > 0) {
				ns[i] -= 1;
			} else {
				ns[i] = 9;
			}
		}
	}

	private static void add(String num1, String num2) {
		char[] ns1 = num1.toCharArray();
		char[] ns2 = num2.toCharArray();
		int ansLength = Math.max(num1.length(), num2.length());
		int[] ans = new int[ansLength + 1];

		int i1 = ns1.length - 1;
		int i2 = ns2.length - 1;
		int i3 = ans.length - 1;

		int n1;
		int n2;
		while (i1 >= 0 || i2 >= 0) {
			n1 = i1 < 0 ? 0 : ns1[i1] - '0';
			n2 = i2 < 0 ? 0 : ns2[i2] - '0';
			ans[i3] += n1 + n2;

			if (ans[i3] >= 10) {
				ans[i3] -= 10;
				ans[i3 - 1] = 1;
			}

			i1--;
			i2--;
			i3--;
		}

		if (ans[0] != 0) {
			System.out.print(ans[0]);
		}

		for (int i = 1; i < ans.length; i++) {
			System.out.print(ans[i]);
		}
		System.out.println();
	}

	private static String handNum(String s) {
		return s.substring(1, s.length() - 1);
	}

}
