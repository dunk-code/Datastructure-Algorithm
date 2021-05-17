package school.xauat.datastructres.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(125);
        //circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(10,20,125);
    }
}

//单向循环链表
class CircleSingleLinkedList{
    private Boy first = null;

    public CircleSingleLinkedList(){}

    /**
     * 添加指定的节点
     * @param nums
     */
    public void add(int nums){
        if(nums < 1){
            System.out.println("输入的数据不合法~~");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i < nums + 1; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                curBoy = first;
                boy.setNext(first);
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy(){
        if (first == null){
            System.out.println("链表为空~~");
            return;
        }
        //因为first不能动，所以创建一个辅助指针
        Boy curBoy = first;
        while(true){
            System.out.println("小孩的编号是" + curBoy.getNo());
            if (curBoy.getNext() == first)
                break;
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户输入，计算小孩出圈的顺序
     * @param startNo   开始小孩的编号
     * @param countNum  一次数几下
     * @param nums      总共小孩数
     */
    public void countBoy(int startNo,int countNum,int nums){
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("输入的数据有误~~~");
            return;
        }
        //创建辅助指针，指向first指针的前一位
        Boy helper = first;
        while(helper.getNext() != first ){
            helper = helper.getNext();
        }
        //根据开始小孩的编号，是first指针指向开始小孩
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数出队
        while(first.getNext() != first){
            //报数
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //出队
            System.out.println("小孩" + first.getNo() + "出圈~~");
            helper.setNext(first.getNext());
            first = first.getNext();
        }
        System.out.println("最后出圈的小孩编号" + first.getNo());
    }
}

//创建一个Boy节点,没一个对象表示一个孩子
class Boy{
    //小孩编号
    private int no;
    //下一个小孩
    private Boy next;

    //构造器
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
