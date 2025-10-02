import java.io.*;
import java.util.*;

public class boj_1516_Re {

    static ArrayList<Integer>[] list;
    static ArrayList<Integer>[] list2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            list[i] = new ArrayList<Integer>();
        }

        list2 = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            list2[i] = new ArrayList<Integer>();
        }

        int[] cnt = new int[N+1]; // 먼저 지어야하는 건물 수
        int[] cost = new int[N+1]; // i번째 건물 자체 짓는데 걸리는 시간
        int[] ans = new int[N+1]; // 누적 짓는데 걸리는 시간

        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());

            int count = 0;
            while(true) {
                int t = Integer.parseInt(st.nextToken());
                if (count == 0) {
                    cost[i] = t;
                    ans[i] = t;
                } else {
                    if (t == -1) break;
                    else {
                        cnt[i]++;
                        list[t].add(i);
                        list2[i].add(t);
                    }
                }
                count++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i < N + 1; i++){
            if(cnt[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int now = pq.poll();

            for(int next : list[now]){
                cnt[next]--;

                if(cnt[next] == 0){
                    pq.add(next);
                    int max = 0;

                    if(list2[next].size() == 1){
                        ans[next] += ans[list2[next].get(0)];
                    }

                    else{
                        for(int i = 0; i < list2[next].size(); i++){
                            max = Math.max(ans[list2[next].get(i)], max);
                        }
                        ans[next] += max;
                    }

                }
            }
        }

        for(int i = 1; i < N + 1; i++){
            System.out.println(ans[i]);
        }
    }

}

/*
 * note : 위상 정렬
 * performance : 288ms
 * title : 게임 개발
 * etc : -> Notion
 * 
 */