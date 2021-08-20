package com.yanliang.codebase.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yanliang
 * @date 2021/2/415:18
 */
public class Test {

	@Data
	@AllArgsConstructor
	public static class KK{
		private String k;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			KK that = (KK) o;
			return this.k.equals(that.k);
		}

		@Override
		public int hashCode() {
			return Objects.hash(k);
		}

	}

	public static void main(String[] args) throws InterruptedException {


		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					System.out.println("000000");
					break;
				}
			}
		}


//		System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));

		//System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//		System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
//		int compress = compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'});
//		int compress = compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c', 'd', 'd'});
//		System.out.println(compress);
	}

	public static void aaa(Object o) {
		System.out.println(o.getClass().getName());
		Class<?> aClass = null;
		try {
			aClass = Class.forName(o.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(aClass.getName());
	}



	public static int compress(char[] chars) {
		int j = 0, start = 0;
		int len = chars.length;
		for (int i = 0; i < len; i ++) {
			if (i + 1 == len || chars[i] != chars[i + 1]) {
				chars[j ++] = chars[i];
				if (i > start) {
					for (char ch: toChar(i - start + 1)) {
						chars[j ++] = ch;
					}
				}
				start = i + 1;
			}
		}
		return j;
	}

	public static char[] toChar(int n) {
		String sn = n + "";
		return sn.toCharArray();
	}



	public static String minWindow(String s, String t) {
		Map<Character, Integer> map = new HashMap<>();
		Map<Character, Integer> windows = new HashMap<>();

		for (int i = 0; i < t.length(); i ++) {
			addValue(map, t.charAt(i));
		}

		int l = 0, r = 0, len = s.length(), vaild = 0;
		int res = Integer.MAX_VALUE, start = 0;
		while (r < len) {
			char ch = s.charAt(r ++);
			if (map.containsKey(ch)) {
				addValue(windows, ch);
				if (getValue(map, ch) == getValue(windows, ch)) vaild ++;
			}

			while (vaild == map.size()) {
				if (r - l < res) {
					res = r - l;
					start = l;
				}
				char tmp = s.charAt(l ++);
				if (map.containsKey(tmp)) {
					subValue(windows, tmp);
					if (getValue(windows, tmp) < getValue(map, tmp)) vaild --;
				}
			}
		}
		return res == Integer.MAX_VALUE ? "" : s.substring(start, res + start);
	}





	public static int getValue(Map<Character, Integer> map, Character key) {
		return map.getOrDefault(key, 0);
	}

	public static void addValue(Map<Character, Integer> map, Character key) {
		map.put(key, map.getOrDefault(key, 0) + 1);
	}

	public static void subValue(Map<Character, Integer> map, Character key) {
		if (getValue(map, key) > 0) {
			map.put(key, map.get(key) - 1);
		}
	}









	static Random random = new Random();
	public static int[] topKFrequent(int[] nums, int k) {
		quickSort(nums, 0, nums.length - 1);

		Set<Integer> set = new HashSet<>();
		for (int i = nums.length - 1; i >= 0; i --) {
			if (set.size() < k) set.add(nums[i]);
		}
		int[] ans = new int[k];
		int j = 0;
		for (int i: set) {
			ans[j ++] = i;
		}
		return ans;
	}

	public static void quickSort(int[] nums, int l, int r) {
		if (l < r) {
			int index = partition(nums, l, r);
			quickSort(nums, l, index - 1);
			quickSort(nums, index + 1, r);
		}
	}

	public static int partition(int[] nums, int l, int r) {
		int index = random.nextInt(r - l) + l;
		swap(nums, r, index);
		int tmp = nums[r], j = l - 1;
		for (int i = l; i < r; i ++) {
			if (nums[i] < tmp) {
				swap(nums, i, ++ j);
			}
		}
		swap(nums, r, ++ j);
		return j;
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
