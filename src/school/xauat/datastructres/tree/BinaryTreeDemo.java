package school.xauat.datastructres.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode hero2 = new HeroNode(2,"卢俊义");
        HeroNode hero3 = new HeroNode(3,"吴用");
        HeroNode hero4 = new HeroNode(4,"公孙胜");
        HeroNode hero5 = new HeroNode(5,"关胜");

        root.setLeft(hero2);
        root.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序遍历查找");
        System.out.println(binaryTree.preOrderSearch(5));

        System.out.println("中序遍历查找");
        System.out.println(binaryTree.infixOrderSearch(5));

        System.out.println("后序遍历查找");
        System.out.println(binaryTree.postOrderSearch(5));


        System.out.println("删除节点前的前序遍历");
        binaryTree.preOrder();

        binaryTree.delete1(1);

        System.out.println("删除节点后的前序遍历");
        binaryTree.preOrder();

    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
            return;
        }
        System.out.println("二叉树为空~~~");
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int id){
        if(this.root != null){
            return this.root.preOrderSearch(id);
        }
        return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id){
        if (this.root != null)
            return this.root.infixOrderSearch(id);
        return null;
    }

    public HeroNode postOrderSearch(int id){
        if (this.root != null){
            return this.root.postOrderSearch(id);
        }
        return null;
    }

    public void delete(int id){
        if(this.root != null){
            if(this.root.getId() == id){
                this.root = null;
                return;
            }
            this.root.deleteNode(id);
            return;
        }
        System.out.println("二叉树为空~~");
    }

    public void delete1(int id){
        if(this.root != null){
            if(this.root.getId() == id){
                this.root = null;
                return;
            }
            this.root.deleteNode1(id);
            return;
        }
        System.out.println("二叉树为空~~");
    }
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if(this.right != null)
            this.right.infixOrder();
    }

    //后序遍历
    public void postOrder(){
        if(this.left != null)
            this.left.postOrder();
        if(this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int id){
        System.out.println(1);
        if(this.id == id)
            return this;
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(id);
            if (resNode != null)
                return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(id);
            if(resNode != null)
                return resNode;
        }
        System.out.println(1);
        if(this.id == id){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }

    //后序遍历查询
    public HeroNode postOrderSearch(int id){
       HeroNode resNode = null;
       if(this.left != null){
           resNode = this.left.postOrderSearch(id);
           if (resNode != null)
               return resNode;
       }
       if(this.right != null){
           resNode = this.right.postOrderSearch(id);
           if(resNode != null)
               return resNode;
       }
        System.out.println(1);
        if(this.id == id)
           return this;
       return null;
    }

    public void deleteNode(int id){
        if(this.left != null){
            if(this.left.id == id){
                this.left = null;
                return;
            }
            this.left.deleteNode(id);
        }
        if(this.right != null){
            if(this.right.id == id){
                this.right = null;
                return;
            }
            this.right.deleteNode(id);
        }
        if(this.left != null && this.left.id == id){
            this.left = null;
        }
        if(this.right != null && this.right.id == id){
            this.right = null;
        }

        if(this.left != null){
            this.left.deleteNode(id);
        }
        if(this.right != null){
            this.right.deleteNode(id);
        }

    }

    public void deleteNode1(int id){
        if(this.left != null){
            if(this.left.id == id){
                if(this.left.left != null){
                    this.left = this.left.left;
                    return;
                }
                if(this.right != null){
                    this.left = this.left.right;
                    return;
                }
                this.left = null;
                return;
            }
            this.left.deleteNode(id);
        }
        if(this.right != null){
            if(this.right.id == id){
                if(this.right.left != null){
                    this.right = this.right.left;
                    return;
                }
                if(this.right.right != null){
                    this.right = this.right.right;
                    return;
                }
                this.right = null;
                return;
            }
            this.right.deleteNode(id);
        }
    }
}
