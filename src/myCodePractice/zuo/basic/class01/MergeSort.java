package myCodePractice.zuo.basic.class01;

public class MergeSort {
    //�鲢����(��С����)
    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, r, mid);
    }

    public static void merge(int[] arr, int l, int r, int mid) {
        if (arr == null || arr.length < 2) return;

        int iHelpLeft = l;
        int iHelpRight = mid + 1;
        int[] helps = new int[r - l + 1];
        int iHelp = 0;

        while (iHelpLeft <= mid && iHelpRight <= r) {
            helps[iHelp++] = arr[iHelpLeft] < arr[iHelpRight] ? arr[iHelpLeft++] : arr[iHelpRight++];
        }
        //��û�����
        while (iHelpLeft <= mid) {
            helps[iHelp++] = arr[iHelpLeft++];
        }
        while (iHelpRight <= r) {
            helps[iHelp++] = arr[iHelpRight++];
        }
        //������ԭ����
        iHelp = 0;
        for (int i = l; i <= r; i++) {
            arr[i] = helps[iHelp++];
        }
    }


}
