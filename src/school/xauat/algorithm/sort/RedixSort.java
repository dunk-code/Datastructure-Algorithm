package school.xauat.algorithm.sort;

public class RedixSort {
    public static void main(String[] args) {
        //int[] arr = {53,3,542,748,14,214};
        System.out.println("测试基数排序的时间");
        int[] arr = new int[80];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        Long startTime = System.currentTimeMillis();
        redixSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }
    public static void redixSort(int[]arr){
        //获取数组中最大元素的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i])
                max = arr[i];
        }
        int maxLength = (max + "").length();
        //定义一个二维数组模拟桶
        int [][] bucket = new int[10][arr.length];
        //为了记录每个桶中的元素个数定义一个一维数组
        int [] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++,n *= 10) {
            //入桶
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            //出桶
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if(bucketElementCounts[j] != 0){
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                //取出元素后，需要将bucketElementCount中的元素清零
                bucketElementCounts[j] = 0;
            }
            //System.out.println("第" + (i + 1) + "次排序后的数组" + Arrays.toString(arr));
        }
    }
}
