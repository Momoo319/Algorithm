import java.io.*;
import java.util.*;

public class boj_19238_Re {

    static class Node implements Comparable<Node>{
        int x;
        int y;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }

        public int compareTo(Node o){
            if(this.y == o.y){
                return Integer.compare(this.x, o.x);
            }
            else{
                return Integer.compare(this.y, o.y);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node other = (Node) o;
            return this.y == other.y && this.x == other.x;
        }

        @Override
        public int hashCode() {
            return 31 * y + x; // 또는 Objects.hash(y, x)
        }
    }

    static int N, startX, startY, M, F;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Node[] arr;
    static boolean[][] visit;
    static int[][] board;
    static HashMap<Node, Node> hMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        hMap = new HashMap<>();

        M = Integer.parseInt(st.nextToken()); // 승객 수
        F = Integer.parseInt(st.nextToken()); // 초기 연료 양

        arr = new Node[M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken()) - 1;
        startX = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;

            arr[i] = new Node(y1, x1);
            hMap.put(new Node(y1, x1), new Node(y2, x2));

            if(M == 1){
                if(y1 == y2 && x1 == x2){
                    System.out.println(F);
                    return;
                }
            }
        }

        Arrays.sort(arr);

        int x = startX;
        int y = startY;

        while(true){
            Node temp = func(y, x);
            if(temp.x == -1){
                System.out.println(-1);
                return;
            }

            Node destination = hMap.get(temp);


            int F = func1(temp.y, temp.x, destination.y, destination.x);
//            System.out.println(temp.y + " " + temp.x + " F : " +F);
            if(F == -1){
                System.out.println(-1);
                return;
            }

            hMap.remove(temp);

            if(hMap.isEmpty())break;

            y = destination.y;
            x = destination.x;
        }

        System.out.println(F);
    }

    // 승객 찾기 bfs
    static Node func(int y, int x){
        visit = new boolean[N][N];
        board = new int[N][N];

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(y, x));
        visit[y][x] = true;
        board[y][x] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            int curX = cur.x;
            int curY = cur.y;

            for(int dir = 0; dir < 4; dir++){
                int nx = dx[dir] + curX;
                int ny = dy[dir] + curY;

                if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                if(visit[ny][nx] || map[ny][nx] == 1)continue;

                q.add(new Node(ny, nx));
                visit[ny][nx] = true;
                board[ny][nx] = board[curY][curX] + 1;
            }

        }

        int min = Integer.MAX_VALUE;
        int ansX = -1;
        int ansY = -1;
        for(int i = 0; i < M; i++){
            if(!hMap.containsKey(new Node(arr[i].y, arr[i].x)))continue;
            if(!visit[arr[i].y][arr[i].x])continue;
            if(min > board[arr[i].y][arr[i].x] && board[arr[i].y][arr[i].x] < F){
                ansX = arr[i].x;
                ansY = arr[i].y;

                min = board[arr[i].y][arr[i].x];
            }
        }

        if(ansX != -1){
            F -= board[ansY][ansX];
        }

        return new Node(ansY, ansX);
    }

    //목적지 찾기 bfs
    static int func1(int sy, int sx, int disY, int disX){
        visit = new boolean[N][N];
        board = new int[N][N];

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(sy, sx));
        visit[sy][sx] = true;
        board[sy][sx] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            int curX = cur.x;
            int curY = cur.y;

            for(int dir = 0; dir < 4; dir++){
                int nx = dx[dir] + curX;
                int ny = dy[dir] + curY;

                if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                if(visit[ny][nx] || map[ny][nx] == 1)continue;

                q.add(new Node(ny, nx));
                visit[ny][nx] = true;
                board[ny][nx] = board[curY][curX] + 1;

                if(disX == nx && disY == ny){
                    if(board[ny][nx] > F)return -1;
                    else{
                        F += board[ny][nx];
                        return F;
                    }
                }
            }

        }

        return -1;

    }

}

/*
 * note : bfs
 * performance : 272ms
 * title : 스타트 택시
 * etc -> notion
 */