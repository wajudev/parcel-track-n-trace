package at.fhtw.swen3.services;
public class BLException extends Exception {
    public BLException(){
        super();
    }
    public BLException(String message, Throwable cause){
        super(message,cause);
    }

    public BLException(String message){
        super(message);
    }
    public BLException(Throwable cause){
        super(cause);
    }
}
