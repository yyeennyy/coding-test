import java.util.*;
import java.io.*;

public class Main {
    public static int N;
    public static int[][] map;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            String[] line = br.readLine().split("");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        recur(0, 0, N, 0);
        System.out.println(sb);

    }

    public static void recur(int y, int x, int len, int depth){
        boolean ok = true;
        int type = map[y][x];
        for(int i=y; i<y+len; i++){
            for(int j=x; j<x+len; j++){
                if(map[i][j] != type){
                    ok = false;
                    break;
                }
            }
        }
        if(ok){
            sb.append(type);
            return;
        }
        if(len==1){
            sb.append(map[y][x]);
            return;
        }
        // 4분할
        len = len/2;
        depth++;
        sb.append("(");
        recur(y, x, len, depth);
        recur(y, x+len, len, depth);
        recur(y+len, x, len, depth);
        recur(y+len, x+len, len, depth);
        sb.append(")");

    }
}
