package leetcode.topinterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC49GroupAnagrams {
    //给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    //
    //示例:
    //
    //输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
    //输出:
    //[
    //  ["ate","eat","tea"],
    //  ["nat","tan"],
    //  ["bat"]
    //]
    //说明：
    //
    //所有输入均为小写字母。
    //不考虑答案输出的顺序。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/group-anagrams
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    //本题中字母个数也是算在内的,比如aabbb和bbaaa不是异位,而aabbb和bbbaa就是异位,异位指字母与字母出现个数相同,而顺序有可能不同
    //都是构建哈希映射的key,分别为按照排序字符串分组与按照字符串中字符出现个数作为key
    //按照排序字符串比较简单,但是时间复杂度比较高,所以这里使用字符串中字符出现个数来实现
    //https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode/
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> helpMap = new HashMap<>();    //这个HashMap中key为下面构建的key,value为同一个key对应的字符串数组
            for (int i = 0; i < strs.length; i++) {
                String tempStr = strs[i];
                char[] tempStr2Chars = tempStr.toCharArray();
                int[] countCharNum = new int[26];
                //数每个字母出现的个数
                for (char tempStr2Char : tempStr2Chars) {
                    countCharNum[tempStr2Char - 'a']++;
                }
                //构建key
                StringBuilder tempKey = new StringBuilder();
                for (int i1 : countCharNum) {
                    tempKey.append("#");
                    tempKey.append(i1);
                }
                String tempKey2Str = tempKey.toString();
                //查找key是否出现过
                if (helpMap.containsKey(tempKey2Str)) {
                    //出现过,加入到对应的分组中
                    helpMap.get(tempKey2Str).add(tempStr);
                }else {
                    //没出现过,map中创建一个新的分组
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(tempStr);
                    helpMap.put(tempKey2Str, tempList);
                }
            }
            //将map中所有的value,分组后放入List<List<String>>中
            List<List<String>> res = new ArrayList<>();
            for (List<String> value : helpMap.values()) {
                res.add(value);
            }
            return res;
        }
    }

//    //这个方法写的偏离题意,将所有用了相同字符的字符串分为一组
//    class Solution {
//        public List<List<String>> groupAnagrams(String[] strs) {
//            //从头遍历字符串数组,将其分解为字符数组(先排序),去重后(LinkedHashSet),
//            // 看后面的字符串经过相同的步骤是否能在LinkedHashSet中找到,如果找到了,放入对应分组(HashMap),如果没找到,开一个新分组
//            HashMap<String, Integer> helpMap = new HashMap<>(); //key为去重排序后的字符串,value为分组下标
//            List<List<String>> res = new ArrayList<>();
//            for (int i = 0; i < strs.length; i++) {
//                char[] curStr2Chars = strs[i].toCharArray();
//                //排序,为了去重后加入字典的字符串相同
//                Arrays.sort(curStr2Chars);
//                //去重
//                LinkedHashSet<Character> helpSet = new LinkedHashSet<>();
//                for (char curStr2Char : curStr2Chars) {
//                    helpSet.add(curStr2Char);
//                }
//                //构建对应到字典中的序号所需的字符串
//                StringBuilder tempDic = new StringBuilder();
//                for (Character character : helpSet) {
//                    tempDic.append(character);
//                }
//                String tempDic2Str = tempDic.toString();
//                //如果字符串已经在字典中,则将本次遍历到的字符串原串放到对应下标上的位置
//                //如果不在字典中,字典中再创建新的key,对应value为下一个下标,并将其放入新的数组中
//                if (helpMap.containsKey(tempDic2Str)) {
//                    int tempNum = helpMap.get(tempDic2Str);
//                    res.get(tempNum).add(strs[i]);
//                } else if (!helpMap.containsKey(tempDic2Str)) {
//                    List<String> tempList = new ArrayList<>();
//                    helpMap.put(tempDic.toString(), res.size());
//                    tempList.add(strs[i]);
//                    res.add(tempList);
//                }
//
//            }
//            return res;
//        }
//    }

//    public static void main(String[] args) {
//        String[] tests.test = new String[]{"eat", "tea", " tan", "ate", "nat", "bat"};
//        Solution solution = new LC49().new Solution();
//        solution.groupAnagrams(tests.test);
//    }
}
