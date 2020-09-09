package leetcode.topinterview;

import java.util.LinkedList;

public class LC207CourseSchedule {
    //你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
    //在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
    //给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
    //示例 1:
    //输入: 2, [[1,0]]
    //输出: true
    //解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
    //示例 2:
    //输入: 2, [[1,0],[0,1]]
    //输出: false
    //解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
    //提示：
    //输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
    //你可以假定输入的先决条件中没有重复的边。
    //1 <= numCourses <= 10^5
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/course-schedule
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * 这题的条件是输出学习课程的顺序,而本题输出能否上完这些课就行,是一道题
     * {@link LC210CourseScheduleII}
     */
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //首先创建一个数组,下标表示课程编号,元素为该课程的前置课程个数
            int[] preClassNums = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                preClassNums[prerequisite[0]]++;
            }
            //创建一个list,装所有没有前置课程的课的标号
            LinkedList<Integer> noPreClasses = new LinkedList<>();
            for (int i = 0; i < preClassNums.length; i++) {
                if (preClassNums[i] == 0) {
                    noPreClasses.addLast(i);
                }
            }
            int res = 0;    //结果就是上完课程的个数,最终和总课程个数比较,相等才返回正确结果
            //每次弹出队列的所有元素,并且在弹出时重新计算每节课依赖的课程数,如果依赖的前置课程数为0,则加入队列,直到队列为空
            while (!noPreClasses.isEmpty()) {
                int curSize = noPreClasses.size();
                res += curSize;
                for (int i = 0; i < curSize; i++) {
                    //一节课上完了,重新统计如果这个课上完了,剩下课程是否有0依赖的
                    int tempClass = noPreClasses.removeFirst();
                    //遍历所有的课程,如果出现了新的依赖课程为0,则将其加入队列中
                    for (int[] prerequisite : prerequisites) {
                        if (tempClass == prerequisite[1]) {
                            if (--preClassNums[prerequisite[0]] == 0) {
                                noPreClasses.addLast(prerequisite[0]);
                            }
                        }
                    }
                }
            }
            return res == numCourses;
        }
    }
}
