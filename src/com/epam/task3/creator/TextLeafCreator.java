package com.epam.task3.creator;

import com.epam.task3.composite.Symbol;
import com.epam.task3.composite.SymbolType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextLeafCreator {
    private static Logger logger = LogManager.getLogger(TextLeafCreator.class);

    public Symbol createSymbol(char character){
        logger.debug("Creating character.");
        return new Symbol(character, SymbolType.CHARACTER);
    }

    public Symbol createPunctuationSign(char sign){
        logger.debug("Creating punctuation sign.");
        return new Symbol(sign,SymbolType.PUNCTUATION_SIGN);
    }
}
