import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map = new int[100][100];
    public static int M, N;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        M = Integer.parseInt(line[0]);
        N = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);

        for(int k=0; k<K; k++){
            line = br.readLine().split(" ");
            int sx = Integer.parseInt(line[0]);
            int sy = Integer.parseInt(line[1]);
            int ex = Integer.parseInt(line[2]);
            int ey = Integer.parseInt(line[3]);
            for(int y=sy; y<ey; y++){
                for(int x=sx; x<ex; x++){
                    map[M-1-y][x] = 1;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int res = 0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 0){
                    arr.add(BFS(i, j));
                    res++;
                }
            }
        }

        // print
        System.out.println(res);
        Collections.sort(arr);
        arr.stream().forEach(x -> System.out.printf("%d ", x));
    }


    public static int BFS(int y, int x){
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        map[y][x] = 2;
        while(!q.isEmpty()){
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int rr = r + dy[d];
                int cc = c + dx[d];
                if(rr<0||cc<0||rr>=M||cc>=N) continue;
                if(map[rr][cc]!=0) continue;
                cnt++;
                map[rr][cc] = 2;
                q.add(new int[]{rr, cc});
            }
        }
        return cnt;
    }
}
