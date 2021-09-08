package com.yanliang.codebase.big_integer;

/**
 * 大数相减
 *
 * 题目描述
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的差。
 *
 * 注意：
 *
 * num1 和num2 都只会包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库
 *
 * 题目分析
 * 两个非负整数相减的结果可能为负。
 *
 * 因此，首先比较两个数的大小。
 *
 * 如代码所示，当小减大时，需将两个参数调换一下位置执行减法，在结果前填上负号即可
 *
 * 注意：结果为0时不加负号。
 *
 * @author yanliang
 */
public class Sub {

	public static void main(String[] args) {
		System.out.println(subString("121", "120"));
		System.out.println(subString("121", "121"));
		System.out.println(subString("121", "21"));
	}

	public static String subString(String a, String b) {
		if (cmp(a, b)) {
			return sub(a, b);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("-");
		sb.append(sub(b, a));
		return sb.toString();
	}

	public static String sub(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;
		int borrow = 0;
		StringBuilder sb = new StringBuilder();
		while (i >= 0 || j >= 0) {
			int m = i >= 0 ? a.charAt(i) - '0' : 0;
			int n = j >= 0 ? b.charAt(j) - '0' : 0;
			int res = (m - borrow - n + 10) % 10;
			sb.append((char) (res + '0'));
			borrow = (m - borrow - n) < 0 ? 1 : 0;
			i --;
			j --;
		}
		sb.reverse();
		int k;
		for (k = 0; k < sb.length() - 1; k ++) {
			if (sb.charAt(k) != '0') break;
		}
		return sb.substring(k);
	}


	/**
	 * 比较两数大小  a >= b ---> true
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean cmp(String a, String b) {
		if (a.length() == b.length()) return a.compareTo(b) >= 0;
		return a.length() > b.length();
	}
}
