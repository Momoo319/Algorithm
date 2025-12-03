import java.io.*;
import java.util.*;

public class boj_1072 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Integer.parseInt(st.nextToken()); // 게임 횟수
        long Y = Integer.parseInt(st.nextToken()); // 이긴 게임

        long max = X;

        long min = 1;
        long mid = 1;
        long tempX = X;
        long tempY = Y;
        long Z = Y * 100/ X ;
        if(Z == 100){
            System.out.println(-1);
            return;
        }

        long ans = -1;
        while(min <= max){
            mid = (max + min) / 2;

            tempX = X + mid;
            tempY = Y + mid;

            long tempZ = tempY * 100 / tempX;

            if(tempZ <= Z){
                min = mid + 1;
            }
            else{
                ans = mid;
                max = mid - 1;
            }
        }

        System.out.println(ans);
    }

}

/*
*   note : binary search
*   performance : 104ms
*   title : 게임
*/