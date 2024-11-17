package org.msvc.product_managment.service.exceptions;

public class CustomBadRequestException  extends RuntimeException{
    public CustomBadRequestException(Throwable cause) {
        super(cause);
    }

    public CustomBadRequestException(String message) {
        super(message);
    }
}
