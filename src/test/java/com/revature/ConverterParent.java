package com.revature;

import org.junit.jupiter.api.BeforeEach;

public abstract class ConverterParent {

    protected Converter converter;
    protected String snakeWord;
    protected String camelWord;
    protected String pascalWord;

    @BeforeEach
    public void setup(){
        converter = new ConverterImp();
        snakeWord = "snake_case";
        camelWord = "camelCase";
        pascalWord = "PascalCase";
    }

}
