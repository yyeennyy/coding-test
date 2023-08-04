import java.util.*;
import java.io.*;

public class Main{
    public static String[][] map;
    public static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String line;
        line = br.readLine();
        map = new String[N][line.length()];
        map[0] = line.split("");
        for(int i=1; i<N; i++){
            String[] tmp = br.readLine().split("");
            map[i] = tmp;
        }

        // 1. 일반
        int num1 = 0;
        num1 += colored("R");
        num1 += colored("G");
        num1 += colored("B");

        // 2. 적록색약 R==G
        int num2 = 0;
        num2 += colored("B");
        for(int i=0; i<N; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j].equals("G")){
                    map[i][j] = "R";
                }
            }
        }
        num2 += colored("R");

        System.out.printf("%d %d\n", num1, num2);
    }

    public static int colored(String color){
        int cnt = 0;
        boolean[][] visit = new boolean[N][map[0].length];
        Queue<int[]> q = new LinkedList<>();
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        for(int i=0; i<N; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j].equals(color)){
                    if(!visit[i][j]){
                        q.add(new int[]{i, j});
                        while(!q.isEmpty()){
                            int[] now = q.poll();
                            int x = now[1];
                            int y = now[0];
                            for(int k=0; k<4; k++){
                                int x_ = x + dx[k];
                                int y_ = y + dy[k];
                                if(x_<0||y_<0||x_>=map[0].length||y_>=N)
                                    continue;
                                if(map[y_][x_].equals(color) && !visit[y_][x_]){
                                    visit[y_][x_] = true;
                                    q.add(new int[]{y_, x_});
                                }
                            }
                        }
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
