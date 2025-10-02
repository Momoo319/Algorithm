import java.io.*;
import java.util.*;

public class boj_1043_Re {

    static int[] p;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N + 1];
        Arrays.fill(p, -1);

        ArrayList<Integer>[] list = new ArrayList[M];
        for(int i = 0; i < M; i++){
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        int truth = Integer.parseInt(st.nextToken()); //진실 아는 사람 수

        int first = 0;
        if(truth != 0){
            first = Integer.parseInt(st.nextToken());
            for(int i = 0; i < truth - 1; i++){
                int second = Integer.parseInt(st.nextToken());

                union(first, second);
            }
        }
        else{
            System.out.println(M);
            return;
        }

        int ans = 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            boolean check = false;
            for(int j = 0; j < num; j++){
                int temp = Integer.parseInt(st.nextToken());

                list[i].add(temp);

            }

            for(int j = 1; j < list[i].size(); j++){
                union(list[i].get(j-1), list[i].get(j));
            }
        }

        for(int i = 0; i < M; i++){
            for(int n: list[i]){
                if(find(first) == find(n)){
                    ans--;
                    break;
                }
            }
            ans++;
        }
        System.out.println(ans);
    }

    static int find(int x){
        if(p[x] < 0)return x;
        return p[x] = find(p[x]);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)return false;

        if(p[x] > p[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        if(p[x] == p[y])p[x]--;

        p[y] = x;

        return true;

    }
}

/*
 * note : union-find
 * performance : 104ms
 * title : 거짓말
 * etc : 1. 진실 아는 사람 union -> 2. 파티 내부 별로 유니온
 * 
(10, 1)

(9, 2)

(8, 3)

(7, 4)

(6, 5)

(5, 7) → {4,7}와 {5,6} 결합 → {4,5,6,7}

(4, 8) → {4,5,6,7}와 {3,8} 결합 → {3,4,5,6,7,8}

(3, 9) → {3,4,5,6,7,8}와 {2,9} 결합 → {2,3,4,5,6,7,8,9}

(2, 10) → 위 큰 집합과 {1,10} 결합 → {1..10 전부 같은 집합}

(1) 단독

즉, 모든 파티를 유니온만 먼저 해두면, 최종적으로 1과 9는 2→10을 거친 연쇄로 같은 집합이 됩니다. 그래서 이후 체크 단계에서 모든 파티가 “진실 루트에 속한 사람이 포함”되어 정답 0이 나와요.

안전한 구현 패턴 (두 단계)

입력에서 파티 참가자들 전부 저장

1차 패스: 각 파티 내에서 첫 사람 기준으로 union(p[0], p[i])를 전부 수행

2차 패스:

진실 아는 사람 목록이 비어있으면 정답 = M

아니면 진실 루트(들)를 잡고, 각 파티 참가자 중 루트 일치자가 하나라도 있으면 제외, 없으면 정답++
 */