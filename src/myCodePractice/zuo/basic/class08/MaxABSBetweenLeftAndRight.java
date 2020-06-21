package myCodePractice.zuo.basic.class08;

public class MaxABSBetweenLeftAndRight {
    //����leftMax��rightMax֮��ľ���ֵ
    //����Ŀ��
    //����һ������ΪN��N>1�� ����������arr�� ���Ի��ֳ���������
    //���֣� �󲿷�Ϊarr[0..K]�� �Ҳ���Ϊarr[K+1..N-1]�� K����ȡ
    //ֵ�ķ�Χ��[0,N-2]�� ����ô�໮�ַ����У� �󲿷��е����ֵ
    //��ȥ�Ҳ������ֵ�ľ���ֵ�У� ����Ƕ��٣�
    //���磺 [2,7,3,1,1]�� ���󲿷�Ϊ[2,7]�� �Ҳ���Ϊ[3,1,1]ʱ��
    //�󲿷��е����ֵ��ȥ�Ҳ������ֵ�ľ���ֵΪ4�� ���󲿷�Ϊ
    //[2,7,3]�� �Ҳ���Ϊ[1,1]ʱ�� �󲿷��е����ֵ��ȥ�Ҳ������
    //ֵ�ľ���ֵΪ6�� ���кܶ໮�ַ����� �����շ���6��

    //�ⷨ:��������
    //�ַ��ǽ�һ������ֳ�����,ÿ�����������1����,�����ø�������
    //�����һ��ȡ���������һ�����ұߵ������(��������)�����ֵ
    //�ұ���һ��ȡ�����ұ���һ������ߵ����ұ�(��������)�����ֵ
    //����ֽд������һ��
    //[0 1 2] [3 4 5]
    //     ��   ��
    //���Ը�������Ҫ��������,�ֱ�Ϊ��ǰ��(��������)��ߵ����ֵ�뵱ǰ��(��������)�ұߵ����ֵ

    public static int maxABSBetweenLeftAndRight(int[] arr) {
        if (arr == null || arr.length < 1) return Integer.MIN_VALUE;

        int[] helpLeftMax = new int[arr.length];
        int[] helpRightMax = new int[arr.length];
        int help = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > help) {
                helpLeftMax[i] = arr[i];
                help = arr[i];
            } else {
                helpLeftMax[i] = help;
            }
        }

        help = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > help) {
                helpRightMax[i] = arr[i];
                help = arr[i];
            } else {
                helpRightMax[i] = help;
            }
        }

        int res = Integer.MIN_VALUE;
        //���ڷֳ����������ÿ����������һ��Ԫ��,������һ��Ӧ�ô�1��N-2λ��
        for (int i = 1; i <= arr.length - 1; i++) {
            res = Math.max(res, Math.abs(helpLeftMax[i - 1] - helpRightMax[i]));
        }
        return res;
    }

    //for test
    public static int maxABS2(int[] arr) {
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            lArr[i] = Math.max(lArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i > -1; i--) {
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, Math.abs(lArr[i] - rArr[i + 1]));
        }
        return max;
    }

    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABSBetweenLeftAndRight(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }
}
