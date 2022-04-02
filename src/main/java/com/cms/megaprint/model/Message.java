package com.cms.megaprint.model;


import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @SequenceGenerator(name = "messagesHS", sequenceName = "messages_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messagesHS")
    private Long id;

    @Column(name = "senderName")
    private String senderName;

    @Column(name = "senderEmail")
    private String senderEmail;

    @Column(name = "text")
    private String text;

    @Column(name = "unread")
    private boolean unread;

    @Column(name = "marked")
    private boolean marked;

    @Column(name = "sendingTime")
    private ZonedDateTime sendingTime;

    @Column(name = "archived")
    private boolean archived;

}
