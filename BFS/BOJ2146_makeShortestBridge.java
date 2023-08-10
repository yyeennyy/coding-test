import java.util.*;
import java.io.*;

public class Main{
    public static int[][] map;
    public static int[][] visit;
    public static int[][] started;
    public static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new int[N][N];
        started = new int[N][N];

        int res = 0;
        for(int i=0; i<N; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = -1 * Integer.parseInt(line[j]);
            }
        }
        // 서로 다른 대륙은 서로 다른 음수로 표현?
        int val = -2;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == -1){
                    Queue<int[]> qq = new LinkedList<>();
                    qq.add(new int[]{i, j});
                    map[i][j] = val;
                    visit[i][j] = 1;
                    while(!qq.isEmpty()){
                        int[] now = qq.poll();
                        int ii = now[0];
                        int jj = now[1];
                        for(int d=0; d<4; d++){
                            int x = jj + dx[d];
                            int y = ii + dy[d];
                            if(x<0||y<0||x>=N||y>=N) continue;
                            if(visit[y][x]==1) continue;
                            if(map[y][x]==-1){
                                map[y][x] = val;
                                qq.add(new int[]{y, x});
                                visit[y][x] = 1;
                            }
                        }
                    }
                    val--;
                }
            }
        }
        
        // 본격 다리찾기
        // visit 초기화
        int min = N*N;
        int type;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if((type = map[i][j]) < 0){
                    for(int d=0; d<4; d++){
                        int x = j + dx[d];
                        int y = i + dy[d];
                        if(x<0||y<0||x>=N||y>=N) continue;
                        if(map[y][x]==0 && started[y][x]==0){  // 시작 안한 바다일 때
                            started[y][x] = 1;
                            // BFS
                            res = BFS(type, y, x);
                            if(res < min) min = res;
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }
    
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    // BFS해서 기록하며 가장 짧은 숫자 리턴하기
    public static int BFS(int type, int r, int c){
        // visit 초기화
        for(int ii=0; ii<N; ii++){
            for(int jj=0; jj<N; jj++){
                visit[ii][jj] = map[ii][jj];
            }
        }
        // visit에 기록하며 map탐색
        int t;
        int min = N*N;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visit[r][c] = 1;
        while(!q.isEmpty()){
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            // 다른 대륙에 닿았을 때, 가장 짧은 숫자인 것은?
            if((t = isReached(type, y, x)) != type){
                if(visit[y][x] < min){
                    min = visit[y][x];
                }
            }
            for(int i=0; i<4; i++){
                int yy = y + dy[i];
                int xx = x + dx[i];
                if(yy<0||xx<0||yy>=N||xx>=N) continue;
                if(visit[yy][xx] != 0 ) continue;
                visit[yy][xx] = visit[y][x] + 1;
                q.add(new int[]{yy, xx});
            }
        }
        return min;
    }
    public static int isReached(int type, int r, int c){
        for(int i=0; i<4; i++){
            int y = r + dy[i];
            int x = c + dx[i];
            if(y<0||x<0||y>=N||x>=N) continue;
            if(map[y][x] < 0 && map[y][x] != type){
                return map[y][x];
            }
        }
        return type;
    }
}
