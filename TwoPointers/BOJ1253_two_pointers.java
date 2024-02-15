import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);  // 정렬
        int res = 0;
        for (int sumIdx = 0; sumIdx < N; sumIdx++) {
            int left = 0; int right = N-1;  // 투포인터
            while (true) {
                if (left == sumIdx) left++;
                else if (right == sumIdx) right --;

                if (left >= right) break;

                if (nums[left] + nums[right] > nums[sumIdx]) right--;
                else if (nums[left] + nums[right] < nums[sumIdx]) left++;
                else{
                    res++;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}

// 배열에서 원래 이중for문으로 O(N^2) 작업을 O(N)에 해결
// 즉 완탐 O(N^2)인데, 투포인터로 O(N)
// 이 문제는 정렬 후 투포인터를 left, right로 두는 것이 핵심!
// 두 포인터의 배열값 합이 sum임을 만나기 위해, 대소비교하며 left right 이동시키는..
// 아무 인덱스나 2중for 도는 것보다, 정렬된 상태로 키워야하냐 줄여야하냐 따지는 게 good이라는 생각

// 이진탐색은 logN을 보장하고(절반씩줄여가니까!), 투포인터는 최악의 경우 N이다.
// 이진탐색은 데이터 정렬이 전제조건이나, 투포인터는 꼭 그렇지 않다. 상황에 따라 다름.
