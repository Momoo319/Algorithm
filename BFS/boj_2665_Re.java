import java.io.*;
import java.util.*;

public class boj_2665_Re {

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
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int[][] count = new int[N][N];

        boolean[][] visit = new boolean[N][N];
        visit[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = dx[dir] + cur.x;
                int ny = dy[dir] + cur.y;

                if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                if(map[ny][nx] == '0'){
                    if(visit[ny][nx]){
                        if(count[ny][nx] > count[cur.y][cur.x] + 1){
                            count[ny][nx] = count[cur.y][cur.x] + 1;
                            q.add(new Node(ny, nx));
                        }
                    }
                    else{
                        count[ny][nx] = count[cur.y][cur.x] + 1;
                        q.add(new Node(ny, nx));
                        visit[ny][nx] = true;
                    }
                }
                else{
                    if(visit[ny][nx]){
                        if(count[ny][nx] > count[cur.y][cur.x]){
                            count[ny][nx] = count[cur.y][cur.x];
                            q.add(new Node(ny, nx));
                        }
                    }
                    else{
                        count[ny][nx] = count[cur.y][cur.x];
                        q.add(new Node(ny, nx));
                        visit[ny][nx] = true;
                    }

                }
            }
        }

        System.out.println(count[N-1][N-1]);
    }
}

/*
 * note : bfs
 * performance : 116ms
 * title : 미로만들기
 * etc : bfs를 돌면서 해당 [ny][nx]까지 거친 벽 개수 최소 갱신
 */