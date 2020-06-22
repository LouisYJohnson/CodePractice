package myCodePractice.zuo.basic.class07;

public class Hanno {
    public static void printHanno(int n) {
        if (n > 0) {

        }
    }

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
