package br.dev.drufontael.our_recipes_api.domain.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("Unauthorized user");
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

}
