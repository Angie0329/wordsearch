package co.edu.lasalle.wordsearch.service.impl;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.lasalle.wordsearch.dto.WordSearchQuery;
import co.edu.lasalle.wordsearch.dto.WordSearchQueryResponse;
import co.edu.lasalle.wordsearch.exception.MatrixComputationException;
import co.edu.lasalle.wordsearch.exception.MatrixValidationException;
import co.edu.lasalle.wordsearch.service.WordSearchService;

/**
 * WordSearchServiceImpl
 */
@Service
public class WordSearchServiceImpl implements WordSearchService {

    @Override
    public char[][] computeMatrix(String targetString, int numRows)
        throws MatrixComputationException, MatrixValidationException {
        final int strLength = targetString.length();
        final int numCols = (int) Math.ceil((double) strLength / numRows);

        char[][] matrix = new char[numRows][numCols];
        int index = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (index < strLength) {
                    matrix[i][j] = targetString.charAt(index++);
                } else {
                    matrix[i][j] = '-';
                }
            }
        }

        return matrix;
    }

    @Override
    public ResponseEntity<WordSearchQueryResponse> searchWordHorizontalAndVertical(
            WordSearchQuery query)
        throws MatrixValidationException, MatrixComputationException  {
        validateSearchWordComposition(query);
        final String targetString = query.getSearchWord();
        final int numRows = query.getRows();
        final String word = query.getWord();
        final char[][] sourceMatrix = computeMatrix(targetString, numRows);
        final WordSearchQueryResponse response = WordSearchQueryResponse
            .builder()
            .wordSearch(query.getSearchWord())
            .rows(query.getRows())
            .word(query.getWord())
            .contains(false)
            .startCol(null)
            .startRow(null)
            .build();

        final int rows = sourceMatrix.length;
        final int cols = sourceMatrix[0].length;

        // horizontal searching
        for (int i = 0; i < rows; i++) {
            String row = new String(sourceMatrix[i]);
            if (row.contains(word)) {
                response.setStartRow(i);
                response.setStartCol(row.indexOf(word));
                response.setContains(true);

                return ResponseEntity.ok(response);
            }
        }

        // vertical searching
        for (int j = 0; j < cols; j++) {
            StringBuilder col = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                col.append(sourceMatrix[i][j]);
            }
            if (col.toString().contains(word)) {
                response.setStartRow(col.indexOf(word));
                response.setStartCol(j);
                response.setContains(true);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WordSearchQueryResponse> searchWordDiagonally(
            WordSearchQuery query)
        throws MatrixValidationException, MatrixComputationException {
        final String targetString = query.getSearchWord();
        final int numRows = query.getRows();
        final String word = query.getWord();
        final char[][] sourceMatrix = computeMatrix(targetString, numRows);
        final WordSearchQueryResponse response = WordSearchQueryResponse
            .builder()
            .wordSearch(query.getSearchWord())
            .rows(query.getRows())
            .word(query.getWord())
            .contains(false)
            .startCol(null)
            .startRow(null)
            .build();

        final int rows = sourceMatrix.length;
        final int cols = sourceMatrix[0].length;

        // Buscar diagonalmente hacia abajo y hacia la derecha
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; i + k < rows && j + k < cols; k++) {
                    diagonal.append(sourceMatrix[i + k][j + k]);
                }
                if (diagonal.toString().contains(word)) {
                    int startRow = i;
                    int startCol = j;
                    for (int k = 0; k < diagonal.length(); k++) {
                        if (diagonal.substring(k).startsWith(word)) {
                            response.setStartRow(startRow + k);
                            response.setStartCol(startCol + k);
                            response.setContains(true);
                            return ResponseEntity.ok(response);
                        }
                    }
                }
            }
        }

        // Buscar diagonalmente hacia arriba y hacia la derecha
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                StringBuilder diagonal = new StringBuilder();
                for (int k = 0; i - k >= 0 && j + k < cols; k++) {
                    diagonal.append(sourceMatrix[i - k][j + k]);
                }
                if (diagonal.toString().contains(word)) {
                    int startRow = i;
                    int startCol = j;
                    for (int k = 0; k < diagonal.length(); k++) {
                        if (diagonal.substring(k).startsWith(word)) {
                            response.setStartRow(startRow - k);
                            response.setStartCol(startCol + k);
                            response.setContains(true);
                            return ResponseEntity.ok(response);
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public void validateSearchWordComposition(WordSearchQuery query)
        throws MatrixValidationException {
        final String searchWord = query.getSearchWord();
        final long rows = query.getRows();
        final String word = query.getWord();

        if(Objects.isNull(searchWord) || searchWord.isBlank()) {
            throw new MatrixValidationException(
                    "The Search Word body is not optional, you must provide it"
                    );
        }

        if(rows <= 0) {
            throw new MatrixValidationException(
                    "The number of rows must be greater than zero (0)"
                    );
        }

        if(Objects.isNull(word) || searchWord.isBlank()) {
            throw new MatrixValidationException(
                    "The word to be search is not optional, you must provide it"
                    );
        }
    }

}
