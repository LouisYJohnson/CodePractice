package myCodePractice.zuo.basic.class07;

public class Cow {
    //ĸţÿ����һֻĸţ�� �³�����ĸţ�ɳ������Ҳ��ÿ����һֻ
    //ĸţ�� ���費������ ��N��� ĸţ��������
    public static int cowProblem(int n) {
        if (n <= 0) return 0;

        return process1(n);
    }

    //�ݹ麯������:
    //��������n,���ص�n��ţ�ĸ���
    public static int process1(int n) {
        //base case
        //��ǰ����,ţ�ĸ�����ÿ������һͷ��
        if (n <= 3) return n;
        //�����ţΪȥ��ţ�ĸ����Ļ�����,��������ǰţ��ͷ��
        //ţ������,���Խ����ţ��ȥ��ţ�Ļ����ϼ�
        //ţ�ɳ�����Ϳ�����Сţ��,������ȥ��ţ�Ļ��������ӵ�����Ϊ
        //����ǰţ��ͷ��
        //�����6��,6-3=3,�����������ţ,��������(����Ҳ��),�ǵ�5��,�ڵ�6���ʱ������С��
        return process1(n - 1) + process1(n - 3);
    }

    public static void main(String[] args) {
        System.out.println(cowProblem(11));
        System.out.println(process2(11));
    }

    //���ÿֻĸţֻ�ܻ�10�꣬ ��N��� ĸţ��������
    //f(N) = f(N - 1) + f(N - 3) - f(N - 10)
    public static int process2(int n) {
        //base case
        //��ε�base case,��Ϊ�����n < 0�����,�����ڴ������С��0��ʱ�򷵻�0����
        //��Ϊ����ݲ�û��ţ
        if (n <= 3 && n >= 0) return n;
        if (n < 0) return 0;

        return process2(n - 1) + process2(n - 3) - process2(n - 10);
    }
}
