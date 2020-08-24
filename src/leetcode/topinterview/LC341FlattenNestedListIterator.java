package leetcode.topinterview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LC341FlattenNestedListIterator {
    //给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
    //列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
    //示例 1:
    //输入: [[1,1],2,[1,1]]
    //输出: [1,1,2,1,1]
    //解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
    //示例 2:
    //输入: [1,[4,[6]]]
    //输出: [1,4,6]
    //解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return null if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private List<NestedInteger> nestedList;
        //使用递归函数,将这个nestedList中所有元素都放到一个链表中去
        private LinkedList<Integer> helpList = new LinkedList<>();
        private int p = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            process(nestedList);
        }

        @Override
        public Integer next() {
            return helpList.get(p++);
        }

        @Override
        public boolean hasNext() {
            if (p < helpList.size()) {
                return true;
            }else {
                return false;
            }
        }

        //递归函数功能:
        //  给定一个nestedList.按照顺序将所有元素加入helpList中
        private void process(List<NestedInteger> nestedList) {
            //base case
            if (nestedList == null) {
                return;
            }

            //循环,分为当前元素是列表还是单个元素,如果是单个元素,直接添加并判断下一个,否则进入递归
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    helpList.addLast(nestedInteger.getInteger());
                }else {
                    process(nestedInteger.getList());
                }
            }
        }

        /**
         * 这么写是错误的,如果一开始就是一个列表,那么只能遍历那一个列表,出不去
         * 可以在后面加上process(nestedList.get(i + 1))但是这样会让函数更难写,不如直接用循环加入递归
         * private void process(List<NestedInteger> nestedList, int i) {
         *             //base case
         *             if (i == nestedList.size() - 1) {   //链表的最后一个元素
         *                 //如果最后一个元素是一个list,就进入下一层,否则将最后那个整数放进去就return
         *                 if (nestedList.get(i).isInteger()) {
         *                     helpList.addLast(nestedList.get(i).getInteger());
         *                     return;
         *                 } else {
         *                     process(nestedList.get(i).getList(), 0);
         *                 }
         *             }
         *
         *             //否则,分为当前元素是列表还是单个元素,如果是单个元素,直接添加并判断下一个,否则进入递归
         *             if (nestedList.get(i).isInteger()) {
         *                 helpList.addLast(nestedList.get(i).getInteger());
         *                 process(nestedList, i + 1);
         *             }else {
         *                 process(nestedList.get(i).getList(), 0);
         *             }
         *         }
         */
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
