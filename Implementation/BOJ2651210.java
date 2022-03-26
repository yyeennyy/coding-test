
/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
import java.util.*;

public class Main{

  public static void main(String[] args) {
    Scanner sc  = new Scanner(System.in);
    
    int N = sc.nextInt();
    int[] secPerMusic = new int[N];
    int Q = sc.nextInt();
    int[] questions = new int[Q];
    for(int i = 0; i < N; i++)
      secPerMusic[i] = sc.nextInt();
    for(int i = 0; i < Q; i++)
      questions[i] = sc.nextInt();
    sc.nextLine();

    ArrayList<Integer> answer = new ArrayList<>();
    for(int i = 0; i < N; i++){
      int numberOfMusic = i+1;
      int nthMusic = secPerMusic[i];
      for(int j = nthMusic; j > 0; j--)
        answer.add(numberOfMusic);
    }

    for(int sec : questions)
      System.out.println(answer.get(sec));
    
    sc.close();
  }
}



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 다른 방법 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
이런 방법도 있다는 인식 정도만 하고 넘어가자.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
    // 질문 개수만큼 for문 돌 거다.
    for (int i = 0; i < Q; i++) {
      // time은 초기에는 1번악보 second로 설정했지만, 흘러갈 것임
			int time = secPerMusic[0];
			for (int j = 0; j < N; j++) {
        // time(기준)보다 question이 묻는 시간이 더 작다면, 
				if (questions[i] < time) {
          // 악보 번호(j+1)를 출력하고 다음 질문(i++)으로 가게 한다.
					System.out.println(j+1);
					break;
				}
        // time을 다음악보 끝나는 시간까지 흘러가게 한다.
				time += secPerMusic[j+1];
			}
		}
