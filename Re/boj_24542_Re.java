import java.io.*;
import java.util.*;

public class boj_24542_Re {

    static final int MOD = 1_000_000_007;
    static int[] p;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N];
        Arrays.fill(p, -1);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            union(from, to);
        }

        long cnt = 1;
        for(int i = 0; i < N; i++){
            if(p[i] < 0){
                cnt = (cnt * (-p[i])) % MOD;
            }
        }

        System.out.println(cnt);
    }

    static int find(int x){
        if(p[x] < 0) return x;
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

        p[x] += p[y];

        p[y] = x;

        return true;
    }
}

/*
 * note : union-find
 * performance : 388ms
 * title : 튜터-튜티 관계의 수
 * 
 */
