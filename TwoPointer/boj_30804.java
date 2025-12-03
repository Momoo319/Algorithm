import java.io.*;
import java.util.*;

public class boj_30804 {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1){
            System.out.println(1);
            return;
        }

        int left = 0;
        int right = 1;
        HashMap<Integer, Integer> hmap = new HashMap<>();
        hmap.put(arr[0], 1);
        int ans = 0;
        while(right < N){
            if(hmap.containsKey(arr[right])){
                hmap.put(arr[right], hmap.get(arr[right]) + 1);
            }
            else{

                if(hmap.size() == 2){
                    int temp = hmap.get(arr[left]);

                    if(temp - 1 == 0){
                        hmap.remove(arr[left]);

                    }
                    else{
                        hmap.put(arr[left], temp - 1);
                    }

                    left++;
                    continue;
    //hmap.size()가 1이 되기 전까지는 새로운 숫자를 put하지 않고 
    //left++하며 왼쪽에서부터 remove해온다.
                }
                else{
                    hmap.put(arr[right], 1);
                }
            }
            ans = Math.max(right-left+1, ans);

            right++;
        }

        System.out.println(ans);
    }
}

/*
*   note : two-pointer
*   performance : 380ms
*   title : 과일 탕후루
 */