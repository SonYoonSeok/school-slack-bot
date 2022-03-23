package schoolslackbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolslackbot.dto.request.SlashCommandsRequest;
import schoolslackbot.service.SchoolService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SlackController {

    private final SchoolService schoolService;

    @PostMapping("/meal")
    public void getMealPlanner(SlashCommandsRequest request) throws Exception {
        log.info("POST /meal request: {}", request);
        schoolService.findSchoolMenu(request.getText());
    }

    @PostMapping("/schedule")
    public void getSchedule(SlashCommandsRequest request) {
        log.info("POST /schedule request: {}", request);

    }
}
