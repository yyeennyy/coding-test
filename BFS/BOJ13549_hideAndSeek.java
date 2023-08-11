(방법1): 큐의 특징을 좀 더 잘 이해하고
순간이동한다는 것을 큐와 연관지어 잘 생각했더라면 좋았을 것 같다.
public class Main{
    public static int limit = 100001;
    public static int N;
    public static int K;
    public static Queue<Integer> q = new LinkedList<>();
    public static int[] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);

        map = new int[limit + 2];
        map[N] = 1;
        q.add(N);
        teleport(N);
        while(map[K] == 0){
            int now = q.poll();
            int[] nextPosition = {now+1, now-1};
            for(int position : nextPosition){
                if(position < 0 || position >= limit) continue;
                if(map[position] != 0) continue;
                map[position] = map[now] + 1;
                q.add(position);
                teleport(position);
            }
        }
        System.out.println(map[K]-1);
    }
    public static void teleport(int num){
        int tmp = num;
        if (tmp == 0) return;
        while(tmp < limit && map[K] == 0){
            if(map[tmp] == 0){
                map[tmp] = map[num];
                q.add(tmp);
                if(tmp == K) return;
            }
            tmp *= 2;
        }
    }
}

(방법2)
// 0-1 BFS
public class Main{
    public static int limit = 200002;
    public static int N;
    public static int K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        int limit = 200000;
        int[] map = new int[limit];
        Arrays.fill(map, -1);
        Deque<Integer> dq = new LinkedList<>(); // ★★★
        map[N] = 0; // ★★★
        dq.addLast(N);
        // 0-1 BFS 알고리즘은 O(V + E)의 선형시간이다.
        // 우선순위인 것을 dq의 앞에 넣고(가중치0), 우선순위 아닌 건 뒤에 넣는다(가중치1)
        // 그래서 순간이동은 dq의 앞에, 일반 +-1이동은 dq의 뒤에 넣는다.
        // 다익스트라처럼 큐에는 오직 이전 정점을 통해 최단 거리가 줄어든 정점만 큐에 삽입한다.
        // 그래서 순간이동한 것들이 바로 그 최단거리가 줄어든 상황이며, 그래서 큐의 앞에 삽입함
        // 큐는 항상 시작점으로부터의 거리에 대해 정렬된 상태임.
        // 그래서 map[index]에는 항상 최단거리가 기록되며,
        // 이미 한번 기록된 이력이 있어 -1값(초기화값)을 벗어나면, 패스함.
        // 그래서 O(V + E)라는 것임. 
        while(!dq.isEmpty()){
            int now = dq.pollFirst();
            if(2*now < limit && map[2*now] == -1){
                map[2*now] = map[now];
                dq.addFirst(2*now);
            }
            int[] nextPosition = {now-1, now+1};
            for(int next : nextPosition){
                if(next < 0 || next >= limit || map[next] != -1) continue;
                map[next] = map[now] + 1;
                dq.addLast(next);
            }
        }
        System.out.println(map[K]);
    }
}
