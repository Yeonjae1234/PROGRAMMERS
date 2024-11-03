public class Main{

    public int solution(String word){
        int answer =0;
        find(0,word);
        answer = count;
        return answer;
    }
    int count=0;
    boolean end=false;
    char[] chArr = {'A','E','I','O','U'};
    char[] make = new char[5];
    public int find(int digit,String word) {
        if (digit > 4) return 0;

        for(char ch:chArr) {
            if (end) break;
            make[digit]=ch;
            count++;
            if (word.equals(String.valueOf(make,0,digit+1))) {//문자열 비교
                end = true;
                return 0;
            }
            find(digit + 1, word);
        }
        make[digit]='\0';
        return 0;
    }


    public static void main(String[] args) throws Exception{
        Main main = new Main();
        System.out.println(main.solution("AAAE"));

    }
}

