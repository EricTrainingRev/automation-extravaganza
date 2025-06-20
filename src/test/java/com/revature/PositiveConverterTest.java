package com.revature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositiveConverterTest extends ConverterParent{

    @Test
    @DisplayName("snake case should convert to camel case")
    public void snakeToCamelPositiveTest(){
        String result = converter.snakeToCamel(snakeWord);
        Assertions.assertEquals("snakeCase", result);
    }

    @Test
    @DisplayName("snake case should convert to pascal case")
    public void snakeToPascalPositiveTest(){
        Assertions.assertEquals("SnakeCase", converter.snakeToPascal(snakeWord));
    }

}
