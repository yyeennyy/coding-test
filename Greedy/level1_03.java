// "1이 될 때까지"

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
1. 만약 N < K일 경우 N % K 가 무의미하다.
   ∴ N < K 에서 무조건 N-1만 하게 한다면 비교연산 횟수를 줄일 수 있다.
   
2. "N가 K의 배수가 될 때까지 -1 해야 한다": 이런 방법도 가능하다.
   int target = (N / K) * K;
   count += (N - target);
   
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

int N = sc.nextInt();
int K = sc.nextInt();

int count = 0;

while(N != 1){
   if (N % K == 0){
     N = (int) N/K;
   }
   else{
     N = N-1;
   }
   count++;
}

System.out.println(count);



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 수정한 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

while(N >= K){
  if (N % K == 0){
    N = (int) N/K;
  }
  else{
    N = N-1;
  }
  count++;
}

while (N > 1){
  N = N - 1;
  count++;
}

System.out.println(count);



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 수정한 코드 2 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
while (true) {
   // 어차피 N / K가 처음으로 가능할 때까지 1을 빼야한다.
   // ∴ N - 1 연산의 반복은 아래 코드로 충분하다.
   int target = (N / K) * K;
   count += (N - target);

   // N: 반드시 K의 배수
   N = target; 
   N /= K;
   count++;

   if (N < K) break; 
}

count += (N - 1);
System.out.println(count);
