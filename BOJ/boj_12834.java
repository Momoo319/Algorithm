import java.io.*;
import java.util.*;

public class boj_12834 {

    static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }

    }

    static ArrayList<Node>[] graph;
    static int[] arr;
    static int ans, N, V, E, K, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 팀원 수
        V = Integer.parseInt(st.nextToken()); // 장소 수
        E = Integer.parseInt(st.nextToken()); // 간선 수
        arr = new int[N];
        graph = new ArrayList[V+1];
        for(int i = 0; i < V + 1; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // KIST 위치
        S = Integer.parseInt(st.nextToken()); // 씨알푸드 위치

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken()); // 팀원들의 집 위치
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        ans = 0;

        for(int i = 0; i < N; i++){
            dijkstra(arr[i]);
        }

        System.out.println(ans);
    }

    static void dijkstra(int start){
        int[] dist = new int[V + 1];
        boolean[] visit = new boolean[V + 1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visit[now.idx])continue;
            visit[now.idx] = true;

            for(Node next : graph[now.idx]){
                if(dist[next.idx] > dist[now.idx] + next.cost){
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }

        }

        if(dist[K] == INF)ans--;
        else ans += dist[K];

        if(dist[S] == INF)ans--;
        else ans += dist[S];

    }

}

/*
 * note : dijkstra
 * performance : 404ms
 * title : 주간 미팅
 * 
 */