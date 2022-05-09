package schoolslackbot.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schoolslackbot.dto.NeisScheduleInfoRow;
import schoolslackbot.dto.NeisSchoolInfoRow;
import schoolslackbot.dto.response.NeisSchoolInfoResponse;
import schoolslackbot.dto.response.NeisSchoolScheduleResponse;
import schoolslackbot.util.RequestUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final SlackService slackService;
    private final MenuService menuService;

    private final RequestUtils requestUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${url.neis.school}")
    private String schoolInfoUrl;

    @Value("${url.schedule}")
    private String scheduleUrl;

    @Value("${neis.api.code}")
    private String neisCode;

    public void findSchoolSchedule(String text) throws Exception {
        String[] texts = text.split(" ");
        String schoolName = texts[0];
        String sno = texts[1];

        NeisSchoolInfoRow schoolInfo = getSchoolInfo(schoolName);
        String schoolType = menuService.getSchoolType(schoolInfo.getScName());
        List<NeisScheduleInfoRow> scheduleInfo = getSchoolScheduleList(schoolInfo.getAtptCode(), schoolInfo.getScCode(), schoolType, sno);
        System.out.println(scheduleInfo);
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

    private List<NeisScheduleInfoRow> getSchoolScheduleList(String atptCode, String schulCode, String schoolType, String sno) throws Exception {
        String requestMethod = "GET";
        String url = scheduleUrl;
        String type;

        if (schoolType.equals("elementary")) {
            type = "elsTimetable";
        } else if (schoolType.equals("middle")) {
            type = "misTimetable";
        } else {
            type = "hisTimetable";
        }
        url += type + "?Type=json&KEY=" + neisCode + "&ATPT_OFCDC_SC_CODE=" + atptCode + "&SD_SCHUL_CODE=" + schulCode + "&GRADE=" + sno.charAt(0) + "&CLASS_NM=" + sno.charAt(1);
        String response = requestUtils.httpRequest(url, requestMethod);
        System.out.println(url);
        System.out.println(response);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        NeisSchoolScheduleResponse scheduleInfo = objectMapper.readValue(response, NeisSchoolScheduleResponse.class);
        return getScheduleByType(scheduleInfo, type);
    }

    private List<NeisScheduleInfoRow> getScheduleByType(NeisSchoolScheduleResponse response, String type) {
        System.out.println(response);
        if (type.equals("elsTimetable")) {
            return response.getElsSchedules()
                    .get(1)
                    .getRow();
        } else if (type.equals("misTimetable")) {
            return response.getMisSchedules()
                    .get(1)
                    .getRow();
        } else {
            return response.getHisSchedules()
                    .get(1)
                    .getRow();
        }
    }
}
