import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static int N;
    public static int[][] map;
    public static Map<Integer, Integer> result = new HashMap<>(); 
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result.put(-1, 0);
        result.put(0, 0);
        result.put(1, 0);

        for(int i=0; i<N; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        recur(0, 0, N);

        System.out.println(result.get(-1));
        System.out.println(result.get(0));
        System.out.println(result.get(1));


    }

    public static void recur(int y, int x, int len){
        boolean ok = true;
        for(int i=y; i<y+len; i++){
            for(int j=x; j<x+len; j++){
                if(map[i][j] != map[y][x]){
                    ok = false;
                    break;
                }
            }
            if(!ok) break;
        }
        if(ok){
            result.put(map[y][x], result.get(map[y][x])+1);
            return;
        }
        len /= 3;
        recur(y, x, len);
        recur(y, x+len, len);
        recur(y, x+len*2, len);
        recur(y+len, x, len);
        recur(y+len, x+len, len);
        recur(y+len, x+len*2, len);
        recur(y+len*2, x, len);
        recur(y+len*2, x+len, len);
        recur(y+len*2, x+len*2, len);
    }
}
