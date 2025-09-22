import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1504 {

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
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost)); //양방향 그래프이기 때문에 간선 양 방향 추가
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()); //거쳐야 할 정점1
        int v2 = Integer.parseInt(st.nextToken()); //거쳐야 할 정점2

         int v1Tov2 = dijkstra(N, v1, v2);
         int v2Tov1 = dijkstra(N, v2, v1);
         int oneTov1 = dijkstra(N, 1, v1);
         int oneTov2 = dijkstra(N, 1, v2);
         int v1ToN = dijkstra(N, v1, N);
         int v2ToN = dijkstra(N, v2, N);

         //1 -> v1 -> v2 -> N  or 1 -> v2 -> v1 -> N
         //각 경우의 경로 중 -1이 포함되었는지 확인
         if(oneTov1 == -1 || v1Tov2 == -1 || v2ToN == -1){ 
             if(oneTov2 == -1 || v2Tov1 == -1 || v1ToN == -1){
                 System.out.println(-1);
             }
             else{
                 int ans = oneTov2 + v2Tov1 + v1ToN;
                 if(ans <= 0 || ans == Integer.MAX_VALUE){
                     System.out.println(-1);
                 }
                 else{
                     System.out.println(ans);
                 }
             }

         }
         else if(oneTov2 == -1 || v2Tov1 == -1 || v1ToN == -1){
             if(oneTov1 == -1 || v1Tov2 == -1 || v2ToN == -1){
                 System.out.println(-1);
             }
             else{
                 int ans = oneTov1 + v1Tov2 + v2ToN;
                 if(ans <= 0 || ans == Integer.MAX_VALUE){
                     System.out.println(-1);
                 }
                 else{
                     System.out.println(ans);
                 }
             }

         }
         else{
             int oneToN1 = oneTov1 + v1Tov2 + v2ToN;
             int oneToN2 = oneTov2 + v2Tov1 + v1ToN;

             int ans = Math.min(oneToN1, oneToN2);
             if(ans <= 0 || ans == Integer.MAX_VALUE){
                 ans = Math.max(oneToN1, oneToN2);
             }

             if(ans <= 0 || ans == Integer.MAX_VALUE){
                 System.out.println(-1);
             }
             else{
                 System.out.println(ans);
             }
         }

     }

    static int dijkstra(int N, int start, int end) {
        boolean[] visit = new boolean[N+1];
        int[] dist = new int[N+1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        if(dist[end] == INF ){ // 최단 경로 존재하지 않음.
            return -1;
        }
        return dist[end];
    }
}

/*
* note : dijkstra
* performance : 676ms
* title : 특정한 최단 경로
 */