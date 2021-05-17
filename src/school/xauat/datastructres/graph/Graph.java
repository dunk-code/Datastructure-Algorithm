package school.xauat.datastructres.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<String> vertexList;//存储定点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;

    public Graph(int n){
        vertexList = new ArrayList<String>();
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public static void main(String[] args) {
        int n = 5;
        Graph graph = new Graph(n);
        String[] vertexs = {"A","B","C","D","E"};
        for (String vertex : vertexs){
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //graph.show();
        System.out.println("深度优先~");
        graph.dfs();
        System.out.println( );
        System.out.println("广度优先~");
        graph.bfs();
    }

    public void bfs(boolean[]isVisited,int i){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while(!queue.isEmpty()){
            i = queue.poll();
            if (!isVisited[i]){
                System.out.print(getValueByIndex(i) + "->");
                isVisited[i] = true;
            }
            int w = getFirstNeighbor(i);
            while(w != -1 && !isVisited[w]){
                queue.add(w);
                w = getNextNeighbor(i,w);
            }
        }
    }

    public void bfs(){
        isVisited = new boolean[5];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    public void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while(w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        isVisited = new boolean[5];
        for (int i = 0; i < edges.length; i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    public int getFirstNeighbor(int i){
        for (int j = 0; j < edges.length; j++) {
            if (edges[i][j] > 0){
                return j;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int i, int w){
        for (int j = w + 1; j < edges.length; j++) {
            if (edges[i][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //返回结点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }


    public void show(){
        for (int[] link : edges){
            System.err.println(Arrays.toString(link));
        }
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
