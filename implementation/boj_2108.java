import java.io.*;
import java.util.*;

public class boj_2108 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        long sum = 0;                    // long으로
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] arr = new int[N];
        HashMap<Integer, Integer> hmap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int temp = Integer.parseInt(st.nextToken());
            hmap.put(temp, hmap.getOrDefault(temp, 0) + 1);
            arr[i] = temp;
            sum += temp;
            max = Math.max(temp, max);
            min = Math.min(temp, min);
        }

        Arrays.sort(arr);

        // 1. 평균 (반올림)
        int avg = (int) Math.round((double) sum / N);
        System.out.println(avg);

        // 2. 중앙값
        System.out.println(arr[N / 2]);

        // 3. 최빈값 (여러 개면 두 번째로 작은 값)
        int maxFreq = 0;
        for (int freq : hmap.values()) {
            if (freq > maxFreq) maxFreq = freq;
        }

        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : hmap.entrySet()) {
            if (e.getValue() == maxFreq) {
                modes.add(e.getKey());
            }
        }
        Collections.sort(modes);

        int mode;
        if (modes.size() >= 2) {
            mode = modes.get(1);
        } else {
            mode = modes.get(0);
        }
        System.out.println(mode);

        // 4. 범위
        System.out.println(max - min);
    }
}

/*
 * note : implementation
 * performance : 756ms
 * title : 통계학
 * 
 */