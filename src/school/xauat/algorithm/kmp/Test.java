package school.xauat.algorithm.kmp;

/**
 * @author ：zsy
 * @date ：Created 2021/4/20 13:58
 * @description：
 */
public class Test {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "abcabcmn";
        int index = kmpSearch(str1,str2);
        System.out.println("index = " + index);
    }

    public static int kmpSearch(String destStr, String subStr) {
        int[] next = getNext(subStr);
        int i = 0;
        int j = 0;
        while (i < destStr.length() && j < subStr.length()) {
            if (j == -1 || destStr.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == subStr.length()) {
            return i - j;
        }
        return -1;
    }

    private static int[] getNext(String subStr) {
        int[] next = new int[subStr.length()];
        next[0] = -1;
        int len = -1;
        int y = 0;
        while (y < subStr.length() - 1) {
            if (len == -1 || subStr.charAt(len) == subStr.charAt(y)) {
                y++;
                len++;
            } else {
                len = next[len];
            }
        }
        return next;
    }

}

