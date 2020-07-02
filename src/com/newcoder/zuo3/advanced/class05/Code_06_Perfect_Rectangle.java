package com.newcoder.zuo3.advanced.class05;

import java.util.HashSet;

public class Code_06_Perfect_Rectangle {
    //假设所有的图形都在第一象限内，
    //rectangles = [
    //[1,1,3,3],
    //[3,1,4,2],
    //[3,2,4,4],
    //[1,3,2,4],
    //[2,3,3,4]
    //]其
    //中，
    //[1,1,3,3]表示第1个矩形左上角的坐标为(1,1)， 右下角的坐标为(3,3)
    //[3,1,4,2]表示第2个矩形左上角的坐标为(3,1)， 右下角的坐标为(4,2)
    //...
    //按照这种方法可以给你几组矩形， 请判断他们能不能正好组成一个完整的大矩形， 且没
    //有重合的部分。
    //完整描述： 搜perfect rectangle
    //做法:
    //使用HashSet做,每次向set中加入一个矩形的四个点(给了两个,但是全都加,加四个),
    // 如果加入的时候发现已经有同样的点了,就把同样的点删掉(里面有矩形重合的判断,如果重合多次,就需要下面那行的判断条件)
    //最终的结果一定是要set中只有四个点,并且累加的area要==这四个点围起来的矩形的面积才行
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 || rectangles[0].length == 0) return false;
        //所有矩形中最左上角的坐标
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        //所有矩形中最右下角的坐标
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        int area = 0;
        HashSet<String> hashSet = new HashSet<>();
        //遍历所有的一维数组
        for (int[] rec : rectangles) {
            x1 = Math.min(x1, rec[0]);
            y1 = Math.min(y1, rec[1]);
            x2 = Math.max(x2, rec[2]);
            y2 = Math.max(y2, rec[3]);
            //矩形的两个角就能够得到矩形的四个角上的坐标
            String x1y1 = rec[0] + "_" + rec[1];
            String x1y2 = rec[0] + "_" + rec[3];
            String x2y1 = rec[2] + "_" + rec[1];
            String x2y2 = rec[2] + "_" + rec[3];
            //如果坐标在set中已经有了,就删掉
            if (!hashSet.add(x1y1)) hashSet.remove(x1y1);
            if (!hashSet.add(x1y2)) hashSet.remove(x1y2);
            if (!hashSet.add(x2y1)) hashSet.remove(x2y1);
            if (!hashSet.add(x2y2)) hashSet.remove(x2y2);
            //area表示目前为止所有的矩形的面积之和(防止矩形重合的第二道保险)
            area += ((rec[2] - rec[0]) * (rec[3] - rec[0]));
        }
        //如果最终点数不是4,或者出现过的最大拼接矩形上的四个边角点上少了任意一个点,就false
        if (hashSet.size() != 4
                || !hashSet.contains(x1 + "_" + y1)
                || !hashSet.contains(x1 + "_" + y2)
                || !hashSet.contains(x2 + "_" + y1)
                || !hashSet.contains(x2 + "_" + y2)) {
            return false;
        }
        //如果最终点数是四而且点还都是最大矩形对应的点,检测面积是不是对应的面积(防止有矩形多次重叠在最大矩形对应的点上)
        return area == (x2 - x1) * (y2 - y1);

    }
}
