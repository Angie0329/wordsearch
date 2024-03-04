package co.edu.lasalle.wordsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class WordSearchQueryResponse {

    @JsonProperty("wordsearch")
    private String wordSearch;
    private long rows;
    private String word;
    private boolean contains;
    @JsonProperty("start_row")
    private Integer startRow;
    @JsonProperty("start_col")
    private Integer startCol;
}
