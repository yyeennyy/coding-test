import java.util.*;
import java.io.*;

public class Main {
    public static int[] map = new int[100005];
    public static int[] footprint = new int[100005];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        if(N==K){
            System.out.println(0);
            System.out.println(N);
            return;
        }
        Arrays.fill(map, -1);
        Queue<Integer> q = new LinkedList<>();
        map[N] = 0;
        q.add(N);
        footprint[N] = -1;
        while(!q.isEmpty()){
            int now = q.poll();
            for(int d=0; d<3; d++){
                int moving = 0;
                switch(d){
                    case(0):
                        moving = now + 1;
                        break;
                    case(1):
                        moving = now - 1;
                        break;
                    case(2):
                        moving = now * 2;
                        break;
                }
                if(moving<0||moving>=100005) continue;
                if(map[moving] != -1) continue;
                map[moving] = map[now] + 1;
                q.add(moving);
                footprint[moving] = now;
                if(map[K] != -1){
                    System.out.println(map[K]);
                    ArrayList<Integer> res = new ArrayList<>();
                    int f = K;
                    while(f != N){
                        res.add(footprint[f]);
                        f = footprint[f];
                    }
                    for(int i=res.size()-1; i>=0; i--){
                        System.out.printf("%d ", res.get(i));
                    }
                    System.out.printf("%d ", K);
                    return;
                }
            }
        }
    }
}
