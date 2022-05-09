package schoolslackbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NeisScheduleInfoRow {

    @JsonProperty(value = "ALL_TI_YMD")
    private String date;

    @JsonProperty(value = "GRADE")
    private String grade;

    @JsonProperty(value = "CLASS_NM")
    private String classRoom;

    @JsonProperty(value = "PERIO")
    private String period;

    @JsonProperty(value = "ITRT_CNTNT")
    private String subject;
}
