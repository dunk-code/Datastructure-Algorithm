package school.xauat.datastructres.sparsearray;

import java.io.*;

public class Hold2File {
    private Hold2File(){}
    public static boolean hold(int arr[][]) {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream("tempFile"),"utf-8");
            for(int[] row : arr){
                for (int data : row){
                    out.write(data+"\t");
                }
                out.write("\n");
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
