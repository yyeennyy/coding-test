import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static int N, M;
    public static int[] nums;
    public static int[] arr;
    public static int[] visit;
    public static StringBuilder sb = new StringBuilder();
    public static Set<String> set = new HashSet<>();

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        nums = new int[N];
        arr = new int[M];
        visit = new int[N];
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(line[i]);
        }

        Arrays.sort(nums);

        backtracking(0, 0);
        System.out.println(sb);
    }

    public static void backtracking(int i, int cnt){
        if(cnt == M){
            StringBuilder tmp = new StringBuilder();
            for(int j=0; j<M; j++){
                tmp.append(arr[j]);
                tmp.append(" ");
            }
            if(set.contains(tmp.toString())) return;
            set.add(tmp.toString());
            sb.append(tmp.toString());
            sb.append("\n");
            return;
        }
        for(int j=i; j<N; j++){
            if(visit[j] == 1) continue;
            visit[j] = 1;
            arr[cnt] = nums[j];
            backtracking(j+1, cnt + 1);
            visit[j] = 0;
        }
    }
}
