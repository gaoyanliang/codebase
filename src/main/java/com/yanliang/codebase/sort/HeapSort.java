package com.yanliang.codebase.sort;

/**
 * 堆排序 https://blog.csdn.net/weixin_45480785/article/details/116198638
 * @author yanliang
 */
public class HeapSort {


	public static void main(String[] args) {

		int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
		System.out.println("排序前:  ");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		heapSort(arr);
		System.out.println();
		System.out.println("排序后:  ");
		for (int i : arr) {
			System.out.print(i + " ");
		}

		int[] arr1 = {16, 7, 3, 20, 17, 8};
		System.out.println("\n 排序前:  ");
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		heapSort(arr1);
		System.out.println();
		System.out.println("排序后:  ");
		for (int i : arr1) {
			System.out.print(i + " ");
		}

	}


	public static void heapSort(int[] nums) {
		//创建堆
		// (arr.length - 1) / 2 第一次非叶子节点
		for (int i = nums.length / 2 - 1; i >= 0; i--) {
			//从第一个非叶子结点从下至上，由右至左
			adjustHeap(nums, i, nums.length);
		}

		//调整堆结构+交换堆顶元素与末尾元素
		for (int i = nums.length - 1; i > 0; i--) {
			//将堆顶元素与末尾元素进行交换
			int tmp = nums[i];
			nums[i] = nums[0];
			nums[0] = tmp;

			//重新对堆进行调整
			adjustHeap(nums, 0, i);
		}

	}

	/**
	 * 调整堆
	 * 如果是大顶堆（升序） nums[rChild] > nums[lChild] && tmp >= nums[lChild]
	 * 如果是小顶堆（降序） nums[rChild] < nums[lChild] && tmp <= nums[lChild]
	 * @param nums
	 * @param parent
	 * @param len
	 */
	public static void adjustHeap(int[] nums, int parent, int len) {
		// 备份父节点
		int tmp = nums[parent];
		// 左孩子
		int lChild = 2 * parent + 1;
		while (lChild < len) {
			// 右孩子
			int rChild = lChild + 1;
			// 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
			if (rChild < len && nums[rChild] < nums[lChild]) {
				lChild ++;
			}

			// 如果父结点的值已经大于孩子结点的值，则直接结束
			if (tmp <= nums[lChild]) {
				break;
			}

			// 把孩子结点的值赋给父结点
			nums[parent] = nums[lChild];
			//选取孩子结点的左孩子结点,继续向下筛选
			parent = lChild;
			lChild = 2 * parent + 1;
		}
		//将父节点赋值给孩子节点
		nums[parent] = tmp;
	}
}
