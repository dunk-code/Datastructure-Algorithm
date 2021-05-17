package school.xauat.algorithm.search;

import java.util.Arrays;

public class Search {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        //int res = seqSearch(arr,102);
        //int res = binarySearch(arr,0,arr.length - 1,1234);
        //int res = insertValueSearch(arr,0,arr.length - 1,1234);
        int res = fibonacciSearch(arr,1);
        System.out.println(res);
    }

    public static int seqSearch(int[]arr,int findVal){
        for (int i = 0; i < arr.length; i++) {
            if(findVal == arr[i]){
                return i;
            }
        }
        System.out.println("没有找到~~~");
        return -1;
    }

    public static int binarySearch(int[] arr,int left,int right ,int findVal){
        if(left > right)
            return -1;
        int midIndex = (left + right) / 2;
        int mid = arr[midIndex];
        if(mid == findVal)
            return midIndex;
        return mid > findVal ? binarySearch(arr,left,midIndex - 1,findVal) :
                binarySearch(arr,midIndex + 1,right,findVal);
    }

    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1])
            return -1;
        int midIndex = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        int mid = arr[midIndex];
        if(mid == findVal)
            return midIndex;
        return mid > findVal ? insertValueSearch(arr,left,midIndex - 1,findVal) :
                insertValueSearch(arr,midIndex + 1,right,findVal);
    }

    public static int[] fib(){
        int[] arr = new int[20];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    public static int fibonacciSearch(int[] arr,int findVal){
        int k = 0;
        int[] f = fib();
        while((f[k] - 1) < arr.length){
            k++;
        }
        int[] temp = Arrays.copyOf(arr,f[k] - 1);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = temp[arr.length - 1];
        }
        int left = 0;;
        int right = temp.length - 1;
        while(left <= right){
            int mid = left + f[k - 1] - 1;
            if(temp[mid] > findVal){
                right = mid - 1;
                k--;
            }else if(temp[mid] < findVal){
                left = mid + 1;
                k -= 2;
            }else
                return mid;
        }
        return -1;
    }
}
