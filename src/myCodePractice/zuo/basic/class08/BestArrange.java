package myCodePractice.zuo.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    //һЩ��ĿҪռ��һ�������������� �����Ҳ���ͬʱ����������Ŀ
    //��������
    //����ÿһ����Ŀ��ʼ��ʱ��ͽ�����ʱ��(����һ�����飬 ����
    //��һ�����������Ŀ)�� ���������������ճ̣� Ҫ������ҽ���
    //�������ĳ�����ࡣ ������������������Ρ�

    //��Ŀ����ʱ��Խ��,�ͽ������Ŀ�Ȱ���,��̰���
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Program[] programs) {
        if (programs == null || programs.length == 0) return 0;

        //���ս���ʱ������,����ʱ��Խ��,������ǰ��
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int res = 1;
        int helpEnd = programs[0].end;

        for (int i = 1; i < programs.length; i++) {
            //�����ǰ����Ŀ�ʼʱ������һ���������֮��,���������ܿ�
            if (programs[i].start >= helpEnd) {
                res++;
                helpEnd = programs[i].end;
            }
        }

        return res;
    }
}
