package com.revature.converter;

import org.junit.jupiter.api.BeforeEach;

import com.revature.converter.Converter;
import com.revature.converter.ConverterImp;

public abstract class ConverterParent {

    protected Converter converter;
    protected String snakeWord;
    protected String camelWord;
    protected String pascalWord;
    protected String screamingSnakeWord;

    @BeforeEach
    public void setup(){
        converter = new ConverterImp();
        snakeWord = "snake_case";
        camelWord = "camelCase";
        pascalWord = "PascalCase";
        screamingSnakeWord = "SCREAMING_SNAKE_CASE";
    }

}
