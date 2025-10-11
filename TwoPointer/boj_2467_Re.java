import java.io.*;
import java.util.*;

public class boj_2467_Re {

    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x1 = 0, x2 = 0;

        int left = 0;
        int right = N - 1;
        int sum = Math.abs(arr[left] + arr[right]);

        int temp = 0;

        while(right != left){
            temp = arr[left] + arr[right];

            if(sum >= Math.abs(temp)){
                x1 = left;
                x2 = right;
                sum = Math.abs(temp);
            }

            if(temp > 0){
                right--;
            }
            else if(temp < 0){
                left++;
            }
            else{
                System.out.println(arr[left] + " " + arr[right]);
                return;
            }
        }

        System.out.println(arr[x1] + " " + arr[x2]);
    }

}

/*
 * note : two-pointer
 * performance : 336ms
 * title : 용액
 * 
 */