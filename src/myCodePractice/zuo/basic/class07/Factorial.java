package myCodePractice.zuo.basic.class07;

public class Factorial {
    //��n!�Ľ��
    public static long factorial(int n) {
        if (n <= 0) return -1;

        return process(n);
    }

    //�ݹ麯������:
    //����n!�Ľ��
    public static long process(int n) {
        //base case
        if (n == 1) return 1;

        return n * process(n - 1);
    }

    //�޸Ķ�̬�滮:
    //�ݹ麯����ֻ��һ������,���Ա�ʾ�����dp��һ��һά��,��Χ��1�������N
    //Ϊ�����±�ֱ�Ӷ�Ӧ�����N,�������0��������Ϊ0,�������1��ʼ����
    public static long process1(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 0;
        //��base caseȷ����ʼֵ,n=1ʱdpӦ�����ֵΪ1
        dp[1] = 1;
        //��һ�������µ�ֵ�����ȷ��
        //return�������һ�������µ�ֵ��ȷ������
        //Ϊ��ǰ����ֵ��dp�����е�ǰλ��-1λ�õ�ֵ
        for (int i = 2; i < dp.length; i++) {
            dp[i] = i * dp[i - 1];
        }
        //����������ݹ麯���������Ҫ��dp�����е��ĸ�ֵ
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(factorial(1));
        System.out.println(process1(1));
        System.out.println(factorial(3));
        System.out.println(process1(3));
        System.out.println(factorial(10));
        System.out.println(process1(10));
        System.out.println(factorial(20));
        System.out.println(process1(20));

    }
}
