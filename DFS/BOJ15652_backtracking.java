import java.util.*;
import java.io.*;


public class Main {
    // 간단한 조합 문제 (백트래킹, DFS를 느끼자)
    public static int N;
    public static int M;
    public static int[] arr = new int[9];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        backtracking(1, 0);
    }

    public static void backtracking(int num, int cnt){
        if(cnt == M){
            for(int i=0; i<M; i++){
                System.out.printf("%d ", arr[i]);
            }
            System.out.println();
            return;
        }
        for(int i=num; i<=N; i++){
            arr[cnt] = i;
            backtracking(i, cnt+1);
        }
    }
}
