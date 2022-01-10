package org.wang.entity;

import java.util.Stack;

/**
 * 实现自定义的队列 ，用2个栈 实现
 */
public class MyQueue<T> {

    private Stack<T> first = new Stack<>();

    private Stack<T> second = new Stack<>();

    /**
     * 入队
     */
    public void enterQue(T t){
        first.push(t);
    }

    /**
     * 离队
     * @return
     */
    public T leaveQue() throws Exception {
        if(isEmpty()){
            throw new Exception("越界");
        }
        if(second.isEmpty()) {
            while (!first.isEmpty()) {
                second.push(first.pop());
            }
        }
        return second.pop();
    }

    public boolean isEmpty(){
        return first.isEmpty() && second.isEmpty();
    }

}
