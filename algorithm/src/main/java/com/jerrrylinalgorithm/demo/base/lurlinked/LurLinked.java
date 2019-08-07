package main.java.com.jerrrylinalgorithm.demo.base.lurlinked;

import lombok.Data;

/**
 *  用双向链表实现LUR缓存淘汰算法:   头进尾出
 * @author Jerrrylin
 */

public class LurLinked {

    /**
     * 初始化缓存大小
     */
    int initSize = 5;

    /**
     *双向链表的结构
     */
    @Data
    class MyNode{
        //下个节点  通过判断下下个节点是否为null来判断是否是尾节点
        MyNode next = null;
        //上个节点  通过判断上个节点是否为null来判断是否是首节点
        MyNode pre = null;
        //该节点存储的数据
        String data ;
    }

    public static void main(String[] args){
        LurLinked lurLinked = new LurLinked();
        MyNode firstNode = lurLinked.insertOne(null, "a");
        MyNode secondeNode = lurLinked.insertOne(firstNode, "b");
        firstNode.setNext(secondeNode);
        MyNode thirdNode = lurLinked.insertOne(secondeNode, "c");
        secondeNode.setNext(thirdNode);
        MyNode fourthNode = lurLinked.insertOne(thirdNode, "d");
        thirdNode.setNext(fourthNode);
        MyNode fifthNode = lurLinked.insertOne(thirdNode, "e",null);
        fourthNode.setNext(fifthNode);
        lurLinked.println(firstNode);
    }

    /**
     * 打印
     * @param mNode
     */
    void println(MyNode mNode){
        MyNode currentNode = mNode;

        while(currentNode!=null){
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }


    }




    /**
     * 通过指定位置添加节点
     *  @param preNode  上一节点
     *  @param nextNode 下一节点
     *
     */
    MyNode insertOne(MyNode preNode,String data,MyNode nextNode){
        MyNode myNode = new MyNode();
        myNode.setData(data);
        myNode.setPre(preNode);
        myNode.setNext(nextNode);
        return  myNode;
    }

    MyNode insertOne(MyNode preNode,String data){
        MyNode myNode = new MyNode();
        myNode.setData(data);
        myNode.setPre(preNode);
        return  myNode;
    }



    /**
     * 在链表头插入数据
     *  缓存是否存在该数据
     *  缓存满不满
     *
     * @param data
     */
    void addOnFirst(MyNode firstNode,String data){
       MyNode currentNode = firstNode;
       while(currentNode!=null){
           if(currentNode.data.equals(data)){
               currentNode.getNext().setPre(currentNode.getPre());
               currentNode.getPre().setNext(currentNode.getNext());
                break;
           }
           currentNode = currentNode.next;
       }


    }




}
