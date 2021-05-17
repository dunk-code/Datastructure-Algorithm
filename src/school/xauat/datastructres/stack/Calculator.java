package school.xauat.datastructres.stack;

public class Calculator {
    public static void main(String[] args) {
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        String expresstion = "1*2-3/4+5*6-7*8+9/10";
        //表达式下标
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while(true){
            ch = expresstion.substring(index,index + 1).charAt(0);
            if (operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else{
                keepNum += ch;
                if (index == expresstion.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }else {
                    if(operStack.isOper(expresstion.substring(index + 1,index + 2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index += 1;
            if(index == expresstion.length())
                break;
        }
        while(!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println(expresstion + " = " + res);
    }
}

class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize){
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

    public int peek(){
        return stack[top];
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

    public boolean isOper(char ch){
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    public int priority(int oper){
        if(oper == '*' || oper == '/')
            return 1;
        else if (oper == '+' || oper == '-')
            return 0;
        else
            return -1;
    }

    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num2 / num1;
                break;
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num2 - num1;
                break;
        }
        return res;
    }
}
