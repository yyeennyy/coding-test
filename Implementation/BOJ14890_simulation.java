import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N, L;
    public static int[][] map;
    public static int[][] map2;
    public static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        map = new int[N][N];
        map2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int jj = Integer.parseInt(line[j]);
                map[i][j] = jj;
                map2[j][i] = jj;
            }
        }
        // 인접 높이차 2이상 & 경사로 불가 (높이조건)
        // 0: 높이차 없음 / 1: 높이조건 O / 2: 높이조건 X

        // 가능: 놓아야 하는 지점 판단
        // ? 길이 여유가 되는가
        // 낮->높 : __L__ㅁ
        // 높->낲 : ㅁ__L__
        // ? 이미 놓은 경사로와 겹치는가
        // N길이 배열에 체크
        gogo(map);
        gogo(map2);
        System.out.println(cnt);
    }

    private static void gogo(int[][] map) {
        for (int i = 0; i < N; i++) {
            int heightInfo = checkHeight(map, i);
            if (heightInfo == 0) {
                cnt++; continue;
            }
            if (heightInfo == 2) {
                continue;
            }

            int[] set = new int[N];
            int prev = -1;
            boolean possible = true;
            for (int j = 0; j < N; j++) {
                if (prev != -1) {
                    if (prev > map[i][j]) {
                        // 인덱스
                        if (j + L - 1 > N-1) {
                            possible = false; break;
                        }
                        // 길이
                        for (int idx = j; idx < j + L; idx++) {
                            if (set[idx] == 1) {
                                possible = false; break;
                            }
                        }
                        // 갱신
                        if (possible) {
                            for (int idx = j; idx < j + L; idx++) {
                                set[idx] = 1;
                            }
                        } else {
                            break;
                        }
                    }
                    if (prev < map[i][j]) {
                        // 인덱스
                        if (j - L < 0) {
                            possible = false; break;
                        }
                        // 길이
                        for (int idx = j-1; idx >= j - L; idx--) {
                            if (set[idx] == 1) {
                                possible = false; break;
                            }
                        }
                        // 갱신
                        if (possible) {
                            for (int idx = j-1; idx >= j - L; idx--) {
                                set[idx] = 1;
                            }
                        } else {
                            break;
                        }
                    }
                }
                prev = map[i][j];
            }
            if (possible) {
                cnt++;
            }
        }
    }

    // 가로 행에 대해
    // 0: 높이차 없음 / 1: 높이조건 O / 2: 높이조건 X
    public static int checkHeight(int[][] map, int i) {
        int prev = -1;
        boolean sameHeight = true;
        for (int j = 0; j < N; j++) {
            if (prev != -1) {
                if (Math.abs(map[i][j] - prev) > 1) {
                    return 2;
                }
                if (prev != map[i][j]) {
                    sameHeight = false;
                }
            }
            prev = map[i][j];
        }
        if (sameHeight) {
            return 0;
        }
        return 1;
    }
}