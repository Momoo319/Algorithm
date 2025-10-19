import java.io.*;
import java.util.*;

public class boj_1991_Re {

    static class Node{
        char value;
        Node left;
        Node right;

        public Node(char value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static int N;
    static StringBuilder sb;
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

       N = Integer.parseInt(st.nextToken());
        tree = new Node[N + 1];

       for(int i = 0; i < N; i++){
           st = new StringTokenizer(br.readLine());
           char p = st.nextToken().charAt(0);
           char l = st.nextToken().charAt(0);
           char r = st.nextToken().charAt(0);

           if(tree[p - 'A'] == null){
            // 부모 노드가 아직 생성되지 않은 경우. 'A'는 문자 'A'의 ASCII 값
               tree[p - 'A'] = new Node(p);// 부모 노드 생성
           }
           if(l != '.'){
               tree[l - 'A'] = new Node(l); // 왼쪽 자식 노드 생성
               tree[p - 'A'].left = tree[l - 'A']; // 부모 노드와 연결
           }
           if(r != '.') {
               tree[r - 'A'] = new Node(r); // 오른쪽 자식 노드 생성
               tree[p - 'A'].right = tree[r - 'A']; // 부모 노드와 연결
           }
       }

       preorder(tree[0]);
       sb.append("\n");
        inorder(tree[0]);
        sb.append("\n");
        postorder(tree[0]);
        sb.append("\n");

        System.out.println(sb);
    }

    static void preorder(Node node){
        if(node == null)return;
        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node){
        if(node == null)return;
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    static void postorder(Node node){
        if(node == null)return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }
}

/*
 * note : tree
 * performance : 104ms
 * title : 트리 순회
 */