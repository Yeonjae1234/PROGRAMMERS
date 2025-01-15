// https://school.programmers.co.kr/learn/courses/30/lessons/72410?language=java

import java.io.*;
import java.util.*;

public class Main {

    public static String solution(String new_id){

        StringBuilder sb = new StringBuilder();

        // 1
        new_id=new_id.toLowerCase();

        //2
        String rule = "0123456789-_.";
        Deque<Character> deque_id = new LinkedList<>();
        Deque<Character> temp_deque = new LinkedList<>();

        for(char i:new_id.toCharArray()){
            if(i>=97&&i<=122){
                deque_id.add(i);
            }
            else if(rule.contains(i+"")){
                deque_id.add(i);
            }
        }

        //3
        char char_temp;
        while(!deque_id.isEmpty()){
            char_temp=deque_id.poll();
            if(temp_deque.isEmpty()){
                temp_deque.add(char_temp);
                continue;
            }
            else if(char_temp=='.'&&temp_deque.peekLast()=='.') continue;
            temp_deque.add(char_temp);
        }
        deque_id=new LinkedList<>(temp_deque);
        temp_deque.clear();


        //4
        if (!deque_id.isEmpty()&&deque_id.peek() == '.') {
            deque_id.poll();
        }
        if (!deque_id.isEmpty()&&deque_id.peekLast() == '.') {
            deque_id.pollLast();
        }

        //5
        if(deque_id.isEmpty()){
            deque_id.add('a');
        }

        //6
        if(deque_id.size()>15){
            for(int i=0;i<15;i++){
                temp_deque.add(deque_id.poll());
            }
            if(temp_deque.peekLast()=='.'){
                temp_deque.pollLast();
            }
            deque_id=new LinkedList<>(temp_deque);
            temp_deque.clear();
        }

        //7
        while(deque_id.size()<=2){
            deque_id.add(deque_id.peekLast());
        }

        while(!deque_id.isEmpty()){
            sb.append(deque_id.poll());
        }

        return sb.toString();
    }




    public static void main(String[] args) throws Exception {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String new_id = "abcdefghijklmn.p";

        bw.write(solution(new_id));

        bw.flush();
        bw.close();

    }
}
