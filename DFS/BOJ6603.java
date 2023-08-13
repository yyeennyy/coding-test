import java.util.*;
import java.io.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();;
    public static int[] map;
    public static int[] arr;
    public static int k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            sb = new StringBuilder();
            String[] line = br.readLine().split(" ");
            if(line[0].equals("0")) break;
            k = Integer.parseInt(line[0]);
            map = new int[k];
            arr = new int[6];
            for(int i=1; i<=k; i++){
                map[i-1] = Integer.parseInt(line[i]);
            }
            backtracking(0, 0);
            System.out.println(sb);
        }
    }

    public static void backtracking(int num, int cnt) {
        if(cnt==6){
            for(int i=0; i<6; i++){
                sb.append(arr[i]);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        if(num>=k) return;
        for(int i=num; i<k; i++){
            arr[cnt] = map[i];
            backtracking(i+1, cnt+1);
        }
    }
}
