import java.util.*;
import java.io.*;

public class Main {
  public static Map<Integer, ArrayList<Integer>> map = new HashMap<>();
  public static Queue<Integer> q = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    String[] arr = line.split(" ");
    int N = Integer.parseInt(arr[0]);
    int M = Integer.parseInt(arr[1]);
    int K = Integer.parseInt(arr[2]);
    int X = Integer.parseInt(arr[3]);

    // 인접 리스트 구성
    for(int i=1; i<=N; i++){
      map.put(i, new ArrayList<Integer>());
    }
    for(int i=0; i<M; i++){
      line = br.readLine();
      arr = line.split(" ");
      int A = Integer.parseInt(arr[0]);
      int B = Integer.parseInt(arr[1]);
      map.get(A).add(B);
    }
    // ----------------------------------------------------------------------------
    // ★최단거리 배열
    int[] distance = new int[N+1];
    for(int i=1; i<=N; i++){
      distance[i] = -1;
    }
    distance[X] = 0;
    
    // BFS하면서 반영!
    q.add(X);
    while(!q.isEmpty()){
      int now = q.poll();
      // 현재 도시에서 이동할 수 있는 모든 도시 확인
      List<Integer> list = map.get(now);
      for(int city : list){
        // 아직 방문하지 않은 도시라면
        if(distance[city] == -1){
          // 최단거리 갱신
          distance[city] = distance[now] + 1;
          q.add(city);
        }
      }
    }
    // ----------------------------------------------------------------------------


    // 최단거리 K인 모든 도시의 번호를 오름차 출력
    boolean check = false;
    for(int i=1; i<=N; i++){
      if(distance[i] == K){
        System.out.println(i);
        check = true;
      }
    }
    if(check==false)
      System.out.println(-1);
  }
}
