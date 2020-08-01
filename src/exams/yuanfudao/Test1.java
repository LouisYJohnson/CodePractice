package exams.yuanfudao;


import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
        }

        Help[] helps = new Help[n * 2];
        int i = 0;
        while (i < n) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            helps[i * 2] = new Help(l, 0);
            helps[i * 2 + 1] = new Help(r, 1);
            i++;
        }

        Arrays.sort(helps, new Comparator<Help>() {
            @Override
            public int compare(Help o1, Help o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val > o2.val) {
                    return 1;
                } else {
                    if (o1.type == o2.type) {
                        return 0;
                    }else {
                        if (o1.type > o2.type) {
                            return -1;
                        }else {
                            return 1;
                        }
                    }
                }
            }
        });

        int res = 1;
        int count = 0;
        for (int j = 0; j < 2 * n; j++) {
            if (helps[j].type == 0) {
                count++;
                res = Math.max(res, count);
            }else {
                count--;
            }
        }
        System.out.println(res);
    }

    static class Help {
        public int type;
        public int val;

        public Help(int val, int type) {
            this.type = type;
            this.val = val;
        }
    }

}
