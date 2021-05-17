package school.xauat.datastructres.rbtree;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：zsy
 * @date ：Created 2021/4/17 15:02
 * @description：测试红黑树
 */
public class Test {

    public static void main(String[] args) {
        Map map = new HashMap();
        RBTree<Integer, Integer> rbTree = new RBTree<>();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            String input = scanner.next();
            switch (input) {
                case ("insert") :
                    System.out.println("请输入添加节点key:");
                    int key = scanner.nextInt();
                    rbTree.insert(key, null);
                    rbTree.preOrderPrint();
                    break;
                case ("exit") :
                    flag = false;
                    break;
                default:
                    System.out.println("请重新输入:");
                    break;
            }
        }

        rbTree.inOrderPrint();
        System.out.println("========");
    }
}