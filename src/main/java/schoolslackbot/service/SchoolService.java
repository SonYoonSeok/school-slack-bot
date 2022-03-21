package schoolslackbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schoolslackbot.dto.SchoolInfoDto;
import schoolslackbot.dto.response.FindSchoolResponse;
import schoolslackbot.dto.response.SchoolMenuResponse;
import schoolslackbot.util.RequestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final RequestUtils requestUtils;
    private final ThreadLocal<String> schoolCode = new ThreadLocal<>();

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String codeUrl = "https://schoolmenukr.ml/code/api?q=";

    private String requestMethod = "";

    public SchoolMenuResponse findSchoolMenu(String schoolName) throws Exception {
        SchoolMenuResponse response = null;
        SchoolInfoDto schoolInfo = getSchoolInfo(schoolName);
        String schoolType = getSchoolType(schoolName);

        return response;
    }

    private SchoolInfoDto getSchoolInfo(String schoolName) throws Exception {
        Map<String, String> map;
        FindSchoolResponse list;
        SchoolInfoDto schoolInfo;

        requestMethod = "GET";
        String response = requestUtils.httpRequest(codeUrl + schoolName, requestMethod);
        list = objectMapper.readValue(response, FindSchoolResponse.class);
        schoolInfo = list.getSchoolInfoList().get(0);

        System.out.println(schoolInfo.toString());
        return schoolInfo;
    }

    private String getSchoolType(String schoolName) {
        if (schoolName.contains("초등학교")) {
            return "elementary";
        } else if (schoolName.contains("중학교")) {
            return "middle";
        } else {
            return "high";
        }
    }
}
