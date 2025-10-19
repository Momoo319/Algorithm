import java.io.*;
import java.util.*;

public class boj_11725_bfs {

    static ArrayList<Integer> [] graph;
    static int N;
    static boolean[] visit;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 팀원 수
        graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }
        visit = new boolean[N + 1];
        parent = new int[N + 1];

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        bfs(1);

        for(int i = 2; i < N + 1; i++){
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int x){

        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);

        visit[x] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : graph[cur]){
                if(!visit[next]){
                    q.add(next);
                    parent[next] = cur;
                    visit[next] = true;
                }
            }
        }

    }

}

/*
 * note : bfs
 * performance : 564ms
 * title : 트리 부모 찾기
 * etc : bfs를 활용한 그래프 탐색
 */