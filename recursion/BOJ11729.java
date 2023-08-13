시간초과가 났던 이유: 재귀 조건을 count==0으로 했음. 
잘 작동하지만 count==1에서 처리해도 충분하기 때문에 그렇게 바꾸었더니 시간초과가 해결됨. 
기억해두자.

import java.util.*;
import java.io.*;

public class Main {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int res = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        recur(1, 3, N);
        System.out.println(res);
        // System.out.println((int)Math.pow(2, N)-1);
        System.out.println(sb);
    }

    public static void recur(int start, int target, int count) throws IOException{
        if(count==1){
            sb.append(String.format("%d %d\n", start, target));
            res++;
            return;
        }
        int mid = 6 - start - target;
        recur(start, mid, count-1);
        sb.append(String.format("%d %d\n", start, target)); 
        res++;
        recur(mid, target, count-1);
    }
}
