package com.amazon.sele.customException;

import com.amazon.sele.reporterLogger.ReportFactory;

/* Class Decription - Class define Framework's own custom exception which can be thrown when File extension is not matched.    
 * Created by - Shekhar Kapil
 * Created on - 16th April
 * Modified by
 * Modified on
 * */
@SuppressWarnings("serial")
public class ZipCreationException extends Exception { 
	
    public ZipCreationException(String errorMessage) {
        super("\nError while creating ZIP file for folder :  "+errorMessage);
        ReportFactory.getInstance().error("Error while creating ZIP file for folder : "+errorMessage);
    }
}