package exams.zhongxing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] l = new int[t];
        int[] r = new int[t];
        String[] strings = new String[t];

        for (int i = 0; i < t; i++) {
            strings[i] = sc.next();
            l[i] = sc.nextInt();
            r[i] = sc.nextInt();
        }

        int[] res = new int[t];
        //对每一个字符串做处理
        for (int j = 0; j < strings.length; j++) {
            long resTemp = 0;
            int tempL = l[j];
            int tempR = r[j];
            String tempS = strings[j];
            for (int k = tempL; k <= tempR; k++) {
                //k表示进制,每一个字符串的l到r进制转换为10进制都要相加
                for (int i = tempS.length() - 1; i >= 0; i--) { //每一个字符都要经过进制转换
                    long cur = k;
                    for (int m = 0; m < tempS.length() - 1 - i; m++) {
                        cur *= k;
                    }
                    if (tempS.charAt(i) >= 'A' && tempS.charAt(i) <= 'Z') {  //如果是A-Z,转换成对应的数值
                        int tempNum = tempS.charAt(i) - 'A' + 10;
                        resTemp += (tempNum * cur);
                    } else {
                        int tempNum = tempS.charAt(i) - '0';
                        resTemp += (tempNum * cur);
                    }
                }
            }
            res[j] = resTemp % 2 == 0 ? 0 : 1;
        }
        for (int re : res) {
            System.out.println(re);
        }
    }
}
