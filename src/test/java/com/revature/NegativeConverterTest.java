package com.revature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NegativeConverterTest extends ConverterParent{

    @Test
    @DisplayName("converter should give an error message if pascal given instead of camel for conversion to snake case")
    public void invalidSnakeConvertOne(){
        BadCaseException exception = Assertions.assertThrows(BadCaseException.class, () -> converter.snakeToCamel(pascalWord));
        Assertions.assertEquals("invalid input: expected snake case", exception.getMessage());
    }

    @Test
    @DisplayName("converter should give an error message if camel given instead of pascal for conversion to snake case")
    public void invalidSnakeConvertTwo(){
        BadCaseException exception = Assertions.assertThrows(BadCaseException.class, () -> converter.snakeToCamel(camelWord));
        Assertions.assertEquals("invalid input: expected snake case", exception.getMessage());
    }

    @Test
    @DisplayName("converter should give an error message if camel given instead of snake case for conversion to screaming snake case")
    public void invalidSnakeConvertThree(){
        BadCaseException exception = Assertions.assertThrows(BadCaseException.class, () -> converter.snakeToScreamingSnake(camelWord));
        Assertions.assertEquals("invalid input: expected snake case", exception.getMessage());
    }

}
