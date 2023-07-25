// int val = lop.get(k)*(N-k);로 끝나는 간단한 Greedy문제!

import java.util.*;
import java.io.*;

public class Main{

    public static ArrayList<Integer> lop = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            lop.add(i, Integer.parseInt(br.readLine()));
        }
        Collections.sort(lop);
        // k개의 로프에는 각 w/k중량이 걸린다.
        // 최대 중량 w를 구하자
        int maxW = 0;
        for(int k=N-1; k>=0; k--){
            int val = lop.get(k)*(N-k);
            if(val>maxW)
                maxW = val;
        }
        System.out.println(maxW);
    }
}
