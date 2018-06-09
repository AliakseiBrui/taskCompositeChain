package com.epam.task3.parserchain;

import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.LogicalExpressionException;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.interpreter.LogicalExpressionInterpreter;
import com.epam.task3.composite.TextCompositeType;
import com.epam.task3.interpreter.LogicalExpressionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends DataParser {
    private static final String LEXEME_DELIMITER = "\\p{Blank}+";
    private static final String OPERATOR = "(>{2}|<{2}|&|\\^|\\||~)";
    private static final String PUNCTUATION_SIGN = "[.?!;,:…]";
    private static final Pattern OPERATOR_PATTERN;
    private static final Pattern LEXEME_PATTERN;
    private static final Pattern PUNCTUATION_PATTERN;
    private static Logger logger = LogManager.getLogger(SentenceParser.class);
    private static LogicalExpressionParser logicalExpressionParser = new LogicalExpressionParser();
    private static LogicalExpressionInterpreter logicalExpressionInterpreter = new LogicalExpressionInterpreter();


    static {
        OPERATOR_PATTERN = Pattern.compile(OPERATOR);
        LEXEME_PATTERN = Pattern.compile(LEXEME_DELIMITER);
        PUNCTUATION_PATTERN = Pattern.compile(PUNCTUATION_SIGN);
    }

    public SentenceParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(String sentenceData, TextComposite sentenceComposite) throws WrongCompositeTypeException {

        if(!sentenceComposite.getType().equals(TextCompositeType.SENTENCE)){
            throw new WrongCompositeTypeException("Expected SENTENCE but found " + sentenceComposite.getType());
        }
        logger.debug("Parsing sentence.");
        String[] lexemeArray = LEXEME_PATTERN.split(sentenceData);

        for(String lexemeData : lexemeArray){
            Matcher operatorMatcher = OPERATOR_PATTERN.matcher(lexemeData);
            String afterLexeme = "";

            if(operatorMatcher.find()){
                Matcher punctuationMatcher = PUNCTUATION_PATTERN.matcher(lexemeData);

                if(punctuationMatcher.find()){
                    afterLexeme = punctuationMatcher.group();
                    lexemeData = punctuationMatcher.replaceAll("");
                }
                List<String> expressionList;

                try {
                    expressionList = logicalExpressionParser.parse(lexemeData);
                    lexemeData = logicalExpressionInterpreter.calculate(expressionList) + afterLexeme;
                } catch (LogicalExpressionException e) {
                    throw new RuntimeException(e);
                }
            }
            TextComposite lexeme = new TextComposite(TextCompositeType.LEXEME);
            sentenceComposite.add(lexeme);
            getNextParser().parse(lexemeData,lexeme);
        }
    }
}
