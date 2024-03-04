package co.edu.lasalle.wordsearch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.lasalle.wordsearch.dto.WordSearchQuery;
import co.edu.lasalle.wordsearch.dto.WordSearchQueryResponse;
import co.edu.lasalle.wordsearch.exception.MatrixComputationException;
import co.edu.lasalle.wordsearch.exception.MatrixValidationException;
import co.edu.lasalle.wordsearch.service.WordSearchService;
import lombok.AllArgsConstructor;

/**
 * WordSearchController
 */
@RestController
@RequestMapping("/api/v1/wordsearch")
@AllArgsConstructor
public class WordSearchController {

    private WordSearchService wordSearchService;

    @PostMapping("/search-horandvert")
    public ResponseEntity<WordSearchQueryResponse> searchWordHorizontalAndVertical(
            @RequestBody WordSearchQuery query)
            throws MatrixValidationException, MatrixComputationException {
        return wordSearchService.searchWordHorizontalAndVertical(query);
    }

    @PostMapping("/search")
    public ResponseEntity<WordSearchQueryResponse> searchWordDiagonally(
            @RequestBody WordSearchQuery query)
            throws MatrixValidationException, MatrixComputationException {
        return wordSearchService.searchWordDiagonally(query);
    }
}
