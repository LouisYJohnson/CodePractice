package exams.wubatongcheng;

public class test2 {
    public class Solution {
        /**
         *
         * @param a int整型
         * @param b int整型
         * @return int整型
         */
        public int question (int a, int b) {
            // write code here
            for (int i = 0; i <= 500; i++) {
                int a1 = a + i;
                int a2 = b + i;
                double temp1 = Math.sqrt(a1);
                double temp2 = Math.sqrt(a2);
                if (temp1 == (int) temp1 && temp2 == (int) temp2) {
                    return i;
                }
            }
            return -1;
        }
    }
}
