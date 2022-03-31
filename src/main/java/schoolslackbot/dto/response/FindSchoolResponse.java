package schoolslackbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import schoolslackbot.dto.SchoolInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindSchoolResponse {

    @JsonProperty(value = "server_message")
    private List<String> serverMessage;

    @JsonProperty(value = "school_infos")
    private List<SchoolInfo> schoolInfoList;
}
