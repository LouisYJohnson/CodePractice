package myCodePractice.zuo.advance.class05;

public class ExpressionNumber {
    //表达式得到期望结果的组成种数
    //【题目】
    //给定一个只由0（假） 、 1(真)、 &(逻辑与)、 |（逻辑或） 和^(异或)五种字符组成的字符
    //串express， 再给定一个布尔值desired。 返回express能有多少种组合方式， 可以达到
    //desired的结果。
    //【举例】
    //express="1^0|0|1"， desired=false。
    //只有1^((0|0)|1)和1^(0|(0|1))的组合可以得到false， 返回2。
    //express="1"， desired=false。
    //无组合则可以得到false， 返回0。

    //思路:以每一个符号作为分界线分别计算两边的为true或false的可能组合个数,然后看以当前符号作为组合,将true与false
    //  进行组合,返回组合总次数

    //因为返回的数据中有两个数据,而且还都有用,所以都装到一个对象中返回
    //动态规划:因为要返回两个值,所以说动态规划返回两个表

    public static class ReturnData {
        private int trueNum;
        private int falseNum;

        public ReturnData(int trueNum, int falseNum) {
            this.trueNum = trueNum;
            this.falseNum = falseNum;
        }
    }

    public static int getExpressionNumber1(String express, boolean desired) {
        if (!isValid(express)) return -1;

        char[] express2Chars = express.toCharArray();
        ReturnData res = process(express2Chars, 0, express2Chars.length - 1);
        return desired ? res.trueNum : res.falseNum;
    }

    //递归函数功能:
    //  给定表达式字符数组,左右边界与组合结果desired,返回所有组合成true或者false的可能的组合个数
    public static ReturnData process(char[] express2Chars, int l, int r) {
        //base case
        if (l == r) {
            if (express2Chars[l] == '1') return new ReturnData(1, 0);
            if (express2Chars[l] == '0') return new ReturnData(0, 1);
        }

        int leftTrue = 0;
        int leftFalse = 0;
        int rightTrue = 0;
        int rightFalse = 0;
        int trueNum = 0;
        int falseNum = 0;
        //以每一个符号作为分界线,来算两边的组合有多少
        for (int i = l + 1; i < r; i += 2) {
            ReturnData leftReturn = process(express2Chars, l, i - 1);
            ReturnData rightReturn = process(express2Chars, i + 1, r);
            leftTrue = leftReturn.trueNum;
            leftFalse = leftReturn.falseNum;
            rightTrue = rightReturn.trueNum;
            rightFalse = rightReturn.falseNum;

            if (express2Chars[i] == '&') {
                trueNum += leftTrue * rightTrue;
                falseNum += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            } else if (express2Chars[i] == '|') {
                trueNum += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                falseNum += leftFalse * rightFalse;
            } else if (express2Chars[i] == '^') {
                trueNum += leftTrue * rightFalse + leftFalse * rightTrue;
                falseNum += leftTrue * rightTrue + leftFalse * rightFalse;
            }
        }
        return new ReturnData(trueNum, falseNum);
    }

    //递归改动态规划
    public static int getExpressionNumber2(String express, boolean desire) {
        if (!isValid(express)) return -1;

        char[] express2Chars = express.toCharArray();
        int[][] trueMap = new int[express2Chars.length][express2Chars.length];
        int[][] falseMap = new int[express2Chars.length][express2Chars.length];
        //首先根据base case填初始值
        for (int i = 0; i < trueMap.length; i++) {
            trueMap[i][i] = express2Chars[i] == '1' ? 1 : 0;
            falseMap[i][i] = express2Chars[i] == '0' ? 1 : 0;
        }
        //然后根据递归函数的调用计算出普遍位置上的trueMap与falseMap的数值
        //只有上半部分的map参与运算
        //任意位置的数需要他下面和左边的数,所以从下往上填表
        //因为对角线位置已经填过了,所以从倒数第2行开始填(写倒数第一行也没啥问题)
        for (int row = express2Chars.length - 1; row >= 0; row--) {
            for (int col = row; col < express2Chars.length; col++) {
                for (int i = row + 1; i < col; i += 2) {
                    //按照规则,process中的参数l,r就是解空间的坐标
                    //递归函数中的for (int i = l + 1; i < r; i += 2)
                    //意思就是,在当前解空间坐标为l,r的位置上,进行for循环,从当前位置的横坐标+1开始到当前位置的纵坐标-1结束
                    //为什么上面的l对应到这里的row,上面的r对应到这里的col?
                    //  因为这里的row和col就是解空间中的坐标(遍历),上面的l和r在动态规划中实际上表示的是解空间中的当前坐标值
                    //  同理,trueMap和falseMap分别对应了leftTrue,leftFalse,rightTrue,rightFalse
                    //  其实就是把递归函数中的变量替换成解空间中对应的变量
                    //递归函数改动态规划,在看完base case的固定值后,是要求任意l,r位置上的组合数,也就是按照递归函数中的规则填表
                    //建立解空间的变量为表中的坐标,递归函数的子过程为解空间中对应的位置上的值,按照递归函数中的语句填表即可
                    //而递归函数中通过递归调用返回了四个值,也就分别对应了trueMap与falseMap上的四个位置
                    int leftTrue = trueMap[row][i - 1];
                    int leftFalse = falseMap[row][i - 1];
                    int rightTrue = trueMap[i + 1][col];
                    int rightFalse = falseMap[i + 1][col];
                    if (express2Chars[i] == '&') {
                        trueMap[row][col] += leftTrue * rightTrue;
                        falseMap[row][col] += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                    } else if (express2Chars[i] == '|') {
                        trueMap[row][col] += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                        falseMap[row][col] += leftFalse * rightFalse;
                    } else if (express2Chars[i] == '^') {
                        trueMap[row][col] += leftTrue * rightFalse + leftFalse * rightTrue;
                        falseMap[row][col] += leftTrue * rightTrue + leftFalse * rightFalse;
                    }
                }
            }
        }
        return desire ? trueMap[0][express2Chars.length - 1] : falseMap[0][express2Chars.length - 1];
    }

    //检查字符串是否符合标准(偶数位上为数字1或0,奇数位上为符号,并且长度必须为奇数)
    public static boolean isValid(String express) {
        if (express == null || express.length() == 0) return false;
        if ((express.length() & 1) != 1) return false;

        char[] help = express.toCharArray();
        for (int i = 0; i < help.length; i++) {
            //奇数位上必须为符号
            if ((i & 1) == 1 && (help[i] != '|' && help[i] != '&' && help[i] != '^')) {
                return false;
            } else if ((i & 1) == 0 && help[i] != '1' && help[i] != '0') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(getExpressionNumber1(express, desired));
        System.out.println(getExpressionNumber2(express, desired));
    }
}
