package myCodePractice.zuo.basic.class01;

public class SmallSum {
    //小和问题
    //使用归并排序的中间产物来做小和问题
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;

        return mergeSum(arr, 0, arr.length - 1);
    }

    public static int mergeSum(int[] arr, int l, int r) {
        if (l == r) return 0;

        int mid = l + (r - l) / 2;
        return mergeSum(arr, l, mid) + mergeSum(arr, mid + 1, r) + merge(arr, l, r, mid);
    }

    private static int merge(int[] arr, int l, int r, int mid) {
        int result = 0;
        int[] helps = new int[r - l + 1];
        int iHelpLeft = l;
        int iHelpRight = mid + 1;
        int iHelp = 0;

        while (iHelpLeft <= mid && iHelpRight <= r) {
            if (arr[iHelpLeft] < arr[iHelpRight]) {
                result += arr[iHelpLeft] * (r - iHelpRight + 1);
                helps[iHelp++] = arr[iHelpLeft++];
            } else {
                helps[iHelp++] = arr[iHelpRight++];
            }
        }

        while (iHelpRight <= r) {
            helps[iHelp++] = arr[iHelpRight++];
        }
        while (iHelpLeft <= mid) {
            helps[iHelp++] = arr[iHelpLeft++];
        }
        for (int i = 0; i < helps.length; i++) {
            arr[l++] = helps[i];
        }
        return result;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
