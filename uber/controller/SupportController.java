package com.project.uber.controller;


import com.project.uber.entity.Ticket;
import com.project.uber.services.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    SupportService supportService;

    @PostMapping("/report")
    public Ticket reportIssue(@RequestBody Ticket ticket){
        return supportService.createTicket(ticket);
    }
    @GetMapping("/{userId}")
    public List<Ticket> getUserTickets(@PathVariable Long userId){
        return supportService.getUserTickets(userId);
    }
    @GetMapping("/{ticketId}")
    public Ticket.TicketStatus getTicketStatus(@PathVariable Long ticketId){
        return supportService.getTicketStatus(ticketId);
    }

}
