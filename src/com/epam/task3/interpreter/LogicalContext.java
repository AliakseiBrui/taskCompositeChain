package com.epam.task3.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class LogicalContext {
    private Deque<Integer> contextValues = new ArrayDeque<>();

    public Integer pop(){
        return contextValues.pop();
    }

    public void push(Integer value){
        contextValues.push(value);
    }
}
