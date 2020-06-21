package myCodePractice.zuo.basic.class07;

public class SubArrayMaxSum {
    //����һ����ά���飬 ��ά�����е�ÿ�������������� Ҫ�������
    //���ߵ����½ǣ� ÿһ��ֻ�����һ������¡� ��;����������Ҫ��
    //�������� ������С��·����

    //�ݹ麯������:
    //�����ά��������,������С��·����
//    public static int process(int[][] arr, int row, int col) {
//        //base case
//        //�����ж�Խ��,����д���Ǵ��,��Ϊ��û�����Ʊ����ƶ������½�
//        if (row < 0 || row > arr.length - 1 || col < 0 || col > arr[0].length - 1) return 0;
//
//        int cur = arr[row][col];
//        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
//    }
    //�ݹ麯������:
    //�����ά��������,������С��·����
    public static int process(int[][] arr, int row, int col) {
        //base case
        //��Ϊ���ڲ��޶����ߵķ���,����ֻ���ܴ����Ͻ��ߵ����½�,�����б�Ŀ���
        //�������ֻ�п����ߵ�����,������������λ��
        if (row == arr.length - 1 && col == arr[0].length - 1) return arr[row][col];

        int cur = arr[row][col];
        if (row == arr.length - 1 && col < arr[0].length) { //�ڵײ�,ֻ��������
            return cur + process(arr, row, col + 1);
        }
        if (col == arr[0].length - 1 && row < arr.length) { //�����Ҳ�,ֻ��������
            return cur + process(arr, row + 1, col);
        }
        //����λ��,���ظ�С����һ��
        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
    }


    public static int minPath1(int[][] matrix) {
        return process1(matrix, 0, 0);
    }

    //�ݹ��:
    //��ȷ��������:��i,j�ߵ��������½ǵ���С·��
    //���Բ���Ӧ��Ϊmatrix,i,j
    public static int process1(int[][] matrix, int i, int j) {
        //base case:
        if (i == matrix.length - 1 && j == matrix[0].length - 1) return matrix[i][j];
        //��������±߽���ұ߽�,ֻ�ܺ����߻���������
        if (i == matrix.length - 1) {//�����±߽�,ֻ�ܺ�����
            return matrix[i][j] + process1(matrix, i, j + 1);
        } else if (j == matrix[0].length - 1) {//�����ұ߽�,ֻ��������
            return matrix[i][j] + process1(matrix, i + 1, j);
        }
        //�����û����,���������߻������ߵ���С·����
        return matrix[i][j] + Math.min(process1(matrix, i + 1, j), process1(matrix, i, j + 1));
    }

    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(process(m, 0, 0));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(process(m, 0, 0));
    }

}
