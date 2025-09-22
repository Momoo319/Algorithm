import java.io.*;
import java.util.*;

public class boj_15654 {

    static int N, M;
    static int[] arr;
    static int[] ans;
    static boolean[] visit;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        ans = new int[M];
        visit = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        func(0);

        System.out.println(sb);
    }

    static void func(int cnt){
        if(cnt == M){
            for(int i = 0; i < M; i++){
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++){
            if(!visit[i]){
                visit[i] = true;
                ans[cnt] = arr[i];
                func(cnt + 1);
                visit[i] = false;
            }
        }

    }

}

/*
 * note : dfs
 * performance : 300ms
 * title : N과 M(5)
 * etc : return하면 cnt는 +1 이전으로 돌아가기 때문에 
 * 다시 return 하지 않는다면(for문 종료) 다음 func 호출에도 동일한 cnt + 1 idx에 값이 들어감.
 */
