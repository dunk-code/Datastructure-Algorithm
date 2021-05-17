package school.xauat.algorithm.dynamic;

import java.util.Scanner;

public class completelyKnapsackProblem {
    public static void main(String[]args){
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();//物品的数量
        int V = reader.nextInt();//背包容量
        int w[] = new int[N + 1];//第i件物品的价值
        int v[] = new int[N + 1];//第i件物品的体积
        for(int i = 1; i <= N; i++){
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
        }
        reader.close();
        int dp[][] = new int[N + 1][V + 1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= V; j++){
                for(int k = 0;k*v[i] <= j; k++){
                    dp[i][j] = Math.max(dp[i][j],dp[i - 1][j - k * v[i]] + k * w[i]);
                }
                System.out.println(dp[i][j]);
            }
        }

        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= V; j++){
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(dp[N][V]);
    }
}
