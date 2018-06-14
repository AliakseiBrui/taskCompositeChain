package com.epam.task3.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

class LogicalContext {
    private Deque<Integer> contextValues = new ArrayDeque<>();

    Integer pop(){
        return contextValues.pop();
    }

    void push(Integer value){
        contextValues.push(value);
    }
}
