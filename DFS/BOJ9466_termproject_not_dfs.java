import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int C = Integer.parseInt(br.readLine());
        for(int c=0; c<C; c++){
            int n = Integer.parseInt(br.readLine());
            int ok = n;
            String[] line = br.readLine().split(" ");
            int[] map = new int[n+1];
            int[] visit = new int[n+1];
            for(int i=1; i<=n; i++){
                map[i] = Integer.parseInt(line[i-1]);
                if(map[i] == i){  // 이거 O(n^2) 가능성 있어서 해줬더니 83% 시간초과대에서 벗어남
                    visit[i] = 1;
                    ok--;
                }
            }
            // 써큘러여야 함
            int visit_cnt = n;

            for(int i=1; i<=n; i++){
                if(visit[i] != 0)
                    continue;
                int now = i;
                int cnt = 1;
                boolean flag = false;
                while(cnt <= visit_cnt){
                    int pick = map[now];
                    if(i==pick){
                        int person = i;
                        while(visit[person] != 1){
                            ok--;
                            visit_cnt--;
                            visit[person] = 1;
                            person = map[person];
                        }
                        flag = true;
                        break;
                    }
                    cnt++;
                    now = pick;
                    if(visit[now] == 1){
                        int person = i;
                        while(visit[person] != 1){
                            visit_cnt--;
                            visit[person] = 1;
                            person = map[person];
                        }
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    visit_cnt--;
                    visit[i] = 1;
                }
            }
            sb.append(ok + "\n");
        }
        System.out.println(sb);
    }
}
