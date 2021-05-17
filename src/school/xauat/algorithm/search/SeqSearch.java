package school.xauat.algorithm.search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000, 1234};
        int index = seqSearch(arr,1000);
        System.out.println(index == -1 ? "没有找到":"下标为" + index);
    }

    public static int seqSearch(int[]arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] ==value){
                return i;
            }
        }
        return -1;
    }
}
