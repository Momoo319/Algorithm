import java.io.*;
import java.util.*;

public class boj_2606 {

    static int N;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        boolean[] visit = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int cnt = -1;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(visit[cur])continue;
            cnt++;
            visit[cur] = true;

            for(int next : graph[cur]){
                if(!visit[next])q.add(next);
            }
        }
        System.out.println(cnt);
    }
}

/*
 * note : graph
 * performance : 104ms
 * title : 바이러스
 * 
 */