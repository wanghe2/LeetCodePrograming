package org.wang.entity;

/**
 * 没有现成的链表 ，所以自己实现一个
 */

public class MyLinkNode<T> {
    private T val;
    private MyLinkNode<T> next;

    public T val(){
        return val;
    }

    public MyLinkNode(T data) throws Exception {
        if(data == null){
            throw new Exception("值不能为空");
        }
        this.val = data;
    }

    boolean hasNext(){
        return (next != null) ? true : false;
    }

    public MyLinkNode<T> setNext(MyLinkNode<T> next) {
        this.next = next;
        return next;
    }

    public MyLinkNode<T> getNext(){
        return next;
    }
}
