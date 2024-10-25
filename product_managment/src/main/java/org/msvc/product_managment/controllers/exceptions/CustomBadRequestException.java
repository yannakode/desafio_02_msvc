package org.msvc.product_managment.controllers.exceptions;

public class CustomBadRequestException  extends RuntimeException{
    public CustomBadRequestException(Throwable cause) {
        super(cause);
    }
}
