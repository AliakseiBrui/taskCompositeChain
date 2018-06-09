package com.test.task3.action;

import com.epam.task3.action.TextAction;
import com.epam.task3.composite.TextComposite;
import com.epam.task3.composite.TextCompositeType;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.parserchain.LexemeParser;
import com.epam.task3.parserchain.ParagraphParser;
import com.epam.task3.parserchain.SentenceParser;
import com.epam.task3.parserchain.TextParser;
import com.epam.task3.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TextActionTest {
    private final DataReader dataReader = new DataReader();
    private final TextAction textAction = new TextAction();
    private final TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser())));
    private final TextComposite text = new TextComposite(TextCompositeType.TEXT);
    private static final String FILE_PATH = "data//source.txt";

    @BeforeClass
    public void setUp(){
        List<String> dataList = dataReader.readData(FILE_PATH);
        StringBuilder data = new StringBuilder();

        for(String str : dataList){
            data.append(str);
        }

        try {
            textParser.parse(data.toString(),text);
        } catch (WrongCompositeTypeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sortParagraphsBySentenceNumberTest1(){

        try {
            Assert.assertNotNull(textAction.sortParagraphsBySentenceNumber(text));
        } catch (WrongCompositeTypeException e) {
            Assert.fail();
        }
    }

    @Test(expectedExceptions = WrongCompositeTypeException.class)
    public void sortParagraphsBySentenceNumberTest2() throws WrongCompositeTypeException {

        TextComposite wrongTextComposite = new TextComposite(TextCompositeType.SENTENCE);
        textAction.sortParagraphsBySentenceNumber(wrongTextComposite);
    }

    @Test
    public void sortSentencesByLexemeLengthTest1(){

        try {
            Assert.assertNotNull(textAction.sortSentencesByLexemeLength(text));
        } catch (WrongCompositeTypeException e) {
            Assert.fail();
        }
    }

    @Test(expectedExceptions = WrongCompositeTypeException.class)
    public void sortSentencesByLexemeLengthTest2() throws WrongCompositeTypeException {

        TextComposite wrongTextComposite = new TextComposite(TextCompositeType.LEXEME);
        textAction.sortSentencesByLexemeLength(wrongTextComposite);
    }

    @Test
    public void sortLexemesBySymbolUsagesTest1(){

        try {
            Assert.assertNotNull(textAction.sortLexemesBySymbolUsages(text,'b'));
        } catch (WrongCompositeTypeException e) {
            Assert.fail();
        }
    }

    @Test(expectedExceptions = WrongCompositeTypeException.class)
    public void sortLexemeBySymbolUsagesTest2() throws WrongCompositeTypeException {

        TextComposite wrongTextComposite = new TextComposite(TextCompositeType.PARAGRAPH);
        textAction.sortLexemesBySymbolUsages(wrongTextComposite,'a');
    }
}
