package school.xauat.datastructres.rbtree;

/**
 * @author ：zsy
 * @date ：Created 2021/4/17 11:36
 * @description：红黑树
 */

import java.util.Base64;

/**
 * 步骤：
 *
 * 1. 创建RBTree，定义颜色
 * 2. 创建RBNode
 * 3. 辅助方法定义：parentOf(node)、isRed()、setRed()、setBlack()、inOrderPrint()
 * 4. 左旋右旋方法定义：leftRotate(node)、rightRotate(node)
 * 5. 公开插入接口方法定义：insert(K key,  V value);
 * 6. 内部插入接口方法定义：insert(RBNode node);
 * 7. 修正插入导致红黑树失衡的方法定义：insertFlxUp(RBNode node);
 * 8. 测试
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RBNode root;

    public void insert(K key, V value) {
        RBNode newNode = new RBNode();
        newNode.setKey(key);
        newNode.setValue(value);
        newNode.setColor(RED);
        insert(newNode);
    }

    /**
     * 插入节点
     * @param node
     */
    private void insert(RBNode node) {
        if (root == null) {
            root = node;
            setBlack(root);
            return;
        }
        //寻找插入的父节点
        RBNode tmp = root;
        RBNode parent = null;
        while (tmp != null) {
            parent = tmp;
            int cmp = node.key.compareTo(tmp.key);
            if (cmp == 0) {
                //插入的节点已经存在情景2
                tmp.setValue(node.getValue());
                return;
            }
            if (cmp < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        node.parent = parent;
        if (parent != null) {
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        }
        //调用修复红黑树平衡的方法
        insertFlxUp(node);
    }

    /**
     * 修复红黑树平衡性
     * @param node
     */
    private void insertFlxUp(RBNode node) {
        //根节点染黑
        setBlack(root);
        RBNode parent = parentOf(node);
        RBNode pp = parentOf(parent);
        RBNode uncle = null;
        if (parent != null && isRed(parent)) { //情景4
            //寻找叔叔节点
            if (pp.left != null && pp.left.key.equals(parent.key)) {
                uncle = pp.right;
            } else {
                uncle = pp.left;
            }

            if(uncle != null && isRed(uncle)) {//情景4.1：叔叔存在且为红色
                setBlack(parent);
                setBlack(uncle);
                setRed(pp);
                insertFlxUp(pp);
                return;
            } else { //情景4.2:叔叔节点不存在或者存在为黑节点
                if (pp.left != null && pp.left == parent) { //情景4.2:插入节点的父亲节点是祖父节点的左子节点
                    if (parent.left != null && parent.left == node) {//情景4.2.1LL双红:插入节点为父亲的左节点
                        setBlack(parent);
                        setRed(pp);
                        rightRotate(pp);
                    } else { //情景4.2.2LR双红
                        leftRotate(parent);
                        insertFlxUp(parent);
                        return;
                    }
                } else { //情景4.3:插入节点的父亲节点是祖父节点的右子节点
                    if(parent.right != null && parent.right == node) {//情景4.3.1RR双红:新插入节点，为其父亲节点的右子节点
                        setBlack(parent);
                        setRed(pp);
                        leftRotate(pp);
                        return;
                    } else { //情景4.3.2：RL双红
                        rightRotate(parent);
                        insertFlxUp(parent);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 左旋
     *
     *      p                       p
     *      |                       |
     *      x                       y
     *     / \        ------>      / \
     *   lx   y                   x   ry
     *       / \                 / \
     *     ly  ry               lx  ly
     * @param x
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        //将x的右子节点指向y的左子节点，将左子节点的父节点更新为x
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //当x的父节点不为空时，更新y的父节点为x的父节点，并将x的父节点指向为y
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else { //x为根节点，更新根节点
            this.root = y;
        }
        //将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋
     *
     *       p                       p
     *       |                       |
     *       x                       y
     *      / \        ------>      / \
     *     y  lx                   ly  x
     *    / \                         / \
     *  ly  ry                       ry  lx
     * @param x
     */
    private void rightRotate(RBNode x) {
        RBNode y = x.left;
        //将x的左子节点指向y的右子节点,并将右子节点的父节点更新为x
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        //当x的父节点不为空时，更新y的父节点为x的父节点，并将x的父节点指向为y
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else { //x为根节点，更新根节点
            this.root = y;
        }
        //将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.right = x;
    }

    public void inOrderPrint() {
        inOrderPrint(root);
    }

    public void preOrderPrint() {
        preOrderPrint(root);
    }

    /**
     * 中序打印红黑树
     * @param node
     */
    private void inOrderPrint(RBNode node) {
        if (node == null)return;
        inOrderPrint(node.left);
        System.out.println(node);
        inOrderPrint(node.right);
    }

    /**
     * 中序打印红黑树
     * @param node
     */
    private void preOrderPrint(RBNode node) {
        if (node == null)return;
        System.out.println(node);
        preOrderPrint(node.left);
        preOrderPrint(node.right);
    }

    /**
     * 获取节点的父节点
     * @param node
     * @return
     */
    public RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 判断节点是否是红色
     * @param node
     * @return
     */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 设置当前节点为红色
     * @param node
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置当前节点为黑色
     * @param node
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 判断节点是否是黑色
     * @param node
     * @return
     */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

         public RBNode() {}


         public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
             this.parent = parent;
             this.left = left;
             this.right = right;
             this.color = color;
             this.key = key;
             this.value = value;
         }

         public RBNode getParent() {
             return parent;
         }

         public void setParent(RBNode parent) {
             this.parent = parent;
         }

         public RBNode getLeft() {
             return left;
         }

         public void setLeft(RBNode left) {
             this.left = left;
         }

         public RBNode getRight() {
             return right;
         }

         public void setRight(RBNode right) {
             this.right = right;
         }

         public boolean isColor() {
             return color;
         }

         public void setColor(boolean color) {
             this.color = color;
         }

         public K getKey() {
             return key;
         }

         public void setKey(K key) {
             this.key = key;
         }

         public V getValue() {
             return value;
         }

         public void setValue(V value) {
             this.value = value;
         }

        @Override
        public String toString() {
            return "RBNode{" +
                    "color=" + color +
                    ", key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
