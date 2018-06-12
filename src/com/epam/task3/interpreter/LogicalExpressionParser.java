package com.epam.task3.interpreter;

import com.epam.task3.exception.LogicalExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LogicalExpressionParser {
    private static final String DELIMITER = "(>{2}|<{2}|\\(|\\)|&|\\||\\^|~)";
    private static final String OPERATOR = "(>{2}|<{2}|&|\\||\\^|~)";
    private static Logger logger = LogManager.getLogger(LogicalExpressionParser.class);


    public List<String> parse(String infix) throws LogicalExpressionException {
        logger.debug("Parsing logical expression " + infix + ".");
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        LogicalTokenizer tokenizer = new LogicalTokenizer();
        tokenizer.tokenize(infix);
        String currentToken;

        while (tokenizer.hasToken()) {
            currentToken = tokenizer.nextToken();
            currentToken = currentToken.trim();

            if (!tokenizer.hasToken() && isOperator(currentToken)) {
                throw new LogicalExpressionException("Incorrect expression.");
            }

            if (" ".equals(currentToken)) {
                continue;
            }else if (isDelimiter(currentToken)) {

                if ("(".equals(currentToken)) {
                    stack.push(currentToken);
                } else if (")".equals(currentToken)) {

                    while (!"(".equals(stack.peek())) {
                        postfix.add(stack.pop());

                        if (stack.isEmpty()) {
                            throw new LogicalExpressionException("Brackets are not consistent.");
                        }
                    }
                    stack.pop();

                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                } else {

                    while (!stack.isEmpty() && (priority(currentToken) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                    }
                    stack.push(currentToken);
                }
            } else {
                postfix.add(currentToken);
            }
        }

        while (!stack.isEmpty()) {

            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            } else {
                throw new LogicalExpressionException("Brackets are not consistent.");
            }
        }
        return postfix;
    }

    private boolean isDelimiter(String token) {
        return token.matches(DELIMITER);
    }

    private boolean isOperator(String token) {
        return token.matches(OPERATOR);
    }

    private int priority(String token) {
        int priority;

        switch (token){

            case "(":
                priority = 1;
                break;

            case "|":
                priority = 2;
                break;

            case "^":
                priority = 3;
                break;

            case "&":
                priority = 4;
                break;

            case ">>":
                priority = 5;
                break;

            case "<<":
                priority = 5;
                break;

                default:
                    priority = 6;

        }
        return priority;
    }
}