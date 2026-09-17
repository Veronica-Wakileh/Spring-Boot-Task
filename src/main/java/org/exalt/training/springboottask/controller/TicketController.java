package org.exalt.training.springboottask.controller;

import lombok.AllArgsConstructor;
import org.exalt.training.springboottask.dto.TicketRequest;
import org.exalt.training.springboottask.dto.TicketRequestUpdateStatus;
import org.exalt.training.springboottask.model.Ticket;
import org.exalt.training.springboottask.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/select")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/select/{Id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Ticket findOneTicket(@PathVariable Long Id) {
        return ticketService.getTicketByID(Id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTicket(@RequestBody TicketRequest ticketRequest) {
        return "Ticket Created Successfully with Id: " + ticketService.createNewTicket(ticketRequest);
    }

    @PutMapping("/update/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String updateProject(@RequestBody Ticket ticket, @PathVariable Long Id) {

        ticketService.updateExistingTicket(ticket, Id);
        return "Ticket Updated Successfully !";
    }

    @PatchMapping("/update/status/{Id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateTicketStatus(@PathVariable Long Id, @RequestBody TicketRequestUpdateStatus status) {

        ticketService.updateTicketStatus(Id, status);
        return "Ticket Status Updated Successfully !";
    }

    @DeleteMapping("/delete/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long Id) {
        ticketService.deleteExistingTicket(Id);

    }

}
