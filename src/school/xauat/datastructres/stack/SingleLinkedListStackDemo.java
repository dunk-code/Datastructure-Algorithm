package school.xauat.datastructres.stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack stack = new SingleLinkedListStack();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        String key = "";
        while(loop){
            System.out.println("show:显示栈");
            System.out.println("pop:出栈");
            System.out.println("push:入栈");
            System.out.println("exit:退出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch(key){
                case "show" :
                    stack.list();
                    break;
                case "pop" :
                    stack.pop();
                    break;
                case "push" :
                    System.out.println("请输入数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "exit" :
                    loop = false;
                    break;
            }
        }
        System.out.println("退出栈~~~~");
    }
}
class SingleLinkedListStack{
    private StackEle head = new StackEle(0);

    public SingleLinkedListStack(){}

    //栈空
    public boolean isEmpty(){
        return head.getNext() == null;
    }

    //入栈
    public void push(int no){
        StackEle newStackEle = new StackEle(no);
        newStackEle.setNext(head.getNext());
        head.setNext(newStackEle);
    }

    //出栈
    public void pop(){
        if (isEmpty()){
            System.out.println("栈已空，没有数据~~~");
            return;
        }
        System.out.println("出栈的元素" + head.getNext().getNo());
        if (head.getNext().getNext() == null){
            head.setNext(null);
        }else{
            head.setNext(head.getNext().getNext());
        }
    }

    //显示栈中的数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈已空，没有数据~~");
            return;
        }
        StackEle temp = head.getNext();
        while(temp != null){
            System.out.println(temp.getNo());
            temp = temp.getNext();
        }
        return;
    }
}

//创建栈节点，每一个对象，表示一个栈的节点
class StackEle{
    private int no;
    private StackEle next;

    public StackEle(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public StackEle getNext() {
        return next;
    }

    public void setNext(StackEle next) {
        this.next = next;
    }

}