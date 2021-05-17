package school.xauat.algorithm.search.binarysearchnorecursion;

import java.util.Arrays;

public class BinarySearchNoRecursion {

    public static void main(String[] args) {
        int[]arr = {20,35,22,6,13};
        int res = binarySearch(arr,35);
        System.out.println("index = " + res);
    }

    /**
     * 使用非递归的二分查找
     * @param arr 待查询的数组，必须是有序数组
     * @param findVal 需要查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int findVal){
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int midIndex = (left + right) / 2;
            if (arr[midIndex] == findVal)
                return midIndex;
            if (arr[midIndex] > findVal){
                right = midIndex - 1;
            }else {
                left = midIndex + 1;
            }
        }
        return -1;
    }
}
