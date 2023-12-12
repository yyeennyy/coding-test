import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        List<Nation> nations = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] info = sc.nextLine().split(" ");
            int nation = Integer.parseInt(info[0]);
            int gold = Integer.parseInt(info[1]);
            int sil = Integer.parseInt(info[2]);
            int bron = Integer.parseInt(info[3]);
            nations.add(new Nation(nation, gold, sil, bron));
        }

        // 정렬해둔 상태라 걱정 노
        Collections.sort(nations);
        Collections.reverse(nations);

        int rank = 0;
        List<Nation> prev = new ArrayList<>();
        for (Nation n : nations) {
            int size = prev.size();
            if (size != 0) {
                Nation anyPrev = prev.get(0);
                // 이전이 gold가 더 많으면, prev들의 rank를 확정한다:
                // 현 rank가 2(2등까지 기재됨)일 때 prev가 3개면 셋 다 5등이어야 하니까, rank + prev.size가 rank임
                if (compareGold(anyPrev, n) > 0
                        || compareGold(anyPrev, n) == 0 && compareSilver(anyPrev, n) > 0
                        || compareGold(anyPrev, n) == 0 && compareSilver(anyPrev, n) == 0 && compareBronze(anyPrev, n) > 0) {
                    // prev들을 갱신
                    for (Nation p : prev) {
                        p.rank = rank + 1;
                        if (p.nation == K) {
                            System.out.println(p.rank);
                            return;
                        }
                    }
                    rank = rank + size;
                    prev.clear();
                    prev.add(n);
                    continue;
                }
            }
            // 동등한 나라거나, prev size가 0인 경우임.
            prev.add(n);
        }
        // 마지막 prev에 남은 것들 처리 (얘들은 완전 하위애들임)
        if (prev.size() != 0) {
            for (Nation p : prev) {
                p.rank = rank + 1;
                if (p.nation == K) {
                    System.out.println(p.rank);
                    return;
                }
            }
        }
    }

    public static int compareGold(Nation n1, Nation n2) {
        return n1.gold > n2.gold ? 1 :  n1.gold < n2.gold ? -1 : 0;
    }

    public static int compareSilver(Nation n1, Nation n2) {
        return n1.sil > n2.sil ? 1 :  n1.sil < n2.sil ? -1 : 0;
    }

    public static int compareBronze(Nation n1, Nation n2) {
        return n1.bron > n2.bron ? 1 :  n1.bron < n2.bron ? -1 : 0;
    }
}

class Nation implements Comparable {
    int nation;
    int gold;
    int sil;
    int bron;
    int rank;

    public Nation(int nation, int gold, int sil, int bron) {
        this.nation = nation;
        this.gold = gold;
        this.sil = sil;
        this.bron = bron;
    }

    @Override
    public int compareTo(Object object) {
        Nation o = (Nation) object;
        return this.gold > o.gold ? 1 : this.gold < o.gold ? -1 :
                this.sil > o.sil ? 1 : this.sil < o.sil ? -1 :
                        this.bron  > o.bron ? 1 : this.bron < o.bron ? -1 : 0;
    }
}