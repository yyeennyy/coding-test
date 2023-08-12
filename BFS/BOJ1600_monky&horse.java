// 벽 부수고 이동하기랑 유사함!

import java.util.*;
import java.io.*;

public class Main {

    public static int[][] map;
    public static int[][][] visit;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] hdy = {1, -1, 2, -2, 2, -2, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int W = Integer.parseInt(line[0]);
        int H = Integer.parseInt(line[1]);
        map = new int[201][201];
        visit = new int[201][201][31];

        for(int h=1; h<=H; h++){
            line = br.readLine().split(" ");
            for(int w=1; w<=W; w++){
                map[h][w] = -1 * Integer.parseInt(line[w-1]);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 0});
        visit[1][1][0] = 0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int cnt = now[2];

            if(x==W && y==H){
                System.out.println(visit[y][x][cnt]);
                return;
            }

            // 말
            if(cnt<K){
                for(int d=0; d<8; d++){
                    int yy = y + hdy[d];
                    int xx = x + hdx[d];
                    if(yy<=0||xx<=0||xx>W||yy>H) continue;
                    if(map[yy][xx] == -1) continue;
                    if(visit[yy][xx][cnt+1] != 0) continue;
                    visit[yy][xx][cnt+1] = visit[y][x][cnt] + 1;
                    q.add(new int[]{yy, xx, cnt+1});
                }
            }

            // 원숭이
            for(int d=0; d<4; d++){
                int yy = y + dy[d];
                int xx = x + dx[d];
                if(yy<=0||xx<=0||xx>W||yy>H) continue;
                if(map[yy][xx] == -1) continue;
                if(visit[yy][xx][cnt] != 0) continue;
                visit[yy][xx][cnt] = visit[y][x][cnt] + 1;
                q.add(new int[]{yy, xx, cnt});
            }
        }

        System.out.println(-1);
    }
}
