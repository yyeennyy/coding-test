import java.util.*;

public class Main {

    public static int N;
    public static int[][]  powers;
    public static Map<String, int[][]> combinations = new HashMap<>();
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        powers = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 1; j <= N; j++) {
                powers[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        halfCombination(0, 0, new int[N / 2]);

        Set<String> keys = combinations.keySet();
        for (String key : keys) {
            int[][] combination = combinations.get(key);
            int[] set1 = combination[0];
            int[] set2 = combination[1];

            // 2개씩 조합한 결과 리스트 얻기
            List<int[]> pairs1 = getPairs(set1);
            List<int[]> pairs2 = getPairs(set2);

            int power1 = 0;
            for (int[] pair : pairs1) {
                int a = pair[0];
                int b = pair[1];
                power1 += powers[a][b] + powers[b][a];
            }

            int power2 = 0;
            for (int[] pair : pairs2) {
                int a = pair[0];
                int b = pair[1];
                power2 += powers[a][b] + powers[b][a];
            }

            int diff = Math.abs(power2 - power1);
            if (diff < min) {
                min = diff;
            }
        }

        System.out.println(min);
    }

    public static List<int[]> getPairs(int[] set) {
        List<int[]> result = new ArrayList<>();
        getPairs(set, 0, 0, new int[2], result);

        return result;
    }

    public static void getPairs(int[] set, int cnt, int setIdx, int[] picked, List<int[]> result) {
        if (cnt == 2) {
            result.add(picked);
            return;
        }
        for (int i = setIdx; i < set.length; i++) {
            int[] pickedClone = picked.clone();
            pickedClone[cnt] = set[i];
            getPairs(set, cnt + 1, i + 1, pickedClone, result);
        }
    }



    public static void halfCombination(int idx, int element, int[] picked) {
        if (combinations.containsKey(Arrays.toString(picked))) {
            return;
        }
        if (idx == N / 2) {
            int[] side = new int[N/2];
            int i = 0;
            for(int num=1; num<=N; num++){
                boolean contained = false;
                for (int p : picked) {
                    if (p == num) {
                        contained = true; break;
                    }

                }
                if (!contained){
                    side[i++] = num;
                }
            }
            combinations.put(Arrays.toString(side), new int[][]{picked, side});
            return;
        }
        for (int i = element+1; i <= N; i++) {
            int[] pickedCopy = picked.clone();
            pickedCopy[idx] = i;
            halfCombination(idx + 1, i, pickedCopy);
        }
    }
}