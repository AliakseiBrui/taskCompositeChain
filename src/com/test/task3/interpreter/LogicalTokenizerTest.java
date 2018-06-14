package com.test.task3.interpreter;

import com.epam.task3.interpreter.LogicalTokenizer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LogicalTokenizerTest {
    private static final String EXPRESSION = "(28&4)|9^~2>>4";
    private LogicalTokenizer logicalTokenizer;

    @BeforeMethod
    public void setUp(){
        logicalTokenizer = new LogicalTokenizer();
        logicalTokenizer.tokenize(EXPRESSION);
    }

    @Test
    public void hasTokenTest(){
        Assert.assertTrue(logicalTokenizer.hasToken());
    }

    @Test
    public void nextTokenTest(){
        Assert.assertNotNull(logicalTokenizer.nextToken());
    }

    @Test
    public void tokenizeTest(){
        List<String> expected = List.of("(","28","&","4",")","|","9","^","~","2",">>","4");
        List<String> actual = new ArrayList<>();
        
        while(logicalTokenizer.hasToken()){
            actual.add(logicalTokenizer.nextToken());
        }
        Assert.assertEquals(actual,expected);
    }


}
