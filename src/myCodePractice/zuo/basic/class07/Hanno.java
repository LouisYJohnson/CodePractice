package myCodePractice.zuo.basic.class07;

public class Hanno {
    //��ŵ������
    //��ӡn�㺺ŵ����������ƶ������ұߵ�ȫ������
    //������,�ֱ���from,to,help
    //��������,���ǽ�n-1��from�ŵ�help����to��Ϊ����
    //  Ȼ�󽫵�n����from�ŵ�to��
    //  ���n-1����help�ŵ�to����from��Ϊ����
    public static void printHanno(int n) {
        if (n > 0) {

        }
    }

    //�ݹ麯������:
    //���뺺ŵ������n,from,to,help�˵�����,����n�㺺ŵ����from��toʹ��help�������ƶ�˳��
    public static void process(int n, String from, String to, String help) {
        //base case
        if (n == 1) {
            System.out.println("move from: " + from + " to: " + to);
            return;
        }

        process(n - 1, from, help, to);
        System.out.println("move from: " + from + " to:" + to);
        process(n - 1, help, to, from);
    }

}
