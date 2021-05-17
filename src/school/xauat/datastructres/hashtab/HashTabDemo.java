package school.xauat.datastructres.hashtab;

import java.util.LinkedList;
import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("del:删除雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch(key){
                case "add":
                    System.out.println("请输入id:");
                    Integer id = scanner.nextInt();
                    System.out.println("请输入name:");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.addById(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "del":
                    System.out.println("请输入要删除的雇员id:");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "find":
                    System.out.println("请输入查找id:");
                    id = scanner.nextInt();
                    emp = hashTab.findEmpById(id);
                    System.out.println(emp == null ? "没有找到" : emp);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("输入信息有误，请重新输入~");
                    break;
            }
        }
    }
}

class HashTab{
    private int size;
    private EmpSingleList[] empSingleListArray;

    public int hashFun(int id){
        return id % size;
    }

    public HashTab(int size){
        this.size = size;
        empSingleListArray = new EmpSingleList[size];
        for (int i = 0; i < empSingleListArray.length; i++) {
            empSingleListArray[i] = new EmpSingleList();
        }
    }

    public void add(Emp emp){
        int index = hashFun(emp.id);
        empSingleListArray[index].addEmp(emp);
    }

    public void addById(Emp emp){
        int index = hashFun(emp.id);
        empSingleListArray[index].addEmpByNo(emp);
    }

    public void list(){
        for (int i = 0; i < empSingleListArray.length; i++) {
            empSingleListArray[i].list(i);
        }
    }

    public Emp findEmpById(int id){
        int index = hashFun(id);
        Emp emp = empSingleListArray[index].find(id);
        return emp;
    }

    public void deleteEmpById(int id){
        int index = hashFun(id);
        empSingleListArray[index].delete(id);
    }
}
class EmpSingleList{
    private Emp head;

    public void addEmp(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(curEmp.next != null){
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void addEmpByNo(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        //如果插入的雇员id小于头节点的id
        if(head.id > emp.id){
            emp.next = head;
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(curEmp.next != null){
            if(curEmp.next.id > emp.id){
                emp.next = curEmp.next;
                curEmp.next = emp;
                return;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(int i){
        if (head == null){
            System.out.println("第" + i + "条链表为空~~");
            return;
        }
        Emp curEmp = head;
        while(curEmp != null){
            System.out.print("--->" + curEmp);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp find(int id){
        if (head == null)
            return null;
        Emp curTemp = head;
        while(curTemp != null){
            if(curTemp.id == id)
                return curTemp;
            curTemp = curTemp.next;
        }
        return null;
    }

    public void delete(int id){
        if (head == null){
            System.out.println("没有找到该雇员~");
            return;
        }
        Emp curTemp = head;
        if (head.id == id){
            head = head.next;
            System.out.println("删除成功~");
            return;
        }
        while(curTemp.next != null){
            if(curTemp.next.id == id){
                curTemp.next = curTemp.next.next;
                System.out.println("删除成功~");
                return;
            }
            curTemp = curTemp.next;
        }
        System.out.println("没有找到该雇员~");
    }


}
class Emp{
    public Integer id;
    public String name;
    public Emp next;

    public Emp(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
