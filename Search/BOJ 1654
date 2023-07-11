후기(배운 것)
- 입력값 범위 인식이 바로 되어야 함 (int로 선언 vs long으로 선언)
- 앞으로는 아래와 같은 판단 기준을 가지자. 명료하게 사고해야 한다.
> "정답 조건"인 경우와, "정답 조건이 아닌 경우"를 기준으로 끌고 가야 함
> "정답 조건"을 만족하는 "시점"에는 값을 저장해야 함
> 반복문을 빠져나오는 시점에서 "정답 조건을 만족시켰는지 아닌지"를 고려하기

 

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int K = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);

        long[] list = new long[K];
        for(int i=0; i<K; i++){
            list[i] = Long.parseLong(br.readLine());
        }

        // bin search
        long start = 1;
        Arrays.sort(list);
        long end = list[K-1];
        long seg = 0;
        long mid = 0;
        long res = 0;
        if (list.length != 0){
            while(true){
                seg = 0;
                // mid값
                if (mid == 0) mid = 1;
                else if(start == end) mid = start;
                else mid = start / 2 + end / 2;
              
                // seg 계산
                for(long length : list){
                    seg += length / mid;
                    if (seg >= N)
                        break;
                }
                // ㄴ 유의: seg는 N보다 클 수도, 작을 수도 있다.
              
                // 이진탐색 완료
                if(start >= end)
                    break; // ★유의: 정답 조건일 수 있음에도 res에 값 반영 안된 상태

                // 인덱스 갱신
                if (seg >= N){ // 정답 조건
                    start = mid + 1;
                    if (mid > res) res = mid; 
                    // ㄴ 정답 조건을 만족하는 시점이므로, res에 값 저장
                }
                else{ // 정답 조건 아님
                    end = mid - 1;
                }
            }
        }
        if (mid > res && seg >= N) res = mid; // ★결국 반영이 필요한 부분임을 유의
        System.out.println(res);
    }
}
