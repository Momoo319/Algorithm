import java.io.*;
import java.util.*;

public class boj_1743 {

    static class Node{
        int y;
        int x;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visit = new boolean[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;

            map[y][x] = 1;
        }

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        int max = 0;
        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0 || visit[i][j])continue;

                q.add(new Node(i, j));
                visit[i][j] = true;

                int cnt = 1;
                while(!q.isEmpty()){
                    Node cur = q.poll();
                    int y = cur.y;
                    int x = cur.x;

                    for(int dir = 0; dir < 4; dir++){
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];

                        if(nx < 0 || nx >= M || ny < 0 || ny >= N)continue;
                        if(visit[ny][nx] || map[ny][nx] == 0)continue;

                        visit[ny][nx] = true;
                        q.add(new Node(ny, nx));
                        cnt++;
                    }
                }
                max = Math.max(max, cnt);

            }
        }

        System.out.println(max);

    }

}

/*
*   note : bfs
*   performance : 136ms
*   title : 음식물 피하기
*/