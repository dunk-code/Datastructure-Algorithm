package school.xauat.algorithm.sort;

import java.util.Arrays;

public class sort {
    public static void main(String[] args) {
        //int[] arr = {300,98,132,10,20};
        int N = 8000000;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        //shellSort(arr);
        //quickSort(arr,0,arr.length - 1);
        Long start = System.currentTimeMillis();
        //Arrays.parallelSort(arr);
        //Arrays.sort(arr);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        //redixSort(arr);
    }

    public static void bubbleSort(int[]arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次循环后的顺序为" + Arrays.toString(arr));
            if (flag){
                flag = false;
            }else
                break;
        }
    }

    public static void selectSort(int[]arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) + "次排序后的顺序是" + Arrays.toString(arr));
        }
    }

    public static void insertSort(int[]arr){
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int insertIndex = i;
            int insertValue = arr[insertIndex + 1];
            while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertValue;
            }
            System.out.println("第" + (++count) + "次排序后的顺序是" + Arrays.toString(arr));
        }
    }

    public static void shellSort(int[]arr){
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i - gap;
                int insertValue = arr[i];
                while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                if(insertIndex != (i - gap)){
                    arr[insertIndex + gap] = insertValue;
                }
            }
            System.out.println("第" + (++count) + "次排序后的顺序是" + Arrays.toString(arr));
        }
    }

    public static void quickSort(int[]arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while(l < r){
            while(arr[l] < pivot)
                l++;
            while(arr[r] > pivot)
                r--;
            if(l == r)
                break;
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if(arr[l] == pivot)
                r--;
            if(arr[r] == pivot)
                l++;
        }
        if(l == r){
            l++;
            r--;
        }
        if(l < right){
            quickSort(arr,l,right);
        }
        if(r > left){
            quickSort(arr,left,r);
        }
    }

    public static void redixSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i])
                max = arr[i];
        }
        int maxLength = (max + "").length();
        int[][]bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int i = 0 ,n = 1; i < maxLength; i++,n *= 10) {
            //入桶
            for (int j = 0; j < arr.length; j++) {
                int digitOfEle = arr[j] / n % 10;
                bucket[digitOfEle][bucketElementCounts[digitOfEle]] = arr[j];
                bucketElementCounts[digitOfEle]++;
            }
            int index = 0;
            //出桶
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if(bucketElementCounts[j] != 0){
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                bucketElementCounts[j] = 0;
            }
            System.out.println("第" + (i + 1) + "次排序后的数组为" + Arrays.toString(arr));
        }
    }
}
