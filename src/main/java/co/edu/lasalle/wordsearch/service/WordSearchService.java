package co.edu.lasalle.wordsearch.service;

import org.springframework.http.ResponseEntity;

import co.edu.lasalle.wordsearch.dto.WordSearchQuery;
import co.edu.lasalle.wordsearch.dto.WordSearchQueryResponse;
import co.edu.lasalle.wordsearch.exception.MatrixComputationException;
import co.edu.lasalle.wordsearch.exception.MatrixValidationException;

/**
 * WordSearchService
 */
public interface WordSearchService {

    char[][] computeMatrix(String targetString, int numRows)
            throws MatrixComputationException;
    void validateSearchWordComposition(WordSearchQuery query)
            throws MatrixValidationException;
    ResponseEntity<WordSearchQueryResponse> searchWordHorizontalAndVertical(
            WordSearchQuery query) throws MatrixValidationException, MatrixComputationException;
    ResponseEntity<WordSearchQueryResponse> searchWordDiagonally(
            WordSearchQuery query) throws MatrixValidationException, MatrixComputationException ;
}
