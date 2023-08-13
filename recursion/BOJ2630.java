import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map;
    public static int blue = 0;
    public static int white = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        // recur
        recur(0, 0, N);

        // print
        System.out.println(white);
        System.out.println(blue);

    } 

    public static void recur(int y, int x, int len){
        int type = map[y][x];
        boolean ok = true;
        for(int i=y; i<y+len; i++){
            for(int j=x; j<x+len; j++){
                if(map[i][j] != type){
                    ok = false;
                    break;
                }
            }
        }
        if(ok){
            if(type==0) white++;
            else blue++;
            return;
        }
       
        len = len / 2;
        // 4개로 잘라짐
        recur(y, x, len);
        recur(y, x+len, len);
        recur(y+len, x, len);
        recur(y+len, x+len, len);
    }

}
