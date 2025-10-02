class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] strArr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i = 0; i < strArr.length; i++){
            s = s.replaceAll(strArr[i], Integer.toString(i));
        }
        
        answer = Integer.parseInt(s);
        return answer;
    }
}