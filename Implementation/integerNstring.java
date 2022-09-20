// BOJ 2908

// 세자리 수 int를 (수의 자리를) 뒤집을 때 
// 나는 int -> String -> int 과정을 거쳤다.
// 근데 아래와 같은 방법이 있더라


// 어차피 '세자리 수' 전제다.
// ▼ 그냥 이렇게 매치시켜서 더하면 된다.
// 백의자리 수 : 일의자리 수
// 십의자리 수 : 십의자리 수
// 일의자리 수 : 백의자리 수

// 이런 거.. 자연스럽게 떠올릴 수 있겠다 다음에는..

n1=(n1/100)+(n1%100-n1%10)+(n1%10*100);
n2=(n2/100)+(n2%100-n2%10)+(n2%10*100);
System.out.println(Math.max(n1, n2));
