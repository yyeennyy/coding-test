import java.util.*;
import java.io.*;

public class Main{
    public static int N;
    public static int cnt = 0;
    public static Deque<Integer> deq;


    public static void main(String[] args) throws IOException{
        // 350000(p)개함수 x 350000(n)길이배열
        // 상당하다
        // - 역순 접근이 빨라야 하며
        // - 첫 요소 삭제도 빨라야 함
        // Deque를 사용하자.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deq;
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String cmd = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            // Deque 준비
            deq = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<arr.length(); i++){
                char c = arr.charAt(i);
                if(c==',' || c==']' || c=='['){
                    if(sb.toString().length() != 0){
                        deq.add(Integer.parseInt(sb.toString()));
                        sb = new StringBuilder();
                    }
                    continue;
                }
                sb.append(c-'0');
            }
            // 커맨드를 적용하자.
            boolean reverse = false;
            boolean errorFlag = false;
            for(int i=0; i<cmd.length(); i++){
                char c = cmd.charAt(i);
                switch(c){
                    case 'R':
                        reverse = !reverse;
                        break;
                    case 'D':
                        Integer num = null;
                        if(!reverse)
                            num = deq.pollFirst();
                        else
                            num = deq.pollLast();
                        if(num == null)
                            errorFlag = true;
                }
                if(errorFlag) break;
            }

            // 출력 : 내 문제1: 출력형식에 공백을 검토 안함.. 포함해버림
            if(errorFlag)
                System.out.println("error");
            else{
                if(!reverse){
                    if(deq.size()==0)
                        System.out.println("[]");
                    else
                        System.out.println(deq.toString().replace(" ", ""));
                }else{
                    int[] deqRVS = new int[deq.size()];
                    for(int j=0; j<deqRVS.length; j++){
                        deqRVS[j] = deq.pollLast();
                    }
                    if(deqRVS.length==0) // 내 문제2: deq.size()로 했었음.. 무조건 0인데..
                        System.out.println("[]");
                    else
                        System.out.println(Arrays.toString(deqRVS).replace(" ", ""));
                }
            }
        }
    }
}
