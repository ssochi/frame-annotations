package org.ssochi.fa.core.exceptions;

public class FARunningTimeException extends RuntimeException{
    public FARunningTimeException(String message){
        super(message);
    }
    public FARunningTimeException(String message,Object ... objects){
        super(String.format(message,objects));
    }
}
