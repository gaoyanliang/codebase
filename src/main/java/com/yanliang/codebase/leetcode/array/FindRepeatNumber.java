package com.yanliang.codebase.leetcode.array;

/**
 * 数组去重
 * @author yanliang
 * @date 9/4/20203:30 PM
 */
public class FindRepeatNumber {

	public static void main(String[] args) {
		int[] nums = {0,0,0,0,0,0,1,1,2,2,3,3,4,4,5,5};
		findRepeatNumber(nums);

		for (int num : nums) {
			System.out.print(num + ", ");
		}

		// output: 0, 1, 2, 3, 4, 5, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5,
	}

	public static void findRepeatNumber(int[] nums) {
		int len = nums.length;
		int slow = 0, fast = 1;
		while (fast < len) {
			if (nums[slow] != nums[fast]) {
				slow ++;
				nums[slow] = nums[fast];
			}
			fast ++;
		}
	}
}

// 参考文章：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484478&idx=1&sn=685308e10c32ee5ad3508a5789633b3a&chksm=9bd7fa36aca07320ecbae4a53ed44ff6acc95c69027aa917f5e10b93dedca86119e81c7bad26&scene=21#wechat_redirect