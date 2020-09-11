package exams.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int maxValue = Integer.MIN_VALUE;
    public static void main(String[] args) {
        //首先将这棵树构造出来(没有必要构造Node,而是用一个HashMap来装这个树)
        //key为节点的id,value为一个字符数组,分别装了value,leftSon与rightSon的id

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        HashMap<Integer, ArrayList<Integer>> helpDic = new HashMap<>();
        //获取所有的节点,并将其装入map中
        for(int i = 1; i <= num; i++){
            ArrayList<Integer> helpArray = new ArrayList<Integer>();
            for(int j = 0; j < 4; j++){
                int x = scanner.nextInt();
                helpArray.add(x);
            }
            helpDic.put(i, helpArray);
        }
        process(helpDic.get(1), helpDic);
        System.out.println(maxValue);
    }


    private static int process(ArrayList<Integer> integers, HashMap<Integer, ArrayList<Integer>> helpDic) {
        if (integers == null) {
            return 0;
        }

        int leftNum = process(helpDic.get(integers.get(2)), helpDic);
        int rightNum = process(helpDic.get(integers.get(3)), helpDic);

        int priceNewPath1 = integers.get(1) ^ leftNum ^ rightNum;
        int priceNewPath2 = integers.get(1)  ^ rightNum;
        int priceNewPath3 = integers.get(1) ^ leftNum;
        int priceNewPath4 = integers.get(1);

        int tempMax = Math.max(priceNewPath1, Math.max(priceNewPath2, Math.max(priceNewPath3, priceNewPath4)));
        maxValue = Math.max(tempMax, maxValue);
        return Math.max(priceNewPath2, Math.max(priceNewPath3, priceNewPath4));
    }


}
