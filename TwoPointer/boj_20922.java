import java.io.*;
import java.util.*;

public class boj_20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        HashMap<Integer, Integer> hmap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int left = 0;
        int right = 0;
        int cur = 0;

        while (right < n) {
            int num = arr[right];

            if (hmap.containsKey(num) && hmap.get(num) == k) {
                    hmap.put(arr[left], hmap.get(arr[left]) - 1);
                    left++;
                    cur--;
                } else {
                    hmap.put(num, hmap.getOrDefault(num, 0) + 1);
                    right++;
                    cur++;
                }
                max = Math.max(max, cur);
        }
        System.out.println(max);

    }
}

/*
 * note : two-pointer
 * performance : 536ms
 * title : 겹치는 건 싫어
 * 
 */