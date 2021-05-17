package school.xauat.datastructres.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"公孙胜","入云龙");
        HeroNode2 hero5 = new HeroNode2(5,"大刀","关胜");

        System.out.println("开始按英雄编号添加到链表~~");
        doubleLinkedList.addByNo(hero4);
        doubleLinkedList.addByNo(hero4);
        doubleLinkedList.addByNo(hero5);
        doubleLinkedList.addByNo(hero1);
        doubleLinkedList.addByNo(hero1);
        doubleLinkedList.addByNo(hero3);
        doubleLinkedList.addByNo(hero2);
        System.out.println("正向遍历链表~~~");
        doubleLinkedList.list();
        System.out.println("反向遍历链表~~~");
        doubleLinkedList.reversePrint();
        System.out.println("修改第二个节点+遍历~~~");
        doubleLinkedList.update(new HeroNode2(2,"小卢","火麒麟"));
        doubleLinkedList.list();
        System.out.println("删除编号为1的英雄~~~~");
        doubleLinkedList.delete(5);
        doubleLinkedList.list();
    }

    /**
     * 反转双向链表
     * @param head
     */
    public void reverseList(HeroNode2 head){

    }
}

//定义DoubleLinkedListDemo
class DoubleLinkedList{
    //初始化一个头节点
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    /**
     * 添加一个节点到双向链表的最后
     * @param newHeroNode
     */
    public void add(HeroNode2 newHeroNode){
        HeroNode2 temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newHeroNode;
        newHeroNode.pre = temp;
        return;
    }

    /**
     * 按英雄编号添加英雄
     * @param newHeroNode
     */
    public void addByNo(HeroNode2 newHeroNode){
        HeroNode2 temp = head;
        while(temp.next != null){
            if (temp.next.no == newHeroNode.no){
                System.out.println(newHeroNode.no+"号英雄已经存在~~");
                return;
            }
            if(temp.next.no > newHeroNode.no){
                newHeroNode.next = temp.next;
                temp.next.pre = newHeroNode;
                temp.next = newHeroNode;
                newHeroNode.pre = temp;
                return;
            }
            temp = temp.next;
        }
        temp.next = newHeroNode;
        newHeroNode.pre = temp;
    }
    /**
     * 遍历双向链表
     * @param
     */
    public void list(){
        if (head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode2 temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
        return;
    }

    /**
     * 反向遍历
     */
    public void reversePrint(){
        if (head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode2 temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        while(temp.pre != null){
            System.out.println(temp);
            temp = temp.pre;
        }
        return;
    }

    /**
     * 修改指定节点
     * @param heroNode2
     */
    public void update(HeroNode2 heroNode2){
        if(head.next == null){
            System.out.println("链表为空~~");
            return;
        }
        boolean flag = false;
        HeroNode2 temp = head.next;
        while(temp != null){
            if (temp.no == heroNode2.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
            return;
        }else{
            System.out.println("没有找到该节点~~");
            return;
        }
    }

    /**
     * 删除指定编号的英雄
     * @param no
     */
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode2 temp = head.next;
        while(temp != null){
            if(temp.no == no){
                temp.pre.next = temp.next;
                if(temp.next != null)
                    temp.next.pre = temp.pre;
                break;
            }
            temp = temp.next;
        }
    }
}
//定义HeroNode2，每个HeroNode2对象对应一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点 默认值为null
    public HeroNode2 next;
    //指向前一个节点 默认值为null
    public HeroNode2 pre;

    //构造器
    public HeroNode2 (int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}