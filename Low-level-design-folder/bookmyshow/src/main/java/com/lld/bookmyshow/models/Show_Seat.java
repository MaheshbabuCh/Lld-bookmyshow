package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Show_Seat {
    private Show show;
    private Seat seat;
    private SeatStatus seatStatus;
}
