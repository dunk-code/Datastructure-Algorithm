package school.xauat.algorithm.search;


import java.util.ArrayList;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        //int res = insertValueSearch(arr,0,arr.length - 1,10000);
        ArrayList<Integer> res = insertValueSearch2(arr,0,arr.length - 1,100);
        System.out.println(res);
    }

    public static int insertValueSearch(int[]arr,int left,int right,int findVal){
        /*
        findVal < arr[0] || findVal > arr[arr.length - 1]必须需要
        否则mid会越界
         */
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1])
            return -1;
        int midIndex = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        int mid = arr[midIndex];
        if(mid == findVal)
            return midIndex;
        return mid > findVal ? insertValueSearch(arr,left,midIndex - 1,findVal)
                :insertValueSearch(arr,midIndex + 1,right,findVal);
    }

    public static ArrayList<Integer> insertValueSearch2(int[]arr, int left, int right, int findVal){
        /*
        findVal < arr[0] || findVal > arr[arr.length - 1]必须需要
        否则mid会越界
         */
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1])
            return new ArrayList<>();
        int midIndex = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        int mid = arr[midIndex];
        if(mid == findVal){
            ArrayList<Integer> arrayList = new ArrayList<>();
            int temp = midIndex - 1;
            while(temp >= 0 && arr[temp] == findVal)
                temp--;
            for (int i = temp + 1; i < midIndex; i++) {
                arrayList.add(i);
            }
            temp = midIndex + 1;
            while(temp < arr.length && arr[temp] == findVal)
                temp++;
            for (int i = midIndex; i < temp; i++) {
                arrayList.add(i);
            }
            return arrayList;
        }
        return mid > findVal ? insertValueSearch2(arr,left,midIndex - 1,findVal)
                :insertValueSearch2(arr,midIndex + 1,right,findVal);
    }
}
