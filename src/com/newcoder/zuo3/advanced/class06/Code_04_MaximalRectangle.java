package com.newcoder.zuo3.advanced.class06;

import java.util.Stack;

public class Code_04_MaximalRectangle {
    //求最大子矩阵的大小
    //【题目】
    //给定一个整型矩阵map， 其中的值只有0和1两种， 求其中全是1
    //的所有矩形区域中， 最大的矩形区域为1的数量。
    //例如：
    //1 1 1 0
    //其中， 最大的矩形区域有3个1， 所以返回3。
    //再如：
    //1 0 1 1
    //1 1 1 1
    //1 1 1 0
    //其中， 最大的矩形区域有6个1， 所以返回6。
    //基础问题:给一个表示直方图的数组,问这个直方图数组中包含的最大的都是方块的面积(一个方面面积为1),
    // 问题就变成了每次遍历到一个杆.这个杆左右平移,平移到哪个位置比这个杆低,就停,看这个杆往左往右能扩多少,
    // 这就是直方图中包含的最大的都是方块的面积(用到了单调栈)本质上就是找离这个杆最近且比这个杆小的在这个杆左边和右边的位置,
    // 然后右减左再乘这个平移的杆的高度就是要求的面积了

    //这个问题是基础问题的拓展,在此问题中,分别以每行为底(比如这个3*4的矩阵,
    // 变成三个1*4的向量,分别代表以第1,2,3行为起始的直方图,上面的值就是以该行为起始,上面有多少个连续的1
    //以第一行开始:1 0 1 1,第二行:2,1,2,2第三行:3,2,3,0),然后计算最大的基础问题中的最大面积即可,
    // 然后取三个结果中的最大值,即为所求

    //使用单调栈实现基础问题(基础问题需要用从栈底到栈顶从小到大的栈来解决)
    public static int maxFromBottom(int[] array) {
        if (array == null || array.length == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        //因为弹栈的时候就能同时得到左右距离最近并且比这个数小的数与位置,那么在弹栈的时候就可以算出每个位置上的面积
        //因为通过下标就能够取到数组中的值,所以栈中存的是下标
        stack.push(0);
        for (int i = 1; i < array.length; i++) {
            //如果要入栈的数不满足栈中的从小到大的排列条件
            //一直弹出,弹到这个值可以入栈为止,并且这些被弹出的值右侧距离最近且比被弹出值小的值就是这个让他们出栈的值
            //这些被弹出的值在栈中下面的值就是左边距离最近且比自身小的值
            //如果有值相同的话,在这里的处理方法是也入栈,所以在出栈的时候找左侧比自身小的,就要一直向栈下面找
            //直到找到不相等的数为止
            while (!stack.isEmpty() && (array[stack.peek()] > array[i])) {
                int help = array[stack.pop()];
                //有值相同的情况,因为相同值的下标实际上是落在一起的,所以他们的面积肯定也是一样的
                //找到左边最近的小与右边最近的小然后弹干净了就好了
                if (!stack.isEmpty() && help == array[stack.peek()]) {
                    while (!stack.isEmpty() && (help == array[stack.peek()])) {
                        stack.pop();
                    }
                    //如果栈弹干净了,那说明这串相等的数,左边没有比他们小的了
                    if (stack.isEmpty()) {
                        max = Math.max(help * i, max);
                    } else { //如果栈没有弹干净,找栈中下一个数,就是他们在栈中的时候下面的数,也就是左边最近且小的数
                        max = Math.max(help * (i - stack.peek() - 1), max);
                    }
                }
                //栈中没有相同值的情况(只弹出一个),分为空栈和不空栈的情况
                if (!stack.isEmpty()) {
                    max = Math.max(help * (i - stack.peek()) - 1, max);
                } else {
                    max = Math.max(help * i, max);
                }
            }
            stack.push(i);
        }
        //结束后,单独处理剩余的栈(如果栈中还有数的话)
        while (!stack.isEmpty()) {
            int help1 = array[stack.pop()];
            while (!stack.isEmpty() && array[stack.peek()] == help1) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                max = Math.max(help1 * array.length, max);
            } else {
                max = Math.max(help1 * (array.length - stack.peek() - 1), max);
            }
        }
        return max;
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        int[] help = new int[map[0].length];
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                help[j] = map[i][j] == 0 ? 0 : help[j] + map[i][j];
            }
            max = Math.max(maxFromBottom(help), max);
        }
        return max;
    }

    //for test
    //implemented by zuo:
    //简洁的原因:
    //在于发现了本质:就是如果有多个对应相同值的下标在一起,也按照栈中下一个下标就是左边最近的小值算就可以了,
    //因为只要不弹到最新值能进栈,就会一直弹栈,所以最终会一直谈到栈空或者栈中的下一个下标对应的数小于待入栈的数
    //如果说中间有多个对应相同值的下标,那么中间那些max是不会大于最后弹出的那个相同值的下标的
    public static int maxRecSize1(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
        System.out.println(maxRecSize(map));
    }
}
