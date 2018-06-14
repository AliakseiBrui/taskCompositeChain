package com.epam.task3.interpreter;

import java.util.function.Consumer;

class LogicalOperationCalculator {

    void calculateOperation(LogicalContext context, Consumer<LogicalContext> contextConsumer){
        contextConsumer.accept(context);
    }
}
