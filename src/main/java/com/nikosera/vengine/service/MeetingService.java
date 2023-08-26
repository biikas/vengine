package com.nikosera.vengine.service;

import com.nikosera.vengine.config.VengineResponse;
import com.nikosera.vengine.dto.request.MeetingInitiateRequest;
import com.nikosera.vengine.dto.request.MeetingValidationRequest;

/**
 * @author Bikash Shah
 */
public interface MeetingService {

    VengineResponse initializeMeeting(MeetingInitiateRequest meetingInitiateRequest);

    VengineResponse validateMeeting(MeetingValidationRequest meetingValidationRequest);

}
