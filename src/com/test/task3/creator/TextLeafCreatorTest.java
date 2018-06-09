package com.test.task3.creator;

import com.epam.task3.composite.Symbol;
import com.epam.task3.composite.SymbolType;
import com.epam.task3.creator.TextLeafCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextLeafCreatorTest {
    private TextLeafCreator textLeafCreator = new TextLeafCreator();

    @Test
    public void createPunctuationSignTest1(){
        Assert.assertNotNull(textLeafCreator.createPunctuationSign('.'));
    }

    @Test
    public void createPunctuationSignTest2(){
        Assert.assertEquals(new Symbol(',',SymbolType.PUNCTUATION_SIGN), textLeafCreator.createPunctuationSign(','));
    }

    @Test
    public void createSymbolTest1(){
        Assert.assertNotNull(textLeafCreator.createSymbol('a'));
    }

    @Test
    public void createSymbolTest2(){
        Assert.assertEquals(new Symbol('b', SymbolType.CHARACTER),textLeafCreator.createSymbol('b'));
    }
}
