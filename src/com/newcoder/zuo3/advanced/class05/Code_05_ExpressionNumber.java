package com.newcoder.zuo3.advanced.class05;

public class Code_05_ExpressionNumber {
    //���ʽ�õ�����������������
    //����Ŀ��
    //����һ��ֻ��0���٣� �� 1(��)�� &(�߼���)�� |���߼��� ��^(���)�����ַ���ɵ��ַ�
    //��express�� �ٸ���һ������ֵdesired�� ����express���ж�������Ϸ�ʽ�� ���Դﵽ
    //desired�Ľ����
    //��������
    //express="1^0|0|1"�� desired=false��
    //ֻ��1^((0|0)|1)��1^(0|(0|1))����Ͽ��Եõ�false�� ����2��
    //express="1"�� desired=false��
    //���������Եõ�false�� ����0��

    //�ⷨ:
    //ʽ���еĶ������ǹ̶�˳���,�������С����,����Ҫ���ж����ʽ���Ƿ���Ч
    //Ȼ��д�ݹ�,����ݹ�Ĺ��ܾ�������ַ�������(���÷�����,�ڲ������ݹ麯��������(ͬ���߼���������),���Ƿ�������)
    // ��Ϊtrue��false���ж��������㶼�����
    //ʽ���еĶ������ǹ̶�˳���,�������С����,����Ҫ���ж����ʽ���Ƿ���Ч
    //ʽ�ӱ�Ȼ��0����1��ͷ�ͽ�β,����ż��λ�ϱ���Ϊ0����1,����λ�ϱ���Ϊ�ж�����
    public static boolean isValid(char[] exp) {
        for (int i = 0; i < exp.length; i++) {
            if ((i & 1) == 1) { //���������,����Ϊ�ж�����
                if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
                    return false;
                }
            } else { //�����ż��.����Ϊ0����1
                if (exp[i] != '0' && exp[i] != '1') {
                    return false;
                }
            }
        }
        return true;
    }

    //д�ݹ�,�ݹ麯���Ĺ�����:
    //��һ���ַ���(�ַ������е�������ʼ�����),��������ַ����ܹ��ж�����true��false�����
    //��Ϊ���ص����������������һ��,���Ը���װ��һ���෵��
    public static class ReturnData {
        private int trueNums;
        private int falseNums;

        public ReturnData(int trueNums, int falseNums) {
            this.trueNums = trueNums;
            this.falseNums = falseNums;
        }
    }

    //�ݹ麯���Ĺ�����:
    //��һ���ַ���(�ַ������е�������ʼ�����),��������ַ����ܹ��ж�����true��false�����
    public static ReturnData process(char[] exp, int l, int r) {
        //base case
        if (l == r) {
            if (exp[l] == '0') {
                return new ReturnData(0, 1);
            } else {
                return new ReturnData(1, 0);
            }
        }
        //��ͷ�����ַ������ÿһ���߼�����λ,�������߼�����λ���ҵ��ַ������true��false�ж�����(�ݹ�)
        int trueNums = 0;
        int falseNums = 0;
        //�������Ϊtrue�Ŀ���Ϊa,false����Ϊb
        //�ұ�true�Ŀ���Ϊc,false����Ϊd
        //����м���߼��ж��ַ���&��:
        //  ture:a*c
        //  false:a*d+b*c+b*d
        //����м���߼��ж��ַ���|��:
        //  true:a*c+a*d+b*c
        //  false:b*d
        //����м���߼��ж��ַ���^���:
        //  true:a*d+b*c
        //  false:a*c+b*d
        for (int i = l + 1; i < r; i += 2) {   //ÿ�ζ��ҵ��ַ������е��߼�����λ(��l+1��ʼ,ÿ��������)
            //��Ϊ�ַ������ڽ���ݹ�֮ǰ��Ȼ������valid���,��������һ���ض����ܹ������߼��жϷ����ϵ�
            //���Ϊtrue�Ŀ���Ϊa,false����Ϊb
            //�ұ�true�Ŀ���Ϊc,false����Ϊd
            //������process���ӹ�����,���Ҿ���l��r������0��exp.length-1��
            ReturnData leftPart = process(exp, l, i - 1);
            ReturnData rightPart = process(exp, i + 1, r);
            int a = leftPart.trueNums;
            int b = leftPart.falseNums;
            int c = rightPart.trueNums;
            int d = rightPart.falseNums;
            if (exp[i] == '&') {    //��,&
                trueNums += a * c;
                falseNums += a * d + b * c + b * d;
            } else if (exp[i] == '|') {  //��,|
                trueNums += a * c + a * d + b * c;
                falseNums += b * d;
            } else {     //��� ^
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

    //�����ݹ�Ķ�̬�滮:
    //����ķ��е㸴��,�Ʋ�����,���Ծ�һ����������������ֹ���
    //ˮƽ����:trueMap:a,falseMap:b
    //��ֱ����:trueMap:c,falseMap:d
    //trueNums��Ӧ��trueMap
    //falseNums��Ӧ��falseMap
    public static int num2(String str, boolean desire) {
        char[] chars = str.toCharArray();
        int[][] trueMap = new int[chars.length][chars.length];
        int[][] falseMap = new int[chars.length][chars.length];
        //�������λ��
        for (int i = 0; i < trueMap.length; i += 2) {
            trueMap[i][i] = chars[i] == '1' ? 1 : 0;
            falseMap[i][i] = chars[i] == '0' ? 1 : 0;
        }
        //�����ձ�λ��,��ķ���,���ݾ�������(ͼ��������,����ȥ�μ�����)����ֵ
        //���������Ǵ�l==r��һ��б����,����ÿ��������Ҫ�����±����ұ߸�һ������ֱ��l==rΪֹ
        //������ʱ��,����ÿ���ձ�λ�õ�����������Ҫ��������,Ҳ����63-97��,���Ű�ͺ���
        for (int i = chars.length - 3; i >= 0; i -= 2) {
            for (int j = i + 2; j < chars.length; j += 2) {
                //��������ѭ�����ҵ�ÿһ����Ҫ�����λ��,���һ��forѭ������������63-97�е�����ķ���
                //l��Ӧi,r��Ӧj
                int trueNums = 0;
                int falseNums = 0;
                for (int k = i + 1; k < j; k += 2) {
                    int a = trueMap[i][k - 1];
                    int b = falseMap[i][k - 1];
                    int c = trueMap[k + 1][j];
                    int d = falseMap[k + 1][j];
                    if (chars[k] == '&') {    //��,&
                        trueNums += a * c;
                        falseNums += a * d + b * c + b * d;
                    } else if (chars[k] == '|') {  //��,|
                        trueNums += a * c + a * d + b * c;
                        falseNums += b * d;
                    } else {     //��� ^
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
    //��̬�滮by zuo
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
