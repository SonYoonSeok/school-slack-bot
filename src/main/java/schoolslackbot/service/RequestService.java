package schoolslackbot.service;

import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RequestService {

    public static String httpRequest(String targetUrl) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(targetUrl);
            connection
        }
    }
}
