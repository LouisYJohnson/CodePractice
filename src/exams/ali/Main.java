package exams.ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //遍历res,找到所有字典排序最小的
        ArrayList<Integer> helpList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        process(n, m, 1, helpList, res);
        while (!res.isEmpty()) {
            ArrayList<Integer> temp = res.remove(0);
            String temp1 = "";
            for (Integer integer : temp) {
                temp1 += integer;
                temp1 += " ";
            }
            temp1.substring(0,temp1.length() - 1);
            System.out.println(temp1);
        }

    }

    public static void process(int n, int m, int start, ArrayList<Integer> helpList, ArrayList<ArrayList<Integer>> res){
        if (m < 0) {
            return;
        }
        if (m == 0) {
            int[] help = new int[helpList.size()];
            int index = 0;
            for (Integer integer : helpList) {
                help[index++] = integer;
            }
            Arrays.sort(help);
            ArrayList<Integer> help1 = new ArrayList<>();
            for (int i : help) {
                help1.add(i);
            }
            res.add(help1);
            return;
        }

        for (int i = start; i <= n; i++) {
            helpList.add(i);
            process(n, m - 1, i + 1, helpList, res);
            helpList.remove(helpList.size() - 1);
        }

    }

}
