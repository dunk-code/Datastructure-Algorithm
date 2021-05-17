package school.xauat.datastructres.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建原始数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 2;
        //遍历创建的原始数组
        System.out.println("原始数组~~~~");
        for(int[]row : chessArr1){
            for(int data : row){
                System.out.print(data + "\t");
              }
            System.out.println();
        }

        //遍历原始二维数组，得到有效值
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length ; j++) {
                if(chessArr1[i][j] != 0)
                    sum++;
            }
        }
        System.out.println("sum = " + sum);

        //创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //将二维数组的有效数据存入到稀疏数组中
        //计数器
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11 ; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();
        //将稀疏数组输出到文件中
        System.out.println(Hold2File.hold(sparseArr));
        //遍历数组稀疏数组
        System.out.println("得到的稀疏数组~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.print(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]+"\t");
            System.out.println();
        }
        System.out.println();

        //将稀疏数组恢复成二维数组
        /*
        1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
        2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
         */
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            int row = sparseArr[i][0];
            int col = sparseArr[i][1];
            int val = sparseArr[i][2];
            chessArr2[row][col] = val;
        }

        System.out.println("恢复后的二位数组~~~~");
        for(int[]r : chessArr1){
            for(int data : r){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

    }
}
