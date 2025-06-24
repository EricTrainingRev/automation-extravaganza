package com.revature.converter;

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

    @Test
    @DisplayName("snake case should convert to screaming snake case")
    public void snakeToScreamingSnakeCaseTest(){
        Assertions.assertEquals("SNAKE_CASE", converter.snakeToScreamingSnake(snakeWord));
    }

}
