package schoolslackbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import schoolslackbot.dto.MealMenu;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolMenuResponse {

    @JsonProperty("menu")
    private List<MealMenu> menus;

    @JsonProperty("server_message")
    private List<String> serverMessage;
}
