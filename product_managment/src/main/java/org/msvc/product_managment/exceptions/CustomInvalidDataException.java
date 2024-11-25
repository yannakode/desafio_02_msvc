package org.msvc.product_managment.exceptions;

public class CustomInvalidDataException extends RuntimeException{
    public CustomInvalidDataException(String message) {
        super(message);
    }
}
