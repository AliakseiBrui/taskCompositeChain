package com.epam.task3.parserchain;

import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.composite.TextCompositeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends DataParser {
    private static Logger logger = LogManager.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLITTER = "(?=(\\p{Blank}{4,}))";

    public TextParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(String textData, TextComposite textComposite) throws WrongCompositeTypeException {
        if(!textComposite.getType().equals(TextCompositeType.TEXT)){
            throw new WrongCompositeTypeException("Expected TEXT but found " + textComposite.getType());
        }
        logger.debug("Parsing text.");
        String[] paragraphArray = textData.split(PARAGRAPH_SPLITTER);

        for(String paragraphData : paragraphArray){
                TextComposite paragraph = new TextComposite(TextCompositeType.PARAGRAPH);
                textComposite.add(paragraph);
                getNextParser().parse(paragraphData, paragraph);
        }
    }
}
