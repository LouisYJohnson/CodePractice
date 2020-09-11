package exams.bigo;

public class test3 {
    public class Node{
        public int value;
        public Node next;
    }
    public static void indexOfN(int n, Node root) {
        int len = length(root);
        int x = 0;
        while (x < len/3) {
            root = root.next;
            x++;
        }
        System.out.println(root.value);
    }

    public static int length(Node root) {
        Node temp = root;
        int ans = 0;
        while (temp != null) {
            ans++;
            temp = temp.next;
        }
        return ans;
    }
}
