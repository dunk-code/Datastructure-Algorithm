package school.xauat.algorithm.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 1000, 1000, 1000, 1000, 1000, 1000, 1234};
        /*int index = binarySearch(arr,0,arr.length - 1,1000);
        System.out.println(index);*/
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList = binarySearch2(arr,0,arr.length - 1,1000);
        System.out.println(arrayList);
    }

    /**
     * 二分查找
     * @param arr 待查找的数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 待查询的值
     * @return 查找到的元素下标，没有找到返回-1
     */
    public static int binarySearch(int[]arr,int left,int right,int findVal){
        if(left > right){
            return -1;
        }
        int midIndex = (left + right) / 2;
        int mid = arr[midIndex];
        if(mid == findVal)
            return  midIndex;
        return mid > findVal ? binarySearch(arr,left,midIndex - 1,findVal)
                :binarySearch(arr,midIndex + 1,right,findVal);
    }

    /**
     * {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
     */
    public static ArrayList<Integer> binarySearch2(int[]arr, int left, int right, int findVal){
        ArrayList<Integer> arrayList = new ArrayList();
        int temp = 0;
        if(left > right){
            return arrayList;
        }
        int midIndex = (left + right) / 2;
        int mid = arr[midIndex];
        if(mid == findVal){
            temp = midIndex - 1;
            while(temp >= 0 && arr[temp] == findVal){
                temp--;
            }
            for (int i = temp + 1; i < midIndex; i++) {
                arrayList.add(i);
            }
            temp = midIndex + 1;
            while(temp < arr.length && arr[temp] == findVal){
                temp++;
            }
            for (int i = midIndex; i < temp; i++) {
                arrayList.add(i);
            }
            return  arrayList;}
        return mid > findVal ? binarySearch2(arr,left,midIndex - 1,findVal)
                :binarySearch2(arr,midIndex + 1,right,findVal);
    }
}
