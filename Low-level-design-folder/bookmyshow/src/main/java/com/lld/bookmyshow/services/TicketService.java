package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.exceptions.ShowNotFoundException;
import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.Show_Seat;
import com.lld.bookmyshow.models.Ticket;
import com.lld.bookmyshow.repositories.SeatRepository;
import com.lld.bookmyshow.repositories.ShowRepository;
import com.lld.bookmyshow.repositories.Show_Seat_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    ShowRepository showRepository;
    SeatRepository seatRepository;
    Show_Seat_Repository showSeatRepository;

    // This service will handle the business logic for ticket booking, cancellation, etc.
    // It will interact with the repository layer to perform CRUD operations on Ticket entities.
    @Autowired
    public TicketService(ShowRepository showRepository, SeatRepository seatRepository, Show_Seat_Repository showSeatRepository) {
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
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

       List<Show_Seat> showSeats =  showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());
        if(showSeats.isEmpty()){
            // No show-seats found for the provided show and seats, handle accordingly
            throw new SeatNotAvailableException("Seats are not available for the selected show.");
        }

        return null;
    }

    // Example method to cancel a ticket
    public void cancelTicket() {
        // Logic to cancel a ticket
    }

    // Other methods related to ticket management can be added here
}
