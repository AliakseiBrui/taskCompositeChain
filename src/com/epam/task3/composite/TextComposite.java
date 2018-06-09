package com.epam.task3.composite;

import java.util.LinkedList;

public class TextComposite implements TextComponent {
    private LinkedList<TextComponent> componentList = new LinkedList<>();
    private TextCompositeType type;

    public TextComposite(TextCompositeType type) {
        this.type = type;
    }

    public TextCompositeType getType() {
        return type;
    }

    public boolean add(TextComponent textComponent){
        return componentList.add(textComponent);
    }

    public LinkedList<TextComponent> getComponentList(){
        return componentList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(type.getDelimiter());

        for(TextComponent component : componentList){
            result.append(component.toString());
        }
        return result.toString();
    }
}
