import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
        for(int n=0; n<N; n++){
            // 맵 구성
            ArrayList<Integer> results = new ArrayList<>();
            int l = Integer.parseInt(br.readLine());
            int[][] map = new int[l][l];
            int[][] visit = new int[l][l];
            String[] now = br.readLine().split(" ");
            String[] dst = br.readLine().split(" ");
            Queue<int[]> q = new LinkedList<>();
            int nowX = Integer.parseInt(now[1]);
            int nowY = Integer.parseInt(now[0]);
            int dstX = Integer.parseInt(dst[1]);
            int dstY = Integer.parseInt(dst[0]);
            q.add(new int[]{nowY, nowX});
            map[nowY][nowX] = 1;
            visit[nowY][nowX] = 1;
            while(!q.isEmpty()){
                ArrayList<int[]> tmp = new ArrayList<>();
                int[] you = q.poll();
                int y = you[0];
                int x = you[1];
                if(dstX==x && dstY==y){
                    results.add(visit[y][x] - 1);
                    continue;
                }
                for(int k=0; k<8; k++){
                    int yy = y + dy[k];
                    int xx = x + dx[k];
                    if(yy<0||xx<0|yy>=l||xx>=l) continue;
                    if(visit[yy][xx] != 0) continue;
                    visit[yy][xx] = visit[y][x] + 1;
                    q.add(new int[]{yy, xx});
                }
            }
            int min = l*l;
            for(int res : results){
                if(res < min){
                    min = res;
                }
            }
            if(min==l*l) min = 0;
            System.out.println(min);
        }

    }
}
