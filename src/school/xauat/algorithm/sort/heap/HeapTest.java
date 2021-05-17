package school.xauat.algorithm.sort.heap;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author ：zsy
 * @date ：Created 2021/5/17 9:41
 * @description：二叉堆
 */
public class HeapTest {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        Heap heap = new Heap(arr);
        heap.upAdjust(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        heap = new Heap(arr);
        heap.buildHead();
        System.out.println(Arrays.toString(arr));
    }
}

class Heap {
    private int[] arr;

    public Heap(int[] arr) {
        this.arr = arr;
    }

    public void buildHead() {
        //从最后一个非叶子节点开始，依次下沉
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            downAdjust(arr, i, arr.length);
        }
    }

    private void downAdjust(int[] arr, int parentIndex, int length) {
        int temp = arr[parentIndex];
        int childrenIndex = parentIndex * 2 + 1;
        while (childrenIndex < length) {
            //如果有右孩子，并且右孩子小于左孩子，那么定位到右孩子
            if (childrenIndex + 1 < length && arr[childrenIndex + 1] < arr[childrenIndex]) {
                childrenIndex++;
            }
            //如果父节点小于较小孩子节点的值，直接跳出
            if (temp <= arr[childrenIndex]) break;
            //无需交换，单向赋值
            arr[parentIndex] = arr[childrenIndex];
            parentIndex = childrenIndex;
            childrenIndex = 2 * childrenIndex + 1;
        }
        arr[parentIndex] = temp;
    }

    public void upAdjust(int[] arr) {
        int childrenIndex = arr.length - 1;
        int parentIndex = (childrenIndex - 1) / 2;
        int temp = arr[childrenIndex];
        while (childrenIndex > 0 && temp < arr[parentIndex]) {
            //单向赋值
            arr[childrenIndex] = arr[parentIndex];
            childrenIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        arr[childrenIndex] = temp;
    }
}
