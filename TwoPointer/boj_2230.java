import java.io.*;
import java.util.*;

public class boj_2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = 1;

        while(r < N){
            int diff = arr[r] - arr[l];

            if(diff >= M){ 
    // arr은 정렬 되있기 때문에 diff가 M 이상인 첫번째 조합이 해당 l에서 가장 차이가 작은 조합
                min = Math.min(min, diff);
                l++;
                r = l+1;
            }
            else{
                r++;
            }
        }

        System.out.println(min);
    }

}

/*
 * note : two-pointer
 * performance : 2816ms
 * title : 수 고르기
 * 
 */