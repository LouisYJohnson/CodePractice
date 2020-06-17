package myCodePractice.zuo.basic.class07;

public class KnapSack {
    //������������w��v�� �������鳤����ȣ� w[i]��ʾ��i����Ʒ��
    //������ v[i]��ʾ��i����Ʒ�ļ�ֵ��
    //�ٸ���һ������bag�� Ҫ������ѡ��Ʒ������������һ�����ܳ�
    //��bag�� ����������������£� ���ܻ�õ�����ֵ��

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag < 0) return -1;

        return process(w, v, bag, 0, 0);
    }

    //�ݹ麯������:
    //��������,���޶�����
    //����0~i-1λ���ϵ���Ʒ�Ѿ�ѡ��,iλ�ü��Ժ����Ʒ����ѡ�������С���������������ֵ
    public static int process(int[] w, int[] v, int bag, int i, int curW) {
        //base case
        //���0~i-1λ���ϵ���Ʒ�Ѿ���������������·���߲�ͨ��,ֱ�ӷ�����Сֵ������·ʧЧ
        if (curW > bag) return Integer.MIN_VALUE;
        //����ߵ�������,˵��û�г�������,�������·�Ѿ��ߵ�ͷ��(�ݹ麯���Ĺ���Ϊ0~i-1λ���Ѿ�ѡ��,����˵����������ȫѡ����)
        //�ߵ�ͷ�˾ͷ���0����,��Ϊû�п��Լӵ���
        if (i == w.length) return 0;

        //�ݹ麯�����ܾ��Ƿ���һ�����ֵ,���Բ����ڲ����д��뵱ǰ��ֵ
        return Math.max(process(w, v, bag, i + 1, curW), v[i] + process(w, v, bag, i + 1, curW + w[i]));
    }
}
