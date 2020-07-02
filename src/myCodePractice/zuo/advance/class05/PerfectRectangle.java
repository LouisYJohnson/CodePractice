package myCodePractice.zuo.advance.class05;

import java.util.HashSet;

public class PerfectRectangle {
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
    //  使用HashSet,将每次矩形都加入set中,如果出现重复的点,就删掉这个点(防止矩形重叠)
    //  每次加入矩形的时候都累加面积,并且更新出现过的最大与最小的x,y坐标(对应了最大矩形)
    //  最后检查,set中是否有四个点,这四个点是否是出现过的最大与最小的点坐标,这个面积和之前累加的面积是否相同

    public static boolean isPerfectRectangle(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return false;

        int area = 0;
        HashSet<String> helpSet = new HashSet<>();
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        for (int i = 0; i < rectangles.length; i++) {
            x1 = Math.min(x1, rectangles[i][0]);
            y1 = Math.min(y1, rectangles[i][1]);
            x2 = Math.max(x2, rectangles[i][2]);
            y2 = Math.max(y2, rectangles[i][3]);
            String x1y1 = rectangles[i][0] + "_" + rectangles[i][1];
            String x1y2 = rectangles[i][0] + "_" + rectangles[i][3];
            String x2y1 = rectangles[i][2] + "_" + rectangles[i][1];
            String x2y2 = rectangles[i][2] + "_" + rectangles[i][3];
            area += (rectangles[i][3] - rectangles[i][1]) * (rectangles[i][2] - rectangles[i][0]);
            //HashSet.add(),如果set中有这个元素,添加同样元素会失败,返回一个false
            if (!helpSet.add(x1y1)) helpSet.remove(x1y1);
            if (!helpSet.add(x1y2)) helpSet.remove(x1y2);
            if (!helpSet.add(x2y1)) helpSet.remove(x2y1);
            if (!helpSet.add(x2y2)) helpSet.remove(x2y2);
        }

        //如果set中不是刚好四个点,且这四个点如果有一个不是出现过的最大矩形对应的坐标的话,结果为false
        if (helpSet.size() != 4
                || !helpSet.contains(x1 + "_" + y1)
                || !helpSet.contains(x1 + "_" + y2)
                || !helpSet.contains(x2 + "_" + y1)
                || !helpSet.contains(x2 + "_" + y2)) return false;
        //这四个点能否构成矩形
        return area == (y2 - y1) * (x2 - x1);
    }

}
