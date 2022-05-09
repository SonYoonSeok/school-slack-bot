package schoolslackbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schoolslackbot.dto.MealMenu;
import schoolslackbot.dto.SchoolInfo;
import schoolslackbot.dto.response.FindSchoolResponse;
import schoolslackbot.dto.response.SchoolMenuResponse;
import schoolslackbot.util.RequestUtils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final SlackService slackService;

    private final RequestUtils requestUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final LocalDate now = LocalDate.now();

    @Value("${url.code}")
    private String codeUrl;

    @Value("${url.menu}")
    private String menuUrl;

    public void findSchoolMenu(String schoolName) throws Exception {
        SchoolMenuResponse schoolMenuInfo = null;
        SchoolInfo schoolInfo = getSchoolInfo(schoolName);
        String schoolType = getSchoolType(schoolName);
        String url = menuUrl + schoolType + "/" + schoolInfo.getCode() + "?year=" + now.getYear() + "&month=" + now.getMonth().getValue() + "&date=" + now.getDayOfMonth();

        schoolMenuInfo = getSchoolMenu(url);

        slackService.sendMessage(toSchoolMenuMessage(schoolName, schoolMenuInfo.getMenus().get(0)));
    }

    private String toSchoolMenuMessage(String schoolName, MealMenu menu) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- " + schoolName + "의 " + now.getYear() + "년 " + now.getMonth().getValue() + "월 " + now.getDayOfMonth() + "일 급식 ---\n");
        sb.append("아침\n");
        if (!menu.getBreakfast().isEmpty()) {
            for (String meal : menu.getBreakfast()) {
                meal = meal.split("-|[0-9]")[0];
                sb.append(" - " + meal + "\n");
            }
        } else {
            sb.append(" -   아침이 존재하지 않습니다!   - \n");
        }

        sb.append("\n점심\n");
        if (!menu.getLunch().isEmpty()) {
            for (String meal : menu.getLunch()) {
                meal = meal.split("-|[0-9]")[0];
                sb.append(" - " + meal + "\n");
            }
        } else {
            sb.append(" -   점심이 존재하지 않습니다!   - \n");
        }

        sb.append("\n저녁\n");
        if (!menu.getDinner().isEmpty()) {
            for (String meal : menu.getDinner()) {
                meal = meal.split("-|[0-9]")[0];
                sb.append(" - " + meal + "\n");
            }
        } else {
            sb.append(" -   저녁이 존재하지 않습니다!   - \n");
        }

        return sb.toString();
    }

    private SchoolInfo getSchoolInfo(String schoolName) throws Exception {
        String requestMethod = "GET";
        String response = requestUtils.httpRequest(codeUrl + schoolName, requestMethod);
        FindSchoolResponse list = objectMapper.readValue(response, FindSchoolResponse.class);

        return list.getSchoolInfoList().get(0);
    }

    public String getSchoolType(String schoolName) {
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

        return objectMapper.readValue(response, SchoolMenuResponse.class);
    }
}
