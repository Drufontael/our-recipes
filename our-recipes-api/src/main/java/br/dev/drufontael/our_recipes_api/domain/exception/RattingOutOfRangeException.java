package br.dev.drufontael.our_recipes_api.domain.exception;

public class RattingOutOfRangeException extends RuntimeException {

    public RattingOutOfRangeException() {
        super("Rating must be between 1 and 5");
    }
}
