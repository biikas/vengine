package com.nikosera.vengine.videosdkintegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikosera.vengine.dto.MeetingInfoDTO;
import com.nikosera.vengine.dto.request.MeetingValidationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bikash Shah
 */
@Slf4j
@Component
public class VideSdkIntegrations implements VideoSdkService {

    @Value("${videosdk.api-key}")
    private String apiKey;
    @Value("${videosdk.secret-key}")
    private String secretKey;
    @Value("${videosdk.api-endpoint}")
    private String apiEndpoint;
    private static final long TOKEN_EXPIRATION_DURATION_MS = 86400 * 1000; // 24 hours

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();



    @Override
    public String getToken() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("apikey", apiKey);
        claims.put("permissions", new String[]{"allow_join", "allow_mod"});

        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_DURATION_MS);

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        log.info("Token: {}", token);
        return token;
    }

    @Override
    public MeetingInfoDTO createMeeting(String token)  {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .uri(URI.create(apiEndpoint + "/api/meetings"))
                .setHeader("Authorization", token) // add request header
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                MeetingInfoDTO meetingInfoDTO = objectMapper.readValue(responseBody, MeetingInfoDTO.class);
                return meetingInfoDTO;
            }
        }catch(Exception e){
            log.info("Some error orccured");
        }
        return null;

    }

    @Override
    public MeetingInfoDTO validateMeeting(MeetingValidationRequest meetingValidationRequest) {
        String token = meetingValidationRequest.getToken();
        String meetingId = meetingValidationRequest.getMeetingId();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .uri(URI.create(apiEndpoint + "/api/meetings/"+meetingId))
                .setHeader("Authorization", token) // add request header
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                log.info("Got response {}", responseBody);
                MeetingInfoDTO meetingInfoDTO = objectMapper.readValue(responseBody, MeetingInfoDTO.class);
                return meetingInfoDTO;
            }
        }catch(Exception e){
            log.info("Some error orccured");
        }
        return null;

    }

    @Override
    public void meetingDetail(String token) {

    }
}
