package com.newcoder.zuo3.basic.class08;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    //贪心,项目结束时间越早,就贪这个
    //一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目
    //的宣讲。
    //给你每一个项目开始的时间和结束的时间(给你一个数组， 里面
    //是一个个具体的项目)， 你来安排宣讲的日程， 要求会议室进行
    //的宣讲的场次最多。 返回这个最多的宣讲场次。
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class MyCompatator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end-o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs,new MyCompatator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            //如果开始时间在规定的开始时间之后,这个项目才能进行,并且star变为这个进行的项目的结束时间
            if (start < programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }





}
