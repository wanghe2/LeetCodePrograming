package org.wang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.wang.entity.MyLinkNode;
import org.wang.entity.MyTreeNode;
import org.wang.function.SectionOne;
import org.wang.function.SectionThree;
import org.wang.function.SectionTwo;

public class AppTest 
{
    public void testSectionOne()
    {
        SectionOne sectionOne = new SectionOne();


//      sectionOne.fun1(new int[]{1, 3, 5, 7, 9},8);


//        LinkedList<Integer> list1 =new LinkedList<>(),list2 = new LinkedList<>();
//        list1.add(3);
//        list1.add(9);
//        list1.add(4);
//        list2.add(2);
//        list2.add(4);
//        list2.add(3);
//        list2.add(5);
//        sectionOne.fun2(list1,list2);

//        sectionOne.fun3("abcdddefghijklmna");

//        sectionOne.fun6(329900);

//        sectionOne.fun7(235132);

//        sectionOne.fun9(1994);
    }

    public void sectionTwo() throws Exception {
        SectionTwo sectionTwo = new SectionTwo();

//        sectionTwo.fun1(new int[]{2 ,9,2 ,2 ,5 ,7 ,4 ,3 ,2 , 2,9,2,5});

//        sectionTwo.fun2(new int[][]{{1, 3, 5, 7, 9},{2 , 4, 6, 8, 10} ,{20, 22 , 24, 26, 30}},25);

//          MyLinkNode<Integer> myLinkNode = new MyLinkNode<>(20);
//          myLinkNode.setNext(new MyLinkNode<Integer>(30)).setNext(new MyLinkNode<Integer>(36)).setNext(new MyLinkNode<Integer>(48)).setNext(new MyLinkNode<Integer>(59));
//          sectionTwo.fun3(myLinkNode);


//        MyTreeNode rootNode = new MyTreeNode(10);
//        MyTreeNode leftNode = new MyTreeNode(rootNode,6);
//        MyTreeNode rightNode = new MyTreeNode(rootNode,14);
//        rootNode.setLeft(leftNode);
//        rootNode.setRight(rightNode);
//        MyTreeNode leftChildLeftNode = new MyTreeNode(leftNode,4);
//        MyTreeNode leftChildRightNode = new MyTreeNode(leftNode,8);
//        leftNode.setLeft(leftChildLeftNode);
//        leftNode.setRight(leftChildRightNode);
//        MyTreeNode rightChildLeftNode = new MyTreeNode(rightNode,12);
//        MyTreeNode rightChildRightNode = new MyTreeNode(rightNode, 16);
//        rightNode.setLeft(rightChildLeftNode);
//        rightNode.setRight(rightChildRightNode);
//        //???????????? ??????????????? ?????????????????????
//        sectionTwo.fun4(rootNode);

//          sectionTwo.fun5();

//        sectionTwo.fun6(9);

//          sectionTwo.fun7(20);

//          sectionTwo.fun8(new int[]{1,2,4,5,6,12,17,19,23,25,27,30,32,36},23);
    }

    @Test
    public void testThree() throws Exception {
        SectionThree sectionThree = new SectionThree();

//        sectionThree.fun1(20);

//        MyLinkNode<Integer> headNode = new MyLinkNode<>(20);
//        MyLinkNode<Integer> toDelNode =  new MyLinkNode<Integer>(36);
//        headNode.setNext(new MyLinkNode<Integer>(30)).setNext(toDelNode).setNext(new MyLinkNode<Integer>(48)).setNext(new MyLinkNode<Integer>(59));
//        sectionThree.fun2(headNode,toDelNode);

//        sectionThree.fun3(new int[]{2 , 4, 12 , 24, 23, 21,32,3,5,27});

        MyLinkNode<String> headNode = new MyLinkNode<>("a");
        headNode.setNext(new MyLinkNode<String>("b")).setNext(new MyLinkNode<String>("c")).setNext(new MyLinkNode<String>("d")).setNext(new MyLinkNode<String>("e"));
        sectionThree.fun4(headNode);
    }



}
