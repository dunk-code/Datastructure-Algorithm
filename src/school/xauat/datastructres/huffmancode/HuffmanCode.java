package school.xauat.datastructres.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /*String constant = "i like like like java do you like a java";
        System.out.println(constant);
        byte[] constantBytes = constant.getBytes();
        byte[] huffmanCodesBytes = huffmanZip(constantBytes);
        Map<Byte, String> huffmanCodes = getCodes(createHuffmanTree(getList(constantBytes)));
        byte[] sourceBytes = deCode(huffmanCodesBytes, huffmanCodes);
        System.out.println(new String(sourceBytes));*/

        //测试压缩文件
        /*String srcFile = "d://src.bmp";
        String dstFile = "d://src.zip";
        zipFile(srcFile,dstFile);
        System.out.println("压缩成功！");*/

        //测试解压文件
        String zipFile = "d://src.zip";
        String dstFile = "d://src1.bmp";
        unZipFile(zipFile,dstFile);
        System.out.println("解压成功！");

    }

    public static void unZipFile(String zipFile,String dstFile){
        //创建输入流和对象输入流
        FileInputStream is = null;
        ObjectInputStream ois = null;
        //创建输出流
        FileOutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            byte[] bytes = deCode(huffmanBytes,huffmanCodes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFile(String srcFile,String dstFile){
        //创建输入流
        FileInputStream is = null;
        //创建输出流和对象输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            byte[] huffmanBytes = huffmanZip(b);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //根据将每个数据转换成一个节点，存放在List集合中
    private static ArrayList<Node> getList(byte[]bytes){
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte,Integer> map = new HashMap<>();
        for(byte b : bytes){
            Integer count = map.get(b);
            if(count == null){
                map.put(b,1);
            }else
                map.put(b,map.get(b) + 1);
        }
        //遍历map
        for(Map.Entry<Byte,Integer> entry : map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //根据List集合，获取哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root){
        if(root != null){
            root.preOrder();
            return;
        }
        return;
    }

    //哈夫曼编码的map
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte,String> getCodes(Node root){
        if (root == null)
            return null;
        getCodes(root,"",stringBuilder);
        return huffmanCodes;
    }

    //根据哈夫曼树获得哈夫曼编码
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if(node.data == null) {//非叶子节点
            //向左递归
            getCodes(node.left,"0",stringBuilder1);
            //向右递归
            getCodes(node.right,"1",stringBuilder1);
        }else {
            huffmanCodes.put(node.data,stringBuilder1.toString());
        }
    }

    //根据String和哈夫曼编码进行数据压缩
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            builder.append(huffmanCodes.get(b));
        }
        int length = (builder.length() + 7) / 8 ;
        byte[] by = new byte[length];
        int index = 0;
        String str;
        for (int i = 0; i < builder.length(); i += 8) {
            if((i + 8) > builder.length()){
                str = builder.substring(i);
                by[index] = (byte)Integer.parseInt(str,2);
                index++;
            }else {
                str = builder.substring(i, i + 8);
                by[index] = (byte)Integer.parseInt(str,2);
                index++;
            }
        }
        return by;
    }

    //封装压缩编码
    private static byte[] huffmanZip(byte[] bytes){
        //获取ArrayList<Node>集合
        ArrayList<Node> nodes = getList(bytes);
        //获取哈夫曼树
        Node root = createHuffmanTree(nodes);
        //获取哈夫曼编码表
        Map<Byte,String>huffmanCodes = getCodes(root);
        //压缩数据
        byte[]huffmanCodesBytes = zip(bytes,huffmanCodes);
        return huffmanCodesBytes;
    }

    /**
     * 将字节类型的十进制转换为二进制的字符串类型
     * @param flag 是否是最后一位，最后一位不需要补高位
     * @param b 带转换的字节
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;
        if(flag){
            temp |= 256;//正数需要补高位；
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length() - 8);
        }else
            return str;
    }

    /**
     * 解压
     * @param huffmanCodesBytes 哈夫曼编码后的字节数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 哈夫曼编码前的字节数组
     */
    public static byte[] deCode(byte[]huffmanCodesBytes,Map<Byte,String> huffmanCodes){
        boolean flag = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanCodesBytes.length; i++) {
            byte b = huffmanCodesBytes[i];
            if(i == huffmanCodesBytes.length - 1)
                flag = false;
            builder.append(byteToBitString(flag,b));
        }
        String str = builder.toString();
        //将hashMap反转
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while(start < str.length()){
            while(end < str.length() && map.get(str.substring(start,end)) == null){
                end++;
            }
            list.add(map.get(str.substring(start,end)));
            start = end;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            if(list != null) {
                b[i] = list.get(i);
            }
        }
        return b;
    }
}

class Node implements Comparable<Node>{
    public Byte data;
    public int weight;
    public Node left;
    public Node right;

    public Node(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if(this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
