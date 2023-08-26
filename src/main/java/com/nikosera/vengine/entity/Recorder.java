package com.nikosera.vengine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Bikash Shah
 */
@Entity
@Table(name = "RECORDER")
@Data
public class Recorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 22)
    private Long id;

    @Column(name="INITIATOR_ID", nullable = false)
    private Long initiatorId;

    @Column(name="RECEIVER_ID",nullable = false)
    private Long receiverId;

    @Column(name ="MEETING_ID")
    private String meetingId;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name="TOKEN")
    private String token;

    @Column(name="STATUS", nullable = false)
    private String status;

}
