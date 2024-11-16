import java.util.ArrayList;


public class Main {

    public static int[] solution(int[] answers) {
        ArrayList<Integer> student = new ArrayList<>();
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        int max=-1;

        int[] score = new int[3];
        score[0] = score(one,answers);
        score[1] = score(two,answers);
        score[2] = score(three,answers);

        for(int i=0;i<3;i++){
            if(max<score[i]){
                student.clear();
                student.add(i+1);
                max=score[i];
            }
            else if(max==score[i]){
                student.add(i+1);
            }
        }

        int[] x = new int[student.size()];
        for(int i=0;i<student.size();i++){
            x[i]= student.get(i);
        }

        return x;
    }

    public static int score(int[] student, int[] answers){
        int n = student.length;
        int score = 0;
        for(int i = 0; i< answers.length; i++){
            if(answers[i]==student[i%n]) score++;
        }
        return score;
    }


    public static void main(String[] args) throws Exception{
        int[] answers = {1,3,2,4,2};
        int[] print = solution(answers);
    }
}
