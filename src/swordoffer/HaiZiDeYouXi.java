package swordoffer;

import java.util.LinkedList;
import java.util.concurrent.Executors;

public class HaiZiDeYouXi {
    //题目描述
    //每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,
    // 今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
    // 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
    // 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
    // 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
    // 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
    // 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
    // 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
    //构造环形链表模拟圆圈（时间复杂度o(mn),空间复杂度o(n)）
    public class Solution {
        //其中，在while循环中，每一次应该删除的index的计算：index=（index+（m-1））%list.size()，这是因为：
        //第一次删掉的位置是从0开始数m-1个位置，
        // 以后每次从删掉的下一个节点开始取，
        // 所以每次要在index的索引处加上m-1，
        // 因为是环，所以加了以后对链表长度取余。这样就可以直接计算出每次应该删除的索引位置
        public int LastRemaining_Solution(int n, int m) {
            if(n<1||m<1){
                return -1;
            }
            //直接用数据结构中的LinkedList
            LinkedList<Integer> list=new LinkedList<>();
            for(int i=0;i<n;i++){
                list.add(i);
            }
            int index=0;
            while(list.size()>1){
                //每次都是在当前坐标向后取m-1个,对链表长度取余,保证每次删除的节点索引都在合法的范围内(下标从0开始)
                index=(index+m-1)%list.size();
                list.remove(index);
            }
            return list.size()==1?list.get(0):-1;
        }
    }

    public static void main(String[] args) {
        Executors.newCachedThreadPool();
    }
}
