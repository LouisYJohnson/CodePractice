package myCodePractice.zuo.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    //一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目
    //的宣讲。
    //给你每一个项目开始的时间和结束的时间(给你一个数组， 里面
    //是一个个具体的项目)， 你来安排宣讲的日程， 要求会议室进行
    //的宣讲的场次最多。 返回这个最多的宣讲场次。

    //项目结束时间越早,就贪这个
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

        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int res = 1;
        int helpEnd = programs[0].end;

        for (int i = 1; i < programs.length; i++) {
            if (programs[i].start >= helpEnd) {
                res++;
                helpEnd = programs[i].end;
            }
        }

        return res;
    }
}
