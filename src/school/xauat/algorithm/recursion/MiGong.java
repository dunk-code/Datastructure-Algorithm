package school.xauat.algorithm.recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        //地图
        int map[][] = new int[8][7];
        map[3][1] = 1;
        map[3][2] = 1;
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        setWay(map,1,1);

        System.out.println();
        setWay(map,1,1);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     *  寻找路径，下->右->上->左
     * @param map   地图
     * @param i     起始坐标
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        } else{
            if (map[i][j] == 0){//如果该点没有走过
                map[i][j] = 2;
                if(setWay(map,i + 1,j)){//向下
                    return true;
                } else if(setWay(map,i,j + 1)){//向右
                    return true;
                } else if(setWay(map,i - 1,j)){//向上
                    return true;
                } else if(setWay(map,i,j - 1)){//向左
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { //map[i][j]的值可能是 1 2 3
                return false;
            }
        }
    }
}













