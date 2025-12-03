import java.io.*;
import java.util.*;

public class boj_1389 {

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

    static int N, M;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, 1));
            graph[b].add(new Node(a, 1));
        }

        int sum = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 1; i < N + 1; i++){
            int temp = dijkstra(i);
            if(sum > temp){
                sum = temp;
                ans = i;
            }
        }

        System.out.println(ans);
    }

    static int dijkstra(int start){
        int[] dist = new int[N + 1];
        boolean[] visit = new boolean[N + 1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visit[cur.idx])continue;
            visit[cur.idx] = true;

            for(Node next : graph[cur.idx]){
                if(dist[next.idx] > dist[cur.idx] + next.cost){
                    dist[next.idx] = dist[cur.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        int sum = 0;
        for(int i = 1; i < N + 1; i++){
            sum += dist[i];
        }
        return sum;
    }
}

/*
* note : dijkstra
* performance : 120ms
* title : 케빈 베이컨의 6단계 법칙
* etc : bfs 풀이 better
 */
