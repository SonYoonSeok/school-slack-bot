package schoolslackbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SchoolInfo {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "estDivision")
    private String estDivision;

    @JsonProperty(value = "estType")
    private String estType;

    @JsonProperty(value = "estDate")
    private EstDate estDate;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "website")
    private String website;

    @JsonProperty(value = "address")
    private String address;
}
