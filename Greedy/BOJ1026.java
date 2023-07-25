import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];

        String[] line;
        line = br.readLine().split(" ");
        for(int i=0; i<N; i++)
            A[i] = Integer.parseInt(line[i]);
        line = br.readLine().split(" ");
        for(int i=0; i<N; i++)
            B[i] = Integer.parseInt(line[i]);

        // S의 최솟값 = A * B... 
        Arrays.sort(A);
        Arrays.sort(B);
        int sum = 0;
        for(int i=0; i<N; i++){
            sum += A[i]*B[N-1-i];
        }
        System.out.println(sum);
    }
}
