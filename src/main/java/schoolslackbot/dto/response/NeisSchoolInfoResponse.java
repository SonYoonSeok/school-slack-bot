package schoolslackbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import schoolslackbot.dto.NeisSchoolInfo;

import java.util.List;

@Getter
@ToString
public class NeisSchoolInfoResponse {

    @JsonProperty(value = "schoolInfo")
    private List<NeisSchoolInfo> schoolInfo;
}
