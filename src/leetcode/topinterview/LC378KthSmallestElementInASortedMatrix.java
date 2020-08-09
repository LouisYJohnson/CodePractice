package leetcode.topinterview;

public class LC378KthSmallestElementInASortedMatrix {
    //给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
    //请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
    //示例：
    //matrix = [
    //   [ 1,  5,  9],
    //   [10, 11, 13],
    //   [12, 13, 15]
    //],
    //k = 8,
    //返回 13。
    //提示：
    //你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业kth-smallest-element-in-a-sorted-matrix转载请注明出处。
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            //构建一个小根堆,每次取元素,取到k个为止
            int[] mat2OneD = new int[matrix.length * matrix[0].length];
            int heapSize = mat2OneD.length;
            int index = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    mat2OneD[index++] = matrix[i][j];
                }
            }
            //创建小根堆
            for (int i = 0; i < mat2OneD.length; i++) {
                heapInsert(mat2OneD, i);
            }
            //每次弹出一个元素,直到弹出k-1个为止,此时堆顶的就是要的那个元素
            for (int i = 0; i < k - 1; i++) {
                swap(mat2OneD, 0, heapSize - 1);
                heapify(mat2OneD, 0, --heapSize);
            }

            return mat2OneD[0];
        }

        //小根堆
        //heapInsert(向上)
        //子与父比,如果父比子大,交换,知道父比子小或者到头了为止
        public void heapInsert(int[] heap, int i) {
            while (heap[(i - 1) / 2] > heap[i]) {
                swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        //heapify(向下)
        //父与子比,如果大于子的任何一个则与所有子中更小的那个进行交换
        //i表示要进行heapify操作的元素所在下标,heapSize为堆的大小(里面最多有几个元素)
        public void heapify(int[] heap, int i, int heapSize) {
            while (i < heapSize) {
                int left = i * 2 + 1;
                int right = left + 1;
                //三种情况,左子右子都在,只有左子,左右子都不在
                if (right < heapSize) { //左右子都有
                    //找到左右子中最小数对应的下标
                    int minLeftRightIndex = Math.min(heap[left], heap[right]) == heap[left] ? left : right;
                    if (heap[i] > heap[minLeftRightIndex]) {
                        //如果父比那个最小的大,则交换
                        swap(heap, i, minLeftRightIndex);
                        i = minLeftRightIndex;
                    }else { //如果父最小,直接break,不要换了
                        break;
                    }
                } else if (left < heapSize) {   //只有左子
                    if (heap[i] > heap[left]) {
                        swap(heap, i, left);
                        i = left;
                    }else {
                        break;
                    }
                }else { //左右子都没有
                    break;
                }
            }
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {
        System.out.println(-1/2);
    }
}
