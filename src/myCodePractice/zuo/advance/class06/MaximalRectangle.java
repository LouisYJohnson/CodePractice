package myCodePractice.zuo.advance.class06;

import java.util.Stack;

public class MaximalRectangle {
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
    //基础问题:给一个表示直方图的数组,
    // 问这个直方图数组中包含的最大的都是方块的面积(一个方面面积为1),
    // 问题就变成了每次遍历到一个杆.这个杆左右平移,平移到哪个位置比这个杆低,
    // 就停,看这个杆往左往右能扩多少,这就是直方图中包含的最大的都是方块的面积(用到了单调栈)
    // 本质上就是找离这个杆最近且比这个杆小的在这个杆左边和右边的位置,
    // 然后右减左再乘这个平移的杆的高度就是要求的面积了

    //这个问题是基础问题的拓展,在此问题中,
    // 分别以每行为底(比如这个3*4的矩阵,变成三个1*4的向量,
    // 分别代表以第1,2,3行为起始的直方图,上面的值就是以该行为起始,上面有多少个连续的1
    //以第一行开始:1 0 1 1,第二行:2,1,2,2第三行:3,2,3,0),
    // 然后计算最大的基础问题中的最大面积即可,然后取三个结果中的最大值,即为所求

    //基础问题
    public static int getHistMaxArea(int[] arr) {
        if (arr == null || arr.length == 0) return -1;

        Stack<Integer> helpStack = new Stack<>();
        helpStack.push(0);
        int res = 0;
        int tempPop = 0;
        int tempPopLeft = 0;
        int tempPopRight = 0;
        //实现单调栈,要找左右距离最近的最小值,栈中要严格递增
        //栈中装的是数组下标
        for (int i = 1; i < arr.length; i++) {
            //如果栈顶元素大于要入栈的元素,执行弹栈操作
            while (!helpStack.isEmpty() && arr[helpStack.peek()] > arr[i]) {
                tempPop = helpStack.pop();
                //如果出现栈中叠了多个相同值(下标对应相同值),将这些叠在一起的值都清出去
                //不清出去也行,反正最大值也会更新成最大的
                while (!helpStack.isEmpty() && arr[helpStack.peek()] == arr[tempPop]) {
                    helpStack.pop();
                }
                tempPopRight = i;
                //如果stack中没有数了,证明左边没有值比当前值更小,定义当前坐标值为-1(计算面积方便),否则就是当前值在栈中的下面的代表下标的值
                tempPopLeft = helpStack.isEmpty() ? -1 : helpStack.peek();
                res = Math.max(((tempPopRight - 1) - tempPopLeft) * arr[tempPop], res);
            }
            //弹栈后,压栈
            helpStack.push(i);
        }
        //循环结束后,stack中会剩下一些没有弹出去的下标值,对这些下标值进行单独运算
        //不清出去重复的值也行,反正最大值也会更新成最大的
        tempPopRight = arr.length;
        while (!helpStack.isEmpty()) {
            tempPop = helpStack.pop();
            tempPopLeft = helpStack.isEmpty() ? -1 : helpStack.peek();
            res = Math.max(((tempPopRight - 1) - tempPopLeft) * arr[tempPop], res);
        }
        return res;
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        int[] help = new int[map[0].length];
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                //必须以每行为底,来做基础问题中的直方图
                //如果当前底上为0,那上面不管是多少,当前都是0(因为直方图断了)
                help[j] = map[i][j] == 0 ? 0 : help[j] + map[i][j];
            }
            max = Math.max(getHistMaxArea(help), max);
        }
        return max;
    }

    //for Combinations
    //implemented by zuo:
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
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0},};
        System.out.println(maxRecSize(map));
        System.out.println(maxRecSize1(map));
    }
}
