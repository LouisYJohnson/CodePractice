package leetcode.top100interview;

public class LC11ContainerWithMostWater {
    //注意,本题和TrappingRain不是一回事!
    //思路：
    //算法流程： 设置双指针 ii,jj 分别位于容器壁两端，根据规则移动指针（后续说明），并且更新面积最大值 res，直到 i == j 时返回 res。
    //指针移动规则与证明： 每次选定围成水槽两板高度 h[i]h[i],h[j]h[j] 中的短板，向中间收窄 11 格。以下证明：
    //设每一状态下水槽面积为 S(i, j)S(i,j),(0 <= i < j < n)(0<=i<j<n)，由于水槽的实际高度由两板中的短板决定，则可得面积公式 S(i, j) = min(h[i], h[j]) × (j - i)S(i,j)=min(h[i],h[j])×(j−i)。
    //在每一个状态下，无论长板或短板收窄 11 格，都会导致水槽 底边宽度 -1−1：
    //若向内移动短板，水槽的短板 min(h[i], h[j])min(h[i],h[j]) 可能变大，因此水槽面积 S(i, j)S(i,j) 可能增大。
    //若向内移动长板，水槽的短板 min(h[i], h[j])min(h[i],h[j]) 不变或变小，下个水槽的面积一定小于当前水槽面积。
    //因此，向内收窄短板可以获取面积最大值。换个角度理解：
    //若不指定移动规则，所有移动出现的 S(i, j)S(i,j) 的状态数为 C(n, 2)C(n,2)，即暴力枚举出所有状态。
    //在状态 S(i, j)S(i,j) 下向内移动短板至 S(i + 1, j)S(i+1,j)（假设 h[i] < h[j]h[i]<h[j] ），则相当于消去了 {S(i, j - 1), S(i, j - 2), ... , S(i, i + 1)}S(i,j−1),S(i,j−2),...,S(i,i+1) 状态集合。而所有消去状态的面积一定 <= S(i, j)<=S(i,j)：
    //短板高度：相比 S(i, j)S(i,j) 相同或更短（<= h[i]<=h[i]）；
    //底边宽度：相比 S(i, j)S(i,j) 更短。
    //因此所有消去的状态的面积都 < S(i, j)<S(i,j)。通俗的讲，我们每次向内移动短板，所有的消去状态都不会导致丢失面积最大值 。
    //复杂度分析：
    //时间复杂度 O(N)O(N)，双指针遍历一次底边宽度 NN 。
    //空间复杂度 O(1)O(1)，指针使用常数额外空间。
    //作者：jyd
    //链接：https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    class Solution {
        public int maxArea(int[] height) {
            //双指针解法
            //双指针一开始指向最左边和最右边,然后按照条件向中间靠拢,left == right的时候结束
            //只要向中间靠拢,底边都是会-1的,所以每次都将短板向中间收才有可能获得最大值
            //只有移动短板,面积才可能变大,如果移动长版,短板还在那里放着,只有可能不变或者变小
            if (height == null) {
                return 0;
            }

            int left = 0;
            int right = height.length - 1;

            int res = 0;
            while (left < right) {
                //这题的意思其实就是两个格子之间也能装水,所以right-left就是结果
                res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
                if (height[left] < height[right]) {
                    left++;
                }else {
                    right--;
                }
            }
            return res;
        }
    }
}
