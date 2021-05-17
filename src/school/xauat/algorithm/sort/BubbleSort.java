package school.xauat.algorithm.sort;


import java.util.HashMap;
import java.util.Map;

public class BubbleSort {
    public static void main(String[] args) {
        //int arr[] = {3,9,-1,10,20};
        //int arr[] = {1,2,3,4,5,6,7};

        Map<Integer,Integer> map = new HashMap<>();
        int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);//产生一个[0,8000000)的随机数
        }
        System.out.println("测试冒泡排序的时间");
        Long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)+ "ms");
    }


    public static void bubbleSort(int[] arr){
        int temp = 0;
        //标识变量，表示是否进行过交换
        boolean flag = false;
        //时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) { //一共要排序几次
            for (int j = 0; j < arr.length - 1 - i; j++) {//每次排序需要比较的次数
                if (arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            if (flag){//出现过交换，重置flag
                flag = false;
            }else//在上一趟排序中，一次交换也没有发生过
                break;
        }
    }
}
