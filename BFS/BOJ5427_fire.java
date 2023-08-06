import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        ArrayList<Integer> res = new ArrayList<>();
        for(int n=0; n<N; n++){
            String[] line = br.readLine().split(" ");
            int w = Integer.parseInt(line[0]);
            int h = Integer.parseInt(line[1]);
            int[][] map = new int[h][w];
            int[][] visit = new int[h][w];
            Queue<int[]> fire = new LinkedList<>();
            Queue<int[]> you = new LinkedList<>();
            for(int i=0; i<h; i++){
                line = br.readLine().split("");
                for(int j=0; j<w; j++){
                    String now = line[j];
                    int now_ = 0;
                    if(now.equals("#")) now_ = -1;
                    if(now.equals(".")) now_ = 0;
                    if(now.equals("@")){
                        you.add(new int[]{i, j});
                        visit[i][j] = 1;
                        now_ = -9;
                    }
                    if(now.equals("*")){
                        fire.add(new int[]{i, j});
                        now_ = -4;
                    }
                    map[i][j] = now_;
                }
            }

            int y, x, yy, xx;
            while(!you.isEmpty()){
                ArrayList<int[]> tmp = new ArrayList<>();
                while(!fire.isEmpty()){
                    int[] now = fire.poll();
                    y = now[0]; x = now[1];
                    for(int k=0; k<4; k++){
                        yy = y + dy[k]; xx = x + dx[k];
                        if(yy<0||xx<0||yy>=h||xx>=w) continue;            // 범위
                        if(map[yy][xx]==-4 || map[yy][xx]==-1) continue;  // 벽x 불x
                        tmp.add(new int[]{yy, xx});
                        map[yy][xx] = -4;
                    }
                }
                for(int[] t : tmp)
                    fire.add(t);

                // 상근 퍼뜨리기 
                // 상근은 불 있는 곳은 못감. 후보를 q에 넣기
                tmp = new ArrayList<>();
                while(!you.isEmpty()){
                    int[] now = you.poll();
                    y = now[0]; x = now[1];
                    for(int k=0; k<4; k++){
                        yy = y + dy[k]; xx = x + dx[k];
                        if(yy<0||xx<0||yy>=h||xx>=w) continue;            // 범위
                        if(map[yy][xx]!=0 || visit[yy][xx]!=0) continue;  // 벽x 불x 갔던곳x
                        visit[yy][xx] = visit[y][x] + 1;
                        tmp.add(new int[]{yy, xx});
                    }
                }
                for(int[] t : tmp)
                    you.add(t);
            }
            // 불가능체크
            boolean can = false;
            int min = h*w;
            for(int i=0; i<h; i++){
                if(visit[i][0] > 0){
                    can = true;
                    if(visit[i][0] < min)
                        min = visit[i][0];
                }
                if(visit[i][w-1] > 0){
                    can = true;
                    if(visit[i][w-1] < min)
                        min = visit[i][w-1];
                }
            }
            for(int i=0; i<w; i++){
                if(visit[0][i] > 0){
                    can = true;
                    if(visit[0][i] < min)
                        min = visit[0][i];
                }
                if(visit[h-1][i] > 0){
                    can = true;
                    if(visit[h-1][i] < min)
                        min = visit[h-1][i];
                }
            }
            // 테두리 중 min
            if(can)
                res.add(min);
            else
                res.add(-1);


        }
        for(int r : res){
            if(r==-1)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(r);
        }
    }
}
