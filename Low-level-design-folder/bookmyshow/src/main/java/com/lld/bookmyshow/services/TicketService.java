package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.exceptions.ShowNotFoundException;
import com.lld.bookmyshow.models.*;
import com.lld.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    ShowRepository showRepository;
    SeatRepository seatRepository;
    Show_Seat_Repository showSeatRepository;
    SeatLockService seatLockService;
    UserRepository userRepository;
    TicketRepository ticketRepository;

    // This service will handle the business logic for ticket booking, cancellation, etc.
    // It will interact with the repository layer to perform CRUD operations on Ticket entities.
    @Autowired
    public TicketService(ShowRepository showRepository, SeatRepository seatRepository,
                         Show_Seat_Repository showSeatRepository,
                         SeatLockService seatLockService,
                         UserRepository userRepository,
                         TicketRepository ticketRepository) {
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.seatLockService = seatLockService;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    // Example method to book a ticket
    public Ticket bookTicket(Long showId, List<Long> seatIds, Long userId) throws ShowNotFoundException, IllegalArgumentException, SeatNotAvailableException {

        /*
    Things to do :
    1. Get the show details from the repository using the showId, check if the show exists or not
       - If the show does not exist or is not active, throw an exception or return an error response.
    1.B Get all the seats from the seat repository using the seatIds
       - If the seats do not exist, throw an exception or return an error response.
    2. Check if the seats are available for that particular show using show and seats from Show_seat repository
       - If the seats are not available, throw an exception or return an error response.
    3. If available, lock the ticket
    4. Create a ticket object and save it to the database
     */


        Optional<Show> showOptional = showRepository.findById(showId);

        if(showOptional.isEmpty()){
            // Show not found, handle accordingly (e.g., throw an exception)
            throw new ShowNotFoundException("Show with ID " + showId + " not found.");
        }

        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);

        if(seats.isEmpty()){
            // No seats found with the provided IDs, handle accordingly
            throw new IllegalArgumentException("No seats found with the provided IDs.");
        }

        List<Show_Seat> showSeatsList =seatLockService.getAndLockSeats(seats, showOptional.get());

        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            // User not found, handle accordingly (e.g., throw an exception)
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }

        Ticket ticket = new Ticket();
        ticket.setShow(showOptional.get());
        ticket.setUser(userOptional.get());
        ticket.setAmount(100);
        ticket.setTicketStatus(TicketStatus.CONFIRMED);
        ticket.setSeats(seats);
        ticket.setPaymentMode(PaymentMode.CREDIT_CARD);
        ticket.setStartTime(new Date());

        return ticketRepository.save(ticket);
    }
}

