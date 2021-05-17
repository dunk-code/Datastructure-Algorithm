package school.xauat.datastructres.tree;

public class HeapSort {
    public static void main(String[] args) {
        /*int[] arr = {4,6,8,5,9,-999,854,21,998,1214,821,11000};
        headSort(arr);
        System.out.println(Arrays.toString(arr));*/
        System.out.println("测试堆排序时间");
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Long startTime = System.currentTimeMillis();
        headSort(arr);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    public static void headSort(int[] arr){
        //构建初始堆，将给定无序序列构造成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr,i,arr.length);
        }
        //将堆顶元素与末尾元素进行交换，使末尾元素最大，然后继续调整堆
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjust(arr,0,i);
        }
    }

    /**
     *
     * @param arr 待排序数组
     * @param i 最后一个非叶子节点
     * @param length
     */
    public static void adjust(int[] arr,int i,int length){
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if((k + 1) < length && arr[k] < arr[k + 1])
                k++;
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else
                break;
        }
        arr[i] = temp;
    }
}
