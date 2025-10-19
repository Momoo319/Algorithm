import java.io.*;
import java.util.*;

public class boj_18870 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

       int N = Integer.parseInt(st.nextToken());

       Set<Integer> s = new HashSet<>();
       int[] temp = new int[N];
       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++){
           int t = Integer.parseInt(st.nextToken());
           s.add(t);
           temp[i] = t;
       }

       Integer[] arr = s.toArray(new Integer[0]);

       Arrays.sort(arr);

       HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
       for(int i = 0; i < arr.length; i++){
            hMap.put(arr[i], i);
       }

       for(int i = 0; i < N; i++){
           int answer = hMap.get(temp[i]);
           sb.append(answer).append(" ");
       }

       System.out.print(sb);
    }


}

/*
 * note : 
 * performance : 3040ms
 * title : 좌표 압축
 * etc : notion
 */