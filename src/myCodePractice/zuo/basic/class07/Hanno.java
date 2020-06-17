package myCodePractice.zuo.basic.class07;

public class Hanno {
    //汉诺塔问题
    //打印n层汉诺塔从最左边移动到最右边的全部过程
    //三根杆,分别是from,to,help
    //分析过程,就是将n-1从from放到help上用to作为辅助
    //  然后将第n个从from放到to上
    //  最后将n-1个从help放到to上用from作为辅助
    public static void printHanno (int n) {
        if (n > 0) {

        }
    }

    //递归函数功能:
    //输入汉诺塔层数n,from,to,help杆的名字,返回n层汉诺塔从from到to使用help辅助的移动顺序
    public static void process(int n, String from, String to, String help) {
        //base case
        if (n == 1) {
            System.out.println("move from: " + from + " to: " + to);
            return;
        }

        process(n - 1, from, help, to);
        System.out.println("move from: " + from + " to:" + to);
        process(n - 1, help, to, from);
    }

}
