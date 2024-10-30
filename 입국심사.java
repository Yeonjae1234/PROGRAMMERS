import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        int maxTesterTime = Arrays.stream(times).max().getAsInt();
        long left=0;
        long right=(long)n*maxTesterTime;
        long answer = right;
        while(left<=right){
            long mid=(left+right)/2;
            long sum=0;
            for(int i:times) {
                sum +=mid/i;
                if(sum>=n) break;
            }
            if(sum<n){
                left=mid+1;
            }
            else{
                answer=mid;
                right=mid-1;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws Exception{
        Solution s = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();

        // 공백을 기준으로 첫 번째 숫자와 배열 부분을 분리
        String[] parts = input.split(" ", 2);

        // 첫 번째 값은 int로 변환
        int firstNumber = Integer.parseInt(parts[0]);

        // 배열 부분을 정리하고 숫자로 변환
        String arrayPart = parts[1].replaceAll("[\\[\\],]", ""); // 대괄호 및 쉼표 제거
        int[] array = Arrays.stream(arrayPart.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(s.solution(firstNumber,array));
    }
}

