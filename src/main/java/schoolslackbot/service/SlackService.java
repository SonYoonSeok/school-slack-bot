package schoolslackbot.service;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackService {

    @Value("${url.webhook}")
    private String webhookUrl;

    public WebhookResponse sendMessage(String message) {
        WebhookResponse response = null;
        Slack slack = Slack.getInstance();

        try {
            Payload payload = Payload.builder()
                    .text(message)
                    .build();
            response = slack.send(webhookUrl, payload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
