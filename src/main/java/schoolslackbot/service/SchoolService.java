package schoolslackbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schoolslackbot.dto.SchoolInfoDto;
import schoolslackbot.dto.response.FindSchoolResponse;
import schoolslackbot.dto.response.SchoolMenuResponse;
import schoolslackbot.util.RequestUtils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final RequestUtils requestUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final LocalDate now = LocalDate.now();

    @Value("${url.code}")
    private String codeUrl;

    @Value("${url.menu}")
    private String menuUrl;

    public SchoolMenuResponse findSchoolMenu(String schoolName) throws Exception {

        SchoolMenuResponse schoolMenuInfo = null;
        SchoolInfoDto schoolInfo = getSchoolInfo(schoolName);
        String schoolType = getSchoolType(schoolName);
        String url = menuUrl + schoolType + "/" + schoolInfo.getCode() + "?year=" + now.getYear() + "&month=" + now.getMonth().getValue() + "&date=" + now.getDayOfMonth();

        schoolMenuInfo = getSchoolMenu(url);

        System.out.println(schoolMenuInfo.toString());
        return schoolMenuInfo;
    }

    private SchoolInfoDto getSchoolInfo(String schoolName) throws Exception {
        String requestMethod = "GET";
        String response = requestUtils.httpRequest(codeUrl + schoolName, requestMethod);
        FindSchoolResponse list = objectMapper.readValue(response, FindSchoolResponse.class);
        SchoolInfoDto schoolInfo = list.getSchoolInfoList().get(0);

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

    private SchoolMenuResponse getSchoolMenu(String url) throws Exception {
        String requestMethod = "GET";
        String response = requestUtils.httpRequest(url, requestMethod);
        SchoolMenuResponse schoolMenuInfo = objectMapper.readValue(response, SchoolMenuResponse.class);

        System.out.println(schoolMenuInfo.toString());
        return schoolMenuInfo;
    }
}
