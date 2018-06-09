package com.epam.task3.parserchain;

import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.composite.TextCompositeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class ParagraphParser extends DataParser {
    private static Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String SENTENCE_DELIMITER = "(?<=([\\p{Alnum}()][.!?…]))\\s*";
    private static final Pattern SENTENCE_PATTERN;

    static {
        SENTENCE_PATTERN = Pattern.compile(SENTENCE_DELIMITER);
    }

    public ParagraphParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(String paragraphData, TextComposite paragraphComposite) throws WrongCompositeTypeException {

        if(!paragraphComposite.getType().equals(TextCompositeType.PARAGRAPH)){
            throw new WrongCompositeTypeException("Expected PARAGRAPH but found " + paragraphComposite.getType());
        }
        logger.debug("Parsing paragraph.");
        String[] sentenceArray = SENTENCE_PATTERN.split(paragraphData);

        for(String sentenceData : sentenceArray){
            TextComposite sentence = new TextComposite(TextCompositeType.SENTENCE);
            paragraphComposite.add(sentence);
            getNextParser().parse(sentenceData,sentence);
        }
    }
}
