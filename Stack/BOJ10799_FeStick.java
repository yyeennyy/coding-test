스택 문제인데 나는 이렇게 풀었다. 그런데 스택문제로 풀어도 간단하다.
스택문제로 풀 경우 스택사이즈가 현재막대개수다.
(가 나왔을 때 즉 막대의 시작은 무조건 stack에 추가한다.
)가 나왔을 때 이전문자가 (면 그건 레이저의 경우이므로 하나 pop해주고 스택사이즈를 값에더한다.
그게아니고 진짜 막대의 끝이면 값에 1을 더한다. 그리고 하나 pop한다.

import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        line = line.replace("()", "a");
        int res = 0;
        int val = 0;
        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            if(c == 'a'){
                res += val;
            }
            if(c == '('){
                val++;
            }
            if(c == ')'){
                res++;
                val--;
            }
        }
        System.out.println(res);
    }
}
