package school.xauat.algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[]val = {1500,3000,2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大值
        /*int[][]v = new int[n + 1][m + 1];

        for (int i = 1; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                if(w[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j],v[i - 1][j - w[i - 1]] + val[i - 1]);
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }*/

        /**
         * 背包问题优化
         * 将v[][]优化为一位数组
         */
        int[] v = new int[m + 1];
        v[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= w[i - 1]; j--) {
                v[j] = Math.max(v[j],v[j - w[i - 1]] + val[i - 1]);
            }
            /*for (int j = 0; j <= m; j++) {
                System.out.print(v[j] + "\t");
            }
            System.out.println();*/
        }
        System.out.println(v[m]);
    }
}
