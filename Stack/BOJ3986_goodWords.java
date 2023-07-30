import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<String> stack;
        int cnt = 0;
        for(int i=0; i<N; i++){
            stack = new Stack<>();
            String[] words = br.readLine().split("");
            // 좋은 단어인가?
            for(String w : words){
                if(stack.size() == 0)
                    stack.push(w);
                else {
                    String top = stack.peek();
                    if(top.equals(w))
                        stack.pop();
                    else
                        stack.push(w);
                }
            }
            if(stack.size() == 0)
                cnt++;
        }
        System.out.println(cnt);
    }
}
