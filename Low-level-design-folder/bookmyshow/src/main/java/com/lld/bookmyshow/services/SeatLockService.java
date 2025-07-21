package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.SeatStatus;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.Show_Seat;
import com.lld.bookmyshow.repositories.Show_Seat_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeatLockService {

    Show_Seat_Repository showSeatRepository;

    @Autowired
    public SeatLockService(Show_Seat_Repository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Show_Seat> getAndLockSeats(List<Seat> seats, Show show) throws SeatNotAvailableException {

        List<Show_Seat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, show);

        if (showSeats.isEmpty()) {
            // No show-seats found for the provided show and seats, handle accordingly
            throw new SeatNotAvailableException("Seats are not available for the selected show.");
        }

        List<Show_Seat> savedShowSeats = new ArrayList<>();

        for (Show_Seat showSeat : showSeats) {
            boolean isAvailable = showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE);
            boolean isLockExpired = showSeat.getSeatStatus().equals(SeatStatus.LOCKED) &&
                    showSeat.getLockedAt() != null &&
                    showSeat.getLockedAt().getTime() < System.currentTimeMillis() - 15 * 60 * 1000;

            if (!(isAvailable || isLockExpired)) {
                throw new SeatNotAvailableException("One or more seats are not available for the selected show.");
            }
            // Lock the seat for booking
            showSeat.setSeatStatus(SeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        return savedShowSeats;
    }
}
