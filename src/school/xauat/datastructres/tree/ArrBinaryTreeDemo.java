package school.xauat.datastructres.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrBinaryTree.infixOrder();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();
    }
}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[]arr){
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void infixOrder(){
        infixOrder(0);
    }

    public void postOrder(){
        postOrder(0);
    }

    //前序遍历
    private void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空~~");
            return;
        }
        System.out.println(arr[index]);
        if((index * 2 + 1) < arr.length){
            preOrder(index *2 + 1);
        }
        if((index * 2 + 2) < arr.length){
            preOrder(index * 2 + 2);
        }
    }

    private void infixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空~~");
            return;
        }
        if((index * 2 + 1) < arr.length){
            infixOrder(index *2 + 1);
        }
        System.out.println(arr[index]);
        if((index * 2 + 2) < arr.length){
            infixOrder(index * 2 + 2);
        }
    }

    private void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空~~");
            return;
        }
        if((index * 2 + 1) < arr.length){
            postOrder(index *2 + 1);
        }
        if((index * 2 + 2) < arr.length){
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
    }
}
