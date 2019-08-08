package main.java.com.jerrrylinalgorithm.demo.base.lurlinked;

import lombok.Data;

/**
 * 用双向链表实现LUR缓存淘汰算法:   头进尾出
 *
 * @author Jerrrylin
 */

public class LurLinked {

    /**
     * 初始化缓存大小
     */
    int initSize = 5;

    /**
     * 双向链表的结构
     */
    @Data
    class MyNode {
        //下个节点  通过判断下下个节点是否为null来判断是否是尾节点
        MyNode next = null;
        //上个节点  通过判断上个节点是否为null来判断是否是首节点
        MyNode pre = null;
        //该节点存储的数据
        String data;
    }

    public static void main(String[] args) {
        LurLinked lurLinked = new LurLinked();
        MyNode firstNode = lurLinked.insertOne(null, "a");
        MyNode secondeNode = lurLinked.insertOne(firstNode, "b");
        firstNode.setNext(secondeNode);
        MyNode thirdNode = lurLinked.insertOne(secondeNode, "c");
        secondeNode.setNext(thirdNode);
        MyNode fourthNode = lurLinked.insertOne(thirdNode, "d");
        thirdNode.setNext(fourthNode);
        MyNode fifthNode = lurLinked.insertOne(fourthNode, "e", null);
        fourthNode.setNext(fifthNode);
        lurLinked.println(firstNode);
        System.out.println("-----------------------------------");
        MyNode nowFirstNode = lurLinked.addOnFirst(firstNode, "f");
        lurLinked.println(nowFirstNode);
    }

    /**
     * 打印
     *
     * @param mNode
     */
    void println(MyNode mNode) {
        MyNode currentNode = mNode;

        while (currentNode != null) {
            if (currentNode.getPre() != null) {
                System.out.print("pre=" + currentNode.getPre().getData() + ":");
            }
            System.out.print(currentNode.getData());
            if (currentNode.getNext() != null) {
                System.out.print(": next=" + currentNode.getNext().getData());
            }
            System.out.println("");
            currentNode = currentNode.getNext();
        }


    }


    /**
     * 通过指定位置添加节点
     *
     * @param preNode  上一节点
     * @param nextNode 下一节点
     */
    MyNode insertOne(MyNode preNode, String data, MyNode nextNode) {
        MyNode myNode = new MyNode();
        myNode.setData(data);
        myNode.setPre(preNode);
        myNode.setNext(nextNode);
        return myNode;
    }

    MyNode insertOne(MyNode preNode, String data) {
        MyNode myNode = new MyNode();
        myNode.setData(data);
        myNode.setPre(preNode);
        return myNode;
    }


    /**
     * 在链表头插入数据
     * 缓存是否存在该数据
     * 缓存满不满
     *
     * @param data
     * @return MyNode 缓存的第一节点
     */
    MyNode addOnFirst(MyNode firstNode, String data) {
        MyNode currentNode = firstNode;
        while (currentNode != null) {
            //当前节点包含要缓存的数据
            if (currentNode.data.equals(data)) {
                //如果是第一个节点
                if (firstNode.getData().equals(data)) {
                    return firstNode;
                }
                //如果是最后一个节点包含数据,通过判断原前一个值是否为第一个节点来不同处理
                if (currentNode.next == null) {
                    //如果原有前一节点是第一个节点,两个节点换位皆可
                    if (currentNode.getPre() == firstNode) {
                        //最后节点设置为第一节点
                        currentNode.setNext(firstNode);
                        currentNode.setPre(null);
                        //第一节点设置为最后节点
                        firstNode.setPre(currentNode);
                        firstNode.setNext(null);
                    } else {
                        //有三个或以上节点的情况 倒数第二节点为倒数节点
                        currentNode.getPre().setNext(null);
                        //原第一节点为第二节点
                        firstNode.setPre(currentNode);
                        //原最后节点为现第一节点
                        currentNode.setPre(null);
                        currentNode.setNext(firstNode);
                    }
                }
                //如果不是第一个节点包含的话 ,又不是第一节点，只能是中间  起码有三个或以上节点
                else if (currentNode != firstNode) {
                    //获取原有前一节点和原有的后一节点，并使两者连接起来
                    currentNode.getNext().setPre(currentNode.getPre());
                    currentNode.getPre().setNext(currentNode.getNext());
                    //设置第一节点为第二节点
                    firstNode.setPre(currentNode);
                    //原最后节点为现第一节点
                    currentNode.setPre(null);
                    currentNode.setNext(firstNode);
                }
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }

        //遍历一遍缓存不包括新增数值
        // 缓存不包含部分  判断缓存是否已满 满的话删除为部函数
        MyNode isfull = isfull(initSize, firstNode);
        if (isfull != null) {
            //原倒数第二节点设置为最后节点
            isfull.getPre().setNext(null);
            isfull.setPre(null);
        }
        LurLinked lurLinked = new LurLinked();
        MyNode nowFirstNode = lurLinked.insertOne(null, data, firstNode);
        //设置新节点为第一节点
        firstNode.setPre(nowFirstNode);
        return nowFirstNode;
    }

    /**
     * 通过第一个节点和 全局变量initSize 来判断缓存是否满了
     *
     * @param firstNode
     * @return 满了返回最后一个节点, 不满返回null
     */
    MyNode isfull(int initSize, MyNode firstNode) {
        MyNode currentNode = firstNode;
        while (currentNode.getNext() != null) {
            initSize--;
            currentNode = currentNode.getNext();
        }
        if (initSize == 1) {
            return currentNode;
        }
        return null;
    }


}
