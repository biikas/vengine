package com.nikosera.vengine.controller;

import com.nikosera.vengine.config.VengineResponse;
import com.nikosera.vengine.dto.request.MeetingInitiateRequest;
import com.nikosera.vengine.dto.request.MeetingValidationRequest;
import com.nikosera.vengine.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bikash Shah
 */
@Slf4j
@RestController
@RequestMapping("meeting")
public class MeetingController {

    @Autowired
    private MeetingService  meetingService;

    @PostMapping(value = "/initiate")
    public ResponseEntity<?> initiate(@RequestBody MeetingInitiateRequest meetingInitiateRequest) {

        VengineResponse vengineResponse =meetingService.initializeMeeting(meetingInitiateRequest);
        return new ResponseEntity<>(vengineResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/validate")
    public ResponseEntity<?> initiate(@RequestBody MeetingValidationRequest meetingValidationRequest) {

        VengineResponse vengineResponse =meetingService.validateMeeting(meetingValidationRequest);
        return new ResponseEntity<>(vengineResponse, HttpStatus.OK);
    }


}
