package school.xauat.algorithm.sort;


public class SelectSort {
    public static void main(String[] args) {
        //int arr[] = {101,34,119,1};

        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        System.out.println("测试选择排序的时间");
        Long startTime = System.currentTimeMillis();
        selectSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
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
            //将最小值放在arr[i],即交换
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
