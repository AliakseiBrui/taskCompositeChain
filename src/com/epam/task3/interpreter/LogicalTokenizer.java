package com.epam.task3.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LogicalTokenizer {
    private Queue<String> queue = new ArrayDeque<>();
    private static final String DIGIT = "\\p{Digit}+";
    private static final String DELIMITER ="(?=(>{2}|<{2}|\\(|\\)|&|\\||\\^|~|\\d+))";

    public String nextToken(){
        return queue.poll();
    }

    public boolean hasToken(){
        return !queue.isEmpty();
    }

    public void tokenize(String expression){
        queue.clear();
        ArrayList<String> tokenList=new ArrayList<>(List.of(expression.split(LogicalTokenizer.DELIMITER)));

        for(int i =0;i<tokenList.size();i++){

            if(tokenList.get(i).matches(DIGIT)){

                if(i+1<tokenList.size() && tokenList.get(i+1).matches(DIGIT)){
                    String currentDigit = tokenList.get(i);
                    String nextDigit = tokenList.get(i+1);
                    tokenList.remove(i);
                    tokenList.remove(i);
                    tokenList.add(i,currentDigit+nextDigit);
                    i--;
                }
            }
        }
        queue.addAll(tokenList);
    }

    @Override
    public String toString() {
        return "LogicalTokenizer{" +
                "queue=" + queue +
                '}';
    }
}
