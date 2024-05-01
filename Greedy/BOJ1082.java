import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int N;
    private static int[] costs;
    private static Map<Integer, Integer> map;
    private static int budget;
    private static Room[] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rooms = new Room[N];
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            rooms[i] = new Room(i, costs[i]);
            map.put(i, costs[i]);
        }
        budget = Integer.parseInt(br.readLine());

        Arrays.sort(rooms, (o1, o2) -> {
            // price 오름차순 정렬 & price 동일시 num이 큰 것 우선
            return o1.price > o2.price ? 1 : o1.price == o2.price ? o1.num > o2.num ? 1 : -1 : -1;
        });
//    [우선순위]
//    1. 자릿수
//    2. 앞자리 큰 수

        int n = 0;  // size
        int idx = 0;
        int[] nums = new int[51];
        while (idx < N) {
            if (nums[0] == 0 && rooms[idx].num == 0) {
                idx++;
                continue;
            }
            if (nums[0] == 0 && rooms[idx].num != 0) {
                if (budget >= rooms[idx].price){
                    budget -= rooms[idx].price;
                    nums[n++] = rooms[idx].num;
                    idx = 0;
                    continue;
                } else {
                    System.out.println(0);
                    return;
                }
            }
            if (budget >= rooms[idx].price) {
                budget -= rooms[idx].price;
                nums[n++] = rooms[idx].num;
            } else {
                idx++;
            }
        }

        if (n == 0) {
            System.out.println(0);
            return;
        }
        // 큰 수로 교체
        idx = 0;
        while (idx < n) {
            int now = nums[idx];
            // 차액을 지불할 수 있는가
            for (int i = N - 1; i >= 0; i--) {
                if (i == 0 && idx == 0) continue;
                int diff  = map.get(i) - map.get(now);
                if (budget >= diff) {
                    budget -= diff;
                    nums[idx] = i;
                    break;
                }
            }
            idx++;
        }
        for (int i = 0; i < n; i++)
            System.out.print(nums[i]);
    }
}

class Room {
    int num, price;
    Room(int num, int price) {
        this.num = num;
        this.price = price;
    }
}