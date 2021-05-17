package school.xauat.datastructres.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static Node createHuffmanTree(int[]arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        while(nodes.size() > 1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
            nodes.remove(left);
            nodes.remove(right);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
            return;
        }
        System.out.println("二叉树为空~~~~");
    }
}

class Node implements Comparable<Node>{
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null)
            this.left.preOrder();
        if(this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
