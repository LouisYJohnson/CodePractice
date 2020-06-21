package myCodePractice.zuo.basic.class08;

public class WaterProblem {
    //����һ���������һ��������
    //����[3,1,2,4]��
    //����0λ����һ�����Ϊ1�� �߶�Ϊ3��ֱ��ͼ��
    //����1λ����һ�����Ϊ1�� �߶�Ϊ1��ֱ��ͼ��
    //����2λ����һ�����Ϊ1�� �߶�Ϊ2��ֱ��ͼ��
    //����3λ����һ�����Ϊ1�� �߶�Ϊ4��ֱ��ͼ��
    //����ֱ��ͼ�ĵײ�����һ��ˮƽ���ϣ� �ҽ����š�
    //�����ͼ�����һ�������� �����������װ3���ˮ��
    //����һ��û�и���������arr�� ������װ����ˮ��

    //�������,��Ҫ��������˼��(�Ҳ��岨��),����˼·������,��Ϊһ��С���ȿ������߻��д󲨷�,������ȥ������
    //Ҫ�ھֲ���˼��,��һ�������ܹ�װ����ˮ
    //һ������װˮ�Ķ���ȡ��������ߵ���߷���ұߵ���߷��������һ��������Ĳ�ֵ

    public static int waterProblem(int[] arr) {
        //С��2��λ���ǲ���װˮ��
        if (arr == null || arr.length < 3) return 0;

        //�����ǲ���װˮ��
        //����������������,�ֱ�װ�ŵ�ǰ������ߵ����ֵ���ұߵ����ֵ(��������)
        //������
        int[] helpLeftMax = new int[arr.length];
        //���ҵ���
        int[] helpRightMax = new int[arr.length];

        int help = Integer.MIN_VALUE;
        for (int i = 0; i < helpLeftMax.length; i++) {
            if (help < arr[i]) {
                helpLeftMax[i] = arr[i];
                help = arr[i];
            } else {
                helpLeftMax[i] = help;
            }
        }
        help = Integer.MIN_VALUE;
        for (int i = helpRightMax.length - 1; i >= 0; i--) {
            if (help < arr[i]) {
                helpRightMax[i] = arr[i];
                help = arr[i];
            } else {
                helpRightMax[i] = help;
            }
        }

        int res = 0;
        //ȥ�����ϵ�����λ��,��Ϊ���ϵ�λ���ǲ�����װˮ��
        for (int i = 1; i < arr.length - 1; i++) {
            res += Math.max(Math.min(helpLeftMax[i], helpRightMax[i]) - arr[i], 0);
        }
        return res;
    }

}
