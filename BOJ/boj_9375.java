import java.io.*;
import java.util.*;

public class boj_9375 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            HashMap<String, Integer> cntMap = new HashMap<>();
            // category 별 개수

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());

                String cName = st.nextToken();
                String cate = st.nextToken();


                if(!cntMap.containsKey(cate)){
                    cntMap.put(cate, 1);
                }
                else{
                    cntMap.replace(cate, cntMap.get(cate) + 1);
                }

            }

            int ans = 1;

            for(int cnt : cntMap.values()){
                ans *= (cnt + 1);
            }

            System.out.println(ans - 1);
        }

    }

}

/*
 * note : 구현
 * performance : 104ms
 * title : 패션왕 신해빈
 * 
 */
