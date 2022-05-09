package schoolslackbot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String httpRequest(String targetUrl, String reqMethod) throws Exception {
        HttpURLConnection connection;
        BufferedReader br = null;
        StringBuffer sb;
        String response;
        String returnData = "";

        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(reqMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            sb = new StringBuffer();
            while ((response = br.readLine()) != null) {
                sb.append(response);
            }

            returnData = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return returnData;
    }

    public Map<String, String> jsonToMap(String jsonData) {

        Map<String, String> map = new HashMap<>();
        try {
            map = objectMapper.readValue(jsonData, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
