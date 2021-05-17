package school.xauat.algorithm.sort;

import java.util.Arrays;

/**
 * @author ：zsy
 * @date ：Created 2021/4/11 9:03
 * @description：堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int N = 8;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));
        //构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjust(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素进行交换，然后重新调整大顶堆
        for(int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            adjust(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }


    //调整大顶堆
    public static void adjust(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k++) {
            if((k + 1) < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if(arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else break;
        }
        arr[i] = temp;
    }
}
