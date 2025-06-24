package com.revature.converter;

import com.revature.exception.BadCaseException;

public class ConverterImp implements Converter{
    @Override
    public String snakeToCamel(String word) {
        StringBuilder sb = new StringBuilder(word);
        boolean underscoreAdded = false;
        for(int i = 0; i < sb.length(); i++){
            if (sb.charAt(i) == '_'){
                sb.deleteCharAt(i);
                sb.setCharAt(i,Character.toUpperCase(sb.charAt(i)));
                underscoreAdded = true;
            }
        }
        if(underscoreAdded){
            return sb.toString();
        }
        throw new BadCaseException("invalid input: expected snake case");

    }

    @Override
    public String snakeToPascal(String word) {
        StringBuilder sb = new StringBuilder(word);
        boolean underscoreAdded = false;
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        for(int i = 0; i < sb.length(); i++){
            if (sb.charAt(i) == '_'){
                sb.deleteCharAt(i);
                sb.setCharAt(i,Character.toUpperCase(sb.charAt(i)));
                underscoreAdded = true;
            }
        }
        if (underscoreAdded){
            return sb.toString();
        }
        throw new BadCaseException("invalid case: expected snake case");
    }

    @Override
    public String snakeToScreamingSnake(String word) {
        for (char c: word.toCharArray()){
            if (c == '_'){
                return word.toUpperCase();              
            }
        
        }
        // some change along with script change and again
        throw new BadCaseException("invalid input: expected snake case");
    }

}
