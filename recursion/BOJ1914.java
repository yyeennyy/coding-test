자바의 여러가지 출력 방법에 대한 공부가 필요했던 문제.
그리고, 똑같이 StringBuilder를 사용하더라도 append안에서 String.format()을 사용하면 시간초과가 났다.
왜 그런지 공부 해보기

그리고 BigInteger 사용의 필요성도 직접 느꼈다.
  

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static int N;
    public static BigInteger res;
    public static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = new BigInteger("2");
        System.out.println(res.pow(N).subtract(new BigInteger("1")));

        if(N <= 20){
            sb = new StringBuilder();
            recur(1, 3, N);
        }
        // print
        if(N <= 20){
            System.out.println(sb);
        }
    }

    public static void recur(int start, int target, int cnt) throws IOException{
        if(cnt == 1){
            sb.append(start);
            sb.append(" ");
            sb.append(target);
            sb.append("\n");
            return;
        }
        recur(start, 6 - start - target, cnt-1);
        sb.append(start);
        sb.append(" ");
        sb.append(target);
        sb.append("\n");
        recur(6 - start - target, target, cnt-1);
    }
}
