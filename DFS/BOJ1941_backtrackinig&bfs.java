import java.util.*;
import java.io.*;

public class Main {
    public static String[] map = new String[25];
    public static boolean[] visit = new boolean[25];
    public static int[] arr = new int[7];
    public static int res = 0;

    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < 5; j++) {
                map[5*i + j] = line[j];
            }
        }
        
        backtracking(0, 0, 0);
        System.out.println(res);
    }

    public static void backtracking(int cnt, int n, int som){
        if(som+(7-cnt)<4) return;
        if(cnt==7){
            if(!isClosed(arr.clone())) return;
            Arrays.sort(arr);
            res++;
            return;
        }

        for(int i=n; i<25; i++){
            if(!visit[i]){
                visit[i] = true;
                // 공부해야겠다. 이때 왜 의도대로 안 되는지!
                // som = map[n].equals("S") ? som + 1 : som;
                // System.out.println(som);
                int dasom = map[i].equals("S") ? som + 1 : som;
                arr[cnt] = i;
                backtracking(cnt+1, i+1, dasom);
                visit[i] = false;
            }
        }
    }

    public static boolean isClosed(int[] arr){
        // 2차원 배치를 해봤을 때, 한 덩어리여야 한다.
        // System.out.println(Arrays.toString(arr));
        int checkNum = 0;
        boolean[][] position = new boolean[5][5];
        for(int n : arr){
            int y = n / 5;
            int x = n % 5;
            position[y][x] = true;
        }
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(position[i][j]){
                    // i, j에 대해 BFS
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    position[i][j] = false;
                    while(!q.isEmpty()){
                        int[] now = q.poll();
                        int r = now[0];
                        int c = now[1];
                        for(int d=0; d<4; d++){
                            int rr = r + dy[d];
                            int cc = c + dx[d];
                            if(rr<0||cc<0||rr>=5||cc>=5||!position[rr][cc]) continue;
                            q.add(new int[]{rr, cc});
                            position[rr][cc] = false;
                        }
                    }
                    checkNum++;
                    if(checkNum > 1) return false; // 한덩어리가 아니면 칠공주 조합이 인접한 상태가 아니라서 실패!
                }
            }
        }
        return true;
    }
}
