import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        if(N==K){
            System.out.println(0);
            return;
        }
        int[] map = new int[100005]; // 200000
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while(!q.isEmpty()){
            int now = q.poll();
            if(!(0 > now+1 || now+1 >= 100005)){
                if(!(map[now+1] < map[now] + 1 && map[now+1] != 0)){
                    map[now + 1] = map[now] + 1;
                    q.add(now+1);
                }
            }
            if(!(0 > now-1 || now-1 >= 100005)){
                if(!(map[now-1] < map[now] + 1 && map[now-1] != 0)){
                    map[now - 1] = map[now] + 1;
                    q.add(now-1);
                }
            }
            if(!(0 > now*2 || now*2 >= 100005)){
                if(!(map[now*2] < map[now] + 1 && map[now*2] != 0)){
                    map[now * 2] = map[now] + 1;
                    q.add(now*2);
                }
            }
            if(map[K] != 0){
                System.out.println(map[K]);
                break;
            }
        }
    }
}
