package com.newcoder.zuo3.basic.class02_1;

import java.util.HashMap;

public class Class_02_RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int index;

        public Pool() {
            keyIndexMap = new HashMap<K, Integer>();
            indexKeyMap = new HashMap<Integer, K>();
            index = 0;
        }

        //插入的时候实际上只要一个主链表需要的key就可以了,辅助链表跟着改变就可以了
        //如果链表中已经有了key,千万不要重新插入,虽然hash能够保证唯一性,但是map中的value会进行更新
        public void insert(K key) {
            if (!keyIndexMap.containsKey(key)) {
                keyIndexMap.put(key, index);
                indexKeyMap.put(index, key);
                //这里的index是指向有效区的下一个位置
                //所以在删除的时候要先--才能指向有效区的最后一个位置
                index++;
            }
        }

        //删除一个key后将两个列表中最后一个key移动到被移除key对应的坑上
        public void delete(K key) {
            //在删除的时候要先--才能指向有效区的最后一个位置
            //因为有效区的最后一个位置才是真正要移动的位置
            index--;
            int toBeDeleteIndex = keyIndexMap.get(key);
            keyIndexMap.remove(key);
            indexKeyMap.remove(toBeDeleteIndex);
            K toBeMoveKey = indexKeyMap.get(index);
            indexKeyMap.remove(index);
            indexKeyMap.put(toBeDeleteIndex, toBeMoveKey);
            keyIndexMap.remove(toBeMoveKey);
            keyIndexMap.put(toBeMoveKey, toBeDeleteIndex);

        }

        //随机获取一个key,记得判断边界条件,如果hashMap空了咋办
        public K getRandom() {
            if (index == 0) {
                return null;
            }
            int randomNum = (int) (Math.random() * index);
            return indexKeyMap.get(randomNum);
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        pool.delete("zuo");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

}
