package school.xauat.datastructres.tree.threadedBinaryTree;

public class ThreadedBinaryTreeDemo1 {
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


        //前序线索化
        threadedBinaryTree.preThreaded();
        threadedBinaryTree.preThreadedList();


        //中序线索化
        threadedBinaryTree.infixThreaded();
        threadedBinaryTree.infixThreadedList();


        //后序线索化
        threadedBinaryTree.postThreaded();
        hero3.setParent(root);
        hero6.setParent(root);
        hero8.setParent(hero3);
        hero10.setParent(hero3);
        hero14.setParent(hero6);
        threadedBinaryTree.postThreadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preThreaded() {
        preThreadedNode(root);
    }

    private void preThreadedNode(HeroNode node) {
        //线索化当前节点
        if (node == null)
            return;
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setThreadedLeft(true);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setThreadedRight(true);
        }
        pre = node;
        //线索化左节点
        if (!node.isThreadedLeft()) {
            preThreadedNode(node.getLeft());
        }
        //线索化右节点
        if (!node.isThreadedRight()) {
            preThreadedNode(node.getRight());
        }
    }

    public void preThreadedList() {
        HeroNode node = root;
        while (node != null) {
            if (!node.isThreadedLeft()) {
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    public void infixThreaded() {
        infixThreadedNode(root);
    }

    private void infixThreadedNode(HeroNode node) {
        if (node == null) {
            return;
        }
        //线索化左节点
        infixThreadedNode(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setThreadedLeft(true);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setThreadedRight(true);
        }
        pre = node;
        //线索化右节点
        infixThreadedNode(node.getRight());
    }

    public void infixThreadedList() {
        HeroNode node = root;
        while (node != null) {
            while (!node.isThreadedLeft()) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.isThreadedRight()) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void postThreaded() {
        postThreadedNode(root);
    }

    private void postThreadedNode(HeroNode node) {
        if (node == null)
            return;
        postThreadedNode(node.getLeft());
        postThreadedNode(node.getRight());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setThreadedLeft(true);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setThreadedRight(true);
        }
        pre = node;
    }

    public void postThreadedList() {
        HeroNode node = root;
        while (node != null && !node.isThreadedLeft()) {
            node = node.getLeft();
        }
        pre = null;
        while (node != null) {
            if (node.isThreadedRight()) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (node == root)
                        return;
                    pre = node;
                    node = node.getParent();
                } else {
                    node = node.getRight();
                    while (node != null && !node.isThreadedLeft()) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode parent;
    //true 表示是线索化节点
    //false 表示不是线索化节点
    private boolean ThreadedLeft = false;
    private boolean ThreadedRight = false;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public boolean isThreadedLeft() {
        return ThreadedLeft;
    }

    public void setThreadedLeft(boolean threadedLeft) {
        ThreadedLeft = threadedLeft;
    }

    public boolean isThreadedRight() {
        return ThreadedRight;
    }

    public void setThreadedRight(boolean threadedRight) {
        ThreadedRight = threadedRight;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
