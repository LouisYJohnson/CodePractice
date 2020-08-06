package exams.jingdong;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //接收参数n
        int n = sc.nextInt();

        double[] dp = new double[n];
        dp[0] = (double) (1 / 5.0 - 1 / 10.0);
        for (int i = 1; i < n; i++) {
            dp[i] = (double) (dp[i - 1] + 1.0 / (5 * (2 * (i + 1) - 1)) - 1.0 / (5 * 2 * (i + 1)));
        }
        DecimalFormat decimalFormat = new DecimalFormat("######0.0000");
//        BigDecimal b = new BigDecimal(dp[n - 1]);
//        double res = b.setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println(decimalFormat.format(dp[n - 1]));
    }
}

