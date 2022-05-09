package schoolslackbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import schoolslackbot.dto.NeisScheduleInfo;

import java.util.List;

@Getter
@ToString
public class NeisSchoolScheduleResponse {

    @JsonProperty(value = "elsTimetable")
    private List<NeisScheduleInfo> elsSchedules;

    @JsonProperty(value = "misTimetable")
    private List<NeisScheduleInfo> misSchedules;

    @JsonProperty(value = "hisTimetable")
    private List<NeisScheduleInfo> hisSchedules;
}
