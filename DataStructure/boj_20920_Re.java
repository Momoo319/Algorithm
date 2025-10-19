import java.io.*;
import java.util.*;

public class boj_20920_Re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hmap = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if(str.length() < M)continue;

            hmap.put(str, hmap.getOrDefault(str, 0) + 1);
        }

        ArrayList<String> words = new ArrayList<>(hmap.keySet());

        Collections.sort(words, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){

                if(Integer.compare(hmap.get(o1), hmap.get(o2)) != 0){
                    return Integer.compare(hmap.get(o2), hmap.get(o1));
                }

                if(o1.length() != o2.length()){
                    return o2.length() - o1.length();
                }

                return o1.compareTo(o2);
            }
        });

        for(String s : words){
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }

}

/*
 * note : hashmap, comparator
 * performance : 792ms
 * title : 영단어 암기는 괴로워
 * etc : notion
 */