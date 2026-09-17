package org.exalt.training.springboottask.service;

import lombok.AllArgsConstructor;
import org.exalt.training.springboottask.dto.TicketRequest;
import org.exalt.training.springboottask.dto.TicketRequestUpdateStatus;
import org.exalt.training.springboottask.exception.OperationNotProcessedException;
import org.exalt.training.springboottask.exception.TicketIdMismatchException;
import org.exalt.training.springboottask.exception.TicketNotFoundException;
import org.exalt.training.springboottask.model.Ticket;
import org.exalt.training.springboottask.model.TicketPriority;
import org.exalt.training.springboottask.model.TicketStatus;
import org.exalt.training.springboottask.repository.TicketRepository;
import org.exalt.training.springboottask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    public Ticket getTicketByID(Long Id) {

        return ticketRepository.findById(Id).orElseThrow(() -> {throw new TicketNotFoundException("Ticket Not Found !");});

    }

    public Ticket createNewTicket(TicketRequest ticketRequest){

        Ticket ticket = new Ticket();
        ticket.setTicketPriority(ticketRequest.ticketPriority());
        ticket.setTicketTitle(ticketRequest.ticketTitle());
        ticket.setTicketRequester(userRepository.getReferenceById(ticketRequest.ticketRequester()));
        ticket.setTicketStatus(TicketStatus.OPEN);

        LocalDateTime now = LocalDateTime.now();

        if (ticket.getTicketPriority() == TicketPriority.HIGH) ticket.setDueAt(now.plusHours(4));
        else if (ticket.getTicketPriority() == TicketPriority.MEDIUM) ticket.setDueAt(now.plusHours(24));
        else if (ticket.getTicketPriority() == TicketPriority.LOW) ticket.setDueAt(now.plusHours(72));

        return ticketRepository.save(ticket);
    }

    public Ticket updateExistingTicket(Ticket ticket, Long Id){

        if (!(ticket.getId().equals(Id))) {
            throw new TicketIdMismatchException("Ticket Id Doesn't match the Id provided in the request");
        }
        ticketRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Updating a Non Existing Ticket Can't be Processed");});
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicketStatus(Long Id, TicketRequestUpdateStatus status){

        Ticket ticket = ticketRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Updating a Non Existing Ticket Can't be Processed");});

        if (ticket.getTicketStatus() == TicketStatus.OPEN && status.ticketStatus()!= TicketStatus.IN_PROGRESS){
            throw new OperationNotProcessedException("OPEN Ticket's Status can be only Changed to IN_PROGRESS");
        }

        if (ticket.getTicketStatus() == TicketStatus.IN_PROGRESS && status.ticketStatus() != TicketStatus.RESOLVED){
            throw new OperationNotProcessedException("IN_PROGRESS Ticket's Status can be only Changed to RESOLVED");
        }

        if (ticket.getTicketStatus() == TicketStatus.RESOLVED && status.ticketStatus() != TicketStatus.CLOSED){
            throw new OperationNotProcessedException("RESOLVED Ticket's Status can be only Changed to CLOSED");
        }

        if (ticket.getTicketStatus() == TicketStatus.CLOSED){
            throw new OperationNotProcessedException("CLOSED Ticket's Status Can't be Changed");
        }

        ticket.setTicketStatus(status.ticketStatus());
        return ticketRepository.save(ticket);
    }

    public void deleteExistingTicket(Long Id){

        ticketRepository.findById(Id).orElseThrow(() -> {throw new OperationNotProcessedException("Deleting a Non Existing Ticket Can't be Processed");});
        ticketRepository.deleteById(Id);
    }

}


