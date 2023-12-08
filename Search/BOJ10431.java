import java.util.*;
import java.io.*;

// 일반적인 삽입정렬

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int testCase = Integer.parseInt(input[0]);
            int size = input.length - 1;
            int[] arr = new int[size];
            int ptr = 0;
            int res = 0;
            for (int s = 0; s < size; s++) {
                boolean ok = false;
                int student = Integer.parseInt(input[s+1]);
                for (int j = 0; j < ptr; j++) {
                    if (arr[j] > student) {
                        for (int k = ptr-1; k >= j; k--) {
                            arr[k+1] = arr[k]; res++;
                        }
                        arr[j] = student;
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    arr[ptr] = student;
                }
                ptr++;
            }
            System.out.printf("%d %d\n", testCase, res);
        }
    }
}