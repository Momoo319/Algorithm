import java.io.*;
import java.util.*;

public class boj_2206_Re {

    static class Node{
        int x;
        int y;
        boolean broken;

        public Node(int y, int x, boolean broken){
            this.y = y;
            this.x = x;
            this.broken = broken;
        }
    }

    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] dist;
    static boolean[][] smash;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dist = new int[N][M][2];
        smash = new boolean[N][M]; // 벽 부순 이후의 경로인지 아닌지 check

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = str.charAt(j) - 48;
            }
        }

        Queue<Node> q = new ArrayDeque<>();

        int INF = Integer.MAX_VALUE;
        // 3차원 배열 INF 초기화 (안쪽 1차원에 fill)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        q.add(new Node(0, 0, false));
        dist[0][0][0] = 1;


        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(ny >= N || ny < 0 || nx >= M || nx < 0)continue;

                if(cur.broken){ // 이미 부순 경우
                    if(board[ny][nx] == 1 || dist[ny][nx][1] != INF)continue;
                    if(dist[ny][nx][1] > dist[cur.y][cur.x][1] + 1){
                        dist[ny][nx][1] = dist[cur.y][cur.x][1] + 1;
                        q.add(new Node(ny, nx, true));
                    }
                }
                else {
                    if(board[ny][nx] == 1){
                        if(dist[ny][nx][1] > dist[cur.y][cur.x][0] + 1){
                            dist[ny][nx][1] = dist[cur.y][cur.x][0] + 1;
                            q.add(new Node(ny, nx, true));
                        }
                    }
                    else{
                        if(dist[ny][nx][0] != INF)continue;
                        dist[ny][nx][0] = dist[cur.y][cur.x][0] + 1;
                        q.add(new Node(ny, nx, false));
                    }
                }
            }
        }

        int ans1 = dist[N-1][M-1][1];
        int ans2 = dist[N-1][M-1][0];

        if(ans1 == INF && ans2 == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(Math.min(ans1, ans2));
        }
    }

}

/*
 * note : bfs
 * performance : 776ms
 * title : 벽 부수고 이동하기
 * note : dist [][][x]의 x를 0 : 부수지 않은 상태, 1 : 부순 상태로 분리하여 관리
 * 
 */