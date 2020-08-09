package leetcode.topinterview;

import java.util.HashMap;
import java.util.LinkedList;

public class LC146LruCache {
    //运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
    //获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
    //写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，
    // 则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
    //
    //进阶:
    //你是否可以在 O(1) 时间复杂度内完成这两种操作？
    //示例:
    //LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
    //cache.put(1, 1);
    //cache.put(2, 2);
    //cache.get(1);       // 返回  1
    //cache.put(3, 3);    // 该操作会使得关键字 2 作废
    //cache.get(2);       // 返回 -1 (未找到)
    //cache.put(4, 4);    // 该操作会使得关键字 1 作废
    //cache.get(1);       // 返回 -1 (未找到)
    //cache.get(3);       // 返回  3
    //cache.get(4);       // 返回  4
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/lru-cache
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class LRUCache {
        //使用一个HashMap和一个链表
        //链表用于存储序列中的key与判断是否越界(两个指针)和更加方便的删除最久未使用的数据值(即最早加入的,在链表头部)
        //HashMap用于通过链表中的key找HashMap中相同key对应的value
        //注意,未使用包含了查询get与更改put!!!

        private HashMap<Integer, Integer> helpMap;
        private LinkedList<Integer> helpList;
        private int capacity;

        public LRUCache(int capacity) {
            this.helpList = new LinkedList<>();
            this.helpMap = new HashMap<>();
            this.capacity = capacity;
        }

        public void put(int key, int value) {
            //想要O(1)复杂度,就要用helpMap来处理,因为HashMap的增删改查复杂度都是O(1)
            if (!helpMap.containsKey(key)) {  //如果不包含这个key就要插入,此时要判断是否到达最大容量,如果到了删除LinkedList第一个节点
                //首先处理达到最大容量的情况
                if (helpList.size() == capacity) {
                    //更新的时候也要更新map中的东西
                    int helpKey = helpList.removeFirst();
                    helpMap.remove(helpKey);
                }
                helpList.addLast(key);
                helpMap.put(key, value);
            } else { //如果包含了key,更新值,更新值的时候,那个修改的key应该放到队列的最后,因为改变了
                helpList.remove(helpList.indexOf(key));
                helpList.addLast(key);
                helpMap.put(key, value);
            }
        }

        public int get(int key) {
            //如果关键字不存在,返回-1,如果存在,返回值
            //注意,查询了这个元素也要改变其在链表中的位置,因为这也算是用了
            if (helpMap.containsKey(key)) {
                //LinkedList中的删除方法如果传入的是个int,删除的是对应下标的数
                helpList.remove(helpList.indexOf(key));
                helpList.addLast(key);
                return helpMap.get(key);
            } else {
                return -1;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
