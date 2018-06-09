package com.epam.task3.interpreter;

import java.util.function.Consumer;

public class LogicalOperationCalculator {

    public void calculateOperation(LogicalContext context, Consumer<LogicalContext> contextConsumer){
        contextConsumer.accept(context);
    }
}
