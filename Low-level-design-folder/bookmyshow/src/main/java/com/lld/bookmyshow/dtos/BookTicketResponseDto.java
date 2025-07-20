package com.lld.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookTicketResponseDto {

    private List<String> seatNumbers;

    private int amount;

    private String auditoriumName;

    private String movieName;

    private Long ticketId;

    private String ticketStatus;

    private Date showStartTime;

}
