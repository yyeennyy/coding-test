import java.util.*;
import java.io.*;

public class Main{
    public static int N, M;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        map = new int[300][300];
        for(int n=0; n<N; n++){
            line = br.readLine().split(" ");
            for(int m=0; m<M; m++){
                map[n][m] = Integer.parseInt(line[m]);
            }
        }
        int year = 0;
        while(true){
            // 개수 확인
            if(count_of_mass() > 1){
                System.out.println(year);
                return;
            }
            else if(count_of_mass() == 0){
                System.out.println(0);
                return;
            }
            // 녹이기
            ArrayList<int[]> update = new ArrayList<>();
            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    if(map[n][m] != 0){
                        int val = water(n, m);
                        update.add(new int[]{n, m, Math.max(map[n][m] - val, 0)});
                    }
                }
            }
            for(int[] u : update){
                map[u[0]][u[1]] = u[2];
            }
            // 1년 보내기
            year++;
        }
    }

    public static int water(int n, int m){
        int cnt = 0;
        for(int i=0; i<4; i++){
            int y = n + dy[i];
            int x = m + dx[i];
            if(x<0||y<0||x>=M||y>=N)
                continue;
            if(map[y][x] == 0){
                cnt++;
            }
        }        
        return cnt;
    }

    public static int[][] visit;
    // BFS : 덩어리 수 리턴
    public static int count_of_mass(){
        visit = new int[300][300];
        int cnt = 0;
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(map[n][m] != 0 && visit[n][m] == 0){
                    BFS(n, m);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void BFS(int r, int c){
        if(r<0||c<0||r>=N||c>=M)
            return;
        if(visit[r][c] == 1)
            return;
        if(map[r][c] == 0)
            return;
        visit[r][c] = 1;
        BFS(r+1, c);
        BFS(r-1, c);
        BFS(r, c+1);
        BFS(r, c-1);
    }
}
