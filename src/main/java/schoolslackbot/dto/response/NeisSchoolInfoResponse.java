package schoolslackbot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NeisSchoolInfoResponse {

    @JsonProperty(value = "ATPT_OFCDC_SC_CODE")
    private String atptCode;

    @JsonProperty(value = "ATPT_OFCDC_SC_NM")
    private String atptName;

    @JsonProperty(value = "SD_SCHUL_CODE")
    private String scCode;

    @JsonProperty(value = "SCHUL_NM")
    private String scName;

    @JsonProperty(value = "ENG_SCHUL_NM")
    private String scEngName;

    @JsonProperty(value = "SCHUL_KND_SC_NM")
    private String scKind;

    @JsonProperty(value = "LCTN_SC_NM")
    private String lctnName;

    @JsonProperty(value = "JU_ORG_NM")
    private String orgName;

    @JsonProperty(value = "FOND_SC_NM")
    private String fondName;

    @JsonProperty(value = "ORG_RDNZC")
    private String orgZipCode;

    @JsonProperty(value = "ORG_RDNMA")
    private String orgAddress;

    @JsonProperty(value = "ORG_RDNDA")
    private String orgDetailAddress;

    @JsonProperty(value = "ORG_TELNO")
    private String orgNumber;

    @JsonProperty(value = "HMPG_ADRES")
    private String homePage;

    @JsonProperty(value = "COEDU_SC_NM")
    private String coeducation;

    @JsonProperty(value = "ORG_FAXNO")
    private String faxNum;

    @JsonProperty(value = "HS_SC_NM")
    private String hsName;

    @JsonProperty(value = "INDST_SPECL_CCCCL_EXST_YN")
    private String indstClassYn;

    @JsonProperty(value = "HS_GNRL_BUSNS_SC_NM")
    private String gnrlName;

    @JsonProperty(value = "SPCLY_PURPS_HS_ORD_NM")
    private String spclyName;

    @JsonProperty(value = "ENE_BFE_SEHF_SC_NM")
    private String eneName;

    @JsonProperty(value = "DGHT_SC_NM")
    private String dghtName;

    @JsonProperty(value = "FOND_YMD")
    private String foundDate;

    @JsonProperty(value = "FOAS_MEMRD")
    private String anniversary;

    @JsonProperty(value = "LOAD_DTM")
    private String loadDate;
}
