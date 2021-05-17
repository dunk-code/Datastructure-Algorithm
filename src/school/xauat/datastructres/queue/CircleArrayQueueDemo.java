package school.xauat.datastructres.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(3);
        Scanner scanner =new Scanner(System.in);
        boolean loop = true;
        char k=' ';
        while(loop){
            System.out.println("a : 入队列");
            System.out.println("g : 出队列");
            System.out.println("s : 显示队列");
            System.out.println("h : 显示队头数据");
            System.out.println("v : 显示队列的有效数据的个数");
            System.out.println("e : 退出系统");
            k = scanner.next().charAt(0);
            switch (k){
                case 'a' :
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g' :
                    try{
                        System.out.println(queue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's' :
                    queue.showQueue();
                    break;
                case 'h' :
                    try{
                        System.out.println(queue.headQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'v' :
                    System.out.println(queue.getValidSum());
                    break;
                case 'e' :
                    System.out.println("退出系统~~");
                    loop=false;
                    break;
            }
        }
    }
}
class CircleArray{
    //队头
    private int front;
    //队尾
    private int rear;
    //数组的最大容量
    private int maxSize;
    //该数组用于存放数据，模拟队列
    private int[] arr;

    public CircleArray(int ArrayMaxSize){
        this.maxSize = ArrayMaxSize;
        this.arr = new int[maxSize];
        //指向队列头部
        front = 0;
        //指向队列尾部的后一个位置
        rear = 0;
    }

    /**
     * 判断队列是否为空
     * @return  rear == front
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 判断队列是否满
     * @return (rear + 1) % maxSize == front
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**
     * 入队列
     * @param value
     */
    public void addQueue(int value){
        if(isFull()){
            System.out.println("队列满,无法添加数据~~");
            return;
        }
        arr[rear] = value;
        rear = (++rear) % maxSize;
    }

    /**
     * 出队列
     * @return
     */
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,不能取数据");
        }
        int value = arr[front];
        front = (++front) % maxSize;
        return value;
    }

    /**
     * 获取队列中有效的数据个数
     * @return
     */
    public int getValidSum(){
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列数据
     */
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空,没有数据~~");
            return;
        }
        for (int i = front; i < front + getValidSum(); i++) {
            System.out.println("arr["+i % maxSize+"] = " + arr[i % maxSize]);
        }
    }
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，没有数据~~");
        }
        return arr[front];
    }

}
