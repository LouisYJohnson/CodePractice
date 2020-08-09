package exams.gensheixue;

public class test1 {
    class Solution{
        public String reverseParentheses(String s) {
            //两个指针,指向中间的左右两个括号
            //一个flag表示是否反转
            //如果碰到括号了,flag反转
            //每次找到一个括号,指针向内查找,直到找到另外一个同方向括号停止,这之间的数据根据flag
            //判断是否旋转
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    count++;
                }
            }
            int[] leftIndex = new int[count];
            int[] rightIndex  = new int[count];
            int index = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    leftIndex[index++] = i;
                }
            }
            index = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ')') {
                    rightIndex[index++] = i;
                }
            }

            //两个数组分别存储这括号的下标,这样就可以直接找到对应的括号位置,和反转字符数组了
            //奇数转,偶数不转
            StringBuilder helpSB = new StringBuilder();
            int flag = (count & 1) == 1 ? 1 : 0;

            for (int i = leftIndex.length - 1; i >= 0; i--) {
                int tempLeft = leftIndex[i];
                int tempRight = rightIndex[i];
                String tempString = s.substring(tempLeft + 1, tempRight);
                if (flag == 1) {

                    flag = 0;
                }

            }


            int lastLeftIndexPre = 0;
            int fitstRightIndex = 0;
            return null;


        }

        //输入一个字符串,返回他的逆序
        public String reverseString(String s) {
            char[] helpChar = s.toCharArray();
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                char temp = helpChar[left];
                helpChar[left] = helpChar[right];
                helpChar[right] = temp;
                left++;
                right++;
            }
            return String.valueOf(helpChar);

        }
    }
}
