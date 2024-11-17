class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int x=0,y=0;

        for(int i=yellow;i>=1;i--){
            if(yellow%i==0){
                x=i;
                y=yellow/i;
                if((x+y)*2+4==brown) break;
            }
        }

        answer = new int[] {x+2,y+2};

        return answer;
    }
}
