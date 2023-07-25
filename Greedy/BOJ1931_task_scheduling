import java.util.*;
import java.io.*;

public class Main{
    public static ArrayList<int[]> time = new ArrayList<>();
    public static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] line;
        for(int i=0; i<N; i++){
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int[] pair = {a, b};
            time.add(i, pair);
        }

        Collections.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                return a[1]>b[1] ? 1 : a[1]<b[1] ? -1 : a[0]>b[0] ? 1 : -1;
            }
        });

        // 정렬
        // 오름차순 by 끝나는시간, 시작시간
        // time.stream().forEach(x -> System.out.println(Arrays.toString(x)));
        int ans = 0;
        int t = 0;
        for(int i=0; i<N; i++){
            if(t > time.get(i)[0])
                continue;
            ans++;
            t = time.get(i)[1];
        }
        System.out.println(ans);
    }
}
