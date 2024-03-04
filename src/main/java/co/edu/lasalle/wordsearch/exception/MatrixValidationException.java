package co.edu.lasalle.wordsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MatrixValidationException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MatrixValidationException extends RuntimeException {

    public MatrixValidationException(String message) {
        super(message);
    }
}
