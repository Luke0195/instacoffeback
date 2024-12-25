package br.com.instacoffe.app.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
