package school.xauat.algorithm.search;

import java.util.Arrays;

public class FibonacciSearch {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000, 1234};
        int res = fibonacciSearch(arr,8);
        System.out.println(res);
    }

    public static int[] fib(){
        int[]arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    //斐波那契查找算法
    public static int fibonacciSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int[]f = fib();
        int k = 0;
        while(f[k] - 1 < high){
            k++;
        }
        int[] temp = Arrays.copyOf(arr,f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while(low <= high){
            int mid = low + f[k - 1] - 1;
            if(temp[mid] > key){
                high = mid - 1;
                k--;
            }else if (temp[mid] < key){
                low = mid + 1;
                k -= 2;
            }else {
                return mid > arr.length - 1 ? (arr.length - 1) : mid;
            }
        }
        return -1;
    }
}
