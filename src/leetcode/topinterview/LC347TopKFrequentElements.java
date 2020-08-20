package leetcode.topinterview;

import java.util.HashMap;
import java.util.Map;

public class LC347TopKFrequentElements {
    //给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
    //示例 1:
    //输入: nums = [1,1,1,2,2,3], k = 2
    //输出: [1,2]
    //示例 2:
    //输入: nums = [1], k = 1
    //输出: [1]
    //提示：
    //你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    //你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    //题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    //你可以按任意顺序返回答案。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/top-k-frequent-elements
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            //构建一个哈希表,表中对应每个值出现的次数
            //然后再写一个堆,堆中对应了所有数,但是比较是按照出现的频率比较的(heapify与heapInsert)
            HashMap<Integer, Integer> helpMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!helpMap.containsKey(nums[i])) {
                    helpMap.put(nums[i], 1);
                } else {
                    helpMap.put(nums[i], helpMap.get(nums[i]) + 1);
                }
            }
            int[] res = new int[k];
            //将hashMap中所有的数都放入堆中,并heapInsert
            int[] heap = new int[helpMap.size()];
            int heapSize = heap.length;
            int index = 0;
            for (Map.Entry<Integer, Integer> integerIntegerEntry : helpMap.entrySet()) {
                heap[index] = integerIntegerEntry.getKey();
                heapInsert(heap, index++, helpMap);
            }
            //将堆中前k个元素弹出来
            for (int i = 0; i < k; i++) {
                res[i] = heap[0];
                swap(heap, 0, heapSize - 1);
                heapSize--;
                heapify(heap, 0, heapSize, helpMap);
            }

            return res;
        }

        //heapify,大根堆,从上到下
        public void heapify(int[] heap, int i, int heapSize, HashMap<Integer, Integer> helpMap) {
            //分为只有左子,左右子都有,左右子都没有
            while (i < heapSize) {
                int leftSon = i * 2 + 1;
                int rightSon = leftSon + 1;
                if (rightSon < heapSize) {  //左右子都有,如果左右子中最大的比父大,那么最大的那个子和父换
                    //真正参与比较的,是数组下标上元素在map中对应的value
                    int maxSonIndex = helpMap.get(heap[leftSon]) > helpMap.get(heap[rightSon]) ? leftSon : rightSon;
                    if (helpMap.get(heap[maxSonIndex]) > helpMap.get(heap[i])) {
                        swap(heap, i, maxSonIndex);
                        i = maxSonIndex;
                    } else { //如果都比父小,不换,也不会继续换了
                        break;
                    }
                } else if (leftSon < heapSize) {  //只有左子
                    if (helpMap.get(heap[leftSon]) > helpMap.get(heap[i])) {
                        swap(heap, i, leftSon);
                        i = leftSon;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        //heapInsert,大根堆,从下到上
        public void heapInsert(int[] heap, int i, HashMap<Integer, Integer> helpMap) {
            while (i >= 0) {
                int father = (i - 1) / 2;
                if (helpMap.get(heap[father]) < helpMap.get(heap[i])) {
                    swap(heap, i, father);
                    i = father;
                } else {
                    break;
                }
            }
        }


        public void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] res = new LC347TopKFrequentElements().new Solution().topKFrequent(nums, 2);
        System.out.println(1);
    }
}
