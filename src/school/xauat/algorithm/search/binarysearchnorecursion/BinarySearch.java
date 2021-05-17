package school.xauat.algorithm.search.binarysearchnorecursion;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int res = binarySearch(arr,0,arr.length - 1,1000);
        System.out.println(res);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if (left > right)
            return -1;
        int midIndex = (left + right) / 2;
        int mid = arr[midIndex];
        if (mid == findVal)
            return mid;
        return findVal > mid ? binarySearch(arr,(midIndex + 1),right,findVal) :
                binarySearch(arr,left,(midIndex - 1),findVal);
    }
}
