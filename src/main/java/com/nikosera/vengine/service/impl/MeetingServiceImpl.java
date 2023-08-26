package com.nikosera.vengine.service.impl;

import com.nikosera.vengine.config.VengineResponse;
import com.nikosera.vengine.config.VengineResponseMapper;
import com.nikosera.vengine.dto.MeetingInfoDTO;
import com.nikosera.vengine.dto.request.MeetingInitiateRequest;
import com.nikosera.vengine.dto.request.MeetingValidationRequest;
import com.nikosera.vengine.dto.response.MeetingInitiatorResponse;
import com.nikosera.vengine.entity.Recorder;
import com.nikosera.vengine.enums.MeetingStatusEnums;
import com.nikosera.vengine.repository.RecorderRepository;
import com.nikosera.vengine.service.MeetingService;
import com.nikosera.vengine.service.mapper.MeetingMapper;
import com.nikosera.vengine.videosdkintegration.VideoSdkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bikash Shah
 */
@Slf4j
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private VideoSdkService videoSdkService;
    @Autowired
    private RecorderRepository recorderRepository;


    @Override
    public VengineResponse initializeMeeting(MeetingInitiateRequest meetingInitiateRequest) {

        Recorder recorder = MeetingMapper.convertToInitialRecord(meetingInitiateRequest);

        String token = videoSdkService.getToken();
        MeetingInfoDTO meetingInfoDTO = new MeetingInfoDTO();
        try {
            meetingInfoDTO = videoSdkService.createMeeting(token);
            recorder.setToken(token);
            recorder.setMeetingId(meetingInfoDTO.getMeetingId());
        } catch (Exception e) {
            log.error("Some Error Occurred while connecting to videosdk service", e);
        }
        recorderRepository.save(recorder);

        MeetingInitiatorResponse meetingInitiatorResponse = new MeetingInitiatorResponse(meetingInfoDTO.getMeetingId(), token);
        return VengineResponseMapper.convertToSuccessVengineResponse("Meeting initiated successfully", meetingInitiatorResponse);
    }

    @Override
    public VengineResponse validateMeeting(MeetingValidationRequest meetingValidationRequest) {

        Optional<Recorder> recorder =  recorderRepository.findByMeetingId(meetingValidationRequest.getMeetingId());
        if(recorder.isPresent()){
            MeetingInfoDTO meetingInfoDTO = videoSdkService.validateMeeting(meetingValidationRequest);
            recorder.get().setStatus(MeetingStatusEnums.VALIDATED.name());
            recorderRepository.save(recorder.get());
            return VengineResponseMapper.convertToSuccessVengineResponse("Meeting Validated successfully");
        }
        return VengineResponseMapper.convertToFailureVengineResponse("Meeting Id Not found");
    }
}
