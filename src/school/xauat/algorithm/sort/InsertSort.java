package school.xauat.algorithm.sort;

public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};
        System.out.println("测试插入排序的时间:");
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        Long startTime = System.currentTimeMillis();
        insertSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
    }

    public static void insertSort(int[] arr){
        int insertIndex = 0;
        int insertValue = 0;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while(insertIndex >= 0 && arr[insertIndex] > insertValue){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //优化是否需要赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertValue;
            }
        }
    }
}
