package school.xauat.algorithm.dac;

import java.util.Arrays;

public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    /**
     * 汉诺塔问题
     * @param num  表示一共有多少个盘
     * @param a 第一个塔
     * @param b
     * @param c
     *
     * 两种情况，第一种情况只有一个盘，直接从  A->C
     * 第二种情况，num >= 2 可以看做是两个盘 1、最下面的盘。2、上面的所有盘
     * 步骤：
     *      -先将上面的盘 A->B
     *      -将最下面的盘 A->C
     *      -将上面的盘 B->C
     */
    public static void hanoiTower(int num, char a, char b, char c){
        if (num == 1){
            System.out.println("第1个盘从" + a + "->" + c);
        } else{
            hanoiTower(num - 1,a,c,b);
            //将最下面的盘 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            hanoiTower(num - 1,b,a,c);
        }
    }
}