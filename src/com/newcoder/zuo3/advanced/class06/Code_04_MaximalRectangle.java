package com.newcoder.zuo3.advanced.class06;

import java.util.Stack;

public class Code_04_MaximalRectangle {
    //������Ӿ���Ĵ�С
    //����Ŀ��
    //����һ�����;���map�� ���е�ֵֻ��0��1���֣� ������ȫ��1
    //�����о��������У� ���ľ�������Ϊ1��������
    //���磺
    //1 1 1 0
    //���У� ���ľ���������3��1�� ���Է���3��
    //���磺
    //1 0 1 1
    //1 1 1 1
    //1 1 1 0
    //���У� ���ľ���������6��1�� ���Է���6��
    //��������:��һ����ʾֱ��ͼ������,�����ֱ��ͼ�����а��������Ķ��Ƿ�������(һ���������Ϊ1),
    // ����ͱ����ÿ�α�����һ����.���������ƽ��,ƽ�Ƶ��ĸ�λ�ñ�����˵�,��ͣ,�����������������������,
    // �����ֱ��ͼ�а��������Ķ��Ƿ�������(�õ��˵���ջ)�����Ͼ����������������ұ������С�����������ߺ��ұߵ�λ��,
    // Ȼ���Ҽ����ٳ����ƽ�Ƶĸ˵ĸ߶Ⱦ���Ҫ��������

    //��������ǻ����������չ,�ڴ�������,�ֱ���ÿ��Ϊ��(�������3*4�ľ���,
    // �������1*4������,�ֱ�����Ե�1,2,3��Ϊ��ʼ��ֱ��ͼ,�����ֵ�����Ը���Ϊ��ʼ,�����ж��ٸ�������1
    //�Ե�һ�п�ʼ:1 0 1 1,�ڶ���:2,1,2,2������:3,2,3,0),Ȼ��������Ļ��������е�����������,
    // Ȼ��ȡ��������е����ֵ,��Ϊ����

    //ʹ�õ���ջʵ�ֻ�������(����������Ҫ�ô�ջ�׵�ջ����С�����ջ�����)
    public static int maxFromBottom(int[] array) {
        if (array == null || array.length == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        //��Ϊ��ջ��ʱ�����ͬʱ�õ����Ҿ���������ұ������С������λ��,��ô�ڵ�ջ��ʱ��Ϳ������ÿ��λ���ϵ����
        //��Ϊͨ���±���ܹ�ȡ�������е�ֵ,����ջ�д�����±�
        stack.push(0);
        for (int i = 1; i < array.length; i++) {
            //���Ҫ��ջ����������ջ�еĴ�С�������������
            //һֱ����,�������ֵ������ջΪֹ,������Щ��������ֵ�Ҳ��������ұȱ�����ֵС��ֵ������������ǳ�ջ��ֵ
            //��Щ��������ֵ��ջ�������ֵ������߾�������ұ�����С��ֵ
            //�����ֵ��ͬ�Ļ�,������Ĵ�������Ҳ��ջ,�����ڳ�ջ��ʱ������������С��,��Ҫһֱ��ջ������
            //ֱ���ҵ�����ȵ���Ϊֹ
            while (!stack.isEmpty() && (array[stack.peek()] > array[i])) {
                int help = array[stack.pop()];
                //��ֵ��ͬ�����,��Ϊ��ֵͬ���±�ʵ����������һ���,�������ǵ�����϶�Ҳ��һ����
                //�ҵ���������С���ұ������СȻ�󵯸ɾ��˾ͺ���
                if (!stack.isEmpty() && help == array[stack.peek()]) {
                    while (!stack.isEmpty() && (help == array[stack.peek()])) {
                        stack.pop();
                    }
                    //���ջ���ɾ���,��˵���⴮��ȵ���,���û�б�����С����
                    if (stack.isEmpty()) {
                        max = Math.max(help * i, max);
                    } else { //���ջû�е��ɾ�,��ջ����һ����,����������ջ�е�ʱ���������,Ҳ������������С����
                        max = Math.max(help * (i - stack.peek() - 1), max);
                    }
                }
                //ջ��û����ֵͬ�����(ֻ����һ��),��Ϊ��ջ�Ͳ���ջ�����
                if (!stack.isEmpty()) {
                    max = Math.max(help * (i - stack.peek()) - 1, max);
                } else {
                    max = Math.max(help * i, max);
                }
            }
            stack.push(i);
        }
        //������,��������ʣ���ջ(���ջ�л������Ļ�)
        while (!stack.isEmpty()) {
            int help1 = array[stack.pop()];
            while (!stack.isEmpty() && array[stack.peek()] == help1) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                max = Math.max(help1 * array.length, max);
            } else {
                max = Math.max(help1 * (array.length - stack.peek() - 1), max);
            }
        }
        return max;
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        int[] help = new int[map[0].length];
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                help[j] = map[i][j] == 0 ? 0 : help[j] + map[i][j];
            }
            max = Math.max(maxFromBottom(help), max);
        }
        return max;
    }

    //for test
    //implemented by zuo:
    //����ԭ��:
    //���ڷ����˱���:��������ж����Ӧ��ֵͬ���±���һ��,Ҳ����ջ����һ���±������������Сֵ��Ϳ�����,
    //��ΪֻҪ����������ֵ�ܽ�ջ,�ͻ�һֱ��ջ,�������ջ�һ̸ֱ��ջ�ջ���ջ�е���һ���±��Ӧ����С�ڴ���ջ����
    //���˵�м��ж����Ӧ��ֵͬ���±�,��ô�м���Щmax�ǲ��������󵯳����Ǹ���ֵͬ���±��
    public static int maxRecSize1(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0},};
        System.out.println(maxRecSize(map));
    }
}
