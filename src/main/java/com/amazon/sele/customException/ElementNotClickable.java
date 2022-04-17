package com.amazon.sele.customException;

/* Class Decription - Class define Framework's own custom exception which can be thrown when File extension is not matched.    
 * Created by - Shekhar Kapil
 * Created on - 16th April
 * Modified by
 * Modified on
 * */
@SuppressWarnings("serial")
public class ElementNotClickable extends Exception { 
	
    public ElementNotClickable(String errorMessage) {
        super("\n"+errorMessage + " Not Clickable");
    }
}