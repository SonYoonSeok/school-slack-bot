package schoolslackbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstDate {

    @JsonProperty(value = "y")
    private String year;

    @JsonProperty(value = "m")
    private String month;

    @JsonProperty(value = "d")
    private String day;
}
