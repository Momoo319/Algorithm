import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] board;
    static boolean[][] visited;
    static int[][] bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new boolean[N][N];
        bridge = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int landNum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] || board[i][j] == 0)continue;
                landNum++;
                Queue<Node> q = new LinkedList<Node>();
                q.offer(new Node(i, j));
                visited[i][j] = true;
                board[i][j] = landNum;

                while(!q.isEmpty()){
                    Node cur = q.poll();
                    int curX = cur.x;
                    int curY = cur.y;

                    for(int dir = 0; dir < 4; dir++){
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                        if(visited[nx][ny] || board[nx][ny] == 0)continue;
                        board[nx][ny] = landNum;
                        q.offer(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bridge = new int[N][N];
                if(board[i][j] != 0)continue;

                boolean check = false;
                int landNumCheck = 0;
                for(int dir = 0; dir < 4; dir++){
                    int tempX = i + dx[dir];
                    int tempY = j + dy[dir];

                    if(tempX < 0 || tempX >= N || tempY < 0 || tempY >= N)continue;
                    if(board[tempX][tempY] != 0){
                        check = true;
                        landNumCheck = board[tempX][tempY];
                        break;
                    }
                }
                if(!check)continue;

                Queue<Node> q = new LinkedList<Node>();
                q.offer(new Node(i, j));
                bridge[i][j] = 1;

                for(int dir = 0; dir < 4; dir++){
                    int tempX = i + dx[dir];
                    int tempY = j + dy[dir];
                    if(tempX < 0 || tempX >= N || tempY < 0 || tempY >= N)continue;

                    if(board[tempX][tempY] != 0 && board[tempX][tempY] != landNumCheck){
                        System.out.println(1);
                        return;
                    }
                }
                while(!q.isEmpty()){
                    Node cur = q.poll();
                    int curX = cur.x;
                    int curY = cur.y;

                    for(int dir = 0; dir < 4; dir++){
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                        if(bridge[nx][ny] != 0 || board[nx][ny] == landNumCheck)continue;
                        bridge[nx][ny] = bridge[curX][curY] + 1;
                        if(board[nx][ny] != 0 && board[nx][ny] != landNumCheck){
                            ans = Math.min(ans, bridge[curX][curY]);
                            continue;
                        }
                        q.offer(new Node(nx, ny));
                    }
                }

            }
        }

        System.out.println(ans);
    }
}


/*
*   performance : 472ms
*   link : https://www.acmicpc.net/status?user_id=mo980427&problem_id=2146&from_mine=1
*   note : bfs
 */