package school.xauat.algorithm.kmp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int res = violenceMatch(str1,str2);
        System.out.println(res);
    }

    public static int violenceMatch(String str1,String str2){
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while(i < c1.length && j < c2.length){
            if(c1[i] == c2[j]){
                i++;
                j++;
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == c2.length)
            return (i - j);
        return -1;
    }
}
