// - 내가 성공시킨 구현 방법 - 
// 뼈대: backtracking으로 각 cctv들의 회전 조합을 따진다.
//       backtracking하면서 min을 찾아나간다.
// 핵심:
// if(pick==cctvNum){
//             int[][] map_ = beambeam(arr);  // 현재 조합인 arr대로 beambeam을 쏜다. cctv 종류와 rotation대로 "어디로 쏴야 할지" 판단하는 리스트를 얻으면, 그대로 beambeam을 쏘는 메서드다. ("어디로 쏴야할 지 판단은 whereIBeam() 메서드에서 함)
//             int sagak = sagaksagak(map_);  // beambeam을 쏘고 얻은 map_을 통해 사각지대를 센다.
//             if(sagak < min)                // min 갱신
//                 min = sagak;
//             return;
//         }



import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map;
    public static int N, M, min;
    public static int[][] cctvs;
    public static int cctvNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        map = new int[N+1][M+1];
        cctvs = new int[8+1][1];
        for(int n=1; n<=N; n++){
            line = br.readLine().split(" ");
            for(int m=1; m<=M; m++){
                map[n][m] = Integer.parseInt(line[m-1]);
                // cctvs 파악
                if(map[n][m] != 0 && map[n][m] != 6){
                    cctvs[++cctvNum] = new int[]{map[n][m], n, m};  // 종류, y, x
                }
            }
        }

        min = N*M;
        backtracking(new int[cctvNum+1], 0);

        System.out.println(min);
    }

    public static void backtracking(int[] arr, int pick){
        if(pick==cctvNum){
            int[][] map_ = beambeam(arr);
            int sagak = sagaksagak(map_);

            if(sagak < min)
                min = sagak;
            return;
        }
        // 백트래킹인데 트래킹 수(?)가 다른 경우로 처리할 수도 있겠다.
        for(int i=1; i<=4; i++){
            int[] arr_ = arr.clone();
            arr_[pick+1] = i;
            backtracking(arr_, pick+1);
        }
    }

    public static int sagaksagak(int[][] map_){
        // 사각지대 개수 세기
        int cnt = 0;
        for(int n=1; n<=N; n++){
            for(int m=1; m<=M; m++){
                if(map_[n][m] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static int[][] beambeam(int[] arr){

        int[][] map_ = new int[N+1][M+1];
        for(int n=1; n<=N; n++){
            for(int m=1; m<=M; m++){
                map_[n][m] = map[n][m];
            }
        }

        for(int order=1; order<=cctvNum; order++){
            int kind = cctvs[order][0];
            int y = cctvs[order][1];
            int x = cctvs[order][2];
            int rotation = arr[order];

            String[] list = whereIBeam(kind, rotation);
            for(String go : list){
                if(go == null) break;
                int yy = y;
                int xx = x;
                switch(go){
                    case "u":
                        while(yy>=2){
                            yy--;
                            if(map_[yy][x] == 6) break;
                            map_[yy][x] = 9;
                        } break;
                    case "r":
                        while(xx<=M-1){
                            xx++;
                            if(map_[y][xx] == 6) break;
                            map_[y][xx] = 9;
                        } break;
                    case "d":
                        while(yy<=N-1){
                            yy++;
                            if(map_[yy][x] == 6) break;
                            map_[yy][x] = 9;
                        } break;
                    case "l":
                        while(xx>=2){
                            xx--;
                            if(map_[y][xx] == 6) break;
                            map_[y][xx] = 9;
                        } break;
                }
            }
        }
        return map_;
    }
    public static String[] whereIBeam(int kind, int rotation){
        String[] list = new String[4];
        // r, l, u, d --- in list
        switch(kind){
            case 1:
                list[0] = rotation == 1 ? "r" : rotation == 2 ? "d" : rotation == 3 ? "l" : "u"; break;
            case 2:
                if(rotation == 1 || rotation == 3){
                    list[0] = "r";
                    list[1] = "l";
                } else {
                    list[0] = "u";
                    list[1] = "d";
                } break;
            case 3:
                if(rotation == 1){
                    list[0] = "u";
                    list[1] = "r";
                } else if(rotation == 2){
                    list[0] = "r";
                    list[1] = "d";
                } else if(rotation == 3){
                    list[0] = "d";
                    list[1] = "l";
                } else {
                    list[0] = "l";
                    list[1] = "u";
                } break;
            case 4:
                if(rotation == 1){
                    list[0] = "l";
                    list[1] = "u";
                    list[2] = "r";
                } else if(rotation == 2){
                    list[0] = "u";
                    list[1] = "r";
                    list[2] = "d";
                } else if(rotation == 3){
                    list[0] = "r";
                    list[1] = "d";
                    list[2] = "l";
                } else {
                    list[0] = "d";
                    list[1] = "l";
                    list[2] = "u";
                } break;
            case 5:
                list[0] = "u";
                list[1] = "r";
                list[2] = "d";
                list[3] = "l";
        }
        return list;
    }
}
