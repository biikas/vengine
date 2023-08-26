package com.nikosera.vengine.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class MeetingValidationRequest {

    private String meetingId;
    private String token;
}
