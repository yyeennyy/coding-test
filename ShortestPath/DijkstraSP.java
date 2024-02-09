import java.util.List;

// 가중치 양수 & 방향/무방향 그래프
public class DijkstraSP {
    public int N;
    List<Edge>[] graph;
    public int[] previous;

    public DijkstraSP(List<Edge>[] graph) { // param: Vertex(idx)마다의 List<Edge> 정보
        N = graph.length;
        this.graph = graph;
        previous = new int[N];
    }

    public int[] shortestPath(int s){
        boolean[] visited = new boolean[N];
        int[] D = new int[N];

        // 초기값 세팅
        for (int i = 0; i < N; i++) {
            D[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }

        // 첫 정점은 이전 정점도 0, weight도 0
        previous[s] = 0;
        D[s] = 0;

        for (int i = 0; i < N; i++) {
            int minVertex = -1;
            int min = Integer.MAX_VALUE;

            // 가장 작은 weight를 갖는 minVertex 찾기
            for (int j = 0; j < N; j++) {
                if ((!visited[j]) && (D[j] < min)) {
                    min = D[j];
                    minVertex = j;
                }
            }

            visited[minVertex] = true;

            // 찾아낸 minVertex를 통해서 노드의 값 갱신
            for (Edge e : graph[minVertex]) {
                if (!visited[e.adjVertex]) {
                    int nowDistance = D[e.adjVertex];
                    int newDistance = D[minVertex] + e.weight;
                    if (nowDistance > newDistance) {
                        D[e.adjVertex] = newDistance;
                        previous[e.adjVertex] = minVertex;
                    }
                }
            }
        }
        return D;
    }
}
class Edge {
    int vertex;
    int adjVertex;
    int weight;

    public Edge(int vertex, int adjVertex, int weight) {
        this.vertex = vertex;
        this.adjVertex = adjVertex;
        this.weight = weight;
    }
}

    /**
     * Dijkstra는 greedy + dp 합쳐진 문제다.
     * 현재노드에서 최선경로를 반복적으로 찾으면서도
     * 계산해둔 경로를 활용하기 때문
     *
     * 음의 가중치가 있을 때 적용불가한 이유는
     * 최단경로라고 여겨진 것을 dp테이블에 저장해두고 다시 재방문하지 않기 때문이다.
     */
