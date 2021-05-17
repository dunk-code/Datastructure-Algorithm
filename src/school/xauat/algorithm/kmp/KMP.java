package school.xauat.algorithm.kmp;

import java.util.Arrays;

/**
 * @author ：zsy
 * @date ：Created 2021/4/20 9:25
 * @description：KMP算法
 */
public class KMP {
    public static void main(String[] args) {
        System.out.println(kmpSearch("mississippi", "issip"));
    }

    public static int kmpSearch(String destStr, String subStr) {
        int[] next = getNext(subStr);
        int x = 0;
        int y = 0;
        while(x < destStr.length() && y < subStr.length()) {
            if (y == -1 || destStr.charAt(x) == subStr.charAt(y)) {
                x++;
                y++;
            } else {
                y = next[y];
            }
        }
        if (y == subStr.length()) {
            return x - y;
        }
        return -1;
    }

    private static int[] getNext(String target) {
        //创建一个数组
        int[] next = new int[target.length()];
        //定义两个变量
        int len = -1;//指向当前公共前缀的前端的末尾
        int y = 0;//指向当前公共前缀的后端的末尾
        //赋初值
        next[0] = -1;
        while(y < target.length() - 1) {
            if (len == -1 || target.charAt(len) == target.charAt(y)) {
                len++;
                y++;
                next[y] = len;
            } else {
                len = next[len];
            }
        }
        return next;
    }
}
