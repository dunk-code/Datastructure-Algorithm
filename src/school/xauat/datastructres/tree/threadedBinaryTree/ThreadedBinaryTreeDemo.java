/*
package school.school.xauat.structres.tree.threadedBinaryTree;


public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero3 = new HeroNode(3, "卢俊义");
        HeroNode hero6 = new HeroNode(6, "吴用");
        HeroNode hero8 = new HeroNode(8, "公孙胜");
        HeroNode hero10 = new HeroNode(10, "关胜");
        HeroNode hero14 = new HeroNode(14, "张三");

        root.setLeft(hero3);
        root.setRight(hero6);
        hero3.setLeft(hero8);
        hero3.setRight(hero10);
        hero6.setLeft(hero14);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        //threadedBinaryTree.preOrder();

        */
/*System.out.println("中序线索化");
        threadedBinaryTree.infixThreadedNode();
        System.out.println(hero8.getLeft());
        System.out.println(hero8.getRight());
        System.out.println(hero10.getLeft());
        System.out.println(hero10.getRight());
        System.out.println(hero14.getLeft());
        System.out.println(hero14.getRight());
        //System.out.println("中序遍历");
        //threadedBinaryTree.infixOrder();
        System.out.println("线索化中序遍历");
        threadedBinaryTree.infixThreadedList();*//*


        */
/*System.out.println("前序线索化");
        threadedBinaryTree.preThreadedNode();
        System.out.println(hero8.getLeft());
        System.out.println(hero8.getRight());
        System.out.println(hero14.getLeft());
        System.out.println(hero14.getRight());
        System.out.println(hero10.getLeft());
        System.out.println(hero10.getRight());
        System.out.println("前序线索化遍历");
        threadedBinaryTree.preThreadedList();*//*



        hero3.setParent(root);
        hero6.setParent(root);
        hero8.setParent(hero3);
        hero10.setParent(hero3);
        hero14.setParent(hero6);
        System.out.println("后序线索化");
        threadedBinaryTree.postThreadedNode();
        System.out.println(hero8.getLeft());
        System.out.println(hero8.getRight());
        System.out.println(hero14.getLeft());
        System.out.println(hero14.getRight());
        System.out.println(hero10.getLeft());
        System.out.println(hero10.getRight());
        System.out.println("后序遍历");
        threadedBinaryTree.postThreadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    //前驱节点
    private HeroNode pre;

    public void preThreadedNode() {
        preThreadedNode(root);
    }

    public void infixThreadedNode() {
        infixThreadedNode(root);
    }

    public void postThreadedNode() {
        postThreadedNode(root);
    }

    //前序线索化遍历
    public void preThreadedList(){
        */
/*HeroNode node = root;
        while(node != null){
            System.out.println(node);
            while(node.getLeftType() == 0){
                node = node.getLeft();
                System.out.println(node);
            }
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }*//*


        //代码优化
        HeroNode node = root;
        while(node != null){
            while(node.getLeftType() == 0){
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    //后序线索化遍历
    public void postThreadedList(){
        HeroNode node = root;
        while(node != null && node.getLeftType() == 0){
            node = node.getLeft();
        }
        pre = null;
        while(node != null){
            if(node.getRightType() == 1){
                System.out.println(node);
                pre = node;
                node = node.getRight();
            }else{
                if(node.getRight() == pre){
                    System.out.println(node);
                    if(node == root)
                        return;
                    pre = node;
                    node = node.getParent();
                }else {
                    node = node.getRight();
                    while(node != null && node.getLeftType() == 0){
                        node = node.getLeft();
                    }
                }
            }
        }
    }

    //中序线索化遍历
    public void infixThreadedList() {
        HeroNode node = root;
        while(node != null){
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //中序线索化节点
    private void infixThreadedNode(HeroNode node) {
        if (node == null) {
            return;
        }
        //线索化左节点
        infixThreadedNode(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //线索化右节点
        infixThreadedNode(node.getRight());
    }

    //前序线索化节点
    private void preThreadedNode(HeroNode node){
        if(node == null)
            return;
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if(node.getLeftType() != 1)
            preThreadedNode(node.getLeft());
        if(node.getRightType() != 1)
            preThreadedNode(node.getRight());
    }

    //后序线索化节点
    public void postThreadedNode(HeroNode node) {
        if(node == null)
            return;
        postThreadedNode(node.getLeft());
        postThreadedNode(node.getRight());
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode parent;

    //表示当前节点左节点类型 0 表示左子树 1 表示前驱节点
    private int leftType;
    //表示当前节点右节点类型 0 表示右子树 1 表示后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    public void infixOrder() {
        if (this.left != null && this.leftType == 0)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null && this.rightType == 0)
            this.right.infixOrder();
    }

    public void postOrder() {
        if (this.left != null && this.leftType != 1)
            this.left.postOrder();
        if (this.right != null && this.rightType != 1)
            this.right.postOrder();
        System.out.println(this);
    }

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}*/
