package de.home_skrobanek.fnum.exception;

public class IllegalMathOperationException extends Exception{

    public IllegalMathOperationException(){
        super("This operation is illegal or not supported by this library");
    }
}
