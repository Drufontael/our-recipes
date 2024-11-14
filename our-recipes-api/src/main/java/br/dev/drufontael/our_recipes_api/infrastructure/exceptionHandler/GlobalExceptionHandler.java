package br.dev.drufontael.our_recipes_api.infrastructure.exceptionHandler;

import br.dev.drufontael.our_recipes_api.domain.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RattingOutOfRangeException.class)
    public ResponseEntity<?> rattingOutOfRangeException(RattingOutOfRangeException ex, WebRequest request) {
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExistsException(ResourceAlreadyExistsException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<?> unauthorizedUserException(UnauthorizedUserException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.UNAUTHORIZED.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> expiredJwtException(ExpiredJwtException ex,WebRequest request){
        ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.UNAUTHORIZED.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
