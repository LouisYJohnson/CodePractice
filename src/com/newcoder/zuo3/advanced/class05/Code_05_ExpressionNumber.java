package com.newcoder.zuo3.advanced.class05;

public class Code_05_ExpressionNumber {
    //表达式得到期望结果的组成种数
    //【题目】
    //给定一个只由0（假） 、 1(真)、 &(逻辑与)、 |（逻辑或） 和^(异或)五种字符组成的字符
    //串express， 再给定一个布尔值desired。 返回express能有多少种组合方式， 可以达到
    //desired的结果。
    //【举例】
    //express="1^0|0|1"， desired=false。
    //只有1^((0|0)|1)和1^(0|(0|1))的组合可以得到false， 返回2。
    //express="1"， desired=false。
    //无组合则可以得到false， 返回0

    //动态规划:因为要返回两个值,所以说动态规划返回两个表
    //式子中的东西都是固定顺序的,自由组合小括号,所以要先判断这个式子是否有效
    //然后写递归,这个递归的功能就是这个字符串里面
    // (不用分左右,内部两个递归函数连续用(同样逻辑的子问题),
    // 就是分左右了)的为true和false的有多少种请你都求出来

    public static boolean isValid(char[] exp) {
        for (int i = 0; i < exp.length; i++) {
            if ((i & 1) == 1) {
                if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
                    return false;
                }
            } else {
                if (exp[i] != '0' && exp[i] != '1') {
                    return false;
                }
            }
        }
        return true;
    }

    public static class ReturnData {
        private int trueNums;
        private int falseNums;

        public ReturnData(int trueNums, int falseNums) {
            this.trueNums = trueNums;
            this.falseNums = falseNums;
        }
    }

    public static ReturnData process(char[] exp, int l, int r) {
        //base case
        if (l == r) {
            if (exp[l] == '0') {
                return new ReturnData(0, 1);
            } else {
                return new ReturnData(1, 0);
            }
        }
        int trueNums = 0;
        int falseNums = 0;
        for (int i = l + 1; i < r; i += 2) {
            ReturnData leftPart = process(exp, l, i - 1);
            ReturnData rightPart = process(exp, i + 1, r);
            int a = leftPart.trueNums;
            int b = leftPart.falseNums;
            int c = rightPart.trueNums;
            int d = rightPart.falseNums;
            if (exp[i] == '&') {
                trueNums += a * c;
                falseNums += a * d + b * c + b * d;
            } else if (exp[i] == '|') {
                trueNums += a * c + a * d + b * c;
                falseNums += b * d;
            } else {
                trueNums += a * d + b * c;
                falseNums += a * c + b * d;
            }
        }
        return new ReturnData(trueNums, falseNums);
    }

    public static int num1(String express, boolean desire) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] chars = express.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        ReturnData returnData = process(chars, 0, chars.length - 1);
        if (desire) {
            return returnData.trueNums;
        } else {
            return returnData.falseNums;
        }
    }

    public static int num2(String str, boolean desire) {
        char[] chars = str.toCharArray();
        int[][] trueMap = new int[chars.length][chars.length];
        int[][] falseMap = new int[chars.length][chars.length];
        for (int i = 0; i < trueMap.length; i += 2) {
            trueMap[i][i] = chars[i] == '1' ? 1 : 0;
            falseMap[i][i] = chars[i] == '0' ? 1 : 0;
        }
        for (int i = chars.length - 3; i >= 0; i -= 2) {
            for (int j = i + 2; j < chars.length; j += 2) {
                int trueNums = 0;
                int falseNums = 0;
                for (int k = i + 1; k < j; k += 2) {
                    int a = trueMap[i][k - 1];
                    int b = falseMap[i][k - 1];
                    int c = trueMap[k + 1][j];
                    int d = falseMap[k + 1][j];
                    if (chars[k] == '&') {
                        trueNums += a * c;
                        falseNums += a * d + b * c + b * d;
                    } else if (chars[k] == '|') {
                        trueNums += a * c + a * d + b * c;
                        falseNums += b * d;
                    } else {
                        trueNums += a * d + b * c;
                        falseNums += a * c + b * d;
                    }
                }
                trueMap[i][j] = trueNums;
                falseMap[i][j] = falseNums;
            }
        }
        return desire ? trueMap[0][chars.length - 1] : falseMap[0][chars.length - 1];
    }

    //for test
    //by zuo
//    public static int num2(String express, boolean desired) {
//        if (express == null || express.equals("")) {
//            return 0;
//        }
//        char[] exp = express.toCharArray();
//        if (!isValid(exp)) {
//            return 0;
//        }
//        int[][] t = new int[exp.length][exp.length];
//        int[][] f = new int[exp.length][exp.length];
//        t[0][0] = exp[0] == '0' ? 0 : 1;
//        f[0][0] = exp[0] == '1' ? 0 : 1;
//        for (int i = 2; i < exp.length; i += 2) {
//            t[i][i] = exp[i] == '0' ? 0 : 1;
//            f[i][i] = exp[i] == '1' ? 0 : 1;
//            for (int j = i - 2; j >= 0; j -= 2) {
//                for (int k = j; k < i; k += 2) {
//                    if (exp[k + 1] == '&') {
//                        t[j][i] += t[j][k] * t[k + 2][i];
//                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
//                    } else if (exp[k + 1] == '|') {
//                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
//                        f[j][i] += f[j][k] * f[k + 2][i];
//                    } else {
//                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
//                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
//                    }
//                }
//            }
//        }
//        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
//    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));
    }
}
