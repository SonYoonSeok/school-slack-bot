package schoolslackbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MealMenu {

    @JsonProperty("date")
    private String date;

    @JsonProperty("breakfast")
    private List<String> breakfast;

    @JsonProperty("lunch")
    private List<String> lunch;

    @JsonProperty("dinner")
    private List<String> dinner;
}
