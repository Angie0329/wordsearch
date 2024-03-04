package co.edu.lasalle.wordsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MatrixComputationException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MatrixComputationException extends RuntimeException {

    public MatrixComputationException(String message) {
        super(message);
    }
}
