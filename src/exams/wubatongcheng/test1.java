package exams.wubatongcheng;

import java.util.*;

public class test1 {
    public class Solution {
        /**
         *
         * @param values string字符串ArrayList<ArrayList<>>
         * @return string字符串ArrayList
         */
        public ArrayList<String> findCommonString (ArrayList<ArrayList<String>> values) {
            // write code here
            ArrayList<String> res = new ArrayList<>();
            if (values == null) {
                return res;
            }
            LinkedHashMap<String, Integer> helpMap = new LinkedHashMap<>();
            int arraySize = values.size();

            for (ArrayList<String> value : values) {
                LinkedHashSet<String> helpSet = new LinkedHashSet<>();
                helpSet.addAll(value);
                for (String s : helpSet) {
                    if (!helpMap.containsKey(s)) {
                        helpMap.put(s, 1);
                    }else {
                        helpMap.put(s, helpMap.get(s) + 1);
                    }
                }
            }
            for (String s : helpMap.keySet()) {
                if (helpMap.get(s) == arraySize) {
                    res.add(s);
                }
            }
            return res;
        }
    }
}
