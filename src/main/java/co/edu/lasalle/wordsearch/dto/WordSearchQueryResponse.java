package co.edu.lasalle.wordsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WordSearchQueryResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordSearchQueryResponse {

    @JsonProperty("wordsearch")
    private String wordSearch;
    private long rows;
    private String word;
    private boolean contains;
    @JsonProperty("start_row")
    private long startRow;
    @JsonProperty("start_col")
    private long startCol;
}
