package com.test.task3.app;

import com.epam.task3.action.TextAction;
import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.parserchain.LexemeParser;
import com.epam.task3.parserchain.ParagraphParser;
import com.epam.task3.parserchain.SentenceParser;
import com.epam.task3.parserchain.TextParser;
import com.epam.task3.reader.DataReader;
import com.epam.task3.composite.TextCompositeType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WholeAppTest {
    private static final DataReader dataReader = new DataReader();
    private static final String FILE_PATH = "data//source.txt";
    private static final TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser())));
    private static final TextAction textAction = new TextAction();
    private final TextComposite text = new TextComposite(TextCompositeType.TEXT);

    @Test
    public void testWholeApp(){
        List<String> dataList = dataReader.readData(FILE_PATH);
        StringBuilder data = new StringBuilder();

        for(String str : dataList){
            data.append(str);
        }

        try {
            textParser.parse(data.toString(),text);
            System.out.println(textAction.sortSentencesByLexemeLength(text));

        } catch (WrongCompositeTypeException e) {
            Assert.fail();
        }
        System.out.println(text);
    }
}
