package com.epam.task3.composite;

import java.util.Objects;

public class Symbol implements TextComponent {
    private char character;
    private SymbolType symbolType;

    public Symbol(char character, SymbolType symbolType){
        this.character = character;
        this.symbolType = symbolType;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    @Override
    public String toString() {
        return Character.toString(character);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return character == symbol.character &&
                symbolType == symbol.symbolType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(character, symbolType);
    }
}
