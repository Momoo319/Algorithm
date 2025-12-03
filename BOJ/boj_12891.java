import java.io.*;
import java.util.*;

public class boj_12891 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int cnt = 0;

        int left = 0;
        int right = P - 1;
        int aCnt = 0;
        int cCnt = 0;
        int gCnt = 0;
        int tCnt = 0;

        for(int i = left; i < right; i++){
            switch(str.charAt(i)){
                case 'C':
                    cCnt++;
                    break;
                case 'T':
                    tCnt++;
                    break;
                case 'G':
                    gCnt++;
                    break;
                default:
                    aCnt++;
                    break;
            }

        }

        while(right < S){

            switch(str.charAt(right)){
                case 'C':
                    cCnt++;
                    break;
                case 'T':
                    tCnt++;
                    break;
                case 'G':
                    gCnt++;
                    break;
                default:
                    aCnt++;
                    break;
            }

            if(a <= aCnt && c <= cCnt && t <= tCnt && g <= gCnt){
                cnt++;
            }

            switch(str.charAt(left)){
                case 'C':
                    cCnt--;
                    break;
                case 'T':
                    tCnt--;
                    break;
                case 'G':
                    gCnt--;
                    break;
                default:
                    aCnt--;
                    break;
            }
            left++;
            right++;

        }

        System.out.println(cnt);
    }

}

/*
*   note : string, sliding window
*   performance : 276ms
*   title : DNA 비밀번호
*/