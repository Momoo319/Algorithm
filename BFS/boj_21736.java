import java.io.*;
import java.util.*;

public class boj_21736 {
    static class Node{
        int x;
        int y;

        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        boolean[][] visit = new boolean[N][M];

        int startX = 0;
        int startY = 0;
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                char temp = str.charAt(j);
                map[i][j] = temp;
                if(temp == 'I'){
                    startX = j;
                    startY = i;
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startY, startX));
        visit[startY][startX] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N)continue;
                if(visit[ny][nx] || map[ny][nx] == 'X')continue;

                visit[ny][nx] = true;
                q.add(new Node(ny, nx));
                if(map[ny][nx] == 'P')cnt++;
            }
        }

        if(cnt == 0)System.out.println("TT");
        else{
        System.out.println(cnt);
        }
    }
}

/*
* note : bfs
* performance : 380ms
* title : 헌내기는 친구가 필요해
 */