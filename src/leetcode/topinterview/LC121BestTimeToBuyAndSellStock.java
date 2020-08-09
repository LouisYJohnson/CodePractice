package leetcode.topinterview;

public class LC121BestTimeToBuyAndSellStock {
    //给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    //
    //如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
    //
    //注意：你不能在买入股票前卖出股票。
    //
    // 
    //
    //示例 1:
    //
    //输入: [7,1,5,3,6,4]
    //输出: 5
    //解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    //     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    //示例 2:
    //
    //输入: [7,6,4,3,1]
    //输出: 0
    //解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int maxProfit(int[] prices) {
            //遍历,找到数组中的最小值,然后在当前位置考虑如果是在之前的最小值那里买入的现在卖出能赚多少
            //股票卖出赚的钱和你放了多少天没关系,和你卖出的时候和买入的价格有关系!!!
            int res = 0;
            int minValue = Integer.MAX_VALUE;

            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minValue) { //如果现在的价格比史低还要低,肯定不在今天卖,只更新史低
                    minValue = prices[i];
                }else {
                    res = Math.max(res, prices[i] - minValue);
                }
            }
            return res;
        }
    }
}
