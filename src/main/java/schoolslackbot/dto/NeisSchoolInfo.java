package schoolslackbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NeisSchoolInfo {

    @JsonProperty(value = "row")
    private List<NeisSchoolInfoRow> row;
}
