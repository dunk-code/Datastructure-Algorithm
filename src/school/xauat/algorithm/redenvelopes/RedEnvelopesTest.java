package school.xauat.algorithm.redenvelopes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：zsy
 * @date ：Created 2021/5/17 9:27
 * @description：红包算法
 */
public class RedEnvelopesTest {
    public static void main(String[] args) {
        RedEnvelopes redEnvelopes = new RedEnvelopes(5, 200);
        System.out.println(redEnvelopes.amountList);
    }
}

class RedEnvelopes {
    List<Integer> amountList;

    public RedEnvelopes(int peopleNum, int totalAmount) {
        this.amountList = new ArrayList<>();
        Random random = new Random();
        int restAmount = totalAmount;
        int restPeopleNum = peopleNum;
        for(int i = 0; i < peopleNum - 1; i++) {
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
    }
}