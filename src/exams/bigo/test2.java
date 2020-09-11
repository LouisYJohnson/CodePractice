package exams.bigo;

public class test2 {
    public class Node{
        public int value;
        public Node next;
    }
    public int haveCircle(Node root) {
        Node slow = root;
        Node fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                return -1;
            }
            if (fast == slow) {
                break;
            }
        }

        fast = root;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        int res = 1;
        slow = slow.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            res++;
        }
        return res;
    }
}
