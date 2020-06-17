package myCodePractice.zuo.basic.class07;

public class MoneyProblem {
    //����һ������arr�� ��һ������aim�� �����������ѡ��arr�е�
    //���֣� �ܲ����ۼӵõ�aim�� ����true����false
    public static boolean moneyProblem(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return false;

        return process(arr, 0, aim, 0);
    }

    //�ݹ麯������:
    //iλ��֮ǰ�������Ѿ�ѡ�����,iλ�õ�����λ�û�û�о���
    public static boolean process(int[] arr, int i, int aim, int sum) {
        //base case
        //�������β��,����ǰ�ӳ���sum��aim�Ƿ���ͬ
        //��Ϊ�ݹ麯���Ĺ�����iλ��֮ǰ(������iλ��)�����ݶ��Ѿ�ѡ�����
        //����i����Ҫ��arr.length���ܱ�ʾ�������鶼ѡ����
        if (i == arr.length) {
            return sum == aim;
        }

        //iλ�õ���ѡ��Ҫ���߲�Ҫ,������һ��Ϊtrue����
        return process(arr, i + 1, aim, sum) || process(arr, i + 1, aim, sum + arr[i]);
    }
}
