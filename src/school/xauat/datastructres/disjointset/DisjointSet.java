package school.xauat.datastructres.disjointset;

public class DisjointSet {
    int N;
    //定义一个数组来模拟集合
    int[] parent;
    //定义一个数组标记每个树的深度
    int[] rank;

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {1, 2}, {1,3}, {2, 4}, {3,4}, {2,5}};
        DisjointSet disjointSet = new DisjointSet(6);
        for(int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (!disjointSet.union_vertices(x, y)){
                System.out.println("circle!");
                return;
            }
        }
        System.out.println("no circle");
    }

    public DisjointSet(int N) {
        this.N = N;
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }

    /**
     * 查找某个节点的根节点
     * @Param parent 需要查询的集合
     * @param x 需要查找的节点
     * @return  根节点
     */
    public int find_root(int x, int[]parent) {
        int x_root = x;
        while(parent[x_root] != -1) {
            x_root = parent[x_root];
        }
        return x_root;
    }

    /**
     * 合并两个顶点
     * @param x
     * @param y
     * @return
     *      true 合并成功
     *      false 不需要合并
     */
    public boolean union_vertices(int x, int y) {
        int x_root = find_root(x, parent);
        int y_root = find_root(y, parent);
        if(x_root == y_root) return false;
        if(rank[x_root] > rank[y_root]) {
            parent[y_root] = x_root;
        } else if(rank[x_root] < rank[y_root]) {
            parent[x_root] = y_root;
        } else {
            parent[x_root] = y_root;
            rank[y_root]++;
        }
        return true;
    }
}
