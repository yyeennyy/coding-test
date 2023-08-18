import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static int N, M;
    public static int[] nums;
    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        nums = new int[N];
        arr = new int[M];
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(nums);

        backtracking(0, 0);
        System.out.println(sb);
    }

    public static void backtracking(int i, int cnt){
        if(cnt == M){
            for(int j=0; j<M; j++){
                sb.append(arr[j]);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int j=0; j<N; j++){
            arr[cnt] = nums[j];
            backtracking(j, cnt + 1);
        }
    }
}
