package com.nikosera.vengine.service.mapper;

import com.nikosera.vengine.dto.request.MeetingInitiateRequest;
import com.nikosera.vengine.dto.response.MeetingInitiatorResponse;
import com.nikosera.vengine.entity.Recorder;
import com.nikosera.vengine.enums.MeetingStatusEnums;

import java.util.Date;

/**
 * @author Bikash Shah
 */
public class MeetingMapper {

    public static Recorder convertToInitialRecord(MeetingInitiateRequest meetingInitiateRequest) {
        Recorder recorder = new Recorder();
        recorder.setInitiatorId(meetingInitiateRequest.getInitiatorId());
        recorder.setReceiverId(meetingInitiateRequest.getReceiverId());
        recorder.setCreatedDate(new Date());
        recorder.setStatus(MeetingStatusEnums.INITIATED.name());
        return recorder;
    }

}
