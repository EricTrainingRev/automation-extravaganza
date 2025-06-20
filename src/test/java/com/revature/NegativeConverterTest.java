package com.revature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NegativeConverterTest extends ConverterParent{

    @Test
    @DisplayName("converter should give an error message if pascal given instead of camel for conversion to snake case")
    public void invalidSnakeConvertOne(){
        BadCaseException exception = Assertions.assertThrows(BadCaseException.class, () -> converter.snakeToCamel(snakeWord));
        Assertions.assertEquals("invalid input: expected snake case", exception.getMessage());
    }

    @Test
    @DisplayName("converter should give an error message if camel given instead of pascal for conversion to snake case")
    public void invalidSnakeConvertTwo(){
        BadCaseException exception = Assertions.assertThrows(BadCaseException.class, () -> converter.snakeToCamel(snakeWord));
        Assertions.assertEquals("invalid input: expected snake case", exception.getMessage());
    }

    @Test
    @DisplayName("converter should give an error message if camel given instead of snake case for conversion to screaming snake case")
    @Disabled("implementing next cycle")
    public void invalidSnakeConvertThree(){
        // TODO: implement next cycle
    }

}
