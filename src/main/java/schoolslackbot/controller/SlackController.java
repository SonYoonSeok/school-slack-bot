package schoolslackbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolslackbot.dto.SlashCommandsRequest;
import schoolslackbot.service.SchoolService;
import schoolslackbot.service.SlackService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SlackController {

    private final SlackService slackService;
    private final SchoolService schoolService;

    @PostMapping("/school")
    public void enrollSchool(SlashCommandsRequest request) {
        log.info("POST /school request: {}", request);

    }

    @PostMapping("/time")
    public void enrollTime(SlashCommandsRequest request) {
        log.info("POST /time request: {}", request);

    }
}
