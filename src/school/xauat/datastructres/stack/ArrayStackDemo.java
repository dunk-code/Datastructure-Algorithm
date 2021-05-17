package school.xauat.datastructres.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
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
                    try{
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
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

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈已满，无法入栈~~");
            return;
        }
        stack[++top] = value;
        return;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("stack is empty~~");
        }
        return stack[top--];
    }

    //遍历
    public void list(){
        if (isEmpty()){
            System.out.println("栈已空，没有数据~~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]"+stack[i]);
        }
    }


}