package main.java.com.jerrrylinalgorithm.demo.base.stack;

import lombok.Data;

/**
 * 模拟栈数据结构，用数组和链表
 * @author Administrator
 */
public class MyStack {


    public static void main(String[] args){
        int[] strs = new int[10];
        MyStackArray myStackArray = new MyStackArray();
        myStackArray.inArray(strs,1);
        myStackArray.inArray(strs,2);
        myStackArray.inArray(strs,3);
        System.out.println(strs.toString());
        int i = myStackArray.outArray(strs);
        System.out.println(i);
        MyNode firstNode = new MyNode(1,null);
        MyNode secondNode = new MyNode(2, null);
        MyNode thirdNode = new MyNode(3, null);
        firstNode.inStack(firstNode,secondNode);
        firstNode.inStack(firstNode,thirdNode);
        MyNode myNode = firstNode.outStack(firstNode);
        System.out.println(myNode.data);


    }

    /**
     * 数组模拟栈
     */
    static class MyStackArray{
        int[] strs;

        /**
         * 入栈操作，模拟入栈，数组尾部进为栈头进
         * @param m
         * @return
         */
         void inArray(int[] strs,int m){
            int index = strs.length;
            for(int i=0;i<strs.length;i++){
                if(strs[i]==0){
                    strs[i] = m;
                    break;
                }
            }

        }

        /**
         * 出栈操作
         * @param strs
         * @return
         */
        int outArray(int[] strs){
            if( strs.length==0){
                return -1;
            }
            for(int i = strs.length-1;i>0;i--){
                if(strs[i]!=0){
                    return strs[i];
                }
            }
            return -1;
        }

    }

    /**
     * 单链表模板栈
     */
    @Data
    static class MyNode{
        int data;
        MyNode next = null;

        MyNode(int data,MyNode next){
            this.data   = data;
            this.next   = next;
        }

        /**
         * 入栈操作，
         * @param newNode  新的节点，栈头进
         * @
         */
        void inStack(MyNode lastNode,MyNode newNode){
            MyNode currentNode = lastNode;
            while(currentNode.next!=null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }

        /**
         * 出栈操作
         */
        MyNode outStack(MyNode lastNode){
            MyNode currentNode = lastNode;
            while(currentNode.next!=null){
                currentNode = currentNode.next;
            }
            return currentNode;
        }

    }




}
