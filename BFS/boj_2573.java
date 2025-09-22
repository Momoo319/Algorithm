import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2573 {

    static class Node{
        int x;
        int y;

        public Node(int y, int x){
            this.x = x;
            this.y = y;
        }
    }
    static int[][] board;
    static int[][] board2; // 각 위치의 인접 0의 개수를 담을 배열(board[i][j] -= board2[i][j]로 섬을 높이를 조정)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        board2 = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 1;//시간(년)
        int cnt = 1;//빙하 개수
        int c = 0; //이미 나뉘어져 있는지 확인
        while(cnt != 0){
            visit = new boolean[N][M];
            cnt = 0;

            check();

            for(int i = 1; i < N-1; i++){
                for(int j = 1; j < M-1; j++){
                    if(board[i][j] == 0)continue;
                    board[i][j] -= board2[i][j];
                }
            }

            for(int i = 1; i < N-1; i++){
                for(int j = 1; j < M-1; j++){
                    if(board[i][j] <= 0 || visit[i][j])continue;
                    visit[i][j] = true;

                    Queue<Node> q = new LinkedList<>();
                    q.offer(new Node(i, j));

                    while(!q.isEmpty()){
                        Node cur = q.poll();

                        for(int dir = 0; dir < 4; dir++){
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];

                            if(nx < 1 || nx >= M-1 || ny < 1 || ny >= N-1)continue;
                            if(visit[ny][nx] || board[ny][nx] <= 0)continue;

                            visit[ny][nx] = true;
                            q.offer(new Node(ny, nx));

                        }
                    }
                    cnt++;
                }
            }
            if(cnt > 1){
                if(c == 0)System.out.println(0); //만약 입력부터 나뉘어진 상태일 경우 확인
                else{
                    System.out.println(ans);
                }

                break;
            }
            else if(cnt == 0){
                System.out.println(0);
                break;
            }
            c++;
            ans++;
        }

    }

    static void check(){
        board2 = new int[N][M];
        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                if(board[i][j] > 0){
                    for(int dir = 0; dir < 4; dir++){
                        int nx = j + dx[dir];
                        int ny = i + dy[dir];

                        if(board[ny][nx] <= 0)board2[i][j]++;
                    }
                }
            }
        }
    }



}

/*
 * note : BFS
 * performance : 604ms
 * title : 빙산
 */