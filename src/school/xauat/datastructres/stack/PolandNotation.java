package school.xauat.datastructres.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式"1+((2+3)×4)-5"
        String expresstion = "1+((20+3)*4)-5";
        List<String> infixExpresstion = toInfixExpresstionList(expresstion);
        System.out.println("中缀表达式字符串:" + expresstion);
        System.out.println("中缀表达式:" + infixExpresstion);
        List<String> suffixExpresstion = parseSuffixExpresstion(infixExpresstion);
        System.out.println("后缀表达式:" + suffixExpresstion);
        int res = calculate(suffixExpresstion);
        System.out.println("计算结果 = " + res);
    }

    /**
     * 将后缀表达式转换为ArrayList
     * @param suffixExpresstion 后缀表达式
     * @return
     */
    public static List<String> getList(String suffixExpresstion){
        String[] split = suffixExpresstion.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele : split){
            list.add(ele);
        }
        return list;
    }

    /**
     * 将中缀表达式字符串转成对应的List
     * @param s 输入字符串
     * @return
     */
    public static List<String> toInfixExpresstionList(String s){
        List<String> ls = new ArrayList<>();
        //索引，用于遍历中缀表达式字符串
        int i = 0;
        //用于多位数字的拼接
        String str;
        //每遍历到一个字符，就放入c中
        char c;
        do{
            //如果c是一个符号，直接拼接
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else{
                //扫描到的是一个数字
                str = "";
                //考虑多位数
                while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += s.charAt(i);
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());

        return ls;
    }

    /**
     * 中缀表达式转后缀表达式
     * @param ls    中缀表达式
     * @return
     */
    public static List<String> parseSuffixExpresstion(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        //因为整个过程中s2没有发生过弹栈，所以将s2栈换成一个List
        //Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for(String item : ls){
            if(item.matches("\\d+")){//数字
                s2.add(item);
            }else if(item.equals("(")){//如果是左括号“(”，则直接压入s1
                s1.push(item);
            }else if(item.equals(")")){//如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();// 将“(”弹栈
            }else{//遇到运算符时，比较其与s1栈顶运算符的优先级：
                while(s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并压入s2
        while(s1.size() !=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将List集合入栈，并计算值
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for(String item : ls){
            if (item.matches("\\d+")){
                stack.push(item);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch(item){
                    case "+" :
                        res = num1 + num2;
                        break;
                    case "-" :
                        res = num2 - num1;
                        break;
                    case "*" :
                        res = num1 * num2;
                        break;
                    case "/" :
                        res = num2 / num1;
                        break;
                    default :
                        throw new RuntimeException(item + "符号不匹配");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String ch){
        int res = 0;
        switch(ch){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("没有匹配到该字符");
                break;
        }
        return res;
    }
}