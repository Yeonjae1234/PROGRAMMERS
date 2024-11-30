import java.util.ArrayList;

public class Main {

    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        ArrayList<Integer> arr = new ArrayList<>();
        int termN = terms.length;
        int[][] termArr = new int[termN][2];
        for(int i=0;i<termN;i++){
            String[] st = terms[i].split(" ");
            termArr[i][0] = st[0].charAt(0);
            termArr[i][1] = Integer.parseInt(st[1]);
        }

        for(int i=0;i<privacies.length;i++){
            String[] p = privacies[i].split(" ");
            String[] date = p[0].split("\\.");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            char cterm = p[1].charAt(0);
            int termMonth = 0;
            for(int[] j:termArr){
                if(j[0]==cterm){
                    termMonth = j[1];
                    break;
                }
            }


            month+=termMonth;
            year += month/12;
            month %= 12;
            day -= 1;

            if(month==0){
                year-=1;
                month=12;
            }

            String sMonth ="";
            String sDay ="";
            if(month<10){
                sMonth="0"+month;
            }
            else{
                sMonth+=month;
            }
            if(day<10){
                sDay="0"+day;
            }
            else{
                sDay+=day;
            }
            String endDate =year+"."+sMonth+"."+sDay;
            if(today.compareTo(endDate)>0){
                arr.add(i+1);
            }

        }

        answer = new int[arr.size()];
        for(int i=0;i<arr.size();i++){
            answer[i] = arr.get(i);
        }

        return answer;
    }



    public static void main(String[] args) throws Exception {
    
       String[] terms = {"A 6", "B 12", "C 3"};
       String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
       int[] answer = solution("2022.05.19",terms,privacies);
       

    }
