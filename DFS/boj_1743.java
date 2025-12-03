import java.io.*;
import java.util.*;

public class boj_1743 {

    static int N, M, K;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visit;
    static int cnt = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;

            map[y][x] = 1;
        }



        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                cnt = 0;
                if(!visit[i][j] && map[i][j] > 0){
                    func(i, j);
                    max = Math.max(max, cnt);
                }
            }

        }

        System.out.println(max);
    }

    static void func(int y, int x){
        visit[y][x] = true;
        cnt++;

        for(int dir = 0; dir < 4; dir++){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N)continue;
            if(visit[ny][nx] || map[ny][nx] == 0)continue;

            func(ny, nx);

        }
    }
}

/*
*   note : dfs
*   performance : 128ms
*   title : 음식물 피하기
*   etc : bfs와 performance 차이 없음.
*/