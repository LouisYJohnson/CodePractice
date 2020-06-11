package com.newcoder.zuo3.basic;

public class Class_01_BubbleSort {
	//冒泡排序算法(数组从小到大排列):
	//思路:从头开始,每次一个,每次冒泡最大的在后面,每次的最后一个数字不参与下一次排序
	public static void bubbleSort(int[] arr) {
		if(arr == null || arr.length < 2) return;
		
		for(int i = arr.length - 1;i > 0;i--) {
			for(int j = 0;j < i;j++) {
				if(arr[j] > arr[j + 1]) swap(arr,j,j+1);
			}
		}
	}
	
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
