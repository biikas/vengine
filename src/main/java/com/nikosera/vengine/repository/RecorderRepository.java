package com.nikosera.vengine.repository;

import com.nikosera.vengine.entity.Recorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecorderRepository extends JpaRepository<Recorder, Long> {

    @Query("select t from Recorder t where t.meetingId=:meetingId")
    Optional<Recorder> findByMeetingId(String meetingId);

}
