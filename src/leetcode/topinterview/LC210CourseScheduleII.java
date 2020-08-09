package leetcode.topinterview;

import java.util.LinkedList;

public class LC210CourseScheduleII {
    //现在你总共有 n 门课需要选，记为 0 到 n-1。
    //在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，
    // 我们用一个匹配来表示他们: [0,1]
    //给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
    //可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
    //示例 1:
    //输入: 2, [[1,0]]
    //输出: [0,1]
    //解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
    //示例 2:
    //输入: 4, [[1,0],[2,0],[3,1],[3,2]]
    //输出: [0,1,2,3] or [0,2,1,3]
    //解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
    //     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
    //说明:
    //输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
    //你可以假定输入的先决条件中没有重复的边。
    //提示:
    //这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
    //通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
    //拓扑排序也可以通过 BFS 完成。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/course-schedule-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            //很明显,拓扑排序
            //一个HashMap,一个队列,一个list实现
            //首先遍历一遍所有的节点,如果这些节点的所有入度都不为
            //先做入度为0的点,然后这些点的出度对应的位置入度-1
            //但是这里使用的是边缘列表表示的图形,给的也不是节点
            //图的表示方法
            //https://blog.csdn.net/Jurbo/article/details/74911028
            //这里其实不用非要构造成Node的形式
            //首先遍历课程,并计算所有课的前置课程的个数
            int[] preCourse = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                preCourse[prerequisite[0]]++;
            }
            //构建一个队列,专门存放没有前置课程的课
            LinkedList<Integer> helpList = new LinkedList<>();
            for (int i = 0; i < preCourse.length; i++) {
                if (preCourse[i] == 0) {
                    helpList.addLast(i);
                }
            }
            int[] res = new int[numCourses];
            int index = 0;
            //每次从课程没有前置课程的课中拿出一节课当作上过了
            //然后所以以这节课为前置课程的课,前置课程数量-1,并看是否能加入到这个无前置课程的课中
            while (!helpList.isEmpty()) {
                int tempClass = helpList.removeFirst();
                res[index++] = tempClass;
                for (int[] prerequisite : prerequisites) {
                    if (prerequisite[1] == tempClass) { //如果前置课程学完了,那就将这个课程对应的前置课程个数矩阵相应位置--
                        if (--preCourse[prerequisite[0]] == 0) {
                            helpList.addLast(prerequisite[0]);
                        }
                    }
                }
            }
            //注意,要学完所有课程才能返回那个排序的数组,否则只能返回一个空数组
            return index == res.length ? res : new int[0];
        }
    }
}
