package co.edu.lasalle.wordsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WordSeachQueryDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordSearchQuery {

    @JsonProperty("searchword")
    private String searchWord;
    private int rows;
    private String word;

}
