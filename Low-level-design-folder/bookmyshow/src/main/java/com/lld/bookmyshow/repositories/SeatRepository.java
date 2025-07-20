package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Additional methods for seat-specific queries can be defined here
    // For example, find available seats for a specific show
    //    List<Seat> findAvailableSeatsByShowId(Long showId);
    List<Seat> findAllByIdIn(List<Long> seatIds);
}
