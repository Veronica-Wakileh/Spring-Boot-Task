package org.exalt.training.springboottask.dto;

import org.exalt.training.springboottask.model.TicketPriority;

public record TicketRequest(TicketPriority ticketPriority, String ticketTitle, Long ticketRequester, Long ticketAssignedAgent) {}
