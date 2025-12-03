import java.io.*;
import java.util.*;

public class boj_2167 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;

                for(int i = y1; i <= y2; i++){
                    for(int j = x1; j <= x2; j++){
                        if(i == y1 && j < x1)continue;
                        else if(i == y2 && j > x2)break;

                        sum += arr[i][j];
                    }
                }

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

}

/*
* note : implementation
* performance : 2152ms
* title : 2차원 배열의 합
* note : 누적합으로 Re.
*/