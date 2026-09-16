package org.exalt.training.springboottask.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketTitle;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;

    @ManyToOne
    private User ticketRequester;

    @ManyToOne
    private User ticketAssignedAgent;

    private LocalDateTime dueAt;





}
