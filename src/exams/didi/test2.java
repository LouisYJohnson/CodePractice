package exams.didi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class test2 {
    //暴力法,取出所有的abc与acc
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //获取所有abc(a,b,c不能相同)
        ArrayList<Integer> abc = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            int a = i;
            for (int j = 0; j < 10; j++) {
                int b = j;
                for (int k = 0; k < 10; k++) {
                    int c = k;
                    if (a != b && a != c && b != c) {
                        int temp = i * 100 + j * 10 + k;
                        abc.add(temp);
                    }
                }
            }
        }
        //获取所有acc
        ArrayList<Integer> acc = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            int a = i;
            for (int j = 0; j < 10; j++) {
                int c = j;
                if (a != c) {
                    int temp = i * 100 + j * 10 + j;
                    acc.add(temp);
                }
            }
        }
        int res = 0;
        ArrayList<ArrayList<Integer>> helpList = new ArrayList<>();
        for (int i = 0; i < abc.size(); i++) {
            for (int j = 0; j < acc.size(); j++) {
                if (abc.get(i) + acc.get(j) == n) {
                    res++;
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(abc.get(i));
                    temp.add(acc.get(j));
                    helpList.add(temp);
                }
            }
        }
        if (res == 0) {
            System.out.println(0);
            return;
        }
        helpList.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0) - o1.get(1);
            }
        });
        System.out.println(helpList.size());
        for (ArrayList<Integer> arrayList : helpList) {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.print(arrayList.get(i));
                if (i < arrayList.size() - 1) {
                    System.out.print(" ");
                } else {
                    System.out.println();
                }
            }
        }

    }
}
