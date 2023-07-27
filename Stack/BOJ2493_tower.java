import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<int[]> tower = new Stack<>();
        tower.push(new int[]{100000001, 0});
        String[] tower_ = br.readLine().split(" ");

        for(int i=1; i<=N; i++){
            int h = Integer.parseInt(tower_[i-1]);
            while(tower.peek()[0] < h)
                tower.pop();
            System.out.print(tower.peek()[1] + " ");
            tower.push(new int[]{h, i});
        }
    }
}
