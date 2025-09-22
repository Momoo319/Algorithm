import java.io.*;
import java.util.*;

public class boj_1260 {

    static int N, M, V;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++){
            list[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from].add(to);
            list[to].add(from);
        }

        visited[V] = true;
        for(int i = 1; i < N + 1; i++){
            Collections.sort(list[i]);
        }

        dfs(V, 1);

        System.out.println();
        visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(V);

        while(!q.isEmpty()){
            int idx = q.poll();
            if(visited[idx])continue;
            visited[idx] = true;
            System.out.print(idx + " ");

            for(int next : list[idx]){
                if(visited[next])continue;
                q.add(next);
            }
        }


    }

    static void dfs(int idx, int cnt){
        System.out.print(idx + " ");
        if(cnt == N)return;

        for(int next : list[idx]){
            if(visited[next])continue;
            visited[next] = true;
            dfs(next, cnt + 1);
        }

    }


}

/*
 * note : dfs, bfs
 * performance : 292ms
 * title : DFS와 BFS
 * 
 */
