import java.io.*;
import java.util.*;

public class boj_11286 {

    static class Num implements Comparable<Num>{
        int x;

        public Num(int x){
            this.x = x;
        }

        public int compareTo(Num o){
            if(Math.abs(this.x) == Math.abs(o.x)){
                return Integer.compare(this.x, o.x);
            }
            else{
                return Integer.compare(Math.abs(this.x), Math.abs(o.x));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Num> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            if(x == 0){
                if(pq.isEmpty())sb.append(0).append("\n");
                else {
                    sb.append(pq.poll().x).append("\n");
                }
            }
            else{
                pq.add(new Num(x));
            }

        }

        System.out.print(sb);
    }

}

/*
 * note : priority queue
 * performance : 332ms
 * title : 절댓값 힙
 * 
 */