package com.yanliang.codebase.big_integer;

/**
 * 大数相减
 *
 * @author yanliang
 */
public class Sub {

	public static void main(String[] args) {
		System.out.println("1".compareTo("2"));
		System.out.println("10".compareTo("2"));
		System.out.println("2".compareTo("2"));
		System.out.println("3".compareTo("2"));
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
		while (i >= 0 && j >= 0) {
			int m = i >= 0 ? a.charAt(i) - '0' : 0;
			int n = j >= 0 ? b.charAt(j) - '0' : 0;
			int res = (m - borrow - n + 10) % 10;
			borrow = (m - borrow - n) > 0 ? 0 : 1;

		}
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
