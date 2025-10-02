class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        String[] strArr1 = new String[n];
        String[] strArr2 = new String[n];
        
        for(int i = 0; i < n; i++){
            String temp="";
            strArr1[i] = Integer.toBinaryString(arr1[i]);
            if(strArr1[i].length() < n){
                for(int j = 0; j < n - strArr1[i].length(); j++){
                    temp += "0";
                }
            }
            strArr1[i] = temp + strArr1[i];
            
            temp="";
            strArr2[i] = Integer.toBinaryString(arr2[i]);
            if(strArr2[i].length() < n){
                for(int j = 0; j < n - strArr2[i].length(); j++){
                    temp += "0";
                }
            }
            strArr2[i] = temp + strArr2[i];
            
        }
        
        String temp = "";
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(strArr1[i].charAt(j) == '0' && strArr2[i].charAt(j) == '0'){
                    temp += " ";
                }
                else{
                    temp += "#";
                }
            }
            
            answer[i] = temp;
            temp = "";
        }
        
        return answer;
    }
}