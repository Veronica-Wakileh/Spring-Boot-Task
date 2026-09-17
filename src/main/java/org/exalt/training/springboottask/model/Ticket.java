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
    private Long Id;

    @Column
    private String ticketTitle;

    @Column
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private User ticketRequester;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private User ticketAssignedAgent;

    @Column
    private LocalDateTime dueAt;

}
