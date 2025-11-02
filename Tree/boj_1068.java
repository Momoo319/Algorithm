import java.io.*;
import java.util.*;

public class boj_1068 {

    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int root = 0;
        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == -1){
                root = i;
            }
            else{
                tree[temp].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        int delete = Integer.parseInt(st.nextToken());
        if(root == delete){
            System.out.println(0);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        int ans = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(tree[cur].isEmpty()){
                ans++;
                continue;
            }
            else if(tree[cur].size() == 1 && tree[cur].get(0) == delete){
                ans++;
                continue;
            }

            for(int next : tree[cur]){
                if(next == delete)continue;
                q.add(next);
            }
        }

        System.out.println(ans);
    }
}

/*
 * note : tree + dfs
 * performance : 104ms
 * title : 트리
 * etc : tree[부모] = 자식들.. 
 * -> Queue에 자식들을 넣으며 delete된 정점은 삽입 X -> delete 정점 자식들은 자연히 add 되지 않는다.
 */