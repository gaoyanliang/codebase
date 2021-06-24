package com.yanliang.codebase.string;

/**
 * @author yanliang
 */
public class Ip2Int {

	public static void main(String[] args) {
		System.out.println(ip2Int("10.0.3.255"));
		System.out.println(int2Ip(167773121));
	}

	/**
	 * ip 转 int
	 * @param ipString
	 * @return
	 */
	public static int ip2Int(String ipString) {
		String[] nums = ipString.split("\\.");
		int res = 0;
		for (int i = 0; i < 4; i ++) {
			res = res << 8 | Integer.valueOf(nums[i]);
		}
		return res;
	}

	/**
	 * int 转 ip
	 * @param ip
	 * @return
	 */
	public static String int2Ip(int ip) {
		int[] ipList = new int[4];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			ipList[i] = ip & 255;
			ip = ip >> 8;
		}
		sb.append(ipList[3]);
		for (int i = 2; i >= 0; i --) {
			sb.append(".");
			sb.append(ipList[i]);
		}
		return sb.toString();
	}
}
