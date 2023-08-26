package com.nikosera.vengine.videosdkintegration;

import com.nikosera.vengine.dto.MeetingInfoDTO;
import com.nikosera.vengine.dto.request.MeetingValidationRequest;

/**
 * @author Bikash Shah
 */
public interface VideoSdkService {

    String getToken();

    MeetingInfoDTO createMeeting(String token) ;

    MeetingInfoDTO validateMeeting(MeetingValidationRequest meetingValidationRequest);

    void meetingDetail(String token);
}
