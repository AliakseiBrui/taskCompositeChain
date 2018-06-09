package com.epam.task3.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LogicalExpressionInterpreter {
    private LogicalContext context = new LogicalContext();
    private static LogicalOperationCalculator logicalOperationCalculator = new LogicalOperationCalculator();
    private static Logger logger = LogManager.getLogger(LogicalExpressionInterpreter.class);

    public Integer calculate(List<String> expressionList){
        logger.debug("Calculating logical expression: "+expressionList);

        for(String expression : expressionList){

            switch (expression) {
                case ">>":
                    logicalOperationCalculator.calculateOperation(context,c->{
                        int b = c.pop();
                        int a = c.pop();
                        c.push(a >> b);
                    });
                    break;
                case "<<":
                    logicalOperationCalculator.calculateOperation(context,c->{
                        int b = c.pop();
                        int a = c.pop();
                        c.push(a << b);
                    });
                    break;
                case "|":
                    logicalOperationCalculator.calculateOperation(context,c->c.push(c.pop() | c.pop()));
                    break;
                case "&":
                    logicalOperationCalculator.calculateOperation(context,c->c.push(c.pop() & c.pop()));
                    break;
                case "^":
                    logicalOperationCalculator.calculateOperation(context,c->c.push(c.pop() ^ c.pop()));
                    break;
                case "~":
                    logicalOperationCalculator.calculateOperation(context,c->c.push(~c.pop()));
                    break;
                default:
                    context.push(Integer.valueOf(expression.trim()));
            }
        }
        return context.pop();
    }
}
