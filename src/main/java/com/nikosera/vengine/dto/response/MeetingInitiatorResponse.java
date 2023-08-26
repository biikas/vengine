package com.nikosera.vengine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bikash Shah
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingInitiatorResponse {

    private String meetingId;
    private String token;
}
