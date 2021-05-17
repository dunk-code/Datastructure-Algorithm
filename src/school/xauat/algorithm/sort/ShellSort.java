package school.xauat.algorithm.sort;

public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8,9,1,7,2,3,5,4,6,0};
        //shellSort2(arr);
        System.out.println("测试希尔排序的时间");
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        Long startTime = System.currentTimeMillis();
        shellSort2(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    //交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + (++count) + "次排序的结果是" + Arrays.toString(arr));
        }
    }


    //移位法
    public static void shellSort2(int[]arr){
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i - gap;
                int insertValue = arr[insertIndex + gap];
                while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                if (insertIndex != (i - gap)){
                    arr[insertIndex + gap] = insertValue;
                }
            }
            //System.out.println("第" + (++count) + "次排序后的结果是" + Arrays.toString(arr));
        }
    }





}

