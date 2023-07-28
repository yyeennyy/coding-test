// 하이라이트 ---------------------------------------------------------------
//1. 연산을 차근히 정의하기
    public static void operation2(){
        deq.addLast(deq.pollFirst());
        cnt++;
    }
    public static void operation3(){
        deq.addFirst(deq.pollLast());
        cnt++;
    }
//2. 유의할 부분
int d1 = indexOf(targetN);
int d2 = deq.size()-1 - d1; // 주의. N-1이 아니라 size-1을 조심.
if(d1 <= d2){ // 등호 유의. 이게 더 적게하는 케이스.
// --------------------------------------------------------------------------

import java.util.*;
import java.io.*;

public class Main{
    public static int N;
    public static int cnt = 0;
    public static Deque<Integer> deq;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        deq = new LinkedList<>();
        line = br.readLine().split(" ");

        // deq 초기화
        for(int i=1; i<=N; i++){
            deq.add(i);
        }

        for(String t_ : line){
            int targetN = Integer.parseInt(t_);
            int d1 = indexOf(targetN);
            int d2 = deq.size()-1 - d1; // 주의. N-1이 아니라 size-1을 조심.
            if(d1 <= d2){ // 등호 유의. 이게 더 적게하는 케이스.
                for(int i=0; i<d1; i++)
                    operation2();
            }else{
                for(int i=0; i<=d2; i++)
                    operation3();
            }
            pop();
        }
        System.out.println(cnt);
    }
    public static int indexOf(int number){
        Iterator<Integer> iter = deq.iterator();
        int diff = 0;
        while(iter.hasNext()){
            int num = iter.next();
            if(num == number) break;
            diff++;
        }
        return diff;
    }
    public static void operation2(){
        deq.addLast(deq.pollFirst());
        cnt++;
    }
    public static void operation3(){
        deq.addFirst(deq.pollLast());
        cnt++;
    }
    public static void pop(){
        deq.poll();
    }
}
