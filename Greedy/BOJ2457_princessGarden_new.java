정답코드와 비교하고 난 후기
- 301~1101 날짜가 끽해야 O(276N)이라는 것을 볼 줄 알았다면!
- 모든 t가 포함되어야하니까 t를 꽃지는시간max만큼 늘려가면서 체킹할 줄 알았다면! (정답코드의 알고리즘)


-------------------------------------------------
    
int t = 301, nextT = -1, cnt = 0;
while(t < 1201){
    nextT = t; // nextT는 맥시멈 지는시간 값을 찾는 역할
    // O(N)
    for(int i=0; i<N; i++){
        if(flowers[i][0] <= t && flowers[i][1] > nextT)
            nextT = flowers[i][1];
    }
    // 꽃을 다 돌았는데도 nextT가 안 늘어났다? => 현재 t를 커버하는 꽃이 없음
    if(nextT == t){
        System.out.println(0);
        return;
    }
    // 카운팅!
    cnt++;
    // t 점프!
    t = nextT; 
}
System.out.println(cnt);


----------------------------------------------------
    
int t = 301, cnt = 0;
while(true){
    int nextT = t;
    for(int i=0; i<N; i++){
        if(flowers[i][0] <= t && flowers[i][1] > nextT)
            nextT = flowers[i][1];
    }
    if(t == nextT){
            System.out.println(0);
            return;
    }
    cnt++;
    t = nextT;
    if(t >= 1201){ // 얘가 어차피 O(N)으로 제한해준다!
        System.out.println(cnt);
        return;
    }
}



