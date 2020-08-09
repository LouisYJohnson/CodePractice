package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC93RestoreIpAddresses {
    //给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    //有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
    //示例:
    //输入: "25525511135"
    //输出: ["255.255.11.135", "255.255.111.35"]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/restore-ip-addresses
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            int[] segments = new int[4];    //用来装四个分好的ip地址
            process(s, 0, 0, segments, res);
            return res;
        }

        //递归:
        //  给定字符串,当前已经分好了多少个id(从第0个开始),
        //start表示start之前的都已经分好了,start与start之后的都还没分
        public void process(String s, int numOfId, int start, int[] segments, List<String> res) {
            //base case
            if (numOfId == 4 && start == s.length()) { //如果当前已经分好了(四个id都分完了,并且开始位置在s.length()的位置)
                StringBuilder tempRes = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    tempRes.append(segments[i]);
                    if (i != 3) {
                        tempRes.append('.');
                    }
                }
                res.add(tempRes.toString());
                return;
            }
            //如果还没找到4段ip就已经遍历完了字符串,那么直接回溯
            if (start == s.length()) {
                return;
            }
            //如果找到了4段ip但是当前还没遍历完字符串,也直接回溯
            if (numOfId == 4 && start < s.length()) {
                return;
            }

            //如果当前位置为0,那么只能让这个0单独做一位ip地址
            if (s.charAt(start) == '0') {
                segments[numOfId] = 0;
                process(s, numOfId + 1, start + 1, segments, res);
            }
            //一般情况,枚举所有可能性并递归
            int addr = 0;
            for (int i = start; i < s.length(); i++) {
                addr = addr * 10 + (s.charAt(i) - '0');
                if (addr > 0 && addr <= 255) {
                    segments[numOfId] = addr;   //不用删,下次来直接覆盖了,并且最终的结果一定会重新覆盖所有的可能
                    process(s, numOfId + 1, i + 1, segments, res);
                }else {
                    break;
                }

            }
        }
    }
}
