package com.newcoder.zuo3.gaopin.class01;

public class Problem_03_CardsInLine {
    //有一排正数，玩家A和玩家B都可以看到。
    //每位玩家在拿走数字的时候，都只能从最左和最右的数中选择一个。
    //玩家A先拿，玩家B再拿，两人交替拿走所有的数字，
    //两人都力争自己拿到的数的总和比对方多。请返回最后获胜者的分数。
    //
    //例如：
    //5,2,3,4
    //玩家A先拿，当前他只能拿走5或者4。
    //如果玩家A拿走5，那么剩下2，3，4。轮到玩家B，此时玩家B可以选择2或4中的一个，…
    //如果玩家A拿走4，那么剩下5，2，3。轮到玩家B，此时玩家B可以选择5或3中的一个，…

    //并不是拿到左右最大的就好,因为有可能有其他的组合策略大于这个,所以要用暴力递归找到所有结果中的最大的
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    //先手在arr中从l到r范围内得到的最大值
    public static int first(int[] arr, int l, int r) {
        //base case
        if (l == r) return arr[l];
        //first拿完应该占据second拿法中剩余的最大值
        return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));
    }
    //后手在arr中从l到r范围内得到的最大值
    public static int second(int[] arr, int l, int r) {
        if (l == r) return 0;//后手拿,只剩一个拿不到了
        //second拿一定是first拿法中的最小值,first只能给他拿走了l或者r后剩下的最小的拿法
        return Math.min(first(arr, l + 1, r) , first(arr, l, r - 1));
    }

    //暴力递归改动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        //行为l,列为r
        int[][] firstDp = new int[arr.length][arr.length];
        int[][] secondDp = new int[arr.length][arr.length];
        //递归中的这种依赖关系决定了填表的方式最好是固定列,挨行填
        for (int j = 0; j < arr.length; j++) {  //j为列,i为行
            firstDp[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                firstDp[i][j] = Math.max(arr[i] + secondDp[i + 1][j], arr[j] + secondDp[i][j - 1]);
                secondDp[i][j] = Math.min(firstDp[i + 1][j], firstDp[i][j - 1]);
            }
        }
        return Math.max(firstDp[0][arr.length - 1], secondDp[0][arr.length - 1]);
    }

    //for test
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
