import java.io.*;
import java.util.*;

public class boj_9470_Re {

    static int T, K, M, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            int result = 0;

            ArrayList<Integer>[] list = new ArrayList[M + 1];
            for(int i = 0; i < M + 1; i++){
                list[i] = new ArrayList<>();
            }

            int[] cnt = new int[M + 1]; //위상 정렬의 진입 노드 개수
            int[] ans = new int[M + 1]; //Strahler 순서
            int[] maxCnt = new int[M + 1];//해당 순서가 몇번 들어왔는지

            for(int i = 0; i < P; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                cnt[to]++;
                list[from].add(to);
            }

            Queue<Integer> q = new LinkedList<>();

            for(int i = 1; i < M + 1; i++){
                if(cnt[i] == 0) {
                    q.add(i);
                    ans[i] = 1;
                }
            }

            while(!q.isEmpty()){
                int now = q.poll();

                for(int next : list[now]){
                    if(ans[next] < ans[now]){//처음 들어온 가장 높은 순서
                        ans[next] = ans[now];
                        maxCnt[next] = 1; // 해당 순서에 대해 1로 초기화
                    }
                    else if(ans[next] == ans[now]){
                        maxCnt[next]++;
                    }

                    cnt[next]--;
                    if(cnt[next] == 0){
                        if(maxCnt[next] >= 2)ans[next]++;
                        q.add(next);
                    }
                }
            }

            result = ans[M];

            sb.append(K).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

}

/*
 * note : 위상정렬
 * performance : 104ms
 * title : Strahler 순서
 *
 */
