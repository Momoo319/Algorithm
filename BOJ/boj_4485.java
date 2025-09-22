import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485 {
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

    static ArrayList<Node> [] nodelist;
    static int N, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cnt = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0)break;

            nodelist = new ArrayList[N * N];
            board = new int[N][N];
            for(int i = 0; i < N * N; i++){
                nodelist[i] = new ArrayList<>();
            }

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int dir = 0; dir < 4; dir++){
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;

                        nodelist[i*N + j].add(new Node(ny*N + nx, board[ny][nx]));
                    }
                }
            }

            ans = 0;
            dijkstra(0, N*N-1);
            System.out.println("Problem " + cnt + ": " + ans);
            cnt++;
        }

        return;
    }

    static void dijkstra(int start, int end){
        int[] dist = new int[N * N];
        boolean[] visit = new boolean[N * N];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = board[0][0];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, board[0][0]));

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            if(visit[now])continue;
            visit[now] = true;

            for(Node next : nodelist[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        ans = dist[end];
    }
}

/*
 * note : Dijkstra
 * performance : 300ms
 * title : 녹색 옷 입은 애가 젤다지?
 */