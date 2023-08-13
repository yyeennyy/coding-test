import java.util.*;
import java.io.*;

public class Main {
    public static String[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        map = new String[N+1][N+1];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], " ");
        }
        recur(1, 1, N);

        // print
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                bw.write(map[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    } 

    public static void recur(int y, int x, int len){
        if(len == 3){
            map[y][x] = "*";
            map[y][x+1] = "*";
            map[y][x+2] = "*";
            map[y+1][x] = "*";
            map[y+1][x+2] = "*";
            map[y+2][x] = "*";
            map[y+2][x+1] = "*";
            map[y+2][x+2] = "*";
            return;
        }
        len = len / 3;
        // 8개의 포인트
        recur(y, x, len);
        recur(y, x+len, len);
        recur(y, x+len*2, len);
        recur(y+len, x, len);
        recur(y+len, x+len*2, len);
        recur(y+len*2, x, len);
        recur(y+len*2, x+len, len);
        recur(y+len*2, x+len*2, len);
    }
}
