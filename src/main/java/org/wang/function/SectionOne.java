package org.wang.function;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * LeetCode 的部分题目  （https://doocs.github.io/leetcode/#/solution/README）
 *
 * 考虑有2000多题，暂时搁置
 */
public class SectionOne {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public void fun1(int[] array,int target){
        Map<Integer,Integer> map = new HashMap<>(); // 值为 key ，下标为 value
        for(int index = 0; index < array.length ; index++){
            map.put(array[index],index);
        }
        int[] idxArray = null;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer complementaryNum = target - key;//互补的值
            Integer value = map.get(complementaryNum);
            if(value != null){
                idxArray = new int[]{entry.getValue(),value};
                break;
            }
        }
        if(idxArray == null){
            System.err.println("未找到合适数据");
        }else {
            System.err.println(JSON.toJSONString(idxArray));
        }
    }

    /**
     * 两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     *
     * [2  3  4]   [5 6 7]     432 + 756 = 1188   [1  1  8  8]      （想象一下加法  ，进位）
     */
    public void fun2(LinkedList<Integer> list1 , LinkedList<Integer> list2){
        LinkedList<Integer> sumList = new LinkedList<>();
        //以 长的链表作为主链表
        LinkedList<Integer> longList ,shortList;
        if(list1.size() > list2.size()){
            longList = list1;
            shortList = list2;
        }else {
            longList = list2;
            shortList = list1;
        }
        Integer lastCarry = 0 ;//上一进位
        int idx = 0;
        for(Integer nodeVal : longList){
            Integer sumNode = nodeVal ; //当前这一节点（这一位数） 的和
            if(idx < shortList.size()){
                sumNode += shortList.get(idx);
            }
            sumNode += lastCarry;  //把进位加上
            if(sumNode >= 10){
                sumNode = sumNode - 10;
                lastCarry = 1;
            }else {//进位清零
                lastCarry = 0;
            }
            sumList.add(sumNode);
            idx ++;
        }
        System.err.println(JSON.toJSONString(sumList));
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *  s = "abcdaabcbb"
     *  输出: 4
     *
     *  解法 ：滑动窗口 + 哈希表
     *
     *
     */
    public void fun3(String str){
        Map<Character,Integer> map = new LinkedHashMap<>(); //顺序存储
        Integer unquieLength = 0;
        for(int i = 0 ; i < str.length() ; i++){//遍历字符串
            Character character = str.charAt(i);
            if(map.containsKey(character)){//有重复值 ， 那第一次出现的重复值及之前的值 都不能再存在
                handleRemove(map,character);
            }
            map.put(character, 1); //无论是否重复，都将当前元素加上（ 重复的话 ，前面的已经被去掉了）
            unquieLength = Math.max(unquieLength,map.size());
        }
        System.err.println("最大不重复字符串长度 ："+ unquieLength );
    }

    /**
     * 移除 重复值及之前的元素
     * @param map
     * @param character
     */
    private void handleRemove(Map<Character, Integer> map, Character character) {
        int cursor = 0 ;
        Set<Character> characterSet = map.keySet();
        List<Character> toDel = new ArrayList<>();
        for(Character ele : characterSet){
            toDel.add(ele);
            if(character.equals(ele)){
                break;
            }
            cursor++;
        }
        for (Character ele1 : toDel) {
            map.remove(ele1);
        }
    }


    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     *输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     * 简单 或者 复杂 ，主要在于 优化 时间复杂度  ，这个可以暂时不实现
     */
    public void fun4(int[] numArray1 , int[] numArray2){

    }


    /**
     *
     * 给你一个字符串 s，找到 s 中最长的回文子串
     *
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"  (aba" 同样是符合题意的答案。)
     *
     *  解释一下 ，"回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
     *
     *  (动态规划  )
     */


    /**
     * 动态规划常常适用于有重叠子问题和最优子结构性质的问题，动态规划方法所耗时间往往远少于朴素解法。
     * 动态规划背后的基本思想非常简单。大致上，若要解一个给定问题，我们需要解其不同部分（即子问题），再根据子问题的解以得出原问题的解。
     * 通常许多子问题非常相似，为此动态规划法试图仅仅解决每个子问题一次，从而减少计算量：一旦某个给定子问题的解已经算出，则将其记忆化存储，以便下次需要同一个子问题解之时直接查表。这种做法在重复子问题的数目关于输入的规模呈指数增长时特别有用
     */
    public void fun5(){
        //难

    }

    /**
     *
     * 整数反转
     *
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
     *
     * 输入：x = 123
     * 输出：321
     */
    public void fun6(int num){
        String str = String.valueOf(Math.abs(num));
        List<Character> queue = new ArrayList<>();
        Integer length = str.length();
        for(int i= 0; i < length ; i++){
            queue.add(str.charAt(i));
        }
        Double reversal = 0D ;
        for(int j = 0 ; j< length ; j++) {
            Character character = queue.get(j);
            reversal = reversal + Integer.parseInt(String.valueOf(character)) * Math.pow(10,j);
        }
        System.err.println(reversal.intValue());
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     * @param num
     */
    public void fun7(int num){
        if(num <0 ){
            System.err.println("不是回文数");
            return;
        }
        String str = String.valueOf(num);
        if(str.endsWith("0")){
            System.err.println("不是回文数");
            return;
        }
        //判断是否对称
        Integer length = str.length();
        for(int i = 0 ; i < length / 2 ; i++){
            if(str.charAt(i) != str.charAt(length - i -1)){
                System.err.println("不是回文数");
                return;
            }
        }
        System.err.println("是回文数");
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器。
     *
     * 2个端点间的距离 * 2个端点间 较小的值  ； 取最大
     *
     *
     *
     * 5  1    4   2    4    8   9
     *
     *
     */
    public void fun8(int[] array){
        int length = array.length;
        int i = 0 ,j = length -1;
        int sum = 0 ;//总容量
        while (i < j){
            Integer total = (j - i) * Math.min(array[i],array[j]);
            sum = Math.max(total,sum);
            if(array[i] < array[j]){
                i++;
            }else {
                j--;
            }
        }
        System.err.println(sum);
    }

    /**
     *
     * 阿拉伯数字转化罗马数字
     *
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     *给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * 输入: 3
     * 输出: "III"
     */
    public void fun9(int number){
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(int num : nums){
            while(number > num){
                number -= num;
                sb.append(romans[i]).append(" ");
            }
            i++;
        }
        System.err.println("罗马数字 ：" +sb.toString());
    }


}
