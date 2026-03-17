package com.project.uber.services;

import com.project.uber.entity.Ticket;
import com.project.uber.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportService {
    @Autowired
    private SupportRepository supportRepository;


    public Ticket createTicket(Ticket ticket) {
        ticket.setStatus(Ticket.TicketStatus.OPEN);
        // set priority

        if (ticket.getType() == Ticket.TicketType.SOS) {
            ticket.setPriority(Ticket.TicketPriority.HIGH);
        } else if (ticket.getType() == Ticket.TicketType.FARE_ISSUE || ticket.getType() == Ticket.TicketType.PAYMENT_ISSUE) {
            ticket.setPriority(Ticket.TicketPriority.MEDIUM);
        } else {
            ticket.setPriority(Ticket.TicketPriority.LOW);
        }

        return supportRepository.save(ticket);
    }

    public List<Ticket> getUserTickets(Long userId) {
        return supportRepository.findByUserId(userId);
    }

    public Ticket.TicketStatus getTicketStatus(Long ticketId) {
        return supportRepository.findById(ticketId).get().getStatus();
    }
}
