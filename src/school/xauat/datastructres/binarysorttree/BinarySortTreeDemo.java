package school.xauat.datastructres.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

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
