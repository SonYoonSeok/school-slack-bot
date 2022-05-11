package schoolslackbot.service;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class SlackService {

    @Value("${url.webhook}")
    private String webhookUrl;

    @Async
    public void sendMessage(String message) {
        CompletableFuture
                .runAsync(() -> {
                    Slack slack = Slack.getInstance();
                    Payload payload = Payload.builder()
                            .text(message)
                            .build();
                    try {
                        slack.send(webhookUrl, payload);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("runAsync");
                });
    }
}
