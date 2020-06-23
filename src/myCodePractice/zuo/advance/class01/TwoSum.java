package myCodePractice.zuo.advance.class01;

import java.util.HashMap;

public class TwoSum {
//    给定一个数组arr， 和一个整数aim， 请返回哪两个位置的数可
//    以加出aim来。
//    例如
//            arr = {2, 7, 11, 15}， target = 9
//    返回{0,1}， 因为arr[0] + arr[1] = 2 + 7 = 9
//    可以假设每个数组里只有一组答案

    //1.使用HashSet
    //遍历数组,将数组中元素放入set中
    //再次遍历数组,每遍历到一个元素,寻找aim-当前元素是否在set中,如果在,返回位置
    public static int[] twoSum1(int[] arr, int aim) {
        if (arr.length == 0 || arr ==null) return null;

        HashMap<Integer, Integer> valueAndIndex = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            valueAndIndex.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (valueAndIndex.containsKey(aim - arr[i])) {
                return new int[] {i, valueAndIndex.get(aim - arr[i])};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,7,11,15};
        int aim = 9;
        int[] ans = twoSum1(arr,9);
        for (int an : ans) {
            System.out.println(an);
        }
    }

}
