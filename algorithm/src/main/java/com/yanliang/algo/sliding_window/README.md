## 模板

滑动窗口模板: https://labuladong.gitee.io/algo/1/12/

```java
public void sliding_window(String s, String t){
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();
    
    for (int i = 0; i < t.length(); i ++) {
        char ch = t.charAt(i);
        need.putIfAbsent(ch, 0);
        need.put(ch, need.get(ch) + 1);
    }
    
    int left = 0, right = 0;
    int valid = 0;
    while (right < s.length()) {
        // ch 是将要放入窗口的字符
        char ch = s.charAt(right);
        // 右移窗口
        right ++;
        // 进行窗口内数据的一系列操作...

        /*** debug 输出的位置 ***/
        System.out.println("window: [ "+ left + "," + right + " ]");
        /********************/

        // 判断左窗口是否要收缩
        while (true /** window need shrink */) {
            // d 是将要移出窗口的字符
            char d = s.charAt(left);
            // 左移窗口
            left ++;
            // 进行窗口内数据的一系列操作...
        }
    }
}
```


## 相关题目

- [76.最小覆盖子串（困难）](https://leetcode-cn.com/problems/minimum-window-substring/)
- [code](./MinimumWindowSubstringLC76.java)

![](img/01.png)

- [567.字符串的排列（中等）](https://leetcode-cn.com/problems/permutation-in-string/)
- [code](./PermutationInStringLC567.java)

![](img/02.png)

- [438.找到字符串中所有字母异位词（中等）](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)
- [code](./FindAllAnagramsInAStringLC438.java)

![](img/03.png)

[3.无重复字符的最长子串（中等）](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
- [code](LongestSubstringWithoutRepeatingCharactersLC3.java)

![](img/04.png)

[面试题 17.18. 最短超串](https://leetcode.cn/problems/shortest-supersequence-lcci/)
- [code](ShortestSupersequenceLcci.java)

![](img/05.png)


- [187. 重复的DNA序列](https://leetcode.cn/problems/repeated-dna-sequences/)
- [code](./RepeatedDnaSequencesLC187.java)

![](img/06.png)

- [424. 替换后的最长重复字符](https://leetcode.cn/problems/longest-repeating-character-replacement/)
- [code](./LongestRepeatingCharacterReplacementLC424.java)

![](img/07.png)






