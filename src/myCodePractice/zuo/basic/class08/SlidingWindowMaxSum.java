package myCodePractice.zuo.basic.class08;

import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaxSum {
    //���ɴ������ֵ����
    //����Ŀ��
    //��һ����������arr��һ����СΪw�Ĵ��ڴ����������߻������ұߣ� ����ÿ�����ұ߻�һ��
    //λ�á�
    //���磬 ����Ϊ[4,3,5,4,3,3,6,7]�� ���ڴ�СΪ3ʱ��
    //[4 3 5] 4 3 3 6 7 ���������ֵΪ5
    //4 [3 5 4] 3 3 6 7 ���������ֵΪ5
    //4 3 [5 4 3] 3 6 7 ���������ֵΪ5
    //4 3 5 [4 3 3] 6 7 ���������ֵΪ4
    //4 3 5 4 [3 3 6] 7 ���������ֵΪ6
    //4 3 5 4 3 [3 6 7] ���������ֵΪ7
    //������鳤��Ϊn�� ���ڴ�СΪw�� ��һ������n-w+1�����ڵ����ֵ��
    //��ʵ��һ��������
    //���룺 ��������arr�� ���ڴ�СΪw��
    //����� һ������Ϊn-w+1������res�� res[i]��ʾÿһ�ִ���״̬�µ����ֵ��
    //�Ա���Ϊ���� ���Ӧ�÷���{5,5,5,4,6,7}

    //ʹ��˫�߶���������ָ��l,r(l <= r)��ʵ��,���Ҫȥ�����ֵ,�����е�ֵ������Ϊ�Ӵ�С����,ÿ��ȡ���ֵ��Ϊȡ������ͷ����ֵ����
    //�����д洢����Ԫ�ص��±�,��Ϊͨ���±�Ϳ���ȡ�������е�ֵ
    //r���Ʊ�ʾ�����н����µ�Ԫ��,�������дӺ��濪ʼС�ڵ��ڵ�ǰr��Ӧ��Ԫ�ص��±궼ɾ��,��Ϊ��Щλ�ò�������Ϊ���ֵ����
    //l���Ʊ�ʾ�����г�ȥ��Ԫ��,���������±���l�ƶ�֮ǰ��ͬ�±�����Ƴ�(������ڵĻ�)
    public static int[] slidingWindowMaxSum(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w > arr.length) return null;

        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> window = new LinkedList<>();

        int r = 0;
        int l = 0;
        //window�ĳ�ʼ��
        for (l = 0; l < w; l++) {   //ע��,�˴�l������ǽ�����++����3������ѭ����,���Ǵ�ʱlӦ����ָ��2��λ��
            //�����ں����һ��l--,������õķ�ʽ����������һ����ʱ��������!
            //�տ�ʼû����,ֱ�Ӽ�
            if (window.isEmpty()) {
                window.add(l);
                continue;
            }
            //��������,��ǰ�����еıȽ�,����ֻҪ���ڵ��ڶ���β����,һ��ɾ��
            while (!window.isEmpty() && arr[l] >= arr[window.peekLast()]) {
                window.pollLast();
            }
            window.add(l);
        }
        l--;
        res[0] = arr[window.peekFirst()];

        //window��Ų��ȷ����res�еĽ��
        for (int i = l + 1; i < arr.length; i++) {
            if (window.contains(r)) {
                window.remove(Integer.valueOf(r));
            }
            r++;
            while (!window.isEmpty() && arr[i] >= arr[window.peekLast()]) {
                window.pollLast();
            }
            window.add(i);
            res[i - l] = arr[window.peekFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = slidingWindowMaxSum(arr, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
