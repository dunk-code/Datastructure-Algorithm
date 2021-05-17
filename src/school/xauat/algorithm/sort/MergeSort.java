package school.xauat.algorithm.sort;

public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));*/
        System.out.println("测试归并排序的时间");
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        Long startTime = System.currentTimeMillis();
        mergeSort(arr,0,arr.length - 1,temp);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
        //System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid + 1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }
    /**
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始   索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[]temp){
        //System.out.println("*****");

        int i = left;
        int j = mid + 1;
        int t = 0;

        /*
        (一)
        先把两边有序的数据按照规则填充到temp数组
        指导左右两边的有序序列，有一边处理完毕
        */
        while(i <= mid && j <= right){
            temp[t++] = arr[i] > arr[j] ? arr[j++] : arr[i++];
        }

        /*
        (二)
        把所有剩余数据的一边一次全部填充到temp
         */
        while(i <= mid){
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }

        /*
        (三)
        将temp数组的元素拷贝到arr
         */
        t = 0;
        int tempLeft = left;
        //System.out.println("tempLeft = " + tempLeft + "right = " + right);
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
