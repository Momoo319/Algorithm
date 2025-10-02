import java.io.*;
import java.util.*;

public class boj_2531_Re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] arr = new int[N + N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int dishNum = Integer.parseInt(st.nextToken());

            arr[i] = dishNum;
        }

        for(int i = 0; i < N; i++){
            arr[N + i] = arr[i];
        }

        int cnt = 1;
        int left = 0;
        int right = 0;

        HashMap<Integer, Integer> hMap = new HashMap<>();
        hMap.put(arr[left], 1);
        int maxAns = 1;
        int sumAns = 1;
        while(right+1 < 2*N){

            if(cnt < k){ //아직 k개 만큼 채워지지 않았을 때
                right++;
                if(hMap.containsKey(arr[right])){
                    hMap.put(arr[right], hMap.get(arr[right])+1);
                }
                else {
                    hMap.put(arr[right], 1);
                    sumAns++;
                }
                cnt++;
            }
            else{ // k개가 채워졌을 때

                if(hMap.get(arr[left]) > 1){
                    hMap.put(arr[left], hMap.get(arr[left])-1);
                }
                else{
                    hMap.remove(arr[left]);
                    sumAns--;
                }
                left++;

                right++;
                if(hMap.containsKey(arr[right])){
                    hMap.put(arr[right], hMap.get(arr[right])+1);
                }
                else{
                    hMap.put(arr[right], 1);
                    sumAns++;
                }
            }

            if(sumAns >= maxAns){
                if(hMap.containsKey(c)){
                    maxAns = sumAns;
                }
                else{
                    maxAns = sumAns + 1;
                }

            }
        }

        System.out.println(maxAns);
    }

}

/*
 * note : two-pointer / hashmap
 * performance : 276ms
 * title : 회전 초밥
 * 
 */