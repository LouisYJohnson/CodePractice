package exams.jingdong;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //接收参数n
        int m = sc.nextInt();
        int n = sc.nextInt();
        int res = 0;
        for (int i = m; i <= n; i++) {
            //从m到n每次++,然后看每个数分别去掉某一个数字后能否为回文素数
            if (i == 0 || i == 1) {
                continue;
            }
            StringBuilder helpSB = new StringBuilder(String.valueOf(i));
            if (helpSB.length() == 1) { //如果长度为1直接就是回文素数
                res++;
            } else { //否则,需要判断
                for (int j = 0; j < helpSB.length(); j++) {
                    String tempSB1 = helpSB.substring(0, j);
                    if (j + 1 < helpSB.length()) {  //如果当前位置不是最后位置
                        String tempSB2 = helpSB.substring(j + 1, helpSB.length());
                        String tempNum = tempSB1 + tempSB2;
                        int temp = Integer.parseInt(tempNum);
                        if (temp == 0 || temp == 1) {
                            continue;
                        }
                        if (isP(temp) && isS(temp)) {
                            res++;
                            break;
                        }
                    } else {     //如果当前位置是最后位置
                        int temp1 = Integer.parseInt(tempSB1);
                        if (temp1 == 0 || temp1 == 1) {
                            continue;
                        }
                        if (isP(temp1) && isS(temp1)) {
                            res++;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(res);
    }

    //判断一个数是否为素数
    public static boolean isS(int n) {
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0 || n == 1) return false;
        for (int i = 3; i < Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    //判断数字n是否为回文数
    public static boolean isP(int n) {
        String n2String = String.valueOf(n);

        int l = 0;
        int r = n2String.length() - 1;
        while (l < r) {
            if (n2String.charAt(l++) != n2String.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
