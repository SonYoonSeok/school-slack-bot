package schoolslackbot.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schoolslackbot.dto.NeisSchoolInfoRow;
import schoolslackbot.dto.response.NeisSchoolInfoResponse;
import schoolslackbot.util.RequestUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final SlackService slackService;

    private final RequestUtils requestUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${url.neis.school.info}")
    private String schoolInfoUrl;

    @Value("${neis.api.code}")
    private String neisCode;

    public void findSchoolSchedule(String schoolName) throws Exception {
        NeisSchoolInfoRow schoolInfo = getSchoolInfo(schoolName);
    }

    private NeisSchoolInfoRow getSchoolInfo(String schoolName) throws Exception {
        String requestMethod = "GET";
        String scName = URLEncoder.encode(schoolName, StandardCharsets.UTF_8);
        String url = schoolInfoUrl + scName;
        String response = requestUtils.httpRequest(url, requestMethod);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        NeisSchoolInfoResponse schoolInfo = objectMapper.readValue(response, NeisSchoolInfoResponse.class);

        return schoolInfo.getSchoolInfo()
                .get(1)
                .getRow()
                .get(0);
    }
}
