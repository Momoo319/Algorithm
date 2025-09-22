import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_18428 {

    static class Node{
        int x;
        int y;

        public Node(int y, int x){
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static List<Node> xList, tList;
    static String[][] board;
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static boolean ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        xList = new ArrayList<>();
        tList = new ArrayList<>();
        board = new String[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                String temp = st.nextToken();
                board[i][j] = temp;
                if(temp.equals("X")){
                    xList.add(new Node(i, j));
                }
                else if(temp.equals("T")){
                    tList.add(new Node(i, j));
                }
            }
        }

        ans = false;
        func(0, 0);
        if(ans)System.out.println("YES");
        else System.out.println("NO");

    }

    static void func(int num, int cnt){//조합으로 x들 중 3개를 뽑아 check()를 통해 T와 S 사이를 가리는지 확인
        if(cnt == 3){
            if(check()){
                ans = true;
            }
            return;
        }

        for(int i = num; i < xList.size(); i++){
            Node n = xList.get(i);
            board[n.y][n.x] = "O";
            func(i+1, cnt+1);

            if(ans)return;
            board[n.y][n.x] = "X";
        }
    }

    static boolean check(){
        for(Node t : tList){
            for(int dir = 0; dir < 4; dir++){
                int nx = t.x;
                int ny = t.y;

                while(true){
                    nx += dx[dir];
                    ny += dy[dir];

                    if(0 > nx || nx >= N || ny < 0 || ny >= N)break;
                    if(board[ny][nx].equals("O"))break;
                    if(board[ny][nx].equals("S"))return false;
                }

            }

        }
        return true;
    }

}

/*
 * note : dfs
 * performance : 136ms
 * title : 감시 피하기
 */