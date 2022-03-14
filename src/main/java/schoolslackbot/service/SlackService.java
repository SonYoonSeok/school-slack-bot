package schoolslackbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    @Value(value = "${slack.token}")
    private String token;

    @Value(value = "${slack.channel.monitor}")
    private String channel;
}
