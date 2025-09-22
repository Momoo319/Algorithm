import java.io.*;
import java.util.*;

public class boj_14938 {

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

    static int n, m, r;
    static int[] item;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 지역 개수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 간선 개수

        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
            // 길은 양방향 통행 가능
        }

        int max = 0;
        int cnt = 0;
        for(int i = 1; i <= n; i++){
            cnt = dijkstra(i);
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }

    static int dijkstra(int start){
        int[] dist = new int[n+1];
        boolean[] visit = new boolean[n+1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            int now = pq.poll().idx;
            if(visit[now])continue;
            visit[now] = true;


            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        int ans = 0;
        for(int i = 1; i < n+1; i++){
            if(dist[i] <= m){
                ans += item[i];
            }
        }

        return ans;
    }

}

/*
 * note : Dijkstra
 * performance : 108ms
 * title : 서강그라운드
 * 
 */