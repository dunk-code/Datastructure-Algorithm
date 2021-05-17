package school.xauat.datastructres.avl;

public class AVLTreeDemo{
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12, 8, 9, 7, 6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}
class AVLTree{
    private Node root;

    public Node getRoot(){
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }


    public void add(Node node){
        if(root == null){
            root = node;
            return;
        }
        root.add(node);
    }

    public void infixOrder(){
        if(root != null){
            root.infixOrder();
            return;
        }
        System.out.println("二叉排序树为空~~~");
    }

    public Node search(int val){
        if(root != null){
            return root.search(val);
        }
        return null;
    }

    public Node searchParent(int val){
        if(root != null){
            return root.searchParent(val);
        }
        return null;
    }

    public int delRightTreeMin(Node node){
        Node target = node;
        while(target.left != null){
            target = target.left;
        }
        delNode(target.val);
        return target.val;
    }

    public void delNode(int val){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null && root.val == val){
            root = null;
            return;
        }
        Node target = search(val);
        if(target == null)
            return;
        Node parent = searchParent(val);
        if(target.left == null && target.right == null){//叶子节点
            if (parent.left != null && parent.left.val == val){
                parent.left = null;
                return;
            }
            if(parent.right != null && parent.right.val == val){
                parent.right = null;
                return;
            }
        }else if(target.left != null && target.right != null){//两颗子树的非叶子节点
            int minVal = delRightTreeMin(target.right);
            target.val = minVal;
        }else {//有一个子树的叶子节点
            if(parent.left.val == val){
                if(target.left != null){
                    parent.left = target.left;
                    return;
                }
                if(target.right != null){
                    parent.left = target.right;
                    return;
                }
            }else {
                if(target.left != null){
                    parent.right = target.left;
                    return;
                }
                if(target.right != null){
                    parent.right= target.right;
                    return;
                }
            }
        }
    }

}
class Node{
    public int val;
    public Node left;
    public Node right;

    public Node(int val){
        this.val = val;
    }

    public int leftHeight(){
        if(left != null){
            return this.left.height();
        }
        return 0;
    }

    public int rightHeight(){
        if(right != null){
            return right.height();
        }
        return 0;
    }

    public int height(){
        return Math.max(this.left == null ? 0 : this.left.height(),
                this.right == null ? 0 : this.right.height()) + 1;
    }

    public void add(Node node){
        if(node == null)
            return;
        if(node.val < this.val){
            if(this.left == null) {
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if(this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        if ((rightHeight() - leftHeight()) > 1){
            if (right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return;
        }

        if((leftHeight() - rightHeight()) > 1){
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }

    public void leftRotate(){
        Node newNode = new Node(val);
        newNode.left = left;
        newNode.right = right.left;
        val = right.val;
        right = right.right;
        left = newNode;
    }

    public void rightRotate(){
        Node newNode = new Node(val);
        newNode.right = right;
        newNode.left = left.right;
        val = left.val;
        left = left.left;
        right = newNode;
    }

    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    public Node search(int val){
        if(this.val == val){
            return this;
        }
        if(val < this.val){
            if (this.left != null){
                return this.left.search(val);
            }else
                return null;
        }else {
            if (this.right != null){
                return this.right.search(val);
            }else
                return null;
        }
    }

    public Node searchParent(int val){
        if(this.left != null && this.left.val == val ||
                this.right != null && this.right.val == val){
            return this;
        }
        if(val < this.val && this.left != null){
            return this.left.searchParent(val);
        }else if(val > this.val && this.right != null){
            return this.right.searchParent(val);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}