import java.io.*;
import java.util.*;

public class boj_2230_better {

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
            }
            else if(diff < M){
                r++;
            }


            if(min == M)break;
        }

        System.out.println(min);
    }

}

/*
 * note : two-pointer
 * performance : 360ms
 * title : 수 고르기
 *  end와 start를 상황에 따라만 이동시킵니다.
    차이가 너무 작으면 → end를 더 키워서 차이를 늘림
    차이가 충분히 크면 → start를 늘려서 차이를 줄임
    r(end)를 굳이 l+1로 리셋하지 않고, 계속 오른쪽으로만 진행하기 때문에 한 번의 선형 탐색으로 끝납니다.
    불필요한 중복 탐색이 없어서 더 빠름.
 */