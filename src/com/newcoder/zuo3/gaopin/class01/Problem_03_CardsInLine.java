package com.newcoder.zuo3.gaopin.class01;

public class Problem_03_CardsInLine {
    //��һ�����������A�����B�����Կ�����
    //ÿλ������������ֵ�ʱ�򣬶�ֻ�ܴ���������ҵ�����ѡ��һ����
    //���A���ã����B���ã����˽����������е����֣�
    //���˶������Լ��õ��������ܺͱȶԷ��ࡣ�뷵������ʤ�ߵķ�����
    //
    //���磺
    //5,2,3,4
    //���A���ã���ǰ��ֻ������5����4��
    //������A����5����ôʣ��2��3��4���ֵ����B����ʱ���B����ѡ��2��4�е�һ������
    //������A����4����ôʣ��5��2��3���ֵ����B����ʱ���B����ѡ��5��3�е�һ������

    //�������õ��������ľͺ�,��Ϊ�п�������������ϲ��Դ������,����Ҫ�ñ����ݹ��ҵ����н���е�����
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    //������arr�д�l��r��Χ�ڵõ������ֵ
    public static int first(int[] arr, int l, int r) {
        //base case
        if (l == r) return arr[l];
        //first����Ӧ��ռ��second�÷���ʣ������ֵ
        return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));
    }

    //������arr�д�l��r��Χ�ڵõ������ֵ
    public static int second(int[] arr, int l, int r) {
        if (l == r) return 0;//������,ֻʣһ���ò�����
        //second��һ����first�÷��е���Сֵ,firstֻ�ܸ���������l����r��ʣ�µ���С���÷�
        return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));
    }

    //�����ݹ�Ķ�̬�滮
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        //��Ϊl,��Ϊr
        int[][] firstDp = new int[arr.length][arr.length];
        int[][] secondDp = new int[arr.length][arr.length];
        //�ݹ��е�����������ϵ���������ķ�ʽ����ǹ̶���,������
        for (int j = 0; j < arr.length; j++) {  //jΪ��,iΪ��
            firstDp[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                firstDp[i][j] = Math.max(arr[i] + secondDp[i + 1][j], arr[j] + secondDp[i][j - 1]);
                secondDp[i][j] = Math.min(firstDp[i + 1][j], firstDp[i][j - 1]);
            }
        }
        return Math.max(firstDp[0][arr.length - 1], secondDp[0][arr.length - 1]);
    }

    //for Combinations
    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 20) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) + 1;
        }
        return res;
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int scores = p(arr, 0, arr.length - 1);
        return Math.max(sum - scores, scores);
    }

    public static int p(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        if (i + 1 == j) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(arr[i] + Math.min(p(arr, i + 2, j), p(arr, i + 1, j - 1)),
                arr[j] + Math.min(p(arr, i + 1, j - 1), p(arr, i, j - 2)));
    }

    public static int win4(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            dp[i][i] = arr[i];
            dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
        }
        dp[arr.length - 1][arr.length - 1] = arr[arr.length - 1];
        for (int k = 2; k < arr.length; k++) {
            for (int j = k; j < arr.length; j++) {
                int i = j - k;
                dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                        arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
            }
        }
        return Math.max(dp[0][arr.length - 1], sum - dp[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int testTime = 50000;
        boolean err = false;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRondomArray();
            int r1 = win1(arr);
            int r2 = win2(arr);
            int r3 = win3(arr);
            int r4 = win4(arr);
            if (r1 != r2 || r1 != r3 || r1 != r4) {
                err = true;
            }
        }
        if (err) {
            System.out.println("2333333333");
        } else {
            System.out.println("6666666666");
        }
    }
}
