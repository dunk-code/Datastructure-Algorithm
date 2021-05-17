package school.xauat.datastructres.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        boolean loop = true;
        Scanner scanner = new Scanner (System.in);
        while(loop){
            System.out.println("a:入队列");
            System.out.println("g:出队列");
            System.out.println("s:显示队列");
            System.out.println("h:显示队列头数据");
            System.out.println("e:退出系统");
            key = scanner.next().charAt(0);
            switch (key){
                case 'a' :  //添加数据
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g' :  //取出数据
                    try{
                        System.out.println(queue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's' :  //显示数组
                    queue.showQueue();
                    break;
                case 'h' :  //查看队列头的数据
                    try{
                        int head = queue.headQueue();
                        System.out.println("队头的数据是："+head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :  //退出程序
                    loop = false;
                    System.out.println("退出系统~~~");
                    break;
                default :
                    break;
            }
        }
    }
}

//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    //数组的最大容量
    private int maxSize;
    //队列头
    //初始值 = -1
    private int front;
    //队列尾
    //初始值 = -1
    private int rear;
    //该数组用于存放数据，模拟队列
    private int[] arr;

    public ArrayQueue(int arrayMaxSize){
        this.maxSize = arrayMaxSize;
        this.arr = new int[maxSize];
        //指向队列的头部的前一个位置
        front = -1;
        //指向队列的尾部
        rear = -1;
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 入队列
     * @param n
     */
    public void addQueue(int n){
        if(this.isFull()){
            System.out.println("队列满，不能入队列~");
        }
        arr[++rear] = n;
    }

    /**
     * 出队列
     * @return
     */
    public int getQueue(){
        if(this.isEmpty()){
            throw new RuntimeException("队列空,不能取数据");
        }
        return arr[++front];
    }

    /**
     * 显示队列元素
     */
    public void showQueue(){
        if (this.isEmpty()){
            System.out.println("队列空,没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr["+i+"] = "+arr[i]+"\t");
        }
    }

    /**
     * 显示队列的头数据，不是取出数据
     * @return
     */
    public int headQueue(){
        if (this.isEmpty()){
            throw new RuntimeException("队列空，没有数据~~");
        }
        return arr[front + 1];
    }

}