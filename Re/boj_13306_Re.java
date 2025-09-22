import java.io.*;
import java.util.*;

public class boj_13306_Re {

    static int[] p;
    static int N, Q;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        make();

        int[] arr = new int[N+1];//정점 i와 정점 i의 부모 저장 배열
        for(int i = 1; i <= N - 1; i++){
            st = new StringTokenizer(br.readLine());
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer>[] query = new ArrayList[N - 1 + Q];
        // N - 1 + Q 개의 입력 저장
        for(int i = 0; i < N - 1 + Q; i++){
            st = new StringTokenizer(br.readLine());
            query[i] = new ArrayList<>(2);
            int x = Integer.parseInt(st.nextToken());
            if(x == 1){ // x = 1인 경우 : 연결 확인
                query[i].add(Integer.parseInt(st.nextToken()));
                query[i].add(Integer.parseInt(st.nextToken()));
            }
            else{   // x = 0인 경우 : 연결 끊기
                query[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        boolean[] ans = new boolean[Q];
        int cnt = 0;

        for(int i = query.length - 1; i >= 0; i--){
            if(query[i].size() == 1){ // 입력 x = 0인 경우
                union(query[i].get(0), arr[query[i].get(0)]);
            }
            else{   //입력 x = 1인 경우
                ans[cnt] = (find(query[i].get(0)) == find(query[i].get(1)));
                cnt++;
            }
        }

        for(int i = cnt-1; i >= 0; i--){
            if(ans[i])System.out.println("YES");
            else System.out.println("NO");
        }

    }

    static void make(){
        p = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            p[i] = -1;
        }
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
 * performance : 1876ms
 * title : 트리
 * etc : 유니온파인드는 연결 해제를 지원하지 않기 때문에 
 *       역순으로 진행하며 연결이 다 끊어져있는 상태에서 하나씩 연결을 확인하는 방식으로
 * 
 */