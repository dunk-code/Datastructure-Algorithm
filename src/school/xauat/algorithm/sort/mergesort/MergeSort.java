package school.xauat.algorithm.mergesort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,(arr.length - 1),temp);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr,int left,int right,int temp[]){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,(mid + 1),right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int temp[]){
        int i = left;
        int j = mid + 1;
        int t = 0;
        while(i <= mid && j <= right){
            temp[t++] = arr[i] > arr[j] ? arr[j++] : arr[i++];
        }

        while(i <= mid){
            temp[t++] = arr[i++];
        }
        while(j <= right){
            temp[t++] = arr[j++];
        }

         t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
