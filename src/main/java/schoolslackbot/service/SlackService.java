package schoolslackbot.service;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
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
    public CompletableFuture<WebhookResponse> sendMessage(String message) {
        CompletableFuture<WebhookResponse> future = new CompletableFuture<>();
        Slack slack = Slack.getInstance();
        try {
            Payload payload = Payload.builder()
                    .text(message)
                    .build();
            final WebhookResponse response = slack.send(webhookUrl, payload);
            future = CompletableFuture
                    .supplyAsync(() -> {
                        log.info("runAsync");
                        return response;
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return future;
    }
}
