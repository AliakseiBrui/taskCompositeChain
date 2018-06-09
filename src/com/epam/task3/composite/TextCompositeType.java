package com.epam.task3.composite;

public enum TextCompositeType {
    TEXT,
    PARAGRAPH("\n\t"),
    SENTENCE,
    LEXEME(" ");

    private String delimiter="";

    TextCompositeType(){}

    TextCompositeType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter(){
        return delimiter;
    }
}
