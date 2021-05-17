package school.xauat.algorithm.sort;

public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9,23,0,10,-567,70, 70, 70};

        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        System.out.println("测试快速排序的时间：");
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        Long startTime = System.currentTimeMillis();
        quickSort(arr,0,arr.length - 1);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
        //System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr,int left,int right){

        if(left >= right) return;
        int l = left;
        int r = right;
        int pivot = arr[l];
        while(l < r) {
            while(l < r && arr[r] >= pivot) {
                r--;
            }
            if(l < r) {
                arr[l] = arr[r];
            }
            while(l < r && arr[l] <= pivot) {
                l++;
            }
            if(l < r) {
                arr[r] = arr[l];
            }
            if(l >= r) {
                arr[l] = pivot;
            }
        }
        quickSort(arr, left, r - 1);
        quickSort(arr, r + 1, right);
/*        int r = right;
        int l = left;
        int temp = 0;
        int pivot = arr[(right + left) / 2];
        while(l < r){
            while(arr[l] < pivot){
                l++;
            }
            while(arr[r] > pivot){
                r--;
            }
            if(l == r)
                break;
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            if (arr[l] == pivot){
                r--;
            }
            if (arr[r] == pivot){
                l++;
            }
        }
        if (l == r){
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }*/
    }
}
