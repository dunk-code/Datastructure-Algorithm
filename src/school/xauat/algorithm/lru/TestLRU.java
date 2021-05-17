package school.xauat.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zsy
 * @date ：Created 2021/5/13 23:45
 * @description：LRU算法
 */
public class TestLRU {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,2);
        //lruCache.list.list();
        lruCache.put(2,2);
        //lruCache.list.list();
        lruCache.put(3,2);
        //lruCache.list.list();
        lruCache.get(2);
        //lruCache.list.list();
        lruCache.put(4,3);
        lruCache.list.list();
        lruCache.get(2);
        lruCache.list.list();
        lruCache.put(4,5);
        lruCache.list.list();

    }
}

class LRUCache {
    int capacity;
    Map<Integer, Node> map;
    DoubleList list;

    public LRUCache (int cap) {
        this.capacity = cap;
        this.map = new HashMap<>();
        this.list = new DoubleList();
    }

    /**
     * 添加缓存
     * 如果map中包含当前key，则修改当前的val值，并将节点添加到链表头部
     * 否则
     *  如果双端链表的长度小于cap，直接添加到链表头部
     *  否则，移除链表最后一个节点，添加到链表头部，删除map中的映射
     *  最后，添加到map中
     * @param key
     * @param val
     */
    public void put(int key, int val) {
        Node cur = new Node(key, val);
        if(map.containsKey(key)) {
            list.remove(map.get(key));
            list.addFirst(cur);
        } else {
            if(list.size == capacity) {
                Node node = list.removeLast();
                map.remove(node.key);
            }
            list.addFirst(cur);
        }
        map.put(key, cur);
    }

    /**
     * 如果map中不包含key，返回-1
     * 否则，获取当前节点，删除当前节点（map、list），将节点添加到list头部
     * @param key
     * @return
     */
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node target = map.get(key);
        list.remove(target);
        list.addFirst(target);
        return target.val;
    }
}

class Node {
    int key, val;
    Node next, pre;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }
}

class DoubleList {
    Node head;
    Node tail;
    int size;

    public DoubleList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public void addFirst(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.pre = node;
        node.pre = head;
        size++;
    }


    public Node remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;
        return node;
    }

    public Node removeLast() {
        Node target = tail.pre;
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        size--;
        return target;
    }

    public void list() {
        Node temp = head.next;
        while(temp != tail) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

