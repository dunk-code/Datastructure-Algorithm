package school.xauat.datastructres.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"公孙胜","入云龙");
        HeroNode hero5 = new HeroNode(5,"大刀","关胜");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByNo(hero5);
        singleLinkedList.addByNo(hero5);
        singleLinkedList.addByNo(hero4);
        singleLinkedList.addByNo(hero1);
        singleLinkedList.addByNo(hero3);
        singleLinkedList.addByNo(hero2);
        singleLinkedList.list();

        singleLinkedList.update(new HeroNode(2,"小卢","火麒麟"));
        System.out.println("\n"+"修改后的链表~~");
        singleLinkedList.list();

        singleLinkedList.delete(5);
        System.out.println("\n"+"删除后的链表~");
        singleLinkedList.list();

        System.out.println("查询到的节点~~");
        System.out.println(singleLinkedList.search(4) == null ? "没有找到":singleLinkedList.search(4));
        System.out.println("\n" + "链表的有效节点个数：");
        System.out.println(getLength(singleLinkedList.getHead()));

        System.out.println("\n" + "查询到的倒数第1个节点");
        System.out.println(findLastIndexNode(singleLinkedList.getHead(),1).toString());
        System.out.println();
        System.out.println("反转之前的链表~~");
        singleLinkedList.list();
        System.out.println("\n" + "反转后的单向链表~~");
        singleLinkedList.addByNo(hero5);
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("\n" + "从尾到头打印链表~~~");
        reversePrint(singleLinkedList.getHead());

        /*SingleLinkedList h1 = new SingleLinkedList();
        SingleLinkedList h2 = new SingleLinkedList();
        h1.addByNo(hero5);
        h1.addByNo(hero1);
        h1.addByNo(hero2);
        h2.addByNo(hero4);
        h2.addByNo(hero3);
        System.out.println(h1.getHead().next);
        h1.list();
        System.out.println();
        h2.list();
        System.out.println();
        HeroNode heroNode = mergeTwoLists(h1.getHead().next,h2.getHead().next);
        SingleLinkedList h3 = new SingleLinkedList();
        h3.getHead().next = heroNode;
        h3.list();*/
    }


    /**
     * 计算单链表中的有效节点的个数
     * @param head  单链表的头节点
     * @return  length  单链表有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null)
            return 0;
        int length = 0;
        HeroNode temp = head.next;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第index个节点
     * @param head  单链表的头节点
     * @param index 查询链表的下标
     * @return  指定倒数该下标的节点
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        int length = getLength(head);
        if (length < index || index < 0)
            return null;
        HeroNode temp = head.next;
        int x = length - index;
        while(x > 0){
            temp = temp.next;
            x--;
        }
        return temp;
    }

    /**
     * 使用栈数据结构反向打印链表
     * @param head
     */
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        return;
    }


    /**
     * 使用头插法反转链表
     * @param head
     */
    public static void reverseList(HeroNode head){
        if(head.next == null || head.next.next == null)
            return;
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while(cur != null){
            //将cur的下一个节点保存在next中
            next = cur.next;
            //将reverseHead节点的下一个节点连接到cur节点的下一个
            cur.next = reverseHead.next;
            //reverseHead节点重置
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 按顺序合并两个链表
     * @param h1
     * @param h2
     * @return
     */
    public static HeroNode mergeTwoLists(HeroNode h1,HeroNode h2){
        if(h1.next == null)
            return h2;
        else if(h2.next == null)
            return h1;
        else if(h1.no <= h2.no){
            h1.next = mergeTwoLists(h1.next,h2);
            return h1;
        }
        else {
            h2.next = mergeTwoLists(h2.next,h1);
            return h2;
        }
    }
}

//定义SingleLinkedList
class SingleLinkedList{
    //头节点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead(){
        return head;
    }

    /**
     * 添加节点到单向列表
     * 不考虑编号顺序
     *  -找到当前链表的最后节点
     *  -将最后这个节点的next指向新的节点
     * @param heroNode
     */

    public void add(HeroNode heroNode){
        //因为头节点不能动，所以创建临时节点
        HeroNode temp = head;
        while(true){
            if(temp.next == null)
                break;
            temp = temp.next;
        }
        temp.next = heroNode;
    }


    /**
     * 按照编号大小添加节点到单链表
     * 如果该编号已经存在，则添加失败，给出提示
     * @param heroNode
     */

    public void addByNo(HeroNode heroNode){
        //头节点不能动，因此使用一个辅助节点
        HeroNode temp = head;
        while(true){
            if (temp.next == null)
                break;
            if (temp.next.no == heroNode.no){
                System.out.println(heroNode.no+"号英雄已经存在~");
                return;
            }
            if(temp.next.no > heroNode.no)
                break;
            temp = temp.next;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 修改指定节点
     * @param newHeroNode
     */

    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        boolean flag = false;
        HeroNode temp = head.next;
        while(true){
            if(temp == null)
                break;
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.no = newHeroNode.no;
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("没有找到~");
        }
    }


    /**
     * 删除指定节点
     * @param no
     */
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null)
                break;
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println(no+"号没有找到~");
        }
    }


    /**
     * 查找指定节点
     * @param no
     * @return
     */

    public HeroNode search(int no){
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                return null;
            }
            if(temp.no == no){
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 遍历链表
     */

    public void list(){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if (temp == null)
                return;
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

//定义HeroNode 每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
