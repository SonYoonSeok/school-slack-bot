package schoolslackbot.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SlashCommandsRequest {
    private String token;
    private String team_id;
    private String team_domain;
    private String enterprise_id;
    private String enterprise_name;
    private String user_id;
    private String user_name;
    private String command;
    private String text;
    private String response_url;
    private String trigger_id;
    private String api_app_id;
}
