package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    Ticket save(Ticket ticket);
}
