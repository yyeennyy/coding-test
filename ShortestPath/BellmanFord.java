
// 정점 s로부터 각 정점까지의 최단 경로
// 가중치 음수 & 방향 있는 그래프
// BUT: 사이클 내 가중치 총합이 음수면 X

// 핵심 idea : 출발점에서 각 정점까지의 최단 경로 상에 있는 간선의 수는 최대 n-1개다.
//            최악의 경우 모든 노드 방문!
public class BellmanFord {
    public static final int INF = Integer.MAX_VALUE;
    private int[] D;
    private int[] previous;
    private int N;

    public BellmanFord(int numOfVertices) {
        N = numOfVertices;
        D = new int[N];
        previous = new int[N];
    }

    public void shortestPath(int s, int[][] adjMatrix) {
        for (int i = 0; i < N; i++) D[i] = INF;

        D[s] = 0;
        previous[s] = 0;

        for (int k = 0; k < N - 1; k++) {  // 간선완화 작업을 N-1번 하게 된다.
            // 모든 간선 경우를 다 볼건데
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (adjMatrix[i][j] != INF) {  // 간선에 대해서
                        if (D[j] > D[i] + adjMatrix[i][j]) { // 가중치 갱신이 필요하다면
                            D[j] = D[i] + adjMatrix[i][j];
                            previous[j] = i;
                        }
                    }
                }
            }
        }
    }

    public void printPaths(int s){
        System.out.println("정점 " + s + "으로부터 최단 거리");

        for (int i = 0; i < D.length; i++) {
            if (i == s) continue;
            if (D[i] == Integer.MAX_VALUE) {
                System.out.println(s + "과 " + i + "사이에 경로 없음");
            } else {
                System.out.println("[" + s + ", " + i + "] = " + D[i]);
            }
        }
        System.out.println();
        System.out.println("정점 " + s + "으로부터의 최단 경로");
        for (int i = 0; i < D.length; i++) {
            if (i == s) continue;
            int back = i;
            System.out.println(back);
            while (back != 0) {
                System.out.print("<-" + previous[back]);
                back = previous[back];
            }
            System.out.println();
        }
    }
}

/**
 * 매 단계마다 모든 간선을 모두 확인하는게 특징...
 * 노드간의 최단거리를 갱신갱신해간다. 음의 가중치가 있을 때 사용.
 *
 * - 시간 복잡도 -
 * 정점마다 반복: 총 정점의 개수 n-1
 * 한번의 반복마다 모든 간선을 확인, (간선개수 < n^2)
 * so, O(n^3)
 */