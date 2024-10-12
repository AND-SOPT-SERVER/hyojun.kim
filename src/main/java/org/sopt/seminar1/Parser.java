package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

public class Parser {

    public static long stringToLong(String id){
        try{
            return Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new InvalidInputException();
        }

    }
}
