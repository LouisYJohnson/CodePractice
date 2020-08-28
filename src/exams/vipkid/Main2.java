package exams.vipkid;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start = sc.next();
        String end = sc.next();
        List<String> dic = new LinkedList<>();
        while (sc.hasNext()) {
            dic.add(sc.next());
        }
        System.out.println(process(start, end, dic));
    }

    public static int process(String start, String end, List<String> dic) {
        Set<String> wordSet = new HashSet<>(dic);
        if (wordSet.size() == 0 || !wordSet.contains(end)) {
            return 0;
        }
        wordSet.remove(start);

        LinkedList<String> helpQ = new LinkedList<>();
        helpQ.addLast(start);
        HashSet<String> used = new HashSet<>();
        used.add(start);
        int startLen = start.length();
        int step = 1;
        while (!helpQ.isEmpty()) {
            int curSize = helpQ.size();
            for (int i = 0; i < curSize; i++) {
                String tempWord = helpQ.poll();
                char[] tempArray = tempWord.toCharArray();
                for (int j = 0; j < startLen; j++) {
                    char oriChar = tempArray[j];
                    for (int k = 'a'; k <= 'z'; k++) {
                        if (k == oriChar) {
                            continue;
                        }
                        tempArray[j] = (char) k;
                        String next = String.valueOf(tempArray);
                        if (wordSet.contains(next)) {
                            if (next.equals(end)) {
                                return step + 1;
                            }
                            if (!used.contains(next)) {
                                helpQ.addLast(next);
                                used.add(next);
                            }
                        }
                    }
                    tempArray[j] = oriChar;
                }
            }
            step++;
        }
        return 0;
    }
}
