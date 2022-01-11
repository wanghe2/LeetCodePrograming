package org.wang.function;

import com.alibaba.fastjson.JSON;
import org.wang.entity.MyLinkNode;

import java.util.Stack;

/**
 * 一些的算法题
 */
public class SectionThree {
    /**
     * 输入一个整数，输出该数二进制表示中 1 的个数。其中负数用补码表示。
     */
    public void fun1(int n){
        System.err.print("数字 n :" + n );
        //这里其实利用的是一个思路 ， 看最右侧那位是不是 1， 然后不断的右移 ；如何判断最后一位是不是 1呢 ，与1 相与  ,下面的写法，缺陷是 ，如果是负数 ，可能形成死循环
        int countNum = 0 ;
        while (n != 0){
            int last = n & 1;
            if(last == 1){
                countNum ++;
            }
            n = n >> 1;
        }
        System.err.println(" ； 二进制中 1的个数 ："+ countNum);
    }

    /**
     * O(1)时间删除链表节点
     * 单向链表  ， 由于 是单向链表 ，直接让 删除节点的上一个节点指向该删除节点的下一个节点，这种方式 已经做不到了
     * 现在的思路就是 ，让属性改变 ， 节点要删除了 ，设想下 ，那正确的节点的值就该成为了 下个节点的值 ，指向的 就该是 下下个节点 (相当于 把 next给遗弃了，但属性拷贝给了要删除的节点)
     */
    public void fun2(MyLinkNode<Integer> head,MyLinkNode<Integer> specialNode){
        if(specialNode == head){
            head = null;
            return;
        }
        if(specialNode.getNext() == null){//要删除的是最后一个节点 ，这时需要让他的上一个节点指向null ,这个需要做下处理，要遍历
            MyLinkNode<Integer> iteratorNode = head;
            while(iteratorNode.getNext() != specialNode){
                iteratorNode = iteratorNode.getNext();
            }
            iteratorNode.getNext().setNext(null);
        }
        specialNode.setVal(specialNode.getNext().val());
        specialNode.setNext(specialNode.getNext().getNext());
    }

    /**
     *  将数组中的奇数放在偶数前
     *  输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
     *  并保证奇数和奇数，偶数和偶数之间的相对位置不变
     *
     *   双指针 （我还没专门用过这种算法）
     */
    public void fun3(int[] array){
        if(array.length == 1){
            return;
        }
        //一个指针在头部（奇指针），一个在尾部（偶指针） ； 依次向中间靠拢 ，如果 左边是 奇数 ，右边是偶数 ，再 各自前进 ；
        // 左边是偶 ，右边是奇数，则交换 ； 都为奇数 ，左边指针走， 都为偶数 ，右边指针走   (这里利用了双指针，但是没有保证 相对顺序)
        int lIdx = 0, rIdx = array.length -1 ;
        while (lIdx < rIdx){
            int leftData = array[lIdx] ,rightData = array[rIdx];
            if(leftData%2 == 0 && rightData%2 == 1){//直接交换
                int temp = array[rIdx];
                array[rIdx] = array[lIdx];
                array[lIdx] = temp;
                lIdx++;
                rIdx--;
                continue;
            }
            if(leftData%2 == 1 && rightData%2 == 1){//都为奇
                lIdx++;
                continue;
            }
            if(leftData%2 == 0 && rightData%2 == 0){//都为偶
                rIdx--;
                continue;
            }
            //到这里，说明 就是 正常的左右顺序了 ，左奇右偶
            lIdx++;
            rIdx--;
        }
        System.err.println("处理后的数组 ：" + JSON.toJSONString(array));
    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     */
    public void fun4(MyLinkNode<String> head){
        // a - b - c - d - e - f

        // f - e - d - c- b - a

        //我想到的最low的方法，是 用一个栈 来解决 ，不值一提 ； 这里用迭代 ，这里必须有一个临时变量 ，保证 调换之后 ，能保留住原先位置前一个节点的指针
        //之所以 用一个变量保留 pre ,而不是 保留 next ，是因为遍历节点是 要一路 next ，反而不好操作 ，利用 pre 反向赋值反而简单

        MyLinkNode<String> pre = null;//a
        MyLinkNode<String> current = head;//b
        while(current != null){//a  b c
            MyLinkNode<String> tmp = current.getNext();
            current.setNext(pre);
            pre = current;
            current = tmp;
        }

        // current 到遍历结束就是最后一个元素 ，如果不确定  ，可以试一下
        MyLinkNode<String> reverseNode = pre;
        while (reverseNode != null){
            System.err.println(reverseNode.val());
            MyLinkNode<String> next = reverseNode.getNext();
            if(next == null){
                break;
            }
            reverseNode = next;
        }

    }
}
