package br.dev.drufontael.our_recipes_api.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
