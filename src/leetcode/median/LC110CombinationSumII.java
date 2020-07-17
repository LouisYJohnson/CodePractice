package leetcode.median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LC110CombinationSumII {
    //题目描述
    //给出一组候选数C和一个目标数T，找出候选数中起来和等于T的所有组合。
    //C中的每个数字在一个组合中只能使用一次。
    //注意：
    //题目中所有的数字（包括目标数T）都是正整数
    //组合中的数字 (a 1, a 2, … , a k) 要按非递增排序 (ie, a 1 ≤ a 2 ≤ … ≤ a k).
    //结果中不能包含重复的组合
    //例如：给定的候选数集是[10,1,2,7,6,1,5]，目标数是8
    //解集是：
    //[1, 7]
    //[1, 2, 5]
    //[2, 6]
    //[1, 1, 6]
    //用回溯法做
    public class Solution {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
            Arrays.sort(num);
            //辅助列表
            ArrayList<Integer> arrayList = new ArrayList<>();
            process(num, target, 0, arrayList);
            return res;
        }
        public void process(int[] num, int target, int start, ArrayList arrayList) {
            if (target < 0) {
                return;
            } else if (target == 0) {
                res.add(new ArrayList<>(arrayList));
            }else {
                for (int i = start; i < num.length; i++) {
                    //TODO
                    if (i > start && num[i] == num[i - 1]) {    //加入这句就能给res去重,如果不加就是CombinationSumI,重复结果
                        continue;
                    }
                    arrayList.add(num[i]);
                    //如果允许重复用同一个位置的元素的话的话,这里是i
                    process(num, target - num[i], i + 1, arrayList);
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
    }

//    //用递归做,但是复杂度高而且无法去重
//    public class Solution {
//        public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
//            if (num == null || num.length == 0) return new ArrayList<>();
//
//            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//            ArrayList<Integer> arrayList = new ArrayList<>();
//
//            process(num, 0, 0, arrayList, res, target);
//            //得到的res要进行去重
//            return res;
//        }
//
//        //递归函数功能:
//        //  当前位置i以及i位置之后的数都没有选,i之前的都已经选完了i与i之后的位置还没选
//        //  传入当前位置,当前得出的sum值,以及表示中间结果以及最终结果的ArrayList
//        public void process(int[] num, int i, int sum, ArrayList<Integer> arrayList, ArrayList<ArrayList<Integer>> res, int target) {
//            //base case
//            if (i == num.length) {
//                if (sum == target) {
//                    //此时应该将arrayList中的数字排序后放到res中,而不是直接放进去
//                    //如果直接放进去,res中存储的是arrayList的引用,其中的元素会跟着arrayList一起变化
//                    //而且add多次实际上add都是同一个地址中的arrayList
//                    ArrayList<Integer> temp = new ArrayList<>();
//                    for (Integer integer : arrayList) {
//                        temp.add(integer);
//                    }
//                    temp.sort(new Comparator<Integer>() {
//                        @Override
//                        public int compare(Integer o1, Integer o2) {
//                            //返回负值o1在前面(上面),返回正值o2在前面(上面)
//                            //o1-o2表示升序,o2-o1表示降序
//                            return o1 - o2;
//                        }
//                    });
//                    res.add(temp);
//                }
////                else if (arrayList.size() > 0){
////                    arrayList.remove(arrayList.size() - 1);
////                }
//                return;
//            }
//
//            //分为要当前位置的数与不要当前位置的数
//            arrayList.add(num[i]);
//            process(num, i + 1, sum + num[i], arrayList, res, target);
//            arrayList.remove(arrayList.size() - 1);
//            process(num, i + 1, sum, arrayList, res, target);
//
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new LC110CombinationSumII().new Solution();
//        ArrayList<ArrayList<Integer>> res = solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
//        System.out.println(1);
//    }


}
