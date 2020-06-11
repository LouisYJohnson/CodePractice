package com.newcoder.zuo3.advanced.class05;

import java.util.HashSet;

public class Code_06_Perfect_Rectangle {
    //�������е�ͼ�ζ��ڵ�һ�����ڣ�
    //rectangles = [
    //[1,1,3,3],
    //[3,1,4,2],
    //[3,2,4,4],
    //[1,3,2,4],
    //[2,3,3,4]
    //]��
    //�У�
    //[1,1,3,3]��ʾ��1���������Ͻǵ�����Ϊ(1,1)�� ���½ǵ�����Ϊ(3,3)
    //[3,1,4,2]��ʾ��2���������Ͻǵ�����Ϊ(3,1)�� ���½ǵ�����Ϊ(4,2)
    //...
    //�������ַ������Ը��㼸����Σ� ���ж������ܲ����������һ�������Ĵ���Σ� ��û
    //���غϵĲ��֡�
    //���������� ��perfect rectangle
    //����:
    //ʹ��HashSet��,ÿ����set�м���һ�����ε��ĸ���(��������,����ȫ����,���ĸ�),
    // ��������ʱ�����Ѿ���ͬ���ĵ���,�Ͱ�ͬ���ĵ�ɾ��(�����о����غϵ��ж�,����غ϶��,����Ҫ�������е��ж�����)
    //���յĽ��һ����Ҫset��ֻ���ĸ���,�����ۼӵ�areaҪ==���ĸ���Χ�����ľ��ε��������
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 || rectangles[0].length == 0) return false;
        //���о����������Ͻǵ�����
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        //���о����������½ǵ�����
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        int area = 0;
        HashSet<String> hashSet = new HashSet<>();
        //�������е�һά����
        for (int[] rec : rectangles) {
            x1 = Math.min(x1, rec[0]);
            y1 = Math.min(y1, rec[1]);
            x2 = Math.max(x2, rec[2]);
            y2 = Math.max(y2, rec[3]);
            //���ε������Ǿ��ܹ��õ����ε��ĸ����ϵ�����
            String x1y1 = rec[0] + "_" + rec[1];
            String x1y2 = rec[0] + "_" + rec[3];
            String x2y1 = rec[2] + "_" + rec[1];
            String x2y2 = rec[2] + "_" + rec[3];
            //���������set���Ѿ�����,��ɾ��
            if (hashSet.add(x1y1)) hashSet.remove(x1y1);
            if (hashSet.add(x1y2)) hashSet.remove(x1y2);
            if (hashSet.add(x2y1)) hashSet.remove(x2y1);
            if (hashSet.add(x2y2)) hashSet.remove(x2y2);
            //area��ʾĿǰΪֹ���еľ��ε����֮��(��ֹ�����غϵĵڶ�������)
            area += ((rec[2] - rec[0]) * (rec[3] - rec[0]));
        }
        //������յ�������4,���߳��ֹ������ƴ�Ӿ����ϵ��ĸ��߽ǵ�����������һ����,��false
        if (hashSet.size() != 4
                || !hashSet.contains(x1 + "_" + y1)
                || !hashSet.contains(x1 + "_" + y2)
                || !hashSet.contains(x2 + "_" + y1)
                || !hashSet.contains(x2 + "_" + y2)) {
            return false;
        }
        //������յ������Ķ��ҵ㻹���������ζ�Ӧ�ĵ�,�������ǲ��Ƕ�Ӧ�����(��ֹ�о��ζ���ص��������ζ�Ӧ�ĵ���)
        return area == (x2 - x1) * (y2 - y1);

    }
}
