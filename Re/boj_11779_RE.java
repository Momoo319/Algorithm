import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_11779_RE {
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


    static ArrayList<Node> [] graph;
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(N, start, end);

    }

    static void dijkstra(int N, int start, int end) {
        boolean[] visit = new boolean[N + 1];
        int[] dist = new int[N + 1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        ArrayList<Integer> [] arr = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            arr[i] = new ArrayList<>();
        }
        int[] cnt = new int[N+1];
        cnt[start] = 1;
        arr[start].add(start);

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            if(visit[now])continue;
            visit[now] = true;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                    arr[next.idx] = new ArrayList<>(arr[now]);
                    arr[next.idx].add(next.idx);
                    cnt[next.idx] = cnt[now] + 1;
                }
            }
        }

        System.out.println(dist[end]);
        System.out.println(cnt[end]);
        for(int i = 0; i < cnt[end]; i++){
            System.out.print(arr[end].get(i) + " ");
        }

    }
}

/*
* note : dijkstra
* performance : 528ms
* title : 최소비용 구하기 2
 */