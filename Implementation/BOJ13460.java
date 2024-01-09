import java.util.*;

public class Main {
    public static int minimum = Integer.MAX_VALUE;
    public static String[][] originMap;
    public static int N, M;
    public static int[] holeCoordinate;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        // input map
        originMap = new String[N][M];
        for (int i = 0; i < N; i++) {
            originMap[i] = sc.nextLine().split("");
        }
        // get a hole coordinate
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (originMap[i][j].equals("O")){
                    holeCoordinate = new int[]{i, j};
                }
            }
        }

        int[] initArr = new int[10];
        Arrays.fill(initArr, -1);
        backtracking(initArr, 0, originMap);

        if (minimum <= 10) {
            System.out.println(minimum + 1);
        } else {
            System.out.println(-1);
        }
    }

    public static void backtracking(int[] arr, int idx, String[][] savedMap) {
        if (idx == 10) {
            return;
        }

        int[] arrClone = arr.clone();
        for (int method = 0; method < 4; method++) {
            if (idx != 0 && arrClone[idx-1] == method) {
                continue;
            }

            // 이 움직임 괜찮은지 체크 ----------------------
            String[][] updatedMap = new String[N][M];
            for (int i = 0; i < N; i++) {
                updatedMap[i] = savedMap[i].clone();
            }

            updatedMap = lean(updatedMap, method);

            int[][] blueAndRed = abstractBall(updatedMap);
            int[] blue = blueAndRed[0];
            int[] red = blueAndRed[1];

            if (blue == null && red == null) {
                continue;
            }
            if (red == null) {
                if (idx < minimum)
                    minimum = idx; // 성공 및 minimum임
                continue;
            }
            if (blue == null) {
                continue;
            }

            // 공은 무사하다! 다음 단계로.. -----------------------------
            arrClone[idx] = method;
            backtracking(arrClone, idx + 1, updatedMap);
        }
    }

    public static String[][] lean(String[][] map, int method) {
        // left, right, up, down
        String[][] rotatedMap = new String[N][M];

        switch (method) {
            // 각 방향별 map회전
            case 0: // 90도 반시계 회전
                rotatedMap = rotate90_left(map);
                break;
            case 1: // 90도 시계 회전
                rotatedMap = rotate90_right(map);
                break;
            case 2: // 180도 반시계 회전
                rotatedMap = rotate180(map);
                break;
            case 3: // 그대로 유지
                rotatedMap = map.clone();
                break;
        }

        // '↓' 방향으로 공 이동 구현 (rotatedMap --> resultMap)
        String[][] resultMap1 = applyGravity(rotatedMap);

        // 맵 방향 원상복구하기
        String[][] resultMap2 = new String[N][M];
        switch (method) {
            case 0: // 90도 반시계 회전상태였음
                resultMap2 = rotate90_right(resultMap1);
                break;
            case 1: // 90도 시계 회전상태였음
                resultMap2 = rotate90_left(resultMap1);
                break;
            case 2: // 180도 회전상태였음
                resultMap2 = rotate180(resultMap1);
                break;
            case 3: // 그대로 두면 됨
                resultMap2 = resultMap1;
                break;
        }

        return resultMap2;
    }

    public static int[][] abstractBall(String[][] map) {
        int[] blue = null;
        int[] red = null;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x].equals("B"))
                    blue = new int[]{y, x};
                if (map[y][x].equals("R"))
                    red = new int[]{y, x};
                if (blue != null && red != null)
                    break;
            }
            if (blue != null && red != null)
                break;
        }

        return new int[][]{blue, red};
    }

    private static String[][] applyGravity(String[][] map) {
        int[][] blueAndRed = abstractBall(map);
        int[] blue = blueAndRed[0];
        int[] red = blueAndRed[1];

        // blue랑 red중 y좌표가 큰 것 먼저 떨어뜨리기
        int by = blue[0];
        int ry = red[0];

        String[][] afterGravity;
        if (by >= ry) {
            afterGravity = drop(map, blue, red, "B", "R");
        } else {
            afterGravity = drop(map, red, blue, "R", "B");
        }

        return afterGravity;
    }

    private static String[][] drop(String[][] map, int[] ball1, int[] ball2, String symbol1, String symbol2) {
        int Y = map.length;
        int X = map[0].length;

        String[][] result = new String[Y][X];
        for(int i=0; i<Y; i++){
            result[i] = map[i].clone();
        }

        boolean inHole = false;
        // drop ball1
        int by1 = ball1[0];
        while (by1 < Y-1) {
            String after = map[by1 + 1][ball1[1]];
            if(!after.equals(".") && !after.equals("O"))
                break;
            by1++;
            if(after.equals("O")) {
                inHole = true;
                break;
            }
        }
        result[ball1[0]][ball1[1]] = ".";
        if (!inHole)
            result[by1][ball1[1]] = symbol1;
        if (inHole)
            result[by1][ball1[1]] = "O";

        // drop ball2
        inHole = false;
        int by2 = ball2[0];
        while (by2 < Y-1) {
            String after = result[by2 + 1][ball2[1]];
            if(!after.equals(".") && !after.equals("O"))
                break;
            by2++;
            if(after.equals("O")){
                inHole = true;
                break;
            }
        }
        result[ball2[0]][ball2[1]] = ".";
        if (!inHole)
            result[by2][ball2[1]] = symbol2;
        if (inHole)
            result[by2][ball2[1]] = "O";

        return result;
    }

    private static String[][] rotate90_left(String[][] map) {
        int Y = map.length;
        int X = map[0].length;
        String[][] result = new String[X][Y];
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                result[X-1-j][i] = map[i][j];
            }
        }
        return result;
    }

    private static String[][] rotate90_right(String[][] map) {
        int Y = map.length;
        int X = map[0].length;
        String[][] result = new String[X][Y];
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                result[j][Y-1-i] = map[i][j];
            }
        }
        return result;
    }

    private static String[][] rotate180(String[][] map) {
        return rotate90_left(rotate90_left(map));
    }
}