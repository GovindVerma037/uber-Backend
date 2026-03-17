package com.project.uber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

     private String issue;
     private String agentName;
     private String agentResponse;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;

    @ManyToOne
    User user;

    public enum TicketType{
        SOS,
        FARE_ISSUE,
        PAYMENT_ISSUE,
        DRIVER_BEHAVIOUR,
        OTHER
    }

    public enum TicketStatus{
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }
    public enum TicketPriority{
        HIGH,
        MEDIUM,
        LOW
    }
    @PrePersist
    protected  void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
