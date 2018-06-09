package com.test.task3.interpreter;

import com.epam.task3.exception.LogicalExpressionException;
import com.epam.task3.interpreter.LogicalExpressionInterpreter;
import com.epam.task3.interpreter.LogicalExpressionParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class LogicalExpressionInterpreterTest {
    private static LogicalExpressionParser logicalExpressionParser = new LogicalExpressionParser();
    private static LogicalExpressionInterpreter logicalExpressionInterpreter = new LogicalExpressionInterpreter();
    private static final String EXPRESSION = "(3&4)^45|~2";
    private static final Integer RESULT = -3;
    private List<String> expressionList;

    @BeforeClass
    public void setUp(){

        try {
            expressionList = logicalExpressionParser.parse(EXPRESSION);
        } catch (LogicalExpressionException e) {
            Assert.fail();
        }
    }

    @Test
    public void testCalculate1(){

        Assert.assertNotNull(logicalExpressionInterpreter.calculate(expressionList));
    }

    @Test
    public void testCalculate2(){

        Assert.assertEquals(logicalExpressionInterpreter.calculate(expressionList), RESULT);
    }
}
