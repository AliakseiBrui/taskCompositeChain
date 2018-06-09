package com.epam.task3.parserchain;

import com.epam.task3.composite.TextComponent;
import com.epam.task3.composite.TextComposite;
import com.epam.task3.creator.TextLeafCreator;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.composite.TextCompositeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends DataParser {
    private static Logger logger = LogManager.getLogger(TextParser.class);
    private static final String SYMBOL_DELIMITER = "";
    private static final String PUNCTUATION_SIGN = "[.!?,\\-()\"\']}";
    private static TextLeafCreator textLeafCreator = new TextLeafCreator();

    public LexemeParser(){
        super(null);
    }

    public LexemeParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(String lexemeData, TextComposite lexemeComposite) throws WrongCompositeTypeException {

        if(!lexemeComposite.getType().equals(TextCompositeType.LEXEME)){
            throw new WrongCompositeTypeException("Expected LEXEME but found " + lexemeComposite.getType());
        }
        logger.debug("Parsing lexeme.");
        String[] symbolArray = lexemeData.split(SYMBOL_DELIMITER);

        for(String symbolData : symbolArray){

            if(!symbolData.isEmpty()) {
                TextComponent textLeaf;

                if (symbolData.matches(PUNCTUATION_SIGN)) {
                    textLeaf = textLeafCreator.createPunctuationSign(symbolData.charAt(0));
                } else {
                    textLeaf = textLeafCreator.createSymbol(symbolData.charAt(0));
                }
                lexemeComposite.add(textLeaf);
            }
        }
    }
}
