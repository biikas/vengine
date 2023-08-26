package com.nikosera.vengine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Bikash Shah
 */
@NoArgsConstructor
@Getter
@Setter
public class MeetingInfoDTO {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("meetingId")
    private String meetingId;
    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("webhook")
    private WebhookDTO webhook;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("autoCloseConfig")
    private AutoCloseConfigDTO autoCloseConfig;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("id")
    private String id;

    @NoArgsConstructor
    @Data
    public static class WebhookDTO {
        @JsonProperty("events")
        private List<?> events;
    }

    @NoArgsConstructor
    @Data
    public static class AutoCloseConfigDTO {
        @JsonProperty("type")
        private String type;
    }
}
