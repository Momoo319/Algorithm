import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

       int N = Integer.parseInt(st.nextToken());

       Set<String> set = new HashSet<>();
       for(int i = 0; i < N; i++){
           st = new StringTokenizer(br.readLine());
           set.add(st.nextToken());
       }

       ArrayList<String> words = new ArrayList<>(set);
       Collections.sort(words, new Comparator<String>(){
           public int compare(String o1, String o2){
               if(o1.length() > o2.length()){
                   return 1;
               }
               else if(o1.length() < o2.length()){
                   return -1;
               }

               return o1.compareTo(o2);
           }
       });

       for(int i = 0; i < words.size(); i++){
           sb.append(words.get(i)).append("\n");
       }

       System.out.print(sb);
    }


}

/*
 * note : Comparator
 * performance : 340ms
 * title : 단어 정렬
 * etc : notion
 */