package org.wang.function;

import com.alibaba.fastjson.JSON;
import org.wang.entity.MyLinkNode;
import org.wang.entity.MyQueue;
import org.wang.entity.MyTreeNode;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 这一章节及后面几个章节 ，都会摘取 《剑指offer》 的部分题目进行 编写
 *
 * 算法中常常出现的几种数据结构 ： 数组 、 字符串 、 链表 、 树 、 栈 、队列
 */
public class SectionTwo {

    /**
     * 找出数组中的重复数字
     *
     * 在长度为n 的 数组内 ，所有数字都是 0 ~ n-1 之间
     */
    public void fun1(int[] array){
        /**
         * 我的思路 ： 第一反应 是 利用哈希表 ，具体代码就不写了 (这个不是最优的 一个主要原因是 ，空间复杂度略高)
         *
         * 还有一种思路  ，利用数字下标  （前提就是 数字都小于 数组长度）
         *
         * [2 ,3 ,2 ,5 ,7 ，4 ，3 ，1, 5]  ，  比如 第一个数字 是 2  ,那他 应该在 array[2] 这个下标位置 ，如果 调换下标位置时 ，发现 该下标下已经是正确的值 ，那就说明有重复的
         *
         * 用代码实现下
         */
        for(int i = 0 ; i< array.length ; i++){
            int prefix = i ;

            if(array[prefix] == i){//在自己位置上
                continue;
            }
            do{
                if(array[array[prefix]] == array[prefix]){
                    System.err.println("有重复数字："+ array[prefix]);
                    break;
                }else {
                    int tmp = array[array[i]];
                    array[array[i]] = array[i];
                    array[i] = tmp;
                }
            }while (array[prefix] != prefix);
        }
    }


    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列
     * 都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
     * 个整数，判断数组中是否含有该整数。
     *
     *
     * 用二分法 实现
     */
    public void fun2(int[][] array , int num){
        if(array == null || array.length <1 ){
            return;
        }
        int length = array.length; //长
        int width = array[0].length;//宽 ，每一行都是 等宽的
        int existflag = 0; // 1存在 ，-1 不存在 ，0 还在判断中
        //我自己的思路 ； 由于2分 ，又是二维数组 ，有点绕 ；所以 先确定行 ，再确定列 (行其实 会 锁定 2行 )
        //检讨一下，自己写的解法真的很垃圾 （时间复杂度其实也还行，但代码难懂）

        int middle = length / 2 ,up = 0 , down = length - 1;
        int validRow1 = -1 ,validRow2 = -1;//目标行 （正常是2行，如果是1行，那 num 就等于该行第一列 的值）
        while(true){
            if(num == array[middle][0]){
                validRow1 = validRow2 = middle;
                existflag = 1;
                break;
            }
            if(num < array[up][0] || num > array[down][width-1]){
                existflag = -1 ;
                break;
            }
            if(num < array[middle][0] ){
                 if(num > array[middle - 1][0]){ // 目标锁定在  middle -1 , middle 这2行
                     validRow1 = middle - 1;
                     validRow2 = middle;
                     break;
                 }
                 down = middle;
                 middle = (middle + up) / 2 ;
            }else {
                if(num <= array[middle + 1][width -1]){
                    validRow1 = middle;
                    validRow2 = middle + 1;
                    break;
                }
                up = middle;
                middle = (middle + down ) / 2;
            }
        }

        if(existflag == 1){
            System.err.println("存在: 所在行 ：" + (validRow1 + 1) );
            return;
        }
        if(existflag == -1){
            System.err.println("不存在");
            return;
        }
        System.err.println("目标锁定在  " + (validRow1 + 1) + " ~  " + (validRow2 + 1) + " 这2行，进一步查找");
        if(exist(array[validRow1],num)){
            System.err.println("存在,所在行 ：" + (validRow1 + 1));
            return;
        }
        if (exist(array[validRow2],num)){
            System.err.println("存在,所在行 ：" + (validRow2 + 1));
            return;
        }
        System.err.println("不存在");
    }

    /**
     * 一行只中，再使用二分法
     * @param row
     * @param num
     * @return
     */
    boolean exist(int row[] , int num){
        int middle = row.length / 2;
        int left = 0 , right = row.length - 1;
        while (true){
            if(num < row[0] || num > row[right]){
                return false;
            }
            if(row[middle] == num){
                return true;
            }
            if(num < row[middle] ){
                right = middle;
                middle = (right + left ) / 2;
            }else {
                left = middle;
                middle =  (right + middle ) / 2;
            }
            if(middle == left){//只剩2个了
                if(row[middle] == num || row[right] == num){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * 从尾到头 打印链表
     *
     * 输入一个链表，从尾到头打印链表每个节点的值。
     *
     *  从尾到头 ，可以 用栈
     * （由于没有现成使用的链表 ，所以我自己实现一个链表）
     */
    public void fun3(MyLinkNode<Integer> linkNode){
        Stack<Integer> stack = new Stack<>();
        while (linkNode != null){
            stack.add(linkNode.val());
            linkNode = linkNode.getNext();
        }
        while (!stack.isEmpty()){
            System.err.println(stack.pop());
        }
    }

    /**
     *
     * 二叉树的 前、中、后 3种顺序的遍历 (所谓的前中后 ，指的是 根节点的遍历顺序 ，前序 先遍历根节点 ，中序 先左再根后右  ，后序  先左再右后根)
     *
     * 每种遍历 都有 递归和循环 2种方式 (递归比循环要好实现)
     *
     * (这里的树节点 ，由于没有现成的，自己实现)
     */
    public void fun4(MyTreeNode rootNode){
        beforeIterator(rootNode);
        middleIterator(rootNode);
        afterIterator(rootNode);
    }
    //前序遍历
    private void beforeIterator(MyTreeNode rootNode) {
        List<Integer> list = new ArrayList<>();
        beforeRecursion(rootNode,list);
        System.err.println("前序递归遍历的结果 ：" + JSON.toJSONString(list));
    }


    //前序递归
    private void beforeRecursion(MyTreeNode node,List<Integer> list){
        if(node == null){
            return;
        }
        list.add(node.val());
        beforeRecursion(node.getLeft(),list);
        beforeRecursion(node.getRight(),list);
    }



    //中序遍历
    private void middleIterator(MyTreeNode rootNode){
        List<Integer> list = new ArrayList<>();
        middleRecursion(rootNode,list);
        System.err.println("中序遍历的结果 ：" + JSON.toJSONString(list));
    }

    //中序递归
    private void middleRecursion(MyTreeNode node, List<Integer> list) {
        if(node == null){
            return;
        }
        middleRecursion(node.getLeft(),list);
        list.add(node.val());
        middleRecursion(node.getRight(),list);
    }

    //后序遍历
    private void afterIterator(MyTreeNode rootNode){
        List<Integer> list = new ArrayList<>();
        afterRecursion(rootNode,list);
        System.err.println("后序遍历的结果 ：" + JSON.toJSONString(list));
    }

    //后序递归
    private void afterRecursion(MyTreeNode node, List<Integer> list) {
        if(node == null){
            return;
        }
        afterRecursion(node.getLeft(),list);
        afterRecursion(node.getRight(),list);
        list.add(node.val());
    }

    /**
     * 用2个栈 ，实现一个队列
     */
    public void fun5() throws Exception {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enterQue(2);
        myQueue.enterQue(3);
        myQueue.enterQue(5);
        myQueue.enterQue(10);
        myQueue.enterQue(12);
        while (!myQueue.isEmpty()){
           Integer head = myQueue.leaveQue();
           System.err.println(head);
        }
    }

    /**
     * 输出斐波那契数列的第 n 项
     * 题目描述：现在要求输入一个整数 n，请你输出斐波那契数列的第 n 项。
     *
     *  f(0) = 0
     *  f(1) = 1
     *  f(n) =  f(n-1) + f(n-2)
     */
    public int fun6(int n){
        int fN = 0 , fN2 = 0 , fN1 = 1;
        if(n == 0){
            System.err.println("N为 ：" + n + " ; f(N)为 ：" + 0);
            return 0;
        }
        if(n == 1){
            System.err.println("N为 ：" + n + " ; f(N)为 ：" + 1);
            return 1;
        }
        for(int i = 0 ; i <= n-2 ; i++){
            fN = fN1 + fN2;
            fN2 = fN1;
            fN1 = fN;
        }
        System.err.println("N为 ：" + n + " ; f(N)为 ：" + fN);
        return fN;
    }

    /**
     * 青蛙跳台阶(1 或 2 级)
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
     *
     * 也是利用斐波那契数列 ，但是 怎么利用呢？
     * 只有一级台阶时 ，只有一种跳法  ，有2节台阶时 ，有 2种跳法 ， 有3节台阶时 ，可以在2节台阶的情况下 ，再跳1节 ，也可以在 1节台阶的情况下 ，直接跳2节 （不要想着 1节台阶的情况下，再跳2次1台阶，这个和 第一种是重合的）
     * 所以其实 这个也是  f(n) = f(n-1) + f(n-2)
     * @return
     */
    public int fun7(int n){
        int fN = 0 , fN1 = 2 , fN2 = 1;
        if(n == 1){
            System.err.println("N为 ：" + n + " ; 解法有 ：" + 1);
            return 1;
        }
        if(n == 2){
            System.err.println("N节台阶 ：" + n + " ; 解法有 ：" + 2);
            return 2;
        }
        for(int i= 0 ;i <= n-2 ;i++){
            fN = fN1 + fN2;
            fN2 = fN1;
            fN1 = fN;
        }
        System.err.println("N节台阶 ：" + n + " ; 解法有 ：" + fN);
        return fN;
    }

    /**
     *
     * 有序数组，判断一个数 是否存在于该数组中
     *
     * 题目很简单 ，主要是想 专门写个二分法
     *
     */
    public void fun8(int[] sortArray,int num){
        int length = sortArray.length;
        if(num < sortArray[0] || num > sortArray[length-1]){
            System.err.println("不存在");
            return;
        }
        boolean flag = false;
        int left = 0 , right = length -1;
        do{
            int middle = (left + right) / 2;
            if(num ==  sortArray[middle]){
                flag = true;
                break;
            }
            if(sortArray[middle] > num){//锁定到前半段
                right = middle;
            }
            if(sortArray[middle] < num){//锁定到后半段
                left = middle;
            }
            if( (right - left) == 1){//范围锁定在两个数之间
                if(num == sortArray[left] || num == sortArray[right]){
                    flag = true;
                }
                break;
            }
        }while (left < right);

        if(flag){
            System.err.println("存在");
        }else {
            System.err.println("不存在");
        }
    }

    /**
     * 回溯法 ：适合于 多个步骤，每个步骤有多个选项的情况 ，可以很形象的 用树节点来表示 ，适合用 递归 来实现代码
     *
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向
     * 左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子
     */
    public void fun9(){
        //大概有了思路 ， 一步一步测试 ，前后左右都试一遍 ，而且如果可以 ，会递归到下一个
        //难 ，待完成
    }


    /**
     * 动态规划  ：一般求最优解， 而一个问题可以分解成多个小问题 ，而且分解后的小问题也有最优解 ，把所有最优解组合起来可以得到整个问题的最优解 。
     *
     * 在使用中，常常从解决最小问题开始，并将已经解决的子问题的最优解存储下来 （常放在 一维或二维数组中），并将子问题的最优解组合起来逐步解决更大的问题。
     *
     * 给你一个长度为 n的 绳子 ，请把绳子剪成 m段 （m , n 都是整数）， 每段绳子长度为 k[0] ,k[1] 。。。。，k[m] ，如何让这些绳子的乘积最大 ？
     *
     */
    public void fun10(int n , int m){
            //当剪第一刀时 ，第一段的长度 有 n - 1 种可能 ，
            // f(n) = max （f(i) * f(n-i)）  ； 以这个思路去剪m 刀。

        //难，待完成

    }

    /**
     * 贪心算法  ：在使用贪心算法解决问题的 时候 ，每一步都可以做出一个贪婪的选择 ，基于这个选择，能够得到确定的最优解
     */
    public void fun11(){
        //难 ，待完成
    }


    /**
     * 位运算
     *
     * 作为知识的再次巩固吧
     */
    public void fun12() {
        /**
         *
         *  与 &     ： 0&0 = 0   0&1 = 0    1&0 = 0     1&1 = 1
         *  或 |     ： 0|0 = 0   0|1 = 1    1|0 = 1     1|1 = 1
         *  异或 ^   :  0^0 = 0   0^1 = 1    1^0 = 1     1^1 = 0
         *
         *  左移运算  m<<n :  最左边的n位 被丢弃，最右边的补n个0
         *
         *  右移运算 m>>n  :  最右边的n位将被丢弃 ，如果是无符号数，那最左边n位都补0 ; 如果是有符号数，则用数字的 符合位去填补最左边的n位。
         *
         *
         *  原码 ： 第一位是 符号 （正或负 ，1为负 ，0为正 ，有点奇怪不 ） [ 11111111  ，01111111  ]  ( [-127,127] )
         *
         *  反码 ： 正数的反码是其本身 ，负数的反码，符号位不变，其他位取反  -1的原码是[1000_0001],其反码是[1111_1110]
         *
         *  补码 ： 正数的补码是其本身， 负数的补码 是在反码的基础上 加 1 ， 如 -1的补码 [1111_1111]
         */


        /**
         * 一些实用案例 ： 判断一个数 是不是 2的整数次方 ，   如果是2的整数次方 ，那二进制中 只有一个 1 ，其他位都是0 ，如果 该数减1再和自己做与运算，唯一的1也会变成0
         */

    }

    /**
     * 这里简单聊2句 ，上面一些题还是偏概念性多点 ，  排序 、贪婪算法、动态规划 、以及回溯算法 都没有实际应用  ，后面的章节模块 会对这几个点进行 专门介绍
     */
}
