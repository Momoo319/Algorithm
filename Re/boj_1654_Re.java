import java.io.*;
import java.util.*;

public class boj_1654_Re {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long max = arr[K - 1] + 1;

        long min = 0;
        long mid = 0;

        while(min < max){
             mid = (max + min) / 2;

             long cnt = 0;

             for(int i = 0; i < arr.length; i++){
                 cnt += (arr[i] / mid);
             }

             if(cnt < N){
                 max = mid;
             }
             else{
                 min = mid + 1;
             }
        }

        System.out.println(min - 1);
    }

}

/*
*   note : binary search
*   performance : 200ms
*   title : 랜선 자르기
*   etc : notion
*/