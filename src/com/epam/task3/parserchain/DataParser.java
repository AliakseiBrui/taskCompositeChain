package com.epam.task3.parserchain;

import com.epam.task3.composite.TextComposite;
import com.epam.task3.exception.WrongCompositeTypeException;

public abstract class DataParser {
    private DataParser nextParser;

    DataParser(DataParser nextParser) {
        this.nextParser = nextParser;
    }

    public DataParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(DataParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(String data, TextComposite textComposite) throws WrongCompositeTypeException;
}
