package com.nikosera.vengine.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
public class MeetingInitiateRequest {

    private Long initiatorId;
    private Long receiverId;
    private String type;

}
