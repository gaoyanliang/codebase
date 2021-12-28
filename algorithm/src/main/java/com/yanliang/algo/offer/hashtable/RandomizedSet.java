package com.yanliang.algo.offer.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 380. O(1) 时间插入、删除和获取随机元素 https://leetcode-cn.com/problems/FortPu/
 *
 * <p>https://www.cnblogs.com/labuladong/p/13975110.html
 * https://blog.csdn.net/kangbin825/article/details/120896942
 *
 * <p>实现RandomizedSet 类：
 *
 * <p>RandomizedSet() 初始化 RandomizedSet 对象 bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true
 * ；否则，返回 false 。 bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。 int getRandom()
 * 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。 示例
 * :
 *
 * <p>输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert",
 * "getRandom"] [[], [1], [2], [2], [], [1], [2], []] 输出: [null, true, false, true, 2, true, false,
 * 2] 解释: RandomizedSet randomSet = new RandomizedSet(); // 初始化一个空的集合 randomSet.insert(1); // 向集合中插入
 * 1 ， 返回 true 表示 1 被成功地插入
 *
 * <p>randomSet.remove(2); // 返回 false，表示集合中不存在 2
 *
 * <p>randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 *
 * <p>randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 *
 * <p>randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 *
 * <p>randomSet.insert(2); // 2 已在集合中，所以返回 false
 *
 * <p>randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 *
 * <p>提示：
 *
 * <p>-231 <= val <= 231 - 1 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用 当调用 getRandom
 * 方法时，集合中至少有一个元素
 *
 * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RandomizedSet {

    Map<Integer /* value */, Integer /* index */> table;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        this.table = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * 新插入数据
     *
     * @param val
     * @return
     */
    public boolean insert(int val) {
        if (table.containsKey(val)) {
            return false;
        }
        list.add(val);
        table.put(val, list.size() - 1);
        return true;
    }

    /**
     * 删除数据时，将待删除数据将数组最后一个元素交换，然后删除最后一个元素
     *
     * @param val
     * @return
     */
    public boolean remove(int val) {
        if (!table.containsKey(val)) {
            return false;
        }
        int index = table.get(val);
        int lastVal = list.get(list.size() - 1);

        list.set(index, lastVal);
        table.put(lastVal, index);

        list.remove(list.size() - 1);
        table.remove(val);

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
