import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split("");
        // 뭔가 컴파일러랑 비슷한 느낌이다
        // (()[[]])([])
        // '깊게'들어가보는 것이 필요할듯하다
        // (가 열린개수만큼 닫혔는가?
        // [가 열린개수만큼 닫혔는가?

        // 문제는 이제 연산을 하는 것인데
        // 차근차근 생각해보니 이런 구조로 풀 수 있었음
        Stack<String> stack = new Stack<>();
        int[][] memo = new int[30][3];
        int idx = 0;
        int order = 0;
        for(String data : line){
            // System.out.println(stack);
            if(data.equals("(")){
                stack.push(data + " " + String.valueOf(order++));
            } else if(data.equals("[")){
                stack.push(data + " " + String.valueOf(order++));
            } else {
                if(stack.isEmpty()){
                    System.out.println(0); return;
                }
                // pop해서 확인하기
                String[] str = stack.pop().split(" ");
                String val = str[0];
                int num = Integer.parseInt(str[1]);
                if(data.equals(")")){
                    if(!val.equals("(")){
                        System.out.println(0); return;
                    }
                    memo[idx++] = new int[]{2, num, 0};
                } 
                if(data.equals("]")){
                    if(!val.equals("[")){
                        System.out.println(0); return;
                    }
                    memo[idx++] = new int[]{3, num, 0};
                }
                if(stack.isEmpty()){
                    memo[idx-1][2] = 1;
                }
            }
        }
        if(!stack.isEmpty()){
            System.out.println(0);
            return;
        }
        
        int[][] terms = new int[30][3];
        // for(int[] t_ : memo){
        //     if(t_[0]==0)
        //         break;
        //     System.out.println(Arrays.toString(t_));
        // }
        idx = 0;
        int term_idx = 0;
        int pre_order = -1;
        int val = memo[idx][0];
        while(val != 0){
            order = memo[idx][1];
            if(order > pre_order){
                // 더하는 항
                terms[term_idx++] = memo[idx];
            }
            else if(order < pre_order){
                //이 항에서 1아 아닐 때까지 이전을 탐색
                //그 항을 이전항에 곱하기 (본인보다 order가 높은 항에만!)
                for(int i=idx-1; i>=0; i--){
                    if(memo[i][2]==1)
                        break;
                    if(order > memo[i][1])
                        continue;
                    memo[i][0] *= memo[idx][0];
                }
            }
            pre_order = memo[idx][1];
            val = memo[++idx][0];
        }
        int sum = 0;
        for(int[] tm : terms){
            if(tm[0]==0) break;
            sum += tm[0];
        }
        System.out.println(sum);
    }
}
