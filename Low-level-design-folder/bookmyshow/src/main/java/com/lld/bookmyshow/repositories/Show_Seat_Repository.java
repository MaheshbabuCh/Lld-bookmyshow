package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.Show_Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Show_Seat_Repository extends JpaRepository<Show_Seat, Long> {

    // Additional methods for show-seat specific queries can be defined here
    // For example, find all show-seats by show ID
    // List<Show_Seat> findAllByShowId(Long showId);

    // Find a specific Show_Seat by show ID and seat ID
    List<Show_Seat> findAllBySeatInAndShow(List<Seat> seats, Show show);
}
