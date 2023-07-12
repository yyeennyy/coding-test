아 이제 이런건!


import java.util.*;

import java.io.*;

public class Main {
  public static Queue<Integer> q = new LinkedList<>();
  public static Map<Integer, ArrayList<Integer>> map = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int L = Integer.parseInt(br.readLine());

    // 인접 리스트 구성
    for(int i=1; i<=N; i++){
      map.put(i, new ArrayList<Integer>());
    }
    for(int i=0; i<L; i++){
      String[] nums = br.readLine().split(" ");
      int A = Integer.parseInt(nums[0]);
      int B = Integer.parseInt(nums[1]);
      map.get(A).add(B);
      map.get(B).add(A);
    }

    // BFS 및 counting
    int count = 0;
    int[] wormed = new int[N+1];
    // 1번 컴퓨터가 웜 바이러스에 걸렸음
    q.add(1);  
    wormed[1] = 1;
    while(!q.isEmpty()){
      int now = q.poll();
      ArrayList<Integer> targets = map.get(now);
      // 앞으로 처리해야 할 comp를 차례로 q에 넣는다.
      for(int comp : targets){
        if(wormed[comp] == 0){
          q.add(comp);
          wormed[comp] = 1; // worm에 걸림
          count++;
        }
      }
    }

    // 결과 출력
    System.out.println(count);    
  }
}
