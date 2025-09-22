import java.io.*;
import java.util.*;

public class boj_1051 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for(int i = 0; i < n; i++){
            String line = br.readLine();
            for(int j = 0; j < m; j++){
                board[i][j] = line.charAt(j) - '0';
            }
        }

        int ans = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int temp = board[i][j];

                for(int k = j+1; k < m; k++){
                    if(temp == board[i][k]){

                        for(int l = i+1; l < n; l++){
                            if(temp == board[l][j]){
                                if(temp == board[l][k] && (l - i) == (k - j)){
                                    ans = Math.max(ans, (l-i+1)*(k-j+1));
                                }
                            }

                        }
                    }
                }
            }
        }
        System.out.println(ans);

    }

}

/*
 * note : 구현
 * performance : 160ms
 * title : 숫자 정사각형
 * 
 */
