package org.wang.entity;

/**
 * 树节点 （二叉树节点）  ,这里简单一点 ，不使用泛型了 ，节点值类型就是 整型
 */
public class MyTreeNode {

    private MyTreeNode parent;

    private MyTreeNode left;

    private MyTreeNode right;

    private Integer val;

    public MyTreeNode(Integer val){
        this.val = val;
    }

    public Integer val(){
        return val;
    }

    public MyTreeNode (MyTreeNode parent, Integer val){
        this.parent = parent;
        this.val = val;
    }

    public boolean isLeaf(){
        if(left == null && right == null){
            return true;
        }
        return false;
    }


    public MyTreeNode getParent() {
        return parent;
    }

    public void setParent(MyTreeNode parent) {
        this.parent = parent;
    }

    public MyTreeNode getLeft() {
        return left;
    }

    public void setLeft(MyTreeNode left) {
        this.left = left;
    }

    public MyTreeNode getRight() {
        return right;
    }

    public void setRight(MyTreeNode right) {
        this.right = right;
    }
}
