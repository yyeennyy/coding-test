import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        // 데이터 생성
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[][] flowers = new int[N][2];
        for(int i=0; i<N; i++){
            String[] line = sc.nextLine().split(" ");
            int start = Integer.parseInt(line[0])*100 + Integer.parseInt(line[1]);
            int end = Integer.parseInt(line[2])*100 + Integer.parseInt(line[3]);
            flowers[i] = new int[]{start, end};
        }
        int t = 301, nextT = -1, cnt = 0;
        while(t < 1201){
            nextT = t; // nextT는 맥시멈 지는시간 값을 찾는 역할
            // O(N)
            for(int i=0; i<N; i++){
                if(flowers[i][0] <= t && flowers[i][1] > nextT)
                    nextT = flowers[i][1];
            }
            // 꽃을 다 돌았는데도 nextT가 안 늘어났다? => 현재 t를 커버하는 꽃이 없음
            if(nextT == t){
                System.out.println(0);
                return;
            }
            // 카운팅!
            cnt++;
            // t 점프!
            t = nextT; 
        }
        System.out.println(cnt);
    }
}


