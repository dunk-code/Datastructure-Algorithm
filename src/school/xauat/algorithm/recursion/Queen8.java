package school.xauat.algorithm.recursion;

public class Queen8 {
    //定义一共有多少个皇后
    int max = 8;
    //使用一个数组保存皇后的放置位置
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        Long startTime = System.currentTimeMillis();
        queen8.check(0);
        System.out.println("一共有" + count + "解法");
        System.out.println("一共有" + judgeCount + "次冲突");
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n){
        if (n == 8){
            count++;
            print(array);
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)){ // 第n行的皇后是否合法
                check(n + 1);
            }
        }
    }

    /**
     * 判断第n行的皇后位置是否合法
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]  不在同一列
            //Math.abs(array[n] - array[i]) == Math.abs(n - i)  不在同一斜线
            if (array[i] == array[n] || Math.abs(array[n] - array[i]) == Math.abs(n - i)){
                return false;
            }
        }
        return true;
    }
    /**
     * 打印皇后的摆放位置
     * @param array
     */
    private void print(int[]array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
