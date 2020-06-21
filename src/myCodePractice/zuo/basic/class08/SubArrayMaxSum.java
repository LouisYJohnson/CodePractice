package myCodePractice.zuo.basic.class08;

public class SubArrayMaxSum {
    //����һ������arr�� ����������������ۼӺ��У� �����ۼӺ�
    //ʵ���������ҳ�������Һ�����������
    //���������㶨,cur��max
    //���������ı仯����:
    //max��ʼֵΪInteger.MIN_VALUE,cur��ʼֵΪ0
    //��������,ÿ��һ����,cur�������,������max֮��Ĺ�ϵ,�����max��,����maxֵ
    //Ȼ��cur�Ƿ�Ϊ��,���Ϊ��,�����Ϊ0,max����,������һ��ѭ��
    //���cur >= 0,ֱ�ӽ�����һ��ѭ��
    public static int subArrayMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (cur > max) {
                max = cur;
            }
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }


}
