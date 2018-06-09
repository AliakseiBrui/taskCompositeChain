package com.epam.task3.action;

import com.epam.task3.composite.Symbol;
import com.epam.task3.composite.TextComponent;
import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.WrongCompositeTypeException;
import com.epam.task3.composite.TextCompositeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextAction {
    private static Logger logger = LogManager.getLogger(TextAction.class);

    public List<TextComponent> sortParagraphsBySentenceNumber(TextComposite text) throws WrongCompositeTypeException {

        if(!text.getType().equals(TextCompositeType.TEXT)){
            throw new WrongCompositeTypeException("Expected TEXT but found " + text.getType());
        }
        logger.debug("Sorting text by sentence number.");
        List<TextComponent> paragraphList = new ArrayList<>(text.getComponentList());
        paragraphList.sort(Comparator.comparingInt(p -> ((TextComposite) p).getComponentList().size()));
        return paragraphList;
    }

    public List<TextComponent> sortSentencesByLexemeLength(TextComposite text) throws WrongCompositeTypeException{

        if(!text.getType().equals(TextCompositeType.TEXT)){
            throw new WrongCompositeTypeException("Expected TEXT but found " + text.getType());
        }
        List<TextComponent> sentenceList = new ArrayList<>();
        logger.debug("Sorting sentences by lexeme length.");

        for(TextComponent paragraph : text.getComponentList()){
            sentenceList.addAll(((TextComposite) paragraph).getComponentList());
        }
        sentenceList.sort(Comparator.comparing(s -> findLongestLexeme(((TextComposite) s))));
        return sentenceList;
    }

    public List<TextComponent> sortLexemesBySymbolUsages(TextComposite text, char symbol) throws WrongCompositeTypeException{

        if(!text.getType().equals(TextCompositeType.TEXT)){
            throw new WrongCompositeTypeException("Expected TEXT but found " + text.getType());
        }
        List<TextComponent> lexemeList = new ArrayList<>();
        logger.debug("Sorting lexemes by symbol usages.");

        for(TextComponent paragraph : text.getComponentList()){

            for(TextComponent sentence : ((TextComposite) paragraph).getComponentList()){
                lexemeList.addAll(((TextComposite) sentence).getComponentList());
            }
        }
        lexemeList.sort(Comparator.comparingInt(l -> getNumberOfSymbolUsages(((TextComposite) l), symbol))
                .thenComparing(Object::toString)
                .reversed());
        return lexemeList;
    }

    private String findLongestLexeme(TextComposite sentence){
        String longestLexeme = "";
        int length=-999;

        for(TextComponent lexeme : sentence.getComponentList()){

            if(lexeme.toString().length()>length){
                longestLexeme = lexeme.toString();
                length = lexeme.toString().length();
            }
        }
        return longestLexeme;
    }

    private int getNumberOfSymbolUsages(TextComposite lexeme, char symbol){
        int numberOfSymbolUsages = 0;
        String lexemeData = lexeme.toString();

        for(int i=0;i<lexemeData.length();i++){

            if(lexemeData.charAt(i)==symbol){
                numberOfSymbolUsages++;
            }
        }
        return numberOfSymbolUsages;
    }
}
